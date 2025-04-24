package sit707_week6;

import org.junit.Assert;
import org.junit.Test;

public class WeatherAndMathUtilsTest {
	
	@Test
	public void testStudentIdentity() {
		String studentId = "s223681886";
		Assert.assertNotNull("Student ID is not null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Kaushalya Rajapaksha";
		Assert.assertNotNull("Student name is not null", studentName);
	}
	
	@Test
	public void testFalseNumberIsEven() {
		Assert.assertTrue(WeatherAndMathUtils.isEven(8));
	}
	
    @Test
    public void testCancelWeatherAdvice() {
    	Assert.assertEquals("CANCEL", WeatherAndMathUtils.weatherAdvice(70.1, 0.0));
    }
    
    @Test
    public void testWindSpeedAndPrecipitationCancel1() {
    	String actual = WeatherAndMathUtils.weatherAdvice(71, 7);
    	Assert.assertEquals("CANCEL", actual);
    }
    
    @Test
    public void testWindSpeedAndPrecipitationCancel2() {
    	String actual = WeatherAndMathUtils.weatherAdvice(46, 5);
    	Assert.assertEquals("CANCEL", actual);
    }
    
    @Test
    public void testWindSpeedAndPrecipitationAllClear() {
    	String actual = WeatherAndMathUtils.weatherAdvice(44, 3);
    	Assert.assertEquals("ALL CLEAR", actual);
    }
    
    @Test
    public void testWindSpeedAndPrecipitationWarn() {
    	String actual = WeatherAndMathUtils.weatherAdvice(46, 3);
    	Assert.assertEquals("WARN", actual);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentExceptionWhenBothNegative() {
    	WeatherAndMathUtils.weatherAdvice(-20.0,-4.0);
    }
    
    @Test
    public void testNoIllegalArgumentExceptionWhenAllPositiveValues() {
    	Assert.assertNotNull(WeatherAndMathUtils.weatherAdvice(20.0, 4.0));
    }
    
    // Even number
    @Test
    public void testIsEvenTrueConditon() {
    	boolean actual = WeatherAndMathUtils.isEven(6);
    	Assert.assertTrue(actual);
    }
    
    // Odd number
    @Test
    public void testIsEvenFalseConditon() {
    	boolean actual = WeatherAndMathUtils.isEven(5);
    	Assert.assertFalse(actual);
    }
    
    // Zero
    @Test
    public void testIsEvenZeroConditon() {
    	boolean actual = WeatherAndMathUtils.isEven(0);
    	Assert.assertTrue(actual);
    }
    
    // Negative even number
    @Test
    public void testIsEvenNegativeConditon() {
    	boolean actual = WeatherAndMathUtils.isEven(-12);
    	Assert.assertTrue(actual);
    }
    
    // Negative odd number
    @Test
    public void testIsEvenNegativeOddConditon() {
    	boolean actual = WeatherAndMathUtils.isEven(-11);
    	Assert.assertFalse(actual);
    }
    
	 // Testing isPrime
	 @Test
	 public void testIsPrimeTrueConditon() {
		 boolean actual = WeatherAndMathUtils.isPrime(5);
		 Assert.assertTrue(actual);
	 }
	 
	 // Non-prime number
	 @Test
	 public void testIsPrimeFalseConditon() {
		 boolean actual = WeatherAndMathUtils.isPrime(10);
		 Assert.assertFalse(actual);
	 }
	 
	 // Edge case - 1
	 @Test
	 public void testIsPrime1Conditon() {
		 boolean actual = WeatherAndMathUtils.isPrime(1);
		 Assert.assertTrue(actual);
	 }
	 
	 // Edge case - Negative number
	 @Test
	 public void testIsPrimeNegativeConditon() {
		 boolean actual = WeatherAndMathUtils.isPrime(-5);
		 Assert.assertTrue(actual);
	 }
	 
	 // Edge case - Large prime number
	 @Test
	 public void testIsPrimeLargeNumberTrueConditon() {
		 boolean actual = WeatherAndMathUtils.isPrime(1009);
		 Assert.assertTrue(actual);
	 }
	 
	 // Edge case - Large non-prime number
	 @Test
	 public void testIsPrimeLargeNumberFalseConditon() {
		 boolean actual = WeatherAndMathUtils.isPrime(1008);
		 Assert.assertFalse(actual);
	}
	
}
