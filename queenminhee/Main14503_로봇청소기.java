import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14503_로봇청소기 {
    public final static int WALL = 1;
    public final static int BLANK = 0;

    public static int N, M, cnt;
    public static int[] dir = new int[3];
    public static int[] dx = {-1, 0, 1, 0}; //북동남서 (순서 중요)
    public static int[] dy = {0, 1, 0, -1};
    public static int[][] arr;
    public static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean [N][M];

        st = new StringTokenizer(br.readLine());
        dir[0] = Integer.parseInt(st.nextToken());
        dir[1] = Integer.parseInt(st.nextToken());
        dir[2] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //입력 끝

        search(); //인접한 칸 탐색

        System.out.println(cnt);
    }

    private static void search() {
        clear();  //현위치 청소

        boolean flag = false;
        for(int i = 0; i < 4; i++){
            int nextDir = (dir[2] + 3) % 4; //왼쪽으로 회전
            int nx = dir[0] + dx[nextDir];
            int ny = dir[1] + dy[nextDir];

            if(isRange(nx, ny) && !visited[nx][ny] && arr[nx][ny] == BLANK){ //청소한적도 없고, 빈칸이면 한칸 전진 후 1번부터
                dir[0] = nx;
                dir[1] = ny;
                dir[2] = nextDir;
                search();
                flag = true;
                break;
            }
            dir[2] = nextDir;
        }

        if(!flag){
            int nextDir = (dir[2] + 2) % 4;  //후진
            int nx = dir[0] + dx[nextDir];
            int ny = dir[1] + dy[nextDir];
            if(isRange(nx, ny) && arr[nx][ny] != WALL){
                dir[0] = nx;
                dir[1] = ny;
                search();
            }
        }
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <=nx && nx < N && 0 <= ny && ny < M;
    }

    private static void clear() {
        if(!visited[dir[0]][dir[1]] && arr[dir[0]][dir[1]] == BLANK) {
            cnt++;
            visited[dir[0]][dir[1]] = true;
        }
    }
}

/*
[구현하면서 헤맸던 부분]
1. stackOverflow 해결하기
  1-1. visited 로 방문여부를 표시해서 체크를 했는데 바보같이 !visited[nx][ny] 로 방문안한곳을 찾아야하는데 !를 안붙여서 한참 맞왜틀함ㅋㅋ;;
* */

