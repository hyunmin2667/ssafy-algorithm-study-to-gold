package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2750_numSort {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bf.readLine());
		int[] arr = new int[N];
		
		for(int i =0; i<N; i++)
			arr[i] = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i<N-1; i++) {
			int idx = i;
			for(int j = i; j<N; j++) {
				if(arr[idx] >arr[j])
					idx = j;
			}
			int t = arr[i];
			arr[i] = arr[idx];
			arr[idx] = t;
		}
		StringBuilder sb = new StringBuilder();
		
		for(int i :arr) sb.append(i).append("\n");
		
		//System.out.println(Arrays.toString(arr));
		System.out.println(sb);

	}

}
