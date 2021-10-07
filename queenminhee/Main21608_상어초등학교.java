import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main21608_상어초등학교 {
    public static final int LIKE_NUM = 4;

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static int[] score = {0, 1, 10, 100, 1000};
    public static int[][] batch;
    public static int[][] likeArr;
    public static int N, answer;
    public static List<Shark2> list;

    public static void main(String[] args) throws IOException {
        input();
        makeBatch();
        countAll();
        System.out.println(answer);
    }

    private static void countAll() {
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int curr = batch[i][j];
                int cnt = 0;

                for(int k = 0; k < N* N; k++){
                    if(likeArr[k][0] == curr){
                        for(int d = 0; d < 4; d++){
                            int nx = i + dx[d];
                            int ny = j + dy[d];

                            if(check(nx, ny) && contains(likeArr[k], batch[nx][ny])){
                                cnt++;
                            }
                        }
                        answer+= score[cnt];
                        break;
                    }
                }
            }
        }
    }

    private static void input() throws IOException { //입력, 초기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        batch = new int[N][N];
        likeArr = new int[N*N][LIKE_NUM+1];

        for(int i = 0; i < N * N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < LIKE_NUM + 1; j++){
                likeArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void makeBatch() {//자리배치 메서드
        for(int i = 0; i < N * N; i++){
            list = new ArrayList<>();
            int currShark = likeArr[i][0];

            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    int emptyCnt = 0;
                    int likeCnt = 0;
                    if(batch[j][k] == 0){
                        for (int d = 0; d < 4; d++){
                            int nx = j + dx[d];
                            int ny = k + dy[d];

                            if(check(nx, ny)){
                                if(batch[nx][ny] == 0){
                                    emptyCnt++;
                                }
                                else if(contains(likeArr[i], batch[nx][ny])){
                                    likeCnt++;
                                }
                            }
                        }
                    }
                    Shark2 shark = new Shark2(j, k, likeCnt, emptyCnt);
                    list.add(shark);
                }
            }

            Collections.sort(list);
            var shark = list.get(0);
            batch[shark.x][shark.y] = currShark;
        }
    }

    private static boolean contains(int[] ints, int batch) {
        for (int anInt : ints) {
            if (anInt == batch) {
                return true;
            }
        }
        return false;
    }

    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx < N &&  0 <= ny && ny < N;
    }
}

class Shark2 implements Comparable<Shark2>{
    int x;
    int y;
    int likeCnt;
    int emptyCnt;

    public Shark2(int x, int y, int likeCnt, int emptyCnt) {
        this.x = x;
        this.y = y;
        this.likeCnt = likeCnt;
        this.emptyCnt = emptyCnt;
    }

    @Override
    public int compareTo(Shark2 o) {
        if(this.likeCnt != o.likeCnt){
            return Integer.compare(o.likeCnt, this.likeCnt);
        }else if(this.emptyCnt != o.emptyCnt){
            return Integer.compare(o.emptyCnt, this.emptyCnt);
        }else if(this.x != o.x){
            return Integer.compare(this.x, o.x);
        }else{
            return Integer.compare(this.y, o.y);
        }
    }
}

/*
틀렸다고 뜬다;;
*/