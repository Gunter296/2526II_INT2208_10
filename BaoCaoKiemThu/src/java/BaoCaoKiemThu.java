/**
 * A simple class that evaluates credit input and returns a decision result.
 */
public class BaoCaoKiemThu {

    /**
     * Evaluate the input and return the decision outcome.
     *
     * @param age applicant's age
     * @param income applicant's income in millions
     * @param creditScore applicant's credit score
     * @param employment employment type: C = contract, F = freelance
     * @return "Invalid Input", "REJECT", "MANUAL REVIEW", or "APPROVE"
     */
    public static String evaluate(int age, double income, int creditScore, String employment) {
        if (!isEmploymentValid(employment) || !isAgeValid(age) || !isIncomeValid(income) || !isCreditScoreValid(creditScore)) {
            return "Invalid Input";
        }

        if (creditScore >= 300 && creditScore <= 500) {
            return "REJECT";
        }

        if (creditScore > 500 && creditScore <= 700) {
            if (income < 15.0) {
                return "REJECT";
            }
            return "F".equalsIgnoreCase(employment) ? "MANUAL REVIEW" : "APPROVE";
        }

        if (creditScore > 700 && creditScore <= 850) {
            if (income < 15.0) {
                return "F".equalsIgnoreCase(employment) ? "REJECT" : "MANUAL REVIEW";
            }
            return "C".equalsIgnoreCase(employment) ? "APPROVE" : "MANUAL REVIEW";
        }

        return "Invalid Input";
    }

    /**
     * Validate age is within the allowed range.
     *
     * @param age applicant age
     * @return true if age is between 18 and 65 inclusive
     */
    private static boolean isAgeValid(int age) {
        return age >= 18 && age <= 65;
    }

    /**
     * Check whether the income is within valid bounds.
     *
     * @param income income in millions
     * @return true if income between 5.0 and 500.0 inclusive
     */
    private static boolean isIncomeValid(double income) {
        return income >= 5.0 && income <= 500.0;
    }

    /**
     * Validate credit score range.
     *
     * @param score credit score value
     * @return true if score between 300 and 850 inclusive
     */
    private static boolean isCreditScoreValid(int score) {
        return score >= 300 && score <= 850;
    }

    /**
     * Validate employment code.
     *
     * @param employment employment code string
     * @return true if employment equals "C" or "F" (case-insensitive)
     */
    private static boolean isEmploymentValid(String employment) {
        return "C".equalsIgnoreCase(employment) || "F".equalsIgnoreCase(employment);
    }
}
