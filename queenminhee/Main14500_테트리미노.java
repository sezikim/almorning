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


/*
- 이번 문제부터 입출력은 Scanner 대신 BufferedReader, Writer 를 사용하도록 노력해보기

[문제 idea]
1. 4개의 연결된 1*1 사각형을 찾기 위해서 DFS 로 확인한다.
2. 단, ㅏ 모형은 DFS 대신 4방면을 모두 확인한후, 4개중 가장 작은 방향을 제외시키는 형태로 구현

[idea 를 못떠올렸음;;]
하나하나 다 구현을 해야하나 고민을 했는데 비효율적인 것 같아서 다른 레퍼런스들을 많이 봤는데
대부분 비슷하게 구현한 것 같다.
- DFS 아니면 나처럼 직접 하나하나 다 구할 생각을 했던 것 같다;
- 좀 더 효율적으로 DFS 로 안보고 구현을 해봄

[실수한 부분]
- Math.max를 한 후에는 저장을 해야지
- 날개의 총개수 체크 3개보다 작으면 만들 수 없음!, 그리고 돌면서 3개짜리는 최소값을 빼서 확인할 필요가 없음!!

* */