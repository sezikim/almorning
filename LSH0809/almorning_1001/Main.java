import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main { // Baekjoon_12904 A와 B
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder S = new StringBuilder(br.readLine());
        StringBuilder T = new StringBuilder(br.readLine());

        while (T.length() != S.length()) {
            char chr = T.charAt(T.length() - 1);
            T.deleteCharAt(T.length() - 1);
            if (chr == 'B') {
                T.reverse();
            }
        }

        if (S.toString().equals(T.toString())) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
