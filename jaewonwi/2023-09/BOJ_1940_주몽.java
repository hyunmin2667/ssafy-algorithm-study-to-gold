import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] Arr = new int[N];
		for(int i = 0; i < N; i++) {
			Arr[i] = sc.nextInt();
		}
		
		// 오름차순 정렬
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if (Arr[i] < Arr[j]) {
//					int temp = Arr[i];
//					Arr[i] = Arr[j];
//					Arr[j] = temp;
//				}
//			}
//		}
		Arrays.sort(Arr);
		
//		System.out.println(Arrays.toString(Arr));
		
		int start = 0;
		int end = 1;
		int sum = 0;
		int count = 0;

		while (end < N && Arr[end] < M) {
			while (start < end) {
				if (end < N) {
					sum = Arr[start] + Arr[end];
//					System.out.println(sum);
					if (sum == M) {
//						System.out.println(start + " " + end + " same");
						count++;
						end++;
					} else if (sum < M) {
//						System.out.println(start + " " + end + " small");
						end++;
					} else {
//						System.out.println(start + " " + end + " over");
						end++;
					}	
				} else {
					break;
				}
			}
			start++;
			end = start + 1;
		}
		System.out.println(count);

	}

}
