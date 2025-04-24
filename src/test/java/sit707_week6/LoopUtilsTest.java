package sit707_week6;

import org.junit.Assert;
import org.junit.Test;

public class LoopUtilsTest {

	@Test
	public void testBasicLoop() {
		int result = LoopUtils.basicLoop(7);
		Assert.assertEquals(28, result);
	}
	
	@Test
	public void testBasicLoopZero() {
		int result = LoopUtils.basicLoop(0);
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void testBasicLoopNegative() {
		int result = LoopUtils.basicLoop(-7);
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void testConditionalLoop() {
		int result = LoopUtils.conditionalLoop(5);
		Assert.assertEquals(8, result);
	}
		
	@Test
	public void testConditionalLoopZero() {
		int result = LoopUtils.conditionalLoop(0);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void testConditionalLoopNegative() {
		int result = LoopUtils.conditionalLoop(-11);
		Assert.assertEquals(1, result);
	}
		
}