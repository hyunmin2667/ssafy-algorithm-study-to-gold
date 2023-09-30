import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20055_컨베이어벨트위의로봇_조창래 {
	static int N; 
	static int K;
	static int[] belt; // 내구도를 기록한, 길이가 2N인 배열
	static boolean[] robots; // 로봇이 있는지 판단하는, 길이가 N인 배열
	static int step; // 정답
	
	static void cycle() {
		step = 1;
		while (true) {
			// 벨트와 로봇이 한 칸 회전한다. 이동한 후 칸의 내구도를 감소시킨다. 만약 robots[N-1]이 true라면 robots[N-1] = false로 둔다.
			
			// 벨트 한 칸 회전
			int memory = belt[2 * N - 1];
			for (int i = 2 * N - 1; i > 0; i--) {
				belt[i] = belt[i - 1];
			}
			belt[0] = memory;
			
			// 로봇 한 칸 회전, 칸의 내구도 감소
			robots[N-1] = robots[N - 2];
			if (robots[N-1]) {
				robots[N-1] = false;
			}
			
			for (int i = N - 2; i > 0; i--) {
				robots[i] = robots[i -1];
				if (robots[i]) {
				}
			}
			robots[0] = false;
			
			// idx가 N-1부터 0까지, 로봇이 이동할 수 있으면 이동. 만약 robots[N-1]이 true라면 robots[N-1] = false로 둔다
			if (belt[N-1] > 0 && robots[N-2]) {
				robots[N - 2] = false; // N - 2자리 로봇이 N -1자리로 이동. N - 1자리는 내리는 위치이므로 로봇은 내림.
				belt[N-1]--; // 내구도 감소
			}
			
			for (int i = N - 2; i > 0; i--) {
				if (!robots[i] && belt[i] > 0 && robots[i-1]) {
					robots[i] = robots[i -1]; // i - 1자리 로봇이 i자리로 이동.
					robots[i -1] = false; // i - 1자리에 로봇이 없음
					belt[i]--; // 내구도 감소
				}
			}
			
			
			// belt[0] > 0이라면 robots[0] = true
			if (belt[0] > 0) {
				robots[0] = true; // 0번째 자리에 로봇을 올린다
				belt[0]--; // 내구도 감소
			}
			

			// idx가 0부터 2*N -1까지: belt[idx] == 0인 것의 개수를 센다. 개수가 K개 이상이면 return;
			
			int num = 0;
			for (int i = 0; i < 2 * N; i++) {
				if (belt[i] == 0) num++;
			}
			if (num >= K) return;
			
			step++;
	
		}
		
			}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new int[2 * N];
		robots = new boolean[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		cycle();
		
		System.out.println(step);
		
	}

}
