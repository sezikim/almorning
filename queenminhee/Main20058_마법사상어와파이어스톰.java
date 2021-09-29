import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main20058_마법사상어와파이어스톰 {
    public static int N, Q, L, len, sum, maxIce; //len: 전체 배열 크기
    public static int[][] A;
    public static boolean[][] visited;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        len = (int) Math.pow(2, N);
        A = new int[len][len];

        for(int i = 0; i < len; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < len; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < Q ; i++){
            L = Integer.parseInt(st.nextToken());
            rotate();
            search();
        }
        findAns();

        System.out.println(sum +" "+ maxIce);
    }

    private static void findAns() {
        visited = new boolean[len][len];
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(!visited[i][j] && A[i][j] > 0){
                    visited[i][j] = true;
                    sum += A[i][j];
                    bfs(i, j);
                }
            }
        }
    }

    // 얼음 크기 최대정보 출력용도로 bfs
    private static void bfs(int i, int j) {
        Queue <int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        int cnt = 1;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];

            for(int k = 0; k < 4; k++){
                int nx = cx + dx[k];
                int ny = cy + dy[k];

                if(check(nx, ny) && !visited[nx][ny] && A[nx][ny] > 0){
                    sum += A[nx][ny];
                    visited[nx][ny] = true;
                    cnt++;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        maxIce = Math.max(cnt, maxIce);
    }

    // 이부분도 꽤 헤맸던게, 3개가 연결된거라고 해서 나는 dfs로 다돌아야하나 생각했는데 문제를 다시 읽어보니 자기자신과 연결된 3개니까 cnt해서 표시함
    private static void search() {
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                int cnt = 0;

                for(int k = 0; k < 4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(check(nx, ny) && A[nx][ny] > 0){
                        cnt++;
                    }
                }

                if(cnt < 3){
                    q.add(new int[]{i, j});
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            A[cur[0]][cur[1]] --;
        }
    }

    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx < len && 0 <= ny && ny < len;
    }

    private static void rotate() {
        int innerLen = (int) Math.pow(2, L);
        int[][] temp = new int[len][len];
        //sr, sc 시작점
        for(int sr = 0; sr < len; sr+= innerLen){
            for(int sc = 0; sc < len; sc+= innerLen){
                //시작점으로부터 rotate !! (단 시작점이 바뀌지는 않는다!! 이걸 생각하는게 가장 어려웠음!!)
                for(int i = 0; i < innerLen; i++){
                    for(int j = 0; j < innerLen; j++){
                        temp[sr + j][sc + innerLen - i - 1] = A[sr + i][sc + j];
                    }
                }
            }
        }
        A = temp;
    }

}
