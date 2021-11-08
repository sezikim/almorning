package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main20436_ZOAC3 {
    public static String[] keyboard = {"qwertyuiop", "asdfghjkl", "zxcvbnm"}; //오타 주의
    public static int[] left = new int[2];
    public static int[] right = new int[2];
    public static int[] temp = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        findDir(left, st.nextToken().charAt(0));
        findDir(right, st.nextToken().charAt(0));

        String str = br.readLine();
        int cnt = str.length();

        for(int i = 0; i < str.length(); i++) {
            findDir(temp, str.charAt(i));
            if ((temp[0] <= 1 && temp[1] <= 4) || (temp[0] == 2 && temp[1] <= 3)) { //자음
                cnt += Math.abs(temp[0] - left[0]) + Math.abs(temp[1] - left[1]);
                left[0] = temp[0];
                left[1] = temp[1];
            }else{ //모음
                cnt += Math.abs(temp[0] - right[0]) + Math.abs(temp[1] - right[1]);
                right[0] = temp[0];
                right[1] = temp[1];
            }
        }

        System.out.println(cnt);
    }

    public static void findDir(int[] arr, char c) {
        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[i].length(); j++) {
                if (keyboard[i].charAt(j) == c) {
                    arr[0] = i;
                    arr[1] = j;
                }
            }
        }
    }
}
