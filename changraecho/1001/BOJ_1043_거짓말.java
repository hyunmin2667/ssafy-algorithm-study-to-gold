import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1043_거짓말 {
    static int N, M;
    static int knowsTruth = 0; // 진실을 아는 사람을 아무나 한 명 선택한다.
    static int[] parent; // 길이가 N + 1인 배열
    static int[] partyRep; // 각 파티에 소속된 사람을 아무나 한 명 선택한다.
    static int ans = 0;

    static void initParent() { // parent 배열의 초기화
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }

    static int findParent(int a) {
        if (parent[a] == a)
            return a;

        return parent[a] = findParent(parent[a]);
    }

    static void union(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        parent[pa] = pb;
    }

    static boolean hasSameParent(int a, int b) {
        int pa = findParent(a);
        int pb = findParent(b);

        return pa == pb;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        initParent();

        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수

        if (num > 0) {
            knowsTruth = Integer.parseInt(st.nextToken());
            for (int i = 0; i < num - 1; i++) {
                union(knowsTruth, Integer.parseInt(st.nextToken()));
            }
        }

        partyRep = new int[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int numPartyPpl = Integer.parseInt(st.nextToken());
            int ppl = Integer.parseInt(st.nextToken());
            partyRep[i] = ppl;

            for (int j = 0; j < numPartyPpl - 1; j++) {
                union(ppl, Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < M; i++) {
            if (hasSameParent(partyRep[i], knowsTruth))
                continue;
            ans++;

        }

        System.out.println(ans);

    }

}
