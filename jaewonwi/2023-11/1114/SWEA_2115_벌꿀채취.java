import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, C, ans;
	static int[][] map, honey;
	
	static int profit;	// 각 칸 별 꿀 수익
	static int[] target;	// 각 칸 별 가로로 꿀 배열
	
	static int[] tgt = new int[2];	// 2명이 선택한 인덱스
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int T = Integer.parseInt(br.readLine().trim());
    	for (int t = 1; t <= T; t++) {
    		StringTokenizer st = new StringTokenizer(br.readLine().trim());
    		N = Integer.parseInt(st.nextToken());
    		M = Integer.parseInt(st.nextToken());
    		C = Integer.parseInt(st.nextToken());
    		
    		map = new int[N][N];
    		for (int i = 0; i < N; i++) {
    			st = new StringTokenizer(br.readLine().trim());
    			for (int j = 0; j < N; j++) {
    				map[i][j] = Integer.parseInt(st.nextToken());
    			}
    		}
    
    		honey = new int[N][N];
    		for (int i = 0; i < N; i++) {
    			for (int j = 0; j <= N-M; j++) {
    				profit = 0;
    				honey[i][j] = calculate(i,j);
    			}
    		}
    		  
//    		for (int i = 0; i < N; i++) {
//    			for (int j = 0; j < N; j++) {
//    				System.out.print(honey[i][j]+" ");
//    			}
//    			System.out.println();
//    		}
//    		System.out.println("===================");
    		
    		ans = 0;
    		comb(0, 0);
    		
    		
    		sb.append("#").append(t).append(" ").append(ans).append("\n");
    	}
    	System.out.print(sb);
    }

    static void comb(int idx, int cnt) {
    	if (cnt == tgt.length) {
    		int sum = 0;
    		
    		if (tgt[0]/N == tgt[1]/N) return;
//    		System.out.println(Arrays.toString(tgt));
    		for (int i = 0; i < 2; i++) {
    			int tIdx = tgt[i];
    			int y = tIdx / N;
    			int x = tIdx % N;
    			
    			sum += honey[y][x];
//    			System.out.print("get "+y + " "+ x+ " | ");
    		}
//    		System.out.println();
    		ans = Math.max(ans, sum);
    		return;
    	}
    	
    	if (idx == N*N) {
    		return;
    	}
    	for (int i = idx; i < N*N; i++) {
    		tgt[cnt] = i;
    		comb(i+1, cnt+1);	
    	}
    }
    
    static int calculate(int y, int x) {
    	int sum = 0;
    	target = new int[M];
    	
    	int idx = 0;
    	while (idx < M) {
    		target[idx] = map[y][x+idx];
    		sum += target[idx];
    		idx++;
    	}
    	
    	if (sum <= C) {
    		for (int i = 0; i < M; i++) {
    			profit += target[i] * target[i];
    		}
    	} else if (sum > C) {
    		honeyComb(0, 0, 0);
    	}
    	
    	return profit;
    }
    
    static void honeyComb(int cnt, int sum, int cost) {
    	if (sum > C) return;

//    	System.out.println("honeyComb: "+cnt+" "+sum+" "+ cost);
    
    	if (cnt == M) {
    		profit = Math.max(cost, profit);
    		return;
    	}
	
		honeyComb(cnt+1, sum+target[cnt], cost+(target[cnt]*target[cnt]));
		honeyComb(cnt+1, sum, cost);
    }
}
