import re
import xml.etree.ElementTree as ET
from pathlib import Path

ROOT = Path(__file__).resolve().parent
XML_PATH = ROOT / 'target' / 'surefire-reports' / 'TEST-TriangleTest.xml'
TEST_SRC = ROOT / 'src' / 'test' / 'java' / 'TriangleTest.java'
OUTPUT_MD = ROOT / 'test-report-table.md'

EXPECTED_MAP = {
    'Invalid Input': 'Invalid Input',
    'Not a Triangle': 'Not a Triangle',
    'Equilateral': 'Equilateral',
    'Isosceles': 'Isosceles',
    'Scalene': 'Scalene',
}


def parse_xml_results(xml_path):
    tree = ET.parse(xml_path)
    root = tree.getroot()
    results = {}
    for testcase in root.findall('testcase'):
        name = testcase.attrib.get('name')
        failure = testcase.find('failure')
        error = testcase.find('error')
        skipped = testcase.find('skipped')
        if failure is not None or error is not None:
            results[name] = 'Failed'
        elif skipped is not None:
            results[name] = 'Skipped'
        else:
            results[name] = 'Passed'
    return results


def parse_tests(src_path):
    text = src_path.read_text(encoding='utf-8')
    # Match method names and expected strings, plus input constants.
    pattern = re.compile(
        r'@Test\s+public void (test[\w_]+)\s*\(\) \{\s*assertEquals\("([^"]+)", Triangle\.classifyTriangle\(([^)]+)\)\);',
        re.MULTILINE)
    entries = []
    for match in pattern.finditer(text):
        method_name = match.group(1)
        expected = match.group(2)
        args = match.group(3).strip()
        inputs = args.replace(' ', '')
        if inputs.endswith(','):  # handle stray comma
            inputs = inputs[:-1]
        entries.append((method_name, inputs, expected))
    return entries


def pretty_inputs(method_name, inputs):
    # Convert method suffix names like 1_2_3 or NegativeA into a readable input sequence
    if '_' in method_name:
        parts = method_name.split('_')
        if len(parts) >= 4 and all(p.lstrip('-').isdigit() for p in parts[-3:]):
            return ' '.join(parts[-3:])
    return inputs


def build_markdown(entries, results):
    lines = []
    lines.append('# Test Report Table')
    lines.append('')
    lines.append('| TC_id | Inputs | EO | Result |')
    lines.append('| --- | --- | --- | --- |')
    for idx, (method_name, inputs, expected) in enumerate(entries, start=1):
        result = results.get(method_name, 'Unknown')
        lines.append(f'| tc{idx} | {inputs} | {expected} | {result} |')
    return '\n'.join(lines)


def main():
    if not XML_PATH.exists():
        raise FileNotFoundError(f'Missing XML report: {XML_PATH}')
    if not TEST_SRC.exists():
        raise FileNotFoundError(f'Missing test source: {TEST_SRC}')

    results = parse_xml_results(XML_PATH)
    tests = parse_tests(TEST_SRC)
    md = build_markdown(tests, results)
    OUTPUT_MD.write_text(md, encoding='utf-8')
    print(f'Generated {OUTPUT_MD}')


if __name__ == '__main__':
    main()
