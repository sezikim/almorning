import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 괄호변환 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution2().solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})));
        System.out.println(Arrays.toString(new Solution2().solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"})));
        System.out.println(Arrays.toString(new Solution2().solution(new String[]{"img100.p2ng", "img202.png123"})));
    }
}

class Solution2 {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        List<File2> list = new ArrayList<>();

        for (String f : files) {
            list.add(getFile(f));
        }

        Collections.sort(list);
        for(int i = 0; i < files.length; i++){
            answer[i] = list.get(i).ori;
        }
        return answer;
    }

    private File2 getFile(String f) {
        StringBuilder head = new StringBuilder();
        StringBuilder number = new StringBuilder();
        StringBuilder tail = new StringBuilder();

        int i = 0;

        //f.length()를 처음에 필드에 N = files.length 해둔걸 쓰면서 런타임에러 났었음; 이걸 계속 몿잦아서 읭?했다;;ㅎ
        //이런 경우에는 처음부터 끝까지 다시 읽으면서 점검을 해야 찾을 수 있었다;
        for ( ; i < f.length(); i++) {
            char c = f.charAt(i);
            if ('0' <= c && c <= '9') {
                break;
            }
            head.append(c);
        }

        for (; i < f.length(); i++) {
            char c = f.charAt(i);
            if (! ('0' <=c && c <= '9')) {
                break;
            }
            number.append(c);
        }

        for (; i < f.length(); i++) {
            char c = f.charAt(i);
            tail.append(c);
        }

        return new File2(f, head.toString().toLowerCase(), Integer.parseInt(number.toString()), tail.toString());
    }
}

class File2 implements Comparable<File2> {
    String ori;
    String head;
    int num;
    String tail;

    public File2(String ori, String head, int num, String tail) {
        this.ori = ori;
        this.head = head;
        this.num = num;
        this.tail = tail;
    }

    @Override
    public int compareTo(File2 o) {
        if (!this.head.equalsIgnoreCase(o.head)) {
            return this.head.compareToIgnoreCase(o.head);
        } else if (this.num != o.num) {
            return Integer.compare(this.num, o.num);
        }
        return 0;
    }
}

/*
[오래 걸린 부분]
1. files 의 길이랑 files 의 원소의 길이를 같게 처리해서 (착각함) 계속 런타임 오류가 난 걸 예측하지 못함..ㅎㅎ
2. 문자열 자르고 더하는것에 대해서 조심하자..!
 */