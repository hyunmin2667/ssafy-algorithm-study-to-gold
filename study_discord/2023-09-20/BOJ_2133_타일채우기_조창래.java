import java.io.BufferedReader;
import java.io.InputStreamReader;;

public class BOJ_2133_타일채우기_조창래 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
				
		long[] d = new long[N + 1];
		d[0] = 1;
		
		if (N >= 2) {
			d[2] = 3;	
		}
		
		for (int  i = 4; i <= N; i = i + 2) {
			long res = 0;
			res += 3 * d[i - 2]; // 처음 3 * 2 직사각형이 타일로 덮혀지는 경우
			
			for (int j = 4; j <= i; j = j + 2) {
				res += 2 * d[i - j]; // 처음 3 * j 직사각형이 타일로 덮혀지고, indecomposable인 경우
			}
			
			d[i] = res;
		}
		
		System.out.println(d[N]);
	}

}
