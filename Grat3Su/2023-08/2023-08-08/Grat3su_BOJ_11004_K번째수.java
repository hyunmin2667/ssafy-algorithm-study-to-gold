package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class BOJ_11004_K번째수 {
/*	데이터셋
 * 	5 2
 *  4 1 2 3 5
 *  A를 정렬했을 때 앞에서부터 K번째 있는 수 구하기
 */
	static SortA[] A;
	static int N;
	static int K;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new SortA[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			A[i]= new SortA(Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(A);
		
		System.out.println(Arrays.toString(A));
		System.out.println(A[K-1].getValue());
	}
}

class SortA implements Comparable<SortA>{
	int value;
	@Override
	public int compareTo(SortA o) {
		return this.value-o.value;
	}
	public SortA() {}
	public SortA(int value) {
		super();
		this.value = value;
	}
	@Override
	public String toString() {
		return "SortA [value=" + value + "]";
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
