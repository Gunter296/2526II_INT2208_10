import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTest {
    
    // ===== Input Validation Tests =====
    
    @Test
    public void testInvalidInput_NegativeA() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(-1, 5, 5));
    }
    
    @Test
    public void testInvalidInput_NegativeB() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(5, -1, 5));
    }
    
    @Test
    public void testInvalidInput_NegativeC() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(5, 5, -1));
    }
    
    @Test
    public void testInvalidInput_ZeroA() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(0, 5, 5));
    }
    
    @Test
    public void testInvalidInput_ZeroB() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(5, 0, 5));
    }
    
    @Test
    public void testInvalidInput_ZeroC() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(5, 5, 0));
    }
    
    @Test
    public void testInvalidInput_ExceedsMaxA() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(101, 5, 5));
    }
    
    @Test
    public void testInvalidInput_ExceedsMaxB() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(5, 101, 5));
    }
    
    @Test
    public void testInvalidInput_ExceedsMaxC() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(5, 5, 101));
    }
    
    @Test
    public void testInvalidInput_AllNegative() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(-1, -2, -3));
    }
    
    @Test
    public void testInvalidInput_AllZero() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(0, 0, 0));
    }
    
    @Test
    public void testInvalidInput_AllExceedsMax() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(101, 102, 103));
    }
    
    // ===== Triangle Inequality Tests =====
    
    @Test
    public void testNotTriangle_SumOfTwoBetterThanThird_1() {
        assertEquals("Not a Triangle", Triangle.classifyTriangle(1, 2, 3));
    }
    
    @Test
    public void testNotTriangle_SumOfTwoBetterThanThird_2() {
        assertEquals("Not a Triangle", Triangle.classifyTriangle(1, 2, 5));
    }
    
    @Test
    public void testNotTriangle_ViolateSecondCondition() {
        assertEquals("Not a Triangle", Triangle.classifyTriangle(1, 5, 2));
    }
    
    @Test
    public void testNotTriangle_ViolateThirdCondition() {
        assertEquals("Not a Triangle", Triangle.classifyTriangle(5, 1, 2));
    }
    
    @Test
    public void testNotTriangle_AllConditionsViolated() {
        assertEquals("Not a Triangle", Triangle.classifyTriangle(1, 1, 100));
    }
    
    // ===== Equilateral Triangle Tests =====
    
    @Test
    public void testEquilateral_3_3_3() {
        assertEquals("Equilateral", Triangle.classifyTriangle(3, 3, 3));
    }
    
    @Test
    public void testEquilateral_5_5_5() {
        assertEquals("Equilateral", Triangle.classifyTriangle(5, 5, 5));
    }
    
    @Test
    public void testEquilateral_1_1_1() {
        assertEquals("Equilateral", Triangle.classifyTriangle(1, 1, 1));
    }
    
    @Test
    public void testEquilateral_100_100_100() {
        assertEquals("Equilateral", Triangle.classifyTriangle(100, 100, 100));
    }
    
    @Test
    public void testEquilateral_50_50_50() {
        assertEquals("Equilateral", Triangle.classifyTriangle(50, 50, 50));
    }
    
    // ===== Isosceles Triangle Tests =====
    
    @Test
    public void testIsosceles_2_2_3() {
        assertEquals("Isosceles", Triangle.classifyTriangle(2, 2, 3));
    }
    
    @Test
    public void testIsosceles_2_3_2() {
        assertEquals("Isosceles", Triangle.classifyTriangle(2, 3, 2));
    }
    
    @Test
    public void testIsosceles_3_2_2() {
        assertEquals("Isosceles", Triangle.classifyTriangle(3, 2, 2));
    }
    
    @Test
    public void testIsosceles_5_5_7() {
        assertEquals("Isosceles", Triangle.classifyTriangle(5, 5, 7));
    }
    
    @Test
    public void testIsosceles_5_7_5() {
        assertEquals("Isosceles", Triangle.classifyTriangle(5, 7, 5));
    }
    
    @Test
    public void testIsosceles_7_5_5() {
        assertEquals("Isosceles", Triangle.classifyTriangle(7, 5, 5));
    }
    
    @Test
    public void testIsosceles_10_10_15() {
        assertEquals("Isosceles", Triangle.classifyTriangle(10, 10, 15));
    }
    
    @Test
    public void testIsosceles_1_1_1_Boundary() {
        // This is Equilateral, not Isosceles - boundary case
        assertEquals("Equilateral", Triangle.classifyTriangle(1, 1, 1));
    }
    
    @Test
    public void testIsosceles_100_100_199() {
        assertEquals("Invalid Input", Triangle.classifyTriangle(100, 100, 199));
    }
    
    // ===== Scalene Triangle Tests =====
    
    @Test
    public void testScalene_3_4_5() {
        assertEquals("Scalene", Triangle.classifyTriangle(3, 4, 5));
    }
    
    @Test
    public void testScalene_5_4_3() {
        assertEquals("Scalene", Triangle.classifyTriangle(5, 4, 3));
    }
    
    @Test
    public void testScalene_6_8_10() {
        assertEquals("Scalene", Triangle.classifyTriangle(6, 8, 10));
    }
    
    @Test
    public void testScalene_5_6_7() {
        assertEquals("Scalene", Triangle.classifyTriangle(5, 6, 7));
    }
    
    @Test
    public void testScalene_10_20_25() {
        assertEquals("Scalene", Triangle.classifyTriangle(10, 20, 25));
    }
    
    @Test
    public void testScalene_15_20_30() {
        assertEquals("Scalene", Triangle.classifyTriangle(15, 20, 30));
    }
    
    @Test
    public void testScalene_1_2_2() {
        // Edge case: almost isosceles
        assertEquals("Isosceles", Triangle.classifyTriangle(1, 2, 2));
    }
    
    @Test
    public void testScalene_7_10_15() {
        assertEquals("Scalene", Triangle.classifyTriangle(7, 10, 15));
    }
    
    // ===== Edge Cases =====
    
    @Test
    public void testBoundary_MinValid_1_1_1() {
        assertEquals("Equilateral", Triangle.classifyTriangle(1, 1, 1));
    }
    
    @Test
    public void testBoundary_MaxValid_100_100_100() {
        assertEquals("Equilateral", Triangle.classifyTriangle(100, 100, 100));
    }
    
    @Test
    public void testBoundary_MinMaxMix_1_100_100() {
        assertEquals("Isosceles", Triangle.classifyTriangle(1, 100, 100));
    }
    
    @Test
    public void testBoundary_AlmostInvalid_1_1_2() {
        assertEquals("Not a Triangle", Triangle.classifyTriangle(1, 1, 2));
    }
    
    @Test
    public void testBoundary_JustValid_1_1_1_LowestValid() {
        assertEquals("Equilateral", Triangle.classifyTriangle(1, 1, 1));
    }
}
