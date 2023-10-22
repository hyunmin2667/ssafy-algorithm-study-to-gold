import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스 {
	static int N, M, D, ans;
	static int[] archer = new int[3];
	static List<Enemy> list = new ArrayList<>();	// 적 입력
	public static void main(String[] args) throws Exception {
		// 한 턴마다 모든 궁수가 거리가 D 이하인 적 중에 가장 가까운 적 한명씩 공격(왼쪽부터)
		// 공격이 끝나면 적이 아래로 한 칸 이동. N+1로 오면 게임에서 사라짐. 모든 적이 사라지면 게임 종료
		// 궁수의 공격으로 제거할 수 있는 적의 최대 수를 구하자!

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int n = Integer.parseInt(st.nextToken())	;
				if (n == 1) list.add(new Enemy(i, j, 0, false));
			}
		}
		
		// 궁수 뽑기
		comb(0, 0);
		System.out.println(ans);
		
	}
	
	static void shoot() {
		List<Enemy> enemies = new ArrayList<>();	// 입력 받은 적들을 enemies 리스트로 복사해서 조합마다 계산에 사용
		for (int i = 0; i < list.size(); i++) {
			Enemy e = list.get(i);
			enemies.add(new Enemy(e.y, e.x, e.d, e.dead));
		}
		
		PriorityQueue<Enemy> pq = new PriorityQueue<>((e1, e2) -> e1.d == e2.d ? e1.x - e2.x : e1.d - e2.d);	// lamda 식으로 Priority Queue 이중 조건 지정해주기!! 
		
		int kill = 0;
		while (true) {
			for (int x : archer) {	// 궁수마다
				pq.clear();	// !!! 궁수마다 pq 초기화
				int ax = x;
				
				// 모든 적들에 대해 거리 계산
				for (Enemy e : enemies) {	
//					if (e.dead) continue;	// 이미 다른 궁수가 쏜 적이라면 패스 => !!! 여러 궁수가 한 명의 적을 쏠 수도 있다!
					
					e.d = Math.abs(ax-e.x) + Math.abs(N-e.y);
					if (e.d <= D) {	// 사정거리 내의 적이라면
						pq.offer(e);	// 죽일 후보로 추가해
					}
				}
				
				// 모든 적을 다 살펴보고 후보에 추가했다면... 한명을 쏘자
				if (!pq.isEmpty())	// !!! 쏠 수 있는 적이 없을 경우는 패스
					pq.poll().dead = true;
				
			}
			
			// 적들이 전진!
			// 맨 뒤부터 살펴봐야 remove 시 인덱스가 꼬이지 않는다.
			for (int i = enemies.size()-1; i >= 0; i--) {
				Enemy e = enemies.get(i);
				
				if (e.dead) {
					enemies.remove(e);		// 화살 맞은 애면 적 후보에서 제거
					kill++;	// 죽인 적 숫자 카운트
				} else if (e.y == N - 1) {	// 성 바로 앞까지 와있는 적이라면 적 후보에서 제거
					enemies.remove(e);
				} else {	// 한 칸 씩 아래로 전진
					e.y++;
				}
			}
			
			if (enemies.size() == 0) break;	// 더 이상 적이 없다? 게임 종료
		}
		
		ans = Math.max(ans, kill);
		
		return;
	}
	
	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == archer.length) {
//			System.out.println(Arrays.toString(archer));
			shoot();
			return;
		}
		
		for (int i = srcIdx; i <= M; i++) {	//!!! 열 기준으로 한명씩 선택하는 거니까 잘 체크하기.
			archer[tgtIdx] = i;
			comb(i+1, tgtIdx+1);
		}
	}

	static class Enemy {
		int y, x, d;
		boolean dead;
		
		public Enemy(int y, int x, int d, boolean dead) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.dead = dead;
		}
	}
}
