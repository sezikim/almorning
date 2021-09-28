import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main14500_테트리미노 {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int N, M, maxVal;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 0, 0);
                findAh(i, j);
            }
        }

        System.out.println(maxVal);
    }

    private static void findAh(int cx, int cy) {
        int min = Integer.MAX_VALUE;
        int sum = arr[cx][cy];
        int cnt = 4;

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if(cnt < 3){ //총 날개는 4개
                return;
            }

            if (check(nx, ny)) {
                min = Math.min(min, arr[nx][ny]);
                sum += arr[nx][ny];
            }else{ //범위 밖이라서 날개를 세지 못하는 경우
                cnt--;
            }
        }
        //4개의 날개중 가장 작은 값을 빼준다.
        if(cnt == 4){
            sum-=min;
        }

        //업뎃한 후에는 저장!!잊지 말자!!
        maxVal = Math.max(maxVal, sum);
    }

    private static void dfs(int cx, int cy, int depth, int sum) {
        if (depth == 4) {
            maxVal = Math.max(maxVal, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (check(nx, ny) && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + arr[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

    private static boolean check(int nx, int ny) {
        return (0 <= nx && nx < N && 0 <= ny && ny < M);
    }
}