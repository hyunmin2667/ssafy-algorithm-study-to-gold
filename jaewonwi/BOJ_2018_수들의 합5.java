import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int s = 1;
		int e = 1;
		int sum = 1;
		int count = 1;
		
		while (e != N) {
			if (sum < N) {
				e++;
				sum += e;
			} else if (sum > N) {
				sum -= s;
				s++;
			} else if (sum == N) {
				count++;
				e++;
				sum+=e;
			}
		}
		System.out.println(count);

	}

}
