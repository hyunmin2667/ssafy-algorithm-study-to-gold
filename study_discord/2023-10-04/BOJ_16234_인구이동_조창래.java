import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16234_인구이동_조창래 {
	static int N, L, R;
	static int[] dx = {0, -1, 0, 1}; // 오, 위, 왼, 아
	static int[] dy = {1, 0, -1, 0}; // 오, 위, 왼, 아
	static int[][] population; // 나라의 인구수
	static List<Country> union; // 연합에 포함되어 있는 나라들
	static boolean[][] visited; 
	static int ans = -1;
	static boolean populationChange = true;
	
	static class Country {
		int x, y;

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public Country(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Country [x=" + x + ", y=" + y + "]";
		}
	}
	
	static void openBorder(int x, int y) { // (x, y) 좌표에 있는 나라의 네 가지 방향의 국경 열기
		union.add(new Country(x, y));
			
		int nx, ny;
		for (int d = 0; d < 4; d++) {
			nx = x + dx[d];
			ny = y + dy[d];
			
			if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny] && L <= Math.abs(population[nx][ny] - population[x][y]) && Math.abs(population[nx][ny] - population[x][y]) <= R) {
				visited[nx][ny] = true;
				openBorder(nx, ny);
			}
			
		}
	}
	
	static void changePopulation() { // union에 담겨져 있는 나라들의 인구수들을 변경한다
		int countryNum = union.size();
		if (countryNum > 1) {
			populationChange = true;
		}
		
		int sum = 0;
		
		for (Country country : union) {
			
			
			sum += population[country.x][country.y];
			
		}
		
		int populationNum = sum / countryNum;
		
		for (Country country: union) {
			population[country.x][country.y] = populationNum;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		population = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				population[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		while(populationChange) {
			populationChange = false;
			ans++;
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j]) continue;
					
					union = new ArrayList<>();
					visited[i][j] = true;
					openBorder(i, j);
					changePopulation();
				}
			}
		}
		
		System.out.println(ans);
		
	}

}
