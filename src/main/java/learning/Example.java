package learning;

import java.util.Arrays;

import za.ac.sun.cs.coastal.Symbolic;

public class Example {

  	public static boolean target (char[] A) {
		int state = 0;
		for (int idx = 0; idx < A.length; idx++) {
			char current = A[idx];
			if (state == 0) {
				if ((current == (char)103)) {
					state = 1;
					continue;
				}
				if ((current >= (char)0 && current <= (char)102) || (current >= (char)104 && current <= (char)65535)) {
					state = 3;
					continue;
				}
			}
			if (state == 1) {
				if ((current == (char)122)) {
					state = 2;
					continue;
				}
				if ((current >= (char)0 && current <= (char)121) || (current >= (char)123 && current <= (char)65535)) {
					state = 3;
					continue;
				}
			}
			if (state == 2) {
				if ((current >= (char)0 && current <= (char)65535)) {
					state = 3;
					continue;
				}
			}
			if (state == 3) {
				if ((current >= (char)0 && current <= (char)65535)) {
					state = 3;
					continue;
				}
			}
		}
		if ((state == 2) || false) { 
			return true;
		} else {
			return false;
		}
	}

  	public static boolean candidate (char[] A) {
		int state = 0;
		for (int idx = 0; idx < A.length; idx++) {
			char current = A[idx];
			if (state == 0) {
				if ((current == (char)103)) {
					state = 1;
					continue;
				}
				if ((current >= (char)0 && current <= (char)102) || (current >= (char)104 && current <= (char)65535)) {
					state = 2;
					continue;
				}
			}
			if (state == 1) {
				if ((current >= (char)0 && current <= (char)65535)) {
					state = 2;
					continue;
				}
			}
			if (state == 2) {
				if ((current >= (char)0 && current <= (char)65535)) {
					state = 2;
					continue;
				}
			}
		}
		if ((state == 1) || false) { 
			return true;
		} else {
			return false;
		}
	}

	public static boolean check(char[] input) {
		boolean candidateResult = candidate(input);
		boolean targetResult = target(input);
		if (candidateResult != targetResult) {
			Symbolic.mark("<<Counter Example: " + Arrays.toString(input) + ">>");
			return false;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
    char[] inputValues = new char[] {  '.' , '.'  };

		boolean result = check(inputValues);
	}
}
