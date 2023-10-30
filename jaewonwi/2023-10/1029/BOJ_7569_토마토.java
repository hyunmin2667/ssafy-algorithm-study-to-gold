import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토 {
	// BOJ_7569_토마토
	static int N, M, H, howmany, ans;	// 세로:N-열, 가로:M-행, 높이:H
	static int[][][] box;
	static Queue<Pos> tomato = new ArrayDeque<Pos>();	// 익은 토마토
	
	static class Pos {
		int z, y, x;

		public Pos(int z, int y, int x) {
			super();
			this.z = z;
			this.y = y;
			this.x = x;
		}
	}
	
	// bfs - 위, 아래, 좌, 우, 앞, 뒤
	static boolean[][][] visit;	// bfs check 
	static int[] dy = { 0, 0, 0, 0, -1, 1 };
	static int[] dx = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { 1, -1, 0, 0, 0, 0 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		box = new int[H][M][N];
		visit = new boolean[H][M][N];
		howmany = 0;
		for (int k = 0; k < H; k++) {
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < N; i++) {
					int n = Integer.parseInt(st.nextToken());
					if (n == 1)
						tomato.add( new Pos(k, j, i) );
					else if (n == 0)
						howmany++;
					
					box[k][j][i] = n;
				}
			}
		}
		
		
		ans = 0;
		
//		System.out.println(init.z+" "+init.y+" "+init.x);
//		System.out.println(tomato.size());
//		System.out.println(howmany);
		
		bfs();
		
		if (howmany > 0) System.out.println(-1);
		else System.out.println(ans);
		
	}
	
	static void bfs() {
		
		while (!tomato.isEmpty() && howmany > 0) {
			int curCycle = tomato.size();
			for (int i = 0; i < curCycle; i++) {
				Pos cur = tomato.poll();

				for (int d = 0; d < 6; d++) {
					int nz = cur.z + dz[d];
					int ny = cur.y + dy[d];
					int nx = cur.x + dx[d];
					
					if (nz >= 0 && ny >= 0 && nx >= 0 && nz < H && ny < M && nx < N && box[nz][ny][nx] == 0) {
						box[nz][ny][nx] = 1;
						tomato.add(new Pos(nz, ny, nx));
						howmany--;
					}
				}	
			}
			ans++;
		}
	}
}
