package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
/* 데이터셋
 * K - 2^k 배열
 * Arr
 * H - 구멍위치
2
R D D R
3
 */

public class BOJ_20187_paper {

	static int[][] ans;
	static int size,K,H;
	static Queue<Character> queue = new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		size = (int) Math.pow(2,K);
		ans = new int[size][size];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<(2*K); i++) {
			queue.offer(st.nextToken().charAt(0));
		}
		H = Integer.parseInt(br.readLine());
		int[] cur = {0,0};//x,y 
		fold(cur, size,size);
		
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<size; j++) {
				sb.append(ans[i][j]).append(" ");
			}
			sb.append("\n");
		}
			//System.out.println(Arrays.toString(ans[i]));
		System.out.println(sb);
	}
	
	static void fold(int[] cur, int w, int h) {
		if(queue.isEmpty()) {
			ans[cur[0]][cur[1]] = H;
			return;
		}
		
			char c =queue.poll(); 
			switch (c) {
			case 'L':
				w/=2;				
				fold(cur,w,h);
				
				for(int i = w; i<w*2; i++) {//대칭이 되어야하니까 curx-1~curx-width
					for(int j = cur[1]; j<cur[1]+h; j++) {//y의 위치는 서로 동일하고 이동만 해야함
						int hh = ans[j][i-1];//cur[0]
						if(hh %2==0)//0 2
							ans[j][i] = hh+1;
						else// 1 3
							ans[j][i] = hh-1;
					}
				}			
				w*=2;
				
				break;
			case 'R'://r 방향으로 접을 때 -1
				w/=2;//width 반으로
				cur[0]+=w;//현재 위치 이동
				fold(cur,w,h);//재귀
				for(int i = cur[0]-1; i>cur[0]-w-1; i--) {//대칭이 되어야하니까 curx-1~curx-width
					for(int j = cur[1]; j<cur[1]+h; j++) {//y의 위치는 서로 동일하고 이동만 해야함
						int hh = ans[j][i+1];//cur[0]
						if(hh %2==0)//0 2
							ans[j][i] = hh+1;
						else// 1 3
							ans[j][i] = hh-1;
					}
				}			
				cur[0]-=w;
				w*=2;
				break;
			case 'D'://d 방향으로 접을 때 -2
				h/=2;
				cur[1]+=h;
				fold(cur,w,h);
				for(int i = cur[0]; i<w+cur[0]; i++) {
					for(int j = cur[1]-1; j>cur[1]-h-1; j--) {
						int hh = ans[j+1][i];
						if(hh <2)
							ans[j][i] = hh+2;
						else
							ans[j][i] = hh-2;
					}
				}	
				cur[1]-=h;
				h*=2;
				break;
			case 'U':
				h/=2;
				fold(cur,w,h);
				for(int i = cur[0]; i<w+cur[0]; i++) {
					for(int j = h; j<h*2; j++) {
						int hh = ans[j-1][i];
						if(hh <2)
							ans[j][i] = hh+2;
						else
							ans[j][i] = hh-2;
					}
				}	
				h*=2;
				break;

			default:
				break;
			
		}
		
	}

}
