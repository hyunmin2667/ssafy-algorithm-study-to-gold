package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, L, K, x, y, ans;
//	static boolean[][] map;
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static ArrayList<Pos> stars = new ArrayList<>();
	
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//가로
		M = Integer.parseInt(st.nextToken());	//세로
		L = Integer.parseInt(st.nextToken());	//트램펄린 크기
		K = Integer.parseInt(st.nextToken());	//별똥별 좌표
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			stars.add(new Pos(y,x));
		}
		
		//트램펄린 위치 선정 -> 별똥별 최대로 튕겨내기
		//별똥별이 떨어지는 위치가 모서리인 경우가 최대 -> 두 점을 모서리로 잡아서 생각
		ans = Integer.MIN_VALUE;
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				ans = Math.max(ans, boundStar(stars.get(i).y, stars.get(j).x));
			}
		}
		
		System.out.println(K-ans);
	}
	

	static int boundStar(int i, int j) {
		int result = 0;
		for (Pos pos : stars) {
			if (i <= pos.y && pos.y <= i + L && j <= pos.x && pos.x <= j + L)
				result++;
		}
		return result;
	}
}