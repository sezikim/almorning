import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main20164_홀수홀릭호석 {
    public static int maxAns = 0;
    public static int minAns = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();

        func(N, countOdd(N));

        System.out.println(minAns + " " + maxAns);
    }

    private static void func(String n, int sum) {
        if (n.length() == 1) {
            minAns = Math.min(minAns, sum );
            maxAns = Math.max(maxAns, sum );
        } else if (n.length() == 2) {
            int f = n.charAt(0) - '0';
            int s = n.charAt(1) - '0';
            String nextNum = String.valueOf(f + s);
            func(nextNum, sum + countOdd(nextNum));
        } else if (n.length() >= 3) {
            for (int i = 1; i < n.length() - 1; i++) {
                for (int j = i + 1; j < n.length(); j++) {//f:first, s: second, t: third
                    int f = Integer.parseInt(n.substring(0, i));
                    int s = Integer.parseInt(n.substring(i, j));
                    int t = Integer.parseInt(n.substring(j));
                    String nextNum = String.valueOf(f + s + t);
                    func(nextNum, sum + countOdd(nextNum));
                }
            }
        }

    }

    private static int countOdd(String n) {
        int ret = 0;
        for(int i = 0; i < n.length(); i++){
            if((n.charAt(i) -'0') % 2 != 0){
                ret++;
            }
        }
        return ret;
    }
}
