public class Triangle {
    
    /**
     * Classifies a triangle based on its three sides
     * @param a first side length
     * @param b second side length
     * @param c third side length
     * @return triangle classification or error message
     */
    public static String classifyTriangle(int a, int b, int c) {
        // Check for valid input in range 1 - 100
        if (!isValidInput(a) || !isValidInput(b) || !isValidInput(c)) {
            return "Invalid Input";
        }
        if (!isValidTriangle(a, b, c)) {
            return "Not a Triangle";
        }
        return classifyValidTriangle(a, b, c);
    }
    
    /**
     * Validates if a single input is within the acceptable range
     * @param value the value to validate
     * @return true if value is between 1 and 100 inclusive
     */
    private static boolean isValidInput(int value) {
        return value >= 1 && value <= 100;
    }
    
    /**
     * Validates if three sides can form a valid triangle
     * @param a first side
     * @param b second side
     * @param c third side
     * @return true if triangle inequality is satisfied
     */
    private static boolean isValidTriangle(int a, int b, int c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }
    
    /**
     * Classifies a valid triangle into Equilateral, Isosceles, or Scalene
     * @param a first side
     * @param b second side
     * @param c third side
     * @return classification string
     */
    private static String classifyValidTriangle(int a, int b, int c) {
        if (a == b && b == c) {
            return "Equilateral";
        } else if (a == b || b == c || a == c) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }
}
