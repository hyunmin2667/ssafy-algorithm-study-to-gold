import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_13144_ListOfUniqueNumber {
	static int N;
	static int[] arr;
	static int l, r;
	static long ans;
	static Set<Integer> hashSet;

	// l과 r이 주어졌다. l <= r이다. arr[l] ~ arr[r]은 distinct하다. hashSet은 arr[l] ~ arr[r]로 구성되어 있다.
	// 이때 다음의 조건을 만족하는 f(l)을 구하자
	// f(l) >= l
	// arr[l] ~ arr[f(l)]은 distinct하다
	// f(l) == arr.length - 1이거나, arr(f(l) + 1]은 set(arr[l] ~ arr[(f(l)])에 포함되어 있다

	static int f(int l) {
		while (true) {
			if (r == arr.length - 1) {

				return r;
			} else if (hashSet.contains(arr[r + 1])) {

				return r;
			} else {
				hashSet.add(arr[r + 1]);
				r += 1;
			}
		}
	}

	static void twoPointer() {
		// N == 1인 경우
		if (N == 1) {
			ans = 1;

		}

		// 초기화
		ans = 0;
		l = 0;
		r = 0;

		hashSet = new HashSet<Integer>();
		hashSet.add(arr[l]);

		for (int l = 0; l < arr.length; l++) {
			ans += f(l) - l + 1;
			hashSet.remove(arr[l]);
		}


	}



	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		twoPointer();

		System.out.println(ans);

	}

}
