package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9527_1의개수세기 {
    static long A, B;

    static long getOneNum(long n) { // 1부터 n까지의 모든 자연수들을 이진수로 나타내었을 때, 등장하는 1의 총 개수
        char[] binary = Long.toBinaryString(n).toCharArray();

        long res = 0;
        int binaryLen = binary.length;
        int oneNum = 0;

        for (int i = 0; i < binaryLen; i++) {
            if (binary[i] == '0')
                continue;

            res += (binaryLen - 1 - i) * (long) Math.pow(2, binaryLen - 1 - i - 1);
            res += oneNum * (long) Math.pow(2, binaryLen - 1 - i);
            oneNum++;
        }

        res += oneNum;

        return res;

    }

    static long getAns(long A, long B) {
        return getOneNum(B) - getOneNum(A - 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        System.out.println(getAns(A, B));

    }

}
