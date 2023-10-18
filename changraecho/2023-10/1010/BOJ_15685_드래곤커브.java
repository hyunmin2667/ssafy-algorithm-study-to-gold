import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_15685_드래곤커브 {
    static int N; // 드래곤 커브의 개수
    static boolean[][] marked = new boolean[101][101];

    static class Coord {
        int x, y;

        public Coord(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        public Coord() {
            super();
        }

        @Override
        public String toString() {
            return "Coord [x=" + x + ", y=" + y + "]";
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Coord) {
                Coord crd = (Coord) obj;
                return this.x == crd.x && this.y == crd.y;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return (int) (Math.pow(2, x) * Math.pow(3, y));
        }

    }

    static Coord rotate(Coord origin, Coord point) { // point를 origin 중심으로 시계방향으로 90도 회전
        int ox = origin.x;
        int oy = origin.y;
        int px = point.x;
        int py = point.y;

        int rotatedX = -py + oy + ox;
        int rotatedY = px - ox + oy;

        return new Coord(rotatedX, rotatedY);
    }

    static void markDragonCurve(Coord startCrd, int dir, int g) { // 시작점, 시작방향, 세대가 주어졌을 때 얻어지는 드래곤 커브의 좌표들을 mark한다
        // coords, endCrd initalize
        Set<Coord> coords = new HashSet<>();

        // 시작방향에 따라 처음 endCrd가 달라짐
        Coord endCrd = new Coord();
        if (dir == 0) {
            endCrd.x = startCrd.x + 1;
            endCrd.y = startCrd.y;
        } else if (dir == 1) {
            endCrd.x = startCrd.x;
            endCrd.y = startCrd.y - 1;
        } else if (dir == 2) {
            endCrd.x = startCrd.x - 1;
            endCrd.y = startCrd.y;
        } else if (dir == 3) {
            endCrd.x = startCrd.x;
            endCrd.y = startCrd.y + 1;
        }

        // coords에 startCrd와 endCrd add
        coords.add(startCrd);
        coords.add(endCrd);
        // coords에 포함된 두 개의 점 mark
        marked[startCrd.x][startCrd.y] = true;
        marked[endCrd.x][endCrd.y] = true;

        Set<Coord> coordsTemp = new HashSet<>();

        // 다음 과정을 g번 반복함
        for (int i = 0; i < g; i++) {
            // coords에 있는 모든 점들을 endCrd 중심으로 시계방향으로 90도 회전, coordsTemp에 저장
            // coordsTemp에 있는 점들을 mark
            for (Coord point : coords) {
                Coord rotatedCrd = rotate(endCrd, point);
                coordsTemp.add(rotatedCrd);
                marked[rotatedCrd.x][rotatedCrd.y] = true;
            }
            // coordsTemp에 있는 점들을 coords에 추가
            for (Coord crd : coordsTemp) {
                coords.add(crd);
            }

            // coordsTemp 초기화
            coordsTemp = new HashSet<>();
            // endCrd 재정의: 끝점을 중심으로 시작점을 시계방향으로 90도 회전
            endCrd = rotate(endCrd, startCrd);
        }

        // System.out.println(coords.toString());

    }

    static int getAns() {
        int res = 0;

        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                if (marked[i][j] && marked[i + 1][j] && marked[i][j + 1] && marked[i + 1][j + 1])
                    res++;
            }
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            Coord startCrd = new Coord(startX, startY);
            markDragonCurve(startCrd, dir, g);
        }

        System.out.println(getAns());

    }

}