package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class hwalgo08_부울경_3반_장수영 {
	// 순열문제
	// 위치에 따라 다음 값이 바뀜
	// 오늘 배운거 써보고싶다... next permutation
	// 정렬 배열에 사용 가능

	static int[] numArr;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		numArr = new int[N];
		for (int i = 0; i < N; i++) {// 배열 저장
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numArr);// 정렬
		int result = 0;
		int add = 0;
		while (true) {
			// complete code - 순열이기 때문에
			System.out.println(Arrays.toString(numArr));

			for (int i = 0; i < N - 1; i++) {
				add += Math.abs(numArr[i] - numArr[i + 1]);
			}
			if (add > result)
				result = add;
			add = 0;
			if (!np())
				break;
		}
		System.out.println(result);
	}

	// np
	static boolean np() {
		int i = numArr.length-1;//맨 끝 값에서 시작
		
		while(i>0&&numArr[i-1]>=numArr[i]) {//-가 양수였는데 음수가 된것같은거
			--i;
		}
		if(i==0)//i가 맨 앞으로 가면 끝내기
			return false;
		int j= numArr.length-1;
		while(numArr[i-1]>=numArr[j]) --j;
		
		swap(numArr, i-1,j);//i-1과 끝에 값 바꾸기
		int k = numArr.length-1;
		while(i<k) {
			swap(numArr,i++,k--);//재정렬(다시 np해야하니까)
		}
		return true;
		
	}

	static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
