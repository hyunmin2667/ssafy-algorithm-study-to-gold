import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] arr = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				arr[i][j] = sc.nextInt();	
			}
		}

		// 구간합 배열
		int[][] sarr = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sarr[i][j] = sarr[i][j-1] + sarr[i-1][j] - sarr[i-1][j-1] + arr[i][j]; 	
			}
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 4; j++) {
				int x1 = sc.nextInt();
				int y1 = sc.nextInt();
				int x2 = sc.nextInt();
				int y2 = sc.nextInt();

				int result = sarr[x2][y2] - sarr[x1-1][y2] - sarr[x2][y1-1] + sarr[x1-1][y1-1];
				
				System.out.println(result);
			}
		}
		
		
	}
	
}
