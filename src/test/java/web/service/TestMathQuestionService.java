package web.service;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestMathQuestionService {

	@Test
	public void testTrueAdd() {
		Assert.assertEquals(MathQuestionService.q1Addition("1", "2"), 3, 0);
	}
	@Test
	public void testNegativeAdd() {
		Assert.assertEquals(MathQuestionService.q1Addition("-1", "-2"), -3, 0);
	}
	@Test
	public void testNegativeAndPositiveAdd() {
		Assert.assertEquals(MathQuestionService.q1Addition("-1", "2"), 1, 0);
	}
	@Test
	public void testFloatAdd() {
		Assert.assertEquals(MathQuestionService.q1Addition("1.2", "2.3"), 3.5, 0);
	}
	@Test
	public void testNegativeFloatAdd() {
		Assert.assertEquals(MathQuestionService.q1Addition("-1.2", "-2.3"), -3.5, 0);
	}
	@Test
	public void testFloatingPositiveAndNegativeAdd() {
		Assert.assertEquals(MathQuestionService.q1Addition("1.2", "-2.3"), -1.1, 0.0000001);
	}
	@Test
	public void testBothEmptyAdd() {
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q1Addition(" ", " "));
	}
	@Test
	public void testOneEmptyAdd() {
		Assert.assertThrows(NumberFormatException.class, ()-> MathQuestionService.q1Addition("", "1"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q1Addition("1", ""));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q1Addition("", "-1"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q1Addition("-1", ""));
	}
	@Test
	public void testInvalidValueAdd(){
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q1Addition("2", "abc"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q1Addition("-2", "abc"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q1Addition("abc", "abc"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q1Addition("abc", "2"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q1Addition("abc", "-2"));
	}
	@Test
	public void testLargerValueAdd(){
		assertTrue(Double.isInfinite(MathQuestionService.q1Addition("1e308", "1e308")));
	}

	@Test
	public void testNegativeSubtraction() {
		Assert.assertEquals(MathQuestionService.q2Subtraction("-1", "-2"), 1, 0);
	}
	@Test
	public void testNegativeAndPositiveSubtraction() {
		Assert.assertEquals(MathQuestionService.q2Subtraction("-1", "2"), -3, 0);
	}
	@Test
	public void testFloatSubtraction() {
		Assert.assertEquals(MathQuestionService.q2Subtraction("1.2", "2.3"), -1.1, 0.0000001);
	}
	@Test
	public void testNegativeFloatSubtraction() {
		Assert.assertEquals(MathQuestionService.q2Subtraction("-1.2", "-2.3"), 1.1, 0.0000001);
	}
	@Test
	public void testFloatPositiveFloatNegativeSubtraction() {
		Assert.assertEquals(MathQuestionService.q2Subtraction("1.2", "-2.3"), 3.5, 0);
	}
	@Test
	public void testBothEmptySubtraction() {
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q2Subtraction(" ", " "));
	}
	@Test
	public void testOneEmptySubtraction() {
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q2Subtraction("", "1"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q2Subtraction("1", ""));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q2Subtraction("", "-1"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q2Subtraction("-1", ""));
	}
	@Test
	public void testInvalidValueSubtraction(){
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q2Subtraction("2", "abc"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q2Subtraction("-2", "abc"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q2Subtraction("abc", "abc"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q2Subtraction("abc", "2"));
		Assert.assertThrows(NumberFormatException.class, () -> MathQuestionService.q2Subtraction("abc", "-2"));
	}
	@Test
	public void testLargerValueSubtraction(){
		assertTrue(Double.isInfinite(MathQuestionService.q2Subtraction("1e308", "-1e308")));
	}
}
