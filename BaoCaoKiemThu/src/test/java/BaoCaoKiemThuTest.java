import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BaoCaoKiemThuTest {

    /**
     * Run the test cases based on the defined scenario list.
     *
     * @param tcId test case identifier
     * @param age the applicant's age
     * @param income the applicant's income in millions
     * @param creditScore the applicant's credit score
     * @param employment employment type: C or F
     * @param expectedOutcome expected decision outcome
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("testCases")
    void testEvaluate(String tcId, int age, double income, int creditScore, String employment, String expectedOutcome) {
        String actual = BaoCaoKiemThu.evaluate(age, income, creditScore, employment);
        assertEquals(expectedOutcome, actual, tcId + " failed");
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
            Arguments.of("TC_01", 15, 5.0, 300, "C", "Invalid Input"),
            Arguments.of("TC_02", 67, 20.0, 789, "C", "Invalid Input"),
            Arguments.of("TC_03", 20, 3.8, 300, "C", "Invalid Input"),
            Arguments.of("TC_04", 48, 502.8, 842, "C", "Invalid Input"),
            Arguments.of("TC_05", 32, 100.0, 182, "C", "Invalid Input"),
            Arguments.of("TC_06", 42, 483.4, 866, "C", "Invalid Input"),
            Arguments.of("TC_08", 22, 28.2, 328, "F", "REJECT"),
            Arguments.of("TC_09", 24, 13.5, 521, "F", "REJECT"),
            Arguments.of("TC_10", 24, 12.8, 781, "F", "REJECT"),
            Arguments.of("TC_11", 27, 11.5, 532, "C", "REJECT"),
            Arguments.of("TC_12", 23, 11.9, 620, "F", "REJECT"),
            Arguments.of("TC_13", 26, 13.9, 728, "C", "MANUAL REVIEW"),
            Arguments.of("TC_14", 30, 20.0, 672, "C", "APPROVE"),
            Arguments.of("TC_15", 38, 30.2, 723, "C", "APPROVE"),
            Arguments.of("TC_16", 42, 50.8, 593, "F", "MANUAL REVIEW"),
            Arguments.of("TC_17", 32, 60.7, 723, "F", "MANUAL REVIEW"),
            // Additional boundary and validation tests
            Arguments.of("TC_18", 18, 5.0, 300, "C", "REJECT"),
            Arguments.of("TC_19", 65, 500.0, 850, "C", "APPROVE"),
            Arguments.of("TC_20", 30, 20.0, 700, "X", "Invalid Input"),
            Arguments.of("TC_21", 30, 16.0, 500, "C", "REJECT"),
            Arguments.of("TC_22", 30, 16.0, 501, "C", "APPROVE"),
            Arguments.of("TC_23", 29, 15.0, 723, "F", "MANUAL REVIEW")
        );
    }
}
