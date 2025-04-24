package sit707_week6;

public class LoopUtils {

	/**
	 * Basic Loop for given number.
	 * 
	 * @param n
	 * @return
	 */
    public static int basicLoop(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
    
    /**
	 * Conditional Loop for given number.
	 * 
	 * @param n
	 * @return
	 */
    public static int conditionalLoop(int n) {
        int val = 1;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                val *= i;
            }
        }
        return val;
    }
}
