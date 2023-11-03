import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109_빵집 {
	static int R, C, ans;
	static char[][] map;
	static int[] dy = { -1, 0, 1 };
	public static void main(String[] args) throws Exception {
		// 우, 우상, 우하 => 겹칠 수 X, 접할 수 X
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < R; i++) {
			if (dfs(i, 0)) ans++;
		}
		
		System.out.println(ans);
	}
	
	static boolean dfs(int y, int x) {
		int nx = x + 1;
		if (nx == C-1) return true;
		
		for (int d = 0; d < 3; d++) {
			int ny = y + dy[d];
			
			if (ny < 0 || ny >= R || map[ny][nx] == 'x') continue;
			
			map[ny][nx] = 'x';
			
			if (dfs(ny, nx))
				return true;
		}
		return false;
	}
}
