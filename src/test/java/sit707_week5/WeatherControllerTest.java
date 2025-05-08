package sit707_week5;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {
	
	private static WeatherController wController;
	private static double[] todaysHourlyTemperature;
	
	@BeforeClass
	public static void startUp() {
		
		// Initialise WeatherController Object
		wController = WeatherController.getInstance();
		
		// Load all temperature in local variable to speed up process
		int nHours = wController.getTotalHours();
		todaysHourlyTemperature = new double[nHours];
		for (int i=0; i<nHours; i++) {
			todaysHourlyTemperature[i] = wController.getTemperatureForHour(i+1); 
		}

	}
	
	@AfterClass
	public static void cleanUp() {
		wController.close();
	}

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
	public void testTemperatureMin() {
		System.out.println("+++ testTemperatureMin +++");
		
		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();
		double minTemperature = 1000;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = todaysHourlyTemperature[i]; 
			if (minTemperature > temperatureVal) {
				minTemperature = temperatureVal;
			}
		}
		
		// Should be equal to the min value that is cached in the controller.
		Assert.assertTrue(wController.getTemperatureMinFromCache() == minTemperature);
			
	}
	
	@Test
	public void testTemperatureMax() {
		System.out.println("+++ testTemperatureMax +++");
		
		
		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();
		double maxTemperature = -1;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = todaysHourlyTemperature[i]; 
			if (maxTemperature < temperatureVal) {
				maxTemperature = temperatureVal;
			}
		}
		
		// Should be equal to the min value that is cached in the controller.
		Assert.assertTrue(wController.getTemperatureMaxFromCache() == maxTemperature);
		
	}

	@Test
	public void testTemperatureAverage() {
		System.out.println("+++ testTemperatureAverage +++");
		
		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();
		double sumTemp = 0;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = todaysHourlyTemperature[i]; 
			sumTemp += temperatureVal;
		}
		double averageTemp = sumTemp / nHours;
		
		// Should be equal to the min value that is cached in the controller.
		Assert.assertTrue(wController.getTemperatureAverageFromCache() == averageTemp);

	}
	
	@Test
	public void testTemperaturePersist() {
		/*
		 * Remove below comments ONLY for 5.3C task.
		 */
		System.out.println("+++ testTemperaturePersist +++");
		
		Instant fixedInstant = Instant.parse("2025-04-08T12:00:00Z");
		Clock fixedClock = Clock.fixed(fixedInstant, ZoneId.systemDefault());
		
		// Initialise controller
		WeatherController wController = WeatherController.getInstance(fixedClock);
		
		String persistTime = wController.persistTemperature(10, 19.5);
//		String now = new SimpleDateFormat("H:m:s").format(new Date());
		System.out.println("Persist time: " + persistTime + ", now: " + fixedInstant.toString());
		
		Assert.assertEquals(fixedInstant.toString(), persistTime);
		
		wController.close();
	}
}
