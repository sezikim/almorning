import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main12904_A와B {
//    static boolean flag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        StringBuilder T = new StringBuilder(br.readLine());

        //생각을 거꾸로 해보자! T를 제거해가면서 S의 길이까지 만들어서 같은지 비교하는 방식
        while(T.length() > S.length()){
            if(T.charAt(T.length()-1) == 'A'){
                T.deleteCharAt(T.length() - 1);
            }else{
                T.deleteCharAt(T.length() - 1).reverse();
            }
        }

        System.out.println(T.toString().equals(S) ? 1 : 0);
    }

    /*
    // 처음 코드: 시간초과 코드 (정방향으로 생각해서 재귀로 접근 시 시간초과;; 모든 경우를 다 확인해서 나는 에러인듯..)
    private static void check(String s, String t) {
        if(s.length() > t.length() || flag){ //더 진입해서 확인할 필요 없을 때.
            return;
        }

        if(s.equals(t)){ //답 찾았을 때
            flag = true;
            return;
        }

        StringBuilder sbs = new StringBuilder(s);

        check(sbs.append('A').toString(), t);               //조건 1
        check(sbs.reverse().append('B').toString(), t);     //조건 2
    }
     */
}
