package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;



public class BOJ_1427_SortInsight {
	static SortNum[]arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		String sortArr = br.readLine();
		arr = new SortNum[sortArr.length()];
		for(int i = 0; i<sortArr.length(); i++) {
			arr[i] = new SortNum(sortArr.charAt(i)-'0');
		}

		Arrays.sort(arr);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<arr.length; i++) {
			sb.append(arr[i].getValue());
		}
		System.out.println(sb);
	}
	

}

class SortNum implements Comparable<SortNum>{
	int value;
	
	public SortNum(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	@Override
	public int compareTo(SortNum o) {
		// TODO Auto-generated method stub
		return o.value - this.value;
	}
}
