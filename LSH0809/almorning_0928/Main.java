package baekjoon.Baekjoon_14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0,};

    static void dfs(int y, int x, int total, int sum) {
        if (total == 4) {
            answer = Math.max(answer, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N)
                continue;
            if (visited[nextY][nextX])
                continue;

            visited[nextY][nextX] = true;
            dfs(nextY, nextX, total + 1, sum + arr[nextY][nextX]);
            visited[nextY][nextX] = false;


        }
    }

    static void exception(int y, int x) {
        if (y - 1 >= 0 && x - 1 >= 0 && x + 1 < M) {
            int temp1 = arr[y][x] + arr[y][x - 1] + arr[y][x + 1] + arr[y - 1][x];
            answer = Math.max(answer, temp1);
        }
        if (x - 1 >= 0 && x + 1 < M && y + 1 < N) {
            int temp2 = arr[y][x] + arr[y][x - 1] + arr[y][x + 1] + arr[y + 1][x];
            answer = Math.max(answer, temp2);
        }
        if (x + 1 < M && y + 1 < N && y - 1 >= 0) {
            int temp3 = arr[y][x + 1] + arr[y - 1][x] + arr[y + 1][x] + arr[y][x];
            answer = Math.max(answer, temp3);
        }
        if (x - 1 >= 0 && y - 1 >= 0 && y + 1 < N) {
            int temp4 = arr[y][x - 1] + arr[y - 1][x] + arr[y + 1][x] + arr[y][x];
            answer = Math.max(answer, temp4);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;
        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 0, 0);
                visited[i][j] = false;
                exception(i, j);
            }
        }

        System.out.println(answer);
    }
}
