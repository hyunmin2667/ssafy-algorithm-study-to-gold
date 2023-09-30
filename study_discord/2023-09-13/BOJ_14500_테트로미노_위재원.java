import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노_위재원 {
    static int N, M, ans;
    static int input[][];
    
    public static void main(String[] args) throws Exception {
        // 테트로미노
        // N*M 종이에 테트로미노 하나를 놓는데, 테트로미노가 놓인 칸에 쓰여진 숫자의 합이 최대가 되도록하자.
        // 테트로미노는 회전이나 대칭 가능
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        input = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 풀이
        ans = 0;
        
        for (int i = 1; i <= N; i++) {
        	for (int j = 1; j <= M; j++) {
        		check(i, j);
        	}
        }
        
        System.out.println(ans);

    }
    
    static void check(int y, int x) {
    	int[] sum= new int[5];
    	sum[0] = square(y, x);
    	sum[1] = Math.max(worm_hor(y, x), worm_ver(y, x));
    	sum[2] = vert(y, x);
    	sum[3] = hor(y, x);
    	sum[4] = bar(y, x);
    	
    	for (int s : sum) {
    		ans = Math.max(s, ans);
    	}
    }

	// 사각형 모양 테트로미노
    static int square(int y, int x) {
    	if ((y+1) <= N && (x+1) <= M)
    		return input[y][x] + input[y+1][x] + input[y][x+1] + input[y+1][x+1];
    	return 0;
    }
    
    // 지렁이 모양 테트로미노 (가로방향)
    static int worm_hor(int y, int x) {
    	if (y <= (N-1) && x <= (M-2)) {
	    	// --__ 모양 vs // __-- 모양
	    	int max = input[y][x] + input[y][x+1] + input[y+1][x+1] + input[y+1][x+2];
	    	max = Math.max(max, input[y+1][x] + input[y+1][x+1] + input[y][x+1] + input[y][x+2]);
	    	
	    	return max;
    	}
    	return 0;
    }
    
    // 지렁이 모양 테트로미노 (세로방향)
    static int worm_ver(int y, int x) {
    	if (y <= (N-2) && x <= (M-1)) {
	    	// ㄴㄱ 모양  
	    	int max = input[y][x] + input[y+1][x] + input[y+1][x+1] + input[y+2][x+1];
	    	// 대칭
	    	max = Math.max(max, input[y+1][x] + input[y+1][x+1] + input[y][x+1] + input[y+2][x]);
	    	return max;
    	}
    	return 0;
    }

    // 세로 3개 
    static int vert(int y, int x) {
    	int temp = 0;
    	// 세로 길이 남아있고, 좌우로 한 칸 이상 있는 경우
    	if (y <= N-2 && x >= 2 && x < M) {
        	int sum = input[y][x] + input[y+1][x] + input[y+2][x];
    		for (int i = 0; i < 3; i++) {
    			temp = Math.max(temp, input[y+i][x-1]);
    		}
    		for (int i = 0; i < 3; i++) {
    			temp = Math.max(temp, input[y+i][x+1]);
    		}
    		return sum + temp;
    	}
    	
    	// 세로 길이 남아있고, 왼쪽으로 한 칸 이상 있는 경우
    	else if (y <= N-2 && x >= 2) {
        	int sum = input[y][x] + input[y+1][x] + input[y+2][x];
    		for (int i = 0; i < 3; i++) {
    			temp = Math.max(temp, input[y+i][x-1]);
    		}
    		return sum + temp;
    	}
    	
    	// 세로 길이 남아있고, 오른쪽으로 한 칸 이상 있는 경우
    	else if (y <= N-2 && x < M) {
        	int sum = input[y][x] + input[y+1][x] + input[y+2][x];
    		for (int i = 0; i < 3; i++) {
    			temp = Math.max(temp, input[y+i][x+1]);
    		}
    		return sum + temp;
    	}
    	return 0;
    }
    
    // 가로 3개 
    static int hor(int y, int x) {
    	int temp = 0;
    	// 가로 길이 남아있고, 위아래로 한 칸 이상 있는 경우
    	if (x <= M-2 && y >= 2 && y < N) {
        	int sum = input[y][x] + input[y][x+1] + input[y][x+2];
    		for (int i = 0; i < 3; i++) {
    			temp = Math.max(temp, input[y-1][x+i]);
    		}
    		for (int i = 0; i < 3; i++) {
    			temp = Math.max(temp, input[y+1][x+i]);
    		}
    		return sum + temp;
    	}
    	
    	// 가로 길이 남아있고, 위쪽으로 한 칸 이상 있는 경우
    	else if (x <= M-2 && y >= 2) {
        	int sum = input[y][x] + input[y][x+1] + input[y][x+2];
    		for (int i = 0; i < 3; i++) {
    			temp = Math.max(temp, input[y-1][x+i]);
    		}
    		return sum + temp;
    	}
    	
    	// 가로 길이 남아있고, 아래쪽으로 한 칸 이상 있는 경우
    	else if (x <= M-2 && y < N) {
        	int sum = input[y][x] + input[y][x+1] + input[y][x+2];
    		for (int i = 0; i < 3; i++) {
    			temp = Math.max(temp, input[y+1][x+i]);
    		}
    		return sum + temp;
    	}
    	return 0;
    }

    // 막대
    static int bar(int y, int x) {
    	int max = 0;
    	// 가로 세로
    	if (y <= N - 3 && x <= M - 3) {
    		max = input[y][x] + input[y][x+1] + input[y][x+2] + input[y][x+3];
	    	max = Math.max(max, input[y][x] + input[y+1][x] + input[y+2][x] + input[y+3][x]);
		}
    	// 세로
    	else if (y <= N - 3) {
    		max = input[y][x] + input[y+1][x] + input[y+2][x] + input[y+3][x];
    	}
    	// 가로
    	else if (x <= M - 3) {
    		max = input[y][x] + input[y][x+1] + input[y][x+2] + input[y][x+3];
    	}
    	return max;
    }
}