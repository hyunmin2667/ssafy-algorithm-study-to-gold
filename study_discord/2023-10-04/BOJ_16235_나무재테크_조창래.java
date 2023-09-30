import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16235_나무재테크_조창래래 {
	static int N;
	static int M;
	static int K;
	static int[][] nutrients; // 양분
	static int[][] A; // 로봇이 추가하는 양분
	static List<Tree>[][] trees; // 각 좌표에 심어진 나무들의 목록
	static int[] dx = {0, -1, -1,  -1, 0, 1, 1, 1};
	static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
	
	static PriorityQueue<Tree> liveTrees;
	static PriorityQueue<Tree> liveTreesCopy;
	static List<Tree> deadTrees;
	
	static class Tree implements Comparable<Tree> {
		int x,y; // 나무의 좌표
		int age; // 나무의 나이
		
		@Override
		public String toString() {
			return "Tree [x=" + x + ", y=" + y + ", age=" + age + "]";
		}
		
		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			if (this.age < o.age) {
				return -1;
			} else if (this.age > o.age) {
				return 1;
			} return 0;
		}
		
	}
		
	static void cycle() {
		// 봄
		liveTreesCopy = new PriorityQueue<>();
		deadTrees = new ArrayList<>();
		
		while (!liveTrees.isEmpty()) {
			Tree tree = liveTrees.poll();
			
			if (tree.age <= nutrients[tree.x][tree.y]) {
				nutrients[tree.x][tree.y] -= tree.age;
				tree.age += 1;
				liveTreesCopy.offer(tree);
			} else {
				deadTrees.add(tree);
			}
		}
		
		liveTrees = liveTreesCopy;
		
		
		// 여름
		
		for (Tree tree: deadTrees) {
			nutrients[tree.x][tree.y] += tree.age / 2;
		}
		
		
		
		
		// 가을

		liveTreesCopy = new PriorityQueue<>();
		
		while (!liveTrees.isEmpty()) {
			Tree tree = liveTrees.poll();
			liveTreesCopy.offer(tree);
			if (tree.age % 5 != 0) continue;
			
			for (int d = 0; d < 8; d++) {
				int nx = tree.x + dx[d];
				int ny = tree.y + dy[d];
				
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					liveTreesCopy.offer(new Tree(nx, ny, 1));
				}
			}
			
		}
		
		liveTrees = liveTreesCopy;
		
		// 겨울
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nutrients[i][j] += A[i][j];
			}
		}
		
	}
	
	static int getAnswer() {
		int ans = 0;
	
		ans = liveTrees.size();
		
		return ans;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		nutrients = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nutrients[i][j] = 5;
			}
		}
		

		liveTrees = new PriorityQueue<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			
			liveTrees.offer(new Tree(x, y, age));

		}
		
		for (int i = 0; i < K; i++) {
			cycle();
		}
		
		System.out.println(getAnswer());
	}
	
	}


