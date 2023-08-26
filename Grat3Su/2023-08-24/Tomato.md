# 백준 7576번 토마토

2차원 배열에 있는 토마토 중 1(익은 토마토)가 4방향으로 안익은 토마토(0)를 익게(1)한다.
모든 토마토가 익는 최소일을 구해라. 토마토가 들어있지 않는 빈칸(-1)도 있다.

  -> 사방탐색+BFS 문제

```java
for (int i = 0; i < N; i++) {
    st = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++) {
            box[i][j] = Integer.parseInt(st.nextToken());
            if (box[i][j] == 1) {
                queue.offer(new int[] { i,j });// 1이 여러개일 경우
            }
        }
}
```

 박스 배열 채우기. 
 
 익은 토마토(1)가 여러개일 수 있기 때문에 선입 선출 구조의 큐를 사용했다.
 
  

``` java
for (int i = 0; i < 4; i++) {
    int cx = x + dx[i];
    int cy = y + dy[i];
    if (box[cx][cy] == 0) {
        box[cx][cy] = box[x][y] + 1;
        queue.offer(new int[] { cx, cy });
}
```

델타 탐색으로 사방탐색 안익은 토마토(0)이 나오면

  -> 현재 위치(1)에서 +1해서 depth 체크

![image](https://github.com/hyunmin2667/ssafy10-algorithm-study-to-gold/assets/26815767/eb93e17b-3442-4c8e-87bc-51429da2daf9)

그런데 이렇게 하면 양방향에서 토마토가 익고있을 경우 depth 표기가 제대로 안 될 수 있다.

``` java
else if (box[cx][cy] == 1) {
    box[cx][cy] = Math.min(box[cx][cy], box[x][y] + 1);
}
```
![image](https://github.com/hyunmin2667/ssafy10-algorithm-study-to-gold/assets/26815767/eb1fdd23-fa13-4a65-95e1-14e44aa82d39)
-> 그래서 ! 그림처럼 양방향에서 오는 경우 최솟값을 비교해 더 작은 값을 배열에 넣는다.
