package dad.miclienteftp;

import java.util.Arrays;
import java.util.Random;

public class Pruebas {
	
	public static int [] randomIntArray(int size) {
		Random r = new Random();
		int [] arr = new int[size];
		for (int i = 0; i < size; i++)
			arr[i] = r.nextInt();
		return arr;
	}

	public static void main(String[] args) {
		
		int [] data = randomIntArray(100000);   

		medir(() -> System.out.println("resultado1: " + sum1(data)));
		medir(() -> System.out.println("resultado2: " + sum2(data)));

	}

	public static int sum1(int[] arr) {
		return Arrays.stream(arr).filter(v -> v > 0).sum();
	}
	
    public static int sum2(int[] arr){
        int result = 0;
        for (int i : arr) {
            if (i > 0) {
                result += i;
            }
        }
        return result;
    }
	
	public static void medir(Runnable run) {
		
		long antes = System.currentTimeMillis();
		run.run();
		long despues = System.currentTimeMillis();

		System.out.println("tiempo: " + (despues - antes) + " ms");
		
	}
	
}
