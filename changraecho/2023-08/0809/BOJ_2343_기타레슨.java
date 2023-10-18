
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;
import java.io.BufferedReader;

// 책 참조함
public class BOJ_2343_기타레슨 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str;
        StringBuilder sb = new StringBuilder();

        // N, M
        str = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());

        // 강의 길이 arr
        int[] lectures = new int[N];

        str = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(str.nextToken());
        }

        // 정답이 될 수 있는 수들의 범위를 구한다. 하한선은 max(arr), 상한선은 sum(arr)이다.
        int lowerBound = 0;
        int upperBound = 0;

        for (int l : lectures) {
            lowerBound = Math.max(lowerBound, l);
            upperBound += l;
        }

        // 임의의 자연수 k에 대해, (정답 > k)의 참거짓은 O(N) 시간내에 확인 가능. 그러므로 범위 내에서 이진 탐색을 실행.
        int l = lowerBound;
        int r = upperBound;

        int m;

        // 정답
        int ans = 0;

        // 정답이 m보다 큰 지 확인해야 함
        int curSum;
        int partitionNum;

        while (r - l >= 1) {

            m = (l + r) / 2;

            // 정답이 m보다 큰 경우
            boolean largerThanM;
            curSum = 0;
            partitionNum = 1;

            for (int runningTime : lectures) {
                if (runningTime > m) {
                    largerThanM = false;
                    break;
                } else if (curSum + runningTime <= m) {
                    curSum += runningTime;
                } else {
                    partitionNum++;
                    curSum = runningTime;
                }
            }

            if (partitionNum > M) {
                largerThanM = true;
            } else {
                largerThanM = false;
            }

            if (largerThanM) {
                if (l == m) {
                    ans = r;
                    break;
                }
                l = m;

            } else { // 정답이 m보다 작거나 같은 경우
                if (l == m) {
                    ans = l;
                    break;
                }
                r = m;
            }

        }

        System.out.println(ans);

    }
}
