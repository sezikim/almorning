import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//idea: (누적합)
public class Main2015_수들의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);
        int[] arr = new int[N+1];
        int[] temp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        temp[1] = arr[1];
        for(int i = 1; i <= N; i++){
            temp[i] = temp[i-1] + arr[i];
        }

        Map<Integer, Integer> map = new HashMap<>();
        long cnt = 0;
        for(int i = 1; i <= N; i++){
            if(temp[i] == K) cnt++;
            cnt += map.getOrDefault(temp[i]-K, 0);
            map.put(temp[i], map.getOrDefault(temp[i], 0)+ 1);
        }

        System.out.println(cnt);
    }

}

/*
[틀려서 고쳐야했던 부분]
1. cnt 의 크기를 int 로 잡아서,, (로직의 문제가 없다 생각되면 type 체크를 꼭!)
*/