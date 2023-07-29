import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextLong();
		}
		
		Arrays.sort(arr);
		
		int count = 0;

		for (int k = 0; k < N; k++) {
			long find = arr[k];
			int s = 0;
			int e = N - 1;
			
			while (s < e) {
				if (arr[s] + arr[e] == find) {
					if (s != k && e !=k) {
						count++;
						break;
					} else if (s == k) {
						s++;
					} else if (e == k) {
						e--;
					}
				} else if (arr[s] + arr[e] < find) {
					s++;
				} else {
					e--;
				}
			}
		}
		
		
		System.out.println(count);
		
		
	}
}