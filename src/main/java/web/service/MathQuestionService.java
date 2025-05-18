package web.service;

public class MathQuestionService {

	/**
	 * Calculate Q1 result.
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static double q1Addition(String number1, String number2) {
		if(number1.isEmpty() && number2.isEmpty()) 
			return 0;
		double result = Double.valueOf(number1) + Double.valueOf(number2);
		return result;
	}
	
	/**
	 * Calculate Q2 result.
	 * @param number1
	 * @param number2
	 * @return
	 */
	public static double q2Subtraction(String number1, String number2) {
		if(number1.isEmpty() && number2.isEmpty()) 
			return 0;
		double result = Double.valueOf(number1) - Double.valueOf(number2);
		return result;
	}
}
