package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main17135_캐슬디펜스 {
    public static int[][] map, ori;
    public static boolean[][] visited;
    public static int[] dx = {0, -1, 0};
    public static int[] dy = {-1, 0, 1};
    public static int max, cnt, N, M, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        ori = new int[N][M];
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                ori[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M-2; i++){
            for(int j = i+1; j < M-1; j++){
                for(int k = j+1; k < M; k++){
                    cnt = 0;
                    startGame(i, j, k, N);
                    max = Math.max(max, cnt);
                }
            }
        }

        System.out.println(max);
    }

    private static void startGame(int x, int y, int z, int row) {//궁수 pos
        copyMap();
        while(row > 0){
            bfs(x, row);
            bfs(y, row);
            bfs(z, row);
            row--;
        }
    }

    private static void copyMap() {
        for(int i = 0; i < N; i++){
            if (M >= 0) System.arraycopy(ori[i], 0, map[i], 0, M);
        }
    }

    private static void bfs(int col, int row) {
        Queue <int[]> q = new LinkedList<>();
        Queue <int[]> candidate = new PriorityQueue<>((a,b) -> a[2] == b[2]? Integer.compare(a[1], b[1]): Integer.compare(a[2], b[2]));
        visited = new boolean[row][M];
        q.add(new int[]{row, col, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(!outRange(cur[0], cur[1], row) && map[cur[0]][cur[1]] == 1 && cur[2] <= D){
                candidate.add(cur);
            }

            for(int i = 0; i < 3; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(outRange(nx, ny, row) || cur[2] >= D || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, Math.abs(ny - col) + Math.abs(nx - row)});
            }
        }
        if(candidate.isEmpty()) return;
        int[] temp = candidate.poll();
        map[temp[0]][temp[1]] = 0;
        cnt++;

    }

    private static boolean outRange(int nx, int ny, int row) {
        return nx < 0 || ny < 0 || nx >= row || ny>= M;
    }

}

// 주어진 예제는 다 돌아갔지만, 전체로 돌리니 죽어버렸어요 ㅠ3ㅠ
// 궁수를 둘때 3중반복 * bfs를 너무 많이 돌기 해서 시간내로 안되는게 아닐런지 ㅠㅠ
// + 공간도 꽤나 많이 쓰고....