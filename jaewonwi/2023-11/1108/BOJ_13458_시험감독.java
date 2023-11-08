import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13458_시험감독 {
	static int N, A, B, C;
	static long ans;
	static int[] input;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			int students = input[i];
			
			students -= B;
			ans++;
			
//			System.out.println(students+" -> "+ans);
			
			if (students > 0) {
				int n = students / C ;
				if (students % C > 0)
					n++;
				
				ans += n;
//				System.out.println(students+"/"+C +" = " +n+" -> "+ans);
			}
			
//			System.out.println("-----------");
		}
		System.out.println(ans);
	}

}
