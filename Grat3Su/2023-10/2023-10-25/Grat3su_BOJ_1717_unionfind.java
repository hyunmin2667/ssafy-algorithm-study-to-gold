package algo;

import java.io.*;
import java.util.*;
/*
 * 7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1
 */
 
public class BOJ_1717_unionfind {
	static int N, M;
    static int[] parent;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
 
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
 
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
 
            if (command == 0) {
                union(a, b);
            } else if (command == 1) {
                sb.append((isSameParent(a, b) ? "YES" : "NO") + "\n");
            } else continue;
        }
 
        System.out.println(sb);
    }
 
    // x의 부모를 찾는 연산
    public static int find(int a) {
        if (a == parent[a])	return a;
 
        return parent[a] = find(parent[a]);
    }
 
    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
 
        if (pa != pb) {
            if (pa < pb) {
                parent[pb] = pa;
            } else {
                parent[pa] = pb;
            }
        }
    }
 
    // x와 y의 부모가 같은지 확인
    public static boolean isSameParent(int a, int b) {
        int pa = find(a);
        int pb = find(b);
 
        if (pa == pb) {
            return true;
        }
 
        return false;
    }
 
}