import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_4195_친구네트워크 {
    static HashMap<String, String> parent;
    static HashMap<String, Integer> subsetCard;
    static int F;

    static String findParent(String s) {
        if (s.equals(parent.get(s)))
            return s;

        parent.put(s, findParent(parent.get(s)));

        return parent.get(s);
    }

    static boolean hasSameParent(String a, String b) {
        return findParent(a).equals(findParent(b));
    }

    static int findFriendsNum(String a, String b) {

        if (!parent.containsKey(a)) {
            parent.put(a, a);
            subsetCard.put(a, 1);
        }

        if (!parent.containsKey(b)) {
            parent.put(b, b);
            subsetCard.put(b, 1);
        }

        if (hasSameParent(a, b)) {
            return subsetCard.get(findParent(a));
        }

        else {
            String pa = findParent(a);
            String pb = findParent(b);

            int aCard = subsetCard.get(pa);
            int bCard = subsetCard.get(pb);

            parent.put(pa, pb);

            subsetCard.put(pb, aCard + bCard);

            return aCard + bCard;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            parent = new HashMap<>();
            subsetCard = new HashMap<>();
            F = Integer.parseInt(br.readLine());

            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                sb.append(findFriendsNum(a, b));
                sb.append("\n");

            }

        }

        System.out.println(sb);

    }

}
