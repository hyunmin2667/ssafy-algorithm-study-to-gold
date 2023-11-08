import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13717_포켓몬GO {
	static int N, K, M, ans;
	static String most;
	static Pokemon[] pokemon;
	
	static class Pokemon{
		int need, cur, levelup;
		String name;
		
		private Pokemon(int need, int cur, int levelup, String name) {
			super();
			this.need = need;
			this.cur = cur;
			this.levelup = levelup;
			this.name = name;
		}
	}
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// P포켓몬을 진화시키기 위해 K개의 사탕이 필요. 진화 후 2개 돌려받음
		// 진화시킬수 있는 포켓몬의 총 마리 수
		// 가장 많이 진화시킬 수 있는 포켓몬이 무엇인가	// 도감번호가 작은 것
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		pokemon = new Pokemon[N];
		for (int i = 0; i < N; i++) {
			String name = br.readLine();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			pokemon[i] = new Pokemon(K, M, 0, name);
		}
		 
		int max = -1;
		for (int i = 0; i < N; i++) {
			int k = pokemon[i].need;
			int m = pokemon[i].cur;

			int cnt = 0;
			while (m - k >= 0) {
				cnt++;
				m = m - k + 2;
				
				if (cnt > max) {
					max = cnt;
					most = pokemon[i].name;
				}
			}
			ans += cnt;
		}
		
		System.out.println(ans);
		System.out.println(most);
		
	}

}
