import java.util.Stack;

// 파일명 정렬
public class Sol1 {
    public static void main(String[] args) {
        System.out.println(new Solution1().solution("(()())()"));
        System.out.println(new Solution1().solution(""));
        System.out.println(new Solution1().solution(")("));
        System.out.println(new Solution1().solution("()))((()"));
    }
}

class Solution1 {
    public String solution(String p) {
        return conditionStart(p);
    }

    private String conditionStart(String p) {
        if (p.isBlank()) return p;

        return splitValanceStr(p);
    }

    private String splitValanceStr(String p) {
        int l = 0;
        int r = 0;
        String u = "", v = "";

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') l++;
            else if (p.charAt(i) == ')') r++;

            if (l == r) {
                u = p.substring(0, i + 1);
                v = p.substring(i + 1);
                break;
            }
        }

        if (isCorrectBracket(u)) {
            return u + conditionStart(v);
        } else {
            StringBuilder str = new StringBuilder("(");
            str.append(conditionStart(v));
            str.append(')');

            for(char c: u.substring(1, u.length()-1).toCharArray()){
                str.append(c ==')'? '(': ')');
            }
            return str.toString();
        }
    }

    private boolean isCorrectBracket(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (stack.isEmpty()) {
                if (c == ')') return false;
                else stack.push(c);
            } else {
                if (stack.peek() == '(' && c == ')') stack.pop();
                else stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}

/*
// 나는 isCorrectBracket 함수로 올바른 괄호인지 한번 더 확인을 했는데, (스택 사용해서)
// l, r, 그리고 balance 를 카운트해서 해결하면 조금 더 효율적이었을 것 같다. (아래 레퍼런스)
// https://programmers.co.kr/learn/courses/30/lessons/60058/solution_groups?language=java
 */