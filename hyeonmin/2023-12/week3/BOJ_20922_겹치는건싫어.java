package Algorithm_2023.src.month12.day13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_20922_겹치는건싫어 {

    static int N, K;
    static int[] arr;
    static int[] cntNum = new int[100001];
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        // "같은 원소가 K개 이하인 최장 연속 부분 수열의 길이"
        // 길이가 20만개
        // K는 최대 100
        // 투포인터, 슬라이딩
        // 왼쪽부터 오른쪽으로 크기를 늘리다가 조건에 맞지 않으면 오른쪽으로 슬라이딩 시킨다. 그리고 반복
        // K개 이상이 된 숫자를 check 해놨다가 그 숫자가 사라진 경우에만

        // 10만 이하의 양의 정수
        // 8byte * 100,000 = 800,000byte

        // input
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        cntNum[arr[left]]++;

        while (right + 1 < N) {
            // "오른쪽 확장, 숫자 개수 업데이트"
            if (++cntNum[arr[++right]] > K) {
                // K개 이상? -> 슬라이딩
                list.add(arr[right]);
                if (--cntNum[arr[left++]] <= K) list.remove(new Integer(arr[left - 1]));
                // list에 있는 숫자들이 모두 해결될 때 까지 반복
                while (!list.isEmpty()) {
                    if (right + 1 >= N) break;
                    // right를 올리고 해당 cntNum++
                    if (++cntNum[arr[++right]] > K) list.add(arr[right]);
                    // left를 올리면서 cntNum--
                    if (--cntNum[arr[left++]] <= K) list.remove(new Integer(arr[left - 1]));
                }
            }
        }
        System.out.println(right - left + 1);
    }
}
