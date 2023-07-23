
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class BOJ_12891_DNA비밀번호 {
	static int[] arr = new int[4]; // 'A', 'C', 'T', 'G'의 갯수를 보관하는 배열.
	static int[] minNums = new int[4];
	static String dnaSeq;
	static int l;
	static int r;
	static int s;
	static int p;

	static boolean isPassword() {
		boolean result = true;

		for (int i = 0; i < 4; i++) {
			if (arr[i] < minNums[i]) {
				result = false;
				break;
			}
		}
		return result;
	}

	static void modifyArr(int l, int r) {
		if (dnaSeq.charAt(l) == 'A') {
			arr[0]--;
		} else if (dnaSeq.charAt(l) == 'C') {
			arr[1]--;
		} else if (dnaSeq.charAt(l) == 'G') {
			arr[2]--;
		} else {
			arr[3]--;
		}

		r = r + 1;

		if (r < s) {
			if (dnaSeq.charAt(r) == 'A') {
				arr[0]++;
			} else if (dnaSeq.charAt(r) == 'C') {
				arr[1]++;
			} else if (dnaSeq.charAt(r) == 'G') {
				arr[2]++;
			} else {
				arr[3]++;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer sAndP = new StringTokenizer(br.readLine());

		s = Integer.parseInt(sAndP.nextToken());
		p = Integer.parseInt(sAndP.nextToken());
		dnaSeq = br.readLine();

		StringTokenizer acgt = new StringTokenizer(br.readLine());

		for (int i = 0; i < 4; i++) {
			minNums[i] = Integer.parseInt(acgt.nextToken());
		}

		l = 0;
		r = p - 1;

		for (int i = 0; i < p; i++) {
			if (dnaSeq.charAt(i) == 'A') {
				arr[0]++;
			} else if (dnaSeq.charAt(i) == 'C') {
				arr[1]++;
			} else if (dnaSeq.charAt(i) == 'G') {
				arr[2]++;
			} else {
				arr[3]++;
			}
		}

		int ans = 0;

		while (r < s) {

			if (isPassword()) {
				ans++;
			}

			modifyArr(l, r);
			l++;
			r++;

		}

		System.out.println(ans);
	}
}
