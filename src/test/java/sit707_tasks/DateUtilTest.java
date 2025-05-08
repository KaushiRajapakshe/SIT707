package sit707_tasks;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author Ahsan Habib
 */
public class DateUtilTest {
	
	@Test
	public void testStudentIdentity() {
		String studentId = "s223681886";
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		String studentName = "s223681886";
		Assert.assertNotNull("Student name is null", studentName);
	}

	@Test
	public void testMaxJanuary31ShouldIncrementToFebruary1() {
		// January max boundary area: max+1
		DateUtil date = new DateUtil(31, 1, 2024);
        System.out.println("january31ShouldIncrementToFebruary1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxJanuary31ShouldDecrementToJanuary30() {
		// January max boundary area: max-1
		DateUtil date = new DateUtil(31, 1, 2024);
        System.out.println("january31ShouldDecrementToJanuary30 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(1, date.getMonth());
	}
	
	@Test
	public void testNominalJanuary() {
		int rand_day_1_to_31 = 1 + new Random().nextInt(31);
        DateUtil date = new DateUtil(rand_day_1_to_31, 1, 2024);
        System.out.println("testJanuaryNominal > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(rand_day_1_to_31+1, date.getDay());
	}
	
	/*
	 * Complete below test cases.
	 */

	@Test
	public void testMinJanuary1ShouldIncrementToJanuary2() {
		// Code here
		// January max boundary area: max+1
		DateUtil date = new DateUtil(1, 1, 2024);
		System.out.println("january1ShouldIncrementToJanuary2 > " + date);
		date.increment();
		System.out.println(date);
		Assert.assertEquals(1, date.getMonth());
		Assert.assertEquals(2, date.getDay());
	}
	
	@Test
	public void testMinJanuary1ShouldDecrementToDecember31() {
		// Code here
		// January max boundary area: max-1
		DateUtil date = new DateUtil(1, 1, 2024);
		System.out.println("january1ShouldDecrementToDecember31 > " + date);
		date.decrement();
		System.out.println(date);
		Assert.assertEquals(31, date.getDay());
		Assert.assertEquals(12, date.getMonth());
	}

	/*
	 * Missing test cases
	 */
	
	@Test
	public void testMinJune1ShouldIncrementToJune2() {
		// Code here
		// June max boundary area: max-1
		DateUtil date = new DateUtil(1, 6, 1994);
		System.out.println("june1ShouldIncrementToJune2 > " + date);
		date.increment();
		System.out.println(date);
		Assert.assertEquals(2, date.getDay());
		Assert.assertEquals(6, date.getMonth());
	}
	
	@Test
	public void testMinJune2ShouldIncrementToJune3() {
		// Code here
		// June max boundary area: max-1
		DateUtil date = new DateUtil(2, 6, 1994);
		System.out.println("june2ShouldIncrementToJune3 > " + date);
		date.increment();
		System.out.println(date);
		Assert.assertEquals(3, date.getDay());
		Assert.assertEquals(6, date.getMonth());
	}
	
	@Test
	public void testMinJune15ShouldIncrementToJune16() {
		// Code here
		// June max boundary area: max-1
		DateUtil date = new DateUtil(15, 6, 1994);
		System.out.println("june15ShouldIncrementToJune16 > " + date);
		date.increment();
		System.out.println(date);
		Assert.assertEquals(16, date.getDay());
		Assert.assertEquals(6, date.getMonth());
	}
	
	/*
	 * Write tests for February with leap year condition of year 2024.
	 */
	
	@Test
	public void testMinFebruary28ShouldIncrementToFebruary29() {
		// Code here
		// February max boundary area: max+1
		DateUtil date = new DateUtil(28, 2, 2024);
		System.out.println("february28ShouldIncrementToFebruary29 > " + date);
		date.increment();
		System.out.println(date);
		Assert.assertEquals(2, date.getMonth());
		Assert.assertEquals(29, date.getDay());
	}
	
	@Test
	public void testMinFebruary29ShouldDecrementToFebruary28() {
		// Code here
		// February max boundary area: max-1
		DateUtil date = new DateUtil(29, 2, 2024);
		System.out.println("february29ShouldDecrementToFebruary28 > " + date);
		date.decrement();
		System.out.println(date);
		Assert.assertEquals(28, date.getDay());
		Assert.assertEquals(2, date.getMonth());
	}
	
	@Test
	public void testMinFebruary29ShouldIncrementToMarch1() {
		// Code here
		// February max boundary area: max+1
		DateUtil date = new DateUtil(29, 2, 2024);
		System.out.println("february29ShouldIncrementToMarch1 > " + date);
		date.increment();
		System.out.println(date);
		Assert.assertEquals(3, date.getMonth());
		Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMinMarch1ShouldDecrementToFebruary29() {
		// Code here
		// February max boundary area: max-1
		DateUtil date = new DateUtil(1, 3, 2024);
		System.out.println("march1ShouldDecrementToFebruary29 > " + date);
		date.decrement();
		System.out.println(date);
		Assert.assertEquals(29, date.getDay());
		Assert.assertEquals(2, date.getMonth());
	}
	/*
	 * Write tests for rest months of year 2024.
	 */
	
	@Test
	public void testMaxMarch31ShouldIncrementToApril1() {
		// March max boundary area: max+1
		DateUtil date = new DateUtil(31, 3, 2024);
        System.out.println("march31ShouldIncrementToApril1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(4, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxApril1ShouldDecrementToMarch31() {
		// March max boundary area: max-1
		DateUtil date = new DateUtil(1, 4, 2024);
        System.out.println("april1ShouldDecrementToMarch31 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(31, date.getDay());
        Assert.assertEquals(3, date.getMonth());
	}
	
	@Test
	public void testMaxApril30ShouldIncrementToMay1() {
		// April max boundary area: max+1
		DateUtil date = new DateUtil(30, 4, 2024);
        System.out.println("april30ShouldIncrementToMay1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(5, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxMay1ShouldDecrementToApril30() {
		// April max boundary area: max-1
		DateUtil date = new DateUtil(1, 5, 2024);
        System.out.println("may1ShouldDecrementToapril30 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(4, date.getMonth());
	}
	
	@Test
	public void testMaxMay31ShouldIncrementToJune1() {
		// May max boundary area: max+1
		DateUtil date = new DateUtil(31, 5, 2024);
        System.out.println("may31ShouldIncrementToJune1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(6, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxJune1ShouldDecrementToMay31() {
		// May max boundary area: max-1
		DateUtil date = new DateUtil(1, 6, 2024);
        System.out.println("june1ShouldDecrementToMay31 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(31, date.getDay());
        Assert.assertEquals(5, date.getMonth());
	}
	
	@Test
	public void testMaxJune30ShouldIncrementToJuly1() {
		// June max boundary area: max+1
		DateUtil date = new DateUtil(30, 6, 2024);
        System.out.println("june30ShouldIncrementToJuly1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(7, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxJuly1ShouldDecrementToJune30() {
		// June max boundary area: max-1
		DateUtil date = new DateUtil(1, 7, 2024);
        System.out.println("july1ShouldDecrementToJune30 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(6, date.getMonth());
	}
	
	@Test
	public void testMaxJuly31ShouldIncrementToAugust1() {
		// July max boundary area: max+1
		DateUtil date = new DateUtil(31, 7, 2024);
        System.out.println("july31ShouldIncrementToAugust1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(8, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxAugust1ShouldDecrementToJuly31() {
		// July max boundary area: max-1
		DateUtil date = new DateUtil(1, 8, 2024);
        System.out.println("august1ShouldDecrementToJuly31 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(31, date.getDay());
        Assert.assertEquals(7, date.getMonth());
	}
	
	@Test
	public void testMaxAugust31ShouldIncrementToSeptember1() {
		// August max boundary area: max+1
		DateUtil date = new DateUtil(31, 8, 2024);
        System.out.println("august31ShouldIncrementToSeptember1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(9, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxSeptember1ShouldDecrementToAugust31() {
		// August max boundary area: max-1
		DateUtil date = new DateUtil(1, 9, 2024);
        System.out.println("september1ShouldDecrementToAugust31 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(31, date.getDay());
        Assert.assertEquals(8, date.getMonth());
	}
	
	@Test
	public void testMaxSeptember30ShouldIncrementToOctober1() {
		// September max boundary area: max+1
		DateUtil date = new DateUtil(30, 9, 2024);
        System.out.println("september30ShouldIncrementToOctober1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(10, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxOctober1ShouldDecrementToSeptember30() {
		// September max boundary area: max-1
		DateUtil date = new DateUtil(1, 10, 2024);
        System.out.println("october1ShouldDecrementToSeptember30 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(9, date.getMonth());
	}
	
	@Test
	public void testMaxOctober31ShouldIncrementToNovember1() {
		// October max boundary area: max+1
		DateUtil date = new DateUtil(31, 10, 2024);
        System.out.println("october31ShouldIncrementToNovember1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(11, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxNovember1ShouldDecrementToOctober31() {
		// October max boundary area: max-1
		DateUtil date = new DateUtil(1, 11, 2024);
        System.out.println("november1ShouldDecrementToOctober31 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(31, date.getDay());
        Assert.assertEquals(10, date.getMonth());
	}
	
	@Test
	public void testMaxNovember30ShouldIncrementToDecember1() {
		// November max boundary area: max+1
		DateUtil date = new DateUtil(30, 11, 2024);
        System.out.println("november30ShouldIncrementToDecember1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(12, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}
	
	@Test
	public void testMaxDecember1ShouldDecrementToNovember30() {
		// November max boundary area: max-1
		DateUtil date = new DateUtil(1, 12, 2024);
        System.out.println("december1ShouldDecrementToNovember30 > " + date);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(11, date.getMonth());
	}
	
	@Test
	public void testMaxDecember31ShouldIncrementToJanuary1() {
		// December max boundary area: max+1
		DateUtil date = new DateUtil(31, 12, 2024);
        System.out.println("december31ShouldIncrementToJanuary1 > " + date);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(1, date.getDay());
	}

	// Test cases for day
	// D1: 1, Non-Leap Year
	@Test
	public void testD1NonLeapYearDecrement() {
		// April max boundary area: max-1
		DateUtil date = new DateUtil(1, 04, 2023);
		date.decrement();
		System.out.println("april01ShouldDecrementToMarch31");
		Assert.assertEquals(31, date.getDay());
		Assert.assertEquals(03, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}
	@Test
	public void testD1NonLeapYearIncrement() {
		// April max boundary area: max+1
		DateUtil date = new DateUtil(1, 04, 2023);
		date.increment();
		System.out.println("april01ShouldIncrementToApril2");
		Assert.assertEquals(02, date.getDay());
		Assert.assertEquals(04, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}
	// D2: 29, Leap Year
	@Test
	public void testD2LeapYearDecrement() {
		// February max boundary area: max-1
		DateUtil date = new DateUtil(29, 02, 2024);
		date.decrement();
		System.out.println("february29ShouldDecrementToFebruary28");
		Assert.assertEquals(28, date.getDay());
		Assert.assertEquals(02, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}
	@Test
	public void testD2LeapYearIncrement() {
		// February max boundary area: max+1
		DateUtil date = new DateUtil(29, 02, 2024);
		date.increment();
		System.out.println("february29ShouldIncrementToMarch1");
		Assert.assertEquals(01, date.getDay());
		Assert.assertEquals(03, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}
	// D3: 30, Non-Leap Year
	@Test
	public void testD3NonLeapYearDecrement() {
		// June max boundary area: max-1
		DateUtil date = new DateUtil(30, 06, 2023);
		date.decrement();
		System.out.println("june30ShouldDecrementToJune29");
		Assert.assertEquals(29, date.getDay());
		Assert.assertEquals(06, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}
	@Test
	public void testD3NonLeapYearIncrement() {
		// June max boundary area: max+1
		DateUtil date = new DateUtil(30, 06, 2023);
		date.increment();
		System.out.println("june30ShouldIncrementToJuly1");
		Assert.assertEquals(01, date.getDay());
		Assert.assertEquals(07, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}
	// D4: 31 Leap Year
	@Test
	public void testD4LeapYearDecrement() {
		// January max boundary area: max-1
		DateUtil date = new DateUtil(31, 01, 2024);
		date.decrement();
		System.out.println("january31ShouldDecrementToJanuary30");
		Assert.assertEquals(30, date.getDay());
		Assert.assertEquals(01, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}
	@Test
	public void testD4LeapYearIncrement() {
		// January max boundary area: max+1
		DateUtil date = new DateUtil(31, 01, 2024);
		date.increment();
		System.out.println("january31ShouldIncrementToFebruary1");
		Assert.assertEquals(01, date.getDay());
		Assert.assertEquals(02, date.getMonth());
		Assert.assertEquals(2024, date.getYear());
	}

	// Test cases for month
	// D1, Non-Leap Year
	@Test
	public void testM30D1NonLeapYearIncrement() {
		// April max boundary area: max+1
		DateUtil date = new DateUtil(30, 4, 2023);
		date.increment();
		System.out.println("april30ShouldIncrementToMay1");
		Assert.assertEquals(01, date.getDay());
		Assert.assertEquals(05, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}
	@Test
	public void testM30D1NonLeapYearDecrement() {
		// April max boundary area: max-1
		DateUtil date = new DateUtil(1, 5, 2023);
		date.decrement();
		System.out.println("may1ShouldDecrementToApril30");
		Assert.assertEquals(30, date.getDay());
		Assert.assertEquals(04, date.getMonth());
		Assert.assertEquals(2023, date.getYear());
	}
	// D4, Leap Year
	@Test
	public void testM31D4LeapYearIncrement() {
		// December max boundary area: max+1
		DateUtil date = new DateUtil(31, 12, 2024);
		date.increment();
		System.out.println("december31ShouldIncrementToJanuary1");
		Assert.assertEquals(1, date.getDay());
		Assert.assertEquals(01, date.getMonth());
		Assert.assertEquals(2025, date.getYear());
	}
	@Test
	public void testM31D4LeapYearDecrement() {
		// January max boundary area: max-1
		DateUtil date = new DateUtil(1, 1, 2019);
		date.decrement();
		System.out.println("january1ShouldIncrementToDecember31");
		Assert.assertEquals(31, date.getDay());
		Assert.assertEquals(12, date.getMonth());
		Assert.assertEquals(2018, date.getYear());
	}
	// D1, Non-Leap Year;
	@Test
	public void testFeb28NonLeapYearIncrement() {
		// February max boundary area: max+1
		DateUtil date = new DateUtil(28, 2, 2019);
		date.increment();
		System.out.println("february28ShouldIncrementToMArch1");
		Assert.assertEquals(01, date.getDay());
		Assert.assertEquals(03, date.getMonth());
		Assert.assertEquals(2019, date.getYear());
	}
	//	3.3 D
	@Test
    public void testDayEquivalenceOneToNine() {
        // Equivalence class 1-9
        DateUtil date = new DateUtil(3, 1, 2021);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(4, date.getDay());
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(2021, date.getYear());
    }

    @Test
    public void testDayEquivalenceTenToTwenty() {
        // Equivalence class 10-20
        DateUtil date = new DateUtil(13, 2, 2021);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(12, date.getDay());
        Assert.assertEquals(2, date.getMonth());
        Assert.assertEquals(2021, date.getYear());
    }

    @Test
    public void testDayEquivalenceTwentyOneToTowentyEight() {
        // Equivalence class 21-28
        DateUtil date = new DateUtil(23, 3, 2021);
        date.increment();
        System.out.println(date);
        Assert.assertEquals(24, date.getDay());
        Assert.assertEquals(3, date.getMonth());
        Assert.assertEquals(2021, date.getYear());
    }

    @Test
    public void testDayEquivalenceTwentyNineToThirtyOne() {
        // Equivalence class 29-31
        DateUtil date = new DateUtil(30, 4, 2021);
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(29, date.getDay());
        Assert.assertEquals(4, date.getMonth());
        Assert.assertEquals(2021, date.getYear());
    }

    @Test
    public void testEquivalenceMonthsWithThirtyDays() {
    	// June 25, 2021 (Month with 30 days)
        DateUtil date = new DateUtil(25, 6, 2021); 
        date.increment();
        System.out.println(date);
        Assert.assertEquals(26, date.getDay());
        Assert.assertEquals(6, date.getMonth());
        Assert.assertEquals(2021, date.getYear());
    }

    @Test
    public void testEquivalenceMonthsWithThirtyOneDays() {
    	// July 15, 2021 (Month with 31 days)
        DateUtil date = new DateUtil(15, 7, 2021); 
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(14, date.getDay());
        Assert.assertEquals(7, date.getMonth());
        Assert.assertEquals(2021, date.getYear());
    }

    @Test
    public void testMinimumValidYear() {
    	// January 1, 1700 (Minimum valid year)
        DateUtil date = new DateUtil(1, 1, 1700); 
        date.increment();
        System.out.println(date);
        Assert.assertEquals(2, date.getDay());
        Assert.assertEquals(1, date.getMonth());
        Assert.assertEquals(1700, date.getYear());
    }

    @Test
    public void testMaximumValidYear() {
    	// December 31, 2024 (Maximum valid year)
        DateUtil date = new DateUtil(31, 12, 2024); 
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(30, date.getDay());
        Assert.assertEquals(12, date.getMonth());
        Assert.assertEquals(2024, date.getYear());
    }

    @Test
    public void testMinimumValidDate() {
    	// January 1, 1700 (Minimum valid date)
        DateUtil date = new DateUtil(1, 1, 1700); 
        date.decrement();
        System.out.println(date);
        Assert.assertEquals(31, date.getDay());
        Assert.assertEquals(12, date.getMonth());
        Assert.assertEquals(1699, date.getYear());
    }
}
