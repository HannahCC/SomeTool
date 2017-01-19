package utils;

public class MathUtils {

	public static void arrayAvg(double[] vector1, double[] vector2,
			double[] vector3) {
		int n = vector1.length;
		if (n != vector2.length || n != vector3.length) {
			System.out.println("the length of arrays is not the same.");
			return;
		}
		for (int i = 0; i < n; i++) {
			vector3[i] += (vector1[i] + vector2[i]) / 2;
		}
	}

	public static void arrayAdd(double[] vector1, double[] vector2) {
		int n = vector1.length;
		if (n != vector2.length) {
			System.out.println("the length of arrays is not the same.");
			return;
		}
		for (int i = 0; i < n; i++) {
			vector2[i] += vector1[i];
		}
	}

	public static void arrayDivision(double[] vector, int division) {
		if (division == 0)
			return;
		int n = vector.length;
		for (int i = 0; i < n; i++) {
			vector[i] /= division;
		}
	}

}
