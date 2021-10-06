package answer;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_2015_ANSWER{
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        int[] psum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            // 접두합 계산
            psum[i] = psum[i - 1] + arr[i];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        long ret = 0;
        // 아래 코드는 이렇게 동작한다.
        // 예를들어 map[2] 가 1이였다면, 이전에 psum 에서 2가 나왔다는 의미이고
        // 현재 보고 있는 psum[i] 와 이전에 나온 j의 psum[j] 를 뺴주면 k가 된다.
        // 즉, psum[i] - k 의 의미는 예전의 psum에서 나온 값을 찾는 것이다.
        for(int i = 1; i <= N; i++) {
            if (psum[i] == K) {
                ret++;
            }

            ret += map.getOrDefault(psum[i] - K, 0);

            map.put(psum[i], map.getOrDefault(psum[i], 0) + 1);
        }
        System.out.println(ret);
    }
}
