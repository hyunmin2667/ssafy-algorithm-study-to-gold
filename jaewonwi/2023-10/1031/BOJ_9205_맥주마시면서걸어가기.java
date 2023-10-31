import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9205_맥주마시면서걸어가기 {
	static int T, N, V;
	static int BIG = 101*32767*2;
	static int[][] input, matrix;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			V = N+2;	// 총 정점의 수
			
			input = new int[V][2];
			matrix = new int[V][V];
			for (int i = 0; i < V; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				input[i][0] = Integer.parseInt(st.nextToken());
				input[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < V; i++) {
				int y = input[i][0];
				int x = input[i][1];
				for (int j = 0; j < V; j++) {
					if (i == j) continue;
					
					int yy = input[j][0];
					int xx = input[j][1];
					
					int dist = Math.abs(xx-x) + Math.abs(yy-y);
					
					if (dist <= 50*20) {
						matrix[i][j] = dist;
					} else {
						matrix[i][j] = BIG;
					}
				}
			}
			
			// 플로이드 워샬
			for (int k = 0; k < V; k++) {
				for (int i = 0; i < V; i++) {
					if (k == i) continue;
					for (int j = 0; j < V; j++) {
						if (i == j || k == j) continue;
						matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
					}
				}
			}
			
			sb.append(matrix[0][V-1] < BIG ? "happy" : "sad").append("\n"); 
			
		}
		System.out.println(sb);
	}
}
