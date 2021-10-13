package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main16234_인구이동 {
    public static int N, L, R;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(find());
    }

    private static int find() {
        int cnt = 0;
        while(true){
            visited = new boolean[N][N];
            boolean flag = false;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(visited[i][j]) continue;
                    if(isOpened(i, j)) flag = true;
                }
            }
            if(!flag) break;
            cnt ++;
        }
        return cnt;
    }

    private static boolean isOpened(int x, int y) {
        Queue <int[]> q = new LinkedList<>();
        List<int[]> list = new ArrayList<>();
        q.add(new int[]{x, y});
        list.add(new int[]{x, y});
        visited[x][y] = true;
        int sum = map[x][y];

        while(!q.isEmpty()){
            int[] curr = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
                int diff = Math.abs(map[nx][ny] - map[curr[0]][curr[1]]);
                if(diff < L || diff > R) continue;

                list.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                sum += map[nx][ny];
                q.add(new int[]{nx, ny});
            }
        }

        if(list.size() <= 1) return false;
        int avg = sum / list.size();
        for(int[] curr: list){
            map[curr[0]][curr[1]] = avg;
        }
        return true;
    }
}
