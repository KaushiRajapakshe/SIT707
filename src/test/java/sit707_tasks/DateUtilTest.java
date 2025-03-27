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
//	need to check
	
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
}
