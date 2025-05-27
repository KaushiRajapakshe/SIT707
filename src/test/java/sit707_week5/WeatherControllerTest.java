package sit707_week5;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherControllerTest {
	
	private static WeatherController wController;
	private static double[] todaysHourlyTemperature;
	private static final Logger logger = LoggerFactory.getLogger(WeatherControllerTest.class);
	
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
		logger.debug("Student ID is not null: {}", studentId);
		Assert.assertNotNull("Student ID is not null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "Kaushalya Rajapaksha";
		logger.debug("Student name is not null: {}", studentName);
		Assert.assertNotNull("Student name is not null", studentName);
	}

	@Test
	public void testTemperatureMin() {
		logger.info("+++ testTemperatureMin +++");
		
		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();
		double minTemperature = Double.MAX_VALUE;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = todaysHourlyTemperature[i]; 
			if (minTemperature > temperatureVal) {
				minTemperature = temperatureVal;
			}
		}
		// Should be equal to the min value that is cached in the controller.
		Assert.assertTrue(wController.getTemperatureMinFromCache() == minTemperature);
		logger.debug("Calculated min temperature: {}, Cached min: {}", 
                minTemperature, wController.getTemperatureMinFromCache());
	}
	
	@Test
	public void testTemperatureMax() {
		logger.info("+++ testTemperatureMax +++");
		
		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();
		double maxTemperature = Double.MIN_VALUE;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = todaysHourlyTemperature[i]; 
			if (maxTemperature < temperatureVal) {
				maxTemperature = temperatureVal;
			}
		}
		
		// Should be equal to the max value that is cached in the controller.
		Assert.assertTrue(wController.getTemperatureMaxFromCache() == maxTemperature);
		logger.debug("Calculated max temperature: {}, Cached max: {}", 
				maxTemperature, wController.getTemperatureMaxFromCache());
	}

	@Test
	public void testTemperatureAverage() {
		logger.info("+++ testTemperatureAverage +++");
		
		// Retrieve all the hours temperatures recorded as for today
		int nHours = wController.getTotalHours();
		double sumTemp = 0;
		for (int i=0; i<nHours; i++) {
			// Hour range: 1 to nHours
			double temperatureVal = todaysHourlyTemperature[i]; 
			sumTemp += temperatureVal;
		}
		double averageTemp = sumTemp / nHours;
		
		// Should be equal to the avg value that is cached in the controller.
		Assert.assertTrue(wController.getTemperatureAverageFromCache() == averageTemp);
		logger.debug("Calculated avg temperature: {}, Cached avg: {}", 
				averageTemp, wController.getTemperatureAverageFromCache());
	}
	
	@Test
	public void testTemperaturePersist() {
		logger.info("+++ testTemperaturePersist +++");

		Instant fixedInstant = Instant.parse("2025-04-08T12:00:00Z");
		Clock fixedClock = Clock.fixed(fixedInstant, ZoneId.systemDefault());
		
		wController = WeatherController.getInstance(fixedClock);
		
		String persistTime = wController.persistTemperature(10, 19.5);
		
		Assert.assertEquals(fixedInstant.toString(), persistTime);
		logger.info("Temperature persist time: {} , current time: {}", persistTime, fixedInstant.toString());
	}
}
