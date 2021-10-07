package moreChallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main21608_상어초등학교1 {
    public static final int LIKE_NUM = 4;

    public static int[] dx = {-1, 1, 0, 0}; //순서 (행 앞, 행 뒤, 열 앞, 열 뒤) 순이래서 이렇게 배치
    public static int[] dy = {0, 0, -1, 1};
    public static int[] score = {0, 1, 10, 100, 1000};
    public static int[] nums; //
    public static int[][] batch;
    public static int N, answer;
    public static List<Set<Integer>> likeListSet = new ArrayList<>(); //set에 contains를 사용하기 위해

    public static void main(String[] args) throws IOException {
        input();
        makeBatch();
        countAll();
        System.out.println(answer);
    }

    private static void input() throws IOException { //입력, 초기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N * N];
        batch = new int[N][N];

        for (int i = 0; i <= N * N; i++) {
            likeListSet.add(new HashSet<>());
        }

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < LIKE_NUM; j++) {
                likeListSet.get(nums[i]).add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    private static void makeBatch() {//자리배치 메서드
        for (int i = 0; i < N * N; i++) {
            int currShark = nums[i];
            int maxScore = -1;
            int tx = -1;
            int ty = -1;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (batch[j][k] != 0) continue;
                    int score = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = j + dx[d];
                        int ny = k + dy[d];

                        if (check(nx, ny)) {
                            if (likeListSet.get(currShark).contains(batch[nx][ny])) {
                                score += 5; //임의의 높은 숫자로 카운트
                            } else if (batch[nx][ny] == 0) {
                                score += 1;
                            }
                        }
                    }

                    if (score > maxScore) {
                        tx = j;
                        ty = k;
                        maxScore = score;
                    }
                }
            }
            batch[tx][ty] = nums[i];
        }
    }

    private static void countAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (check(nx, ny) && likeListSet.get(batch[i][j]).contains(batch[nx][ny])) {
                        cnt++;
                    }
                }
                answer += score[cnt];
            }
        }
    }

    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx < N && 0 <= ny && ny < N;
    }
}

/*
[내가 틀린 이유]
1. 좋아하는 애가 옆에있는지 확인할 때 set 대신 직접 for 문으로 직접 돌면서 확인했었다;
2. 조건문의 뎁스가 길어지면서 계산의 어느 부분이 조금 잘못 됬었던 것 같다;
3. 또한 빈자리카운트, 좋아하는 친구 카운트를 일일이 다 한 후에 객체로 만들어서 list에 담은 다음 정렬을 해서
내부적으로 시간초과가 났었을 것으로 예상이 된다.

정답코드는 숫자로 맨끝에 넘버링 하겠습니다 !

*/