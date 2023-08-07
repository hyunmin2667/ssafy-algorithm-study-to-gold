package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1377_BubbleSort {//변하지 않는 위치를 찾는거같은데
//버블 정렬에서 변하지 않은 idx 찾는 문제가 맞다.
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		mData[] A = new mData[N];
		
		for(int i = 0; i<N; i++) {
			A[i] = new mData(Integer.parseInt(br.readLine()), i);
		}
		Arrays.sort(A);
		int max = 0;

		for(int i = 0; i<N; i++) {
			if(max < A[i].index-i) {
				max = A[i].index -i;
			}
		}
		System.out.println(max+1);
	}
	

}
	class mData implements Comparable<mData>{//클래스를 써야 했을줄은..
		int value;
		int index;
		public mData(int value, int index) {
			super();
			this.value = value;
			this.index = index;
		}
		@Override
		public int compareTo(mData o) {
			// TODO Auto-generated method stub
			return this.value - o.value;
		}
	}