import java.util.Stack;

class Solution {

    static String answer = "";

    public String solution(String p) {
        return split_str(p);
    }

    public String split_str(String p) {
        if(p.equals("")) {
            return "";
        }
        int left = 0;
        int right = 0;
        StringBuilder u = new StringBuilder();
        String v = "";

        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) == '(') {
                left++;
            }
            else {
                right++;
            }
            u.append(p.charAt(i));

            if(left == right) {
                v = p.substring(i + 1);
                break;
            }
        }
        if(check(u.toString())) {
            return u.toString() + split_str(v);
        }
        else {
            StringBuilder else_str = new StringBuilder("(");
            else_str.append(split_str(v));
            else_str.append(")");
            u.deleteCharAt(u.length() - 1);
            u.deleteCharAt(0);
            else_str.append(reverseBracket(u.toString()));
            return else_str.toString();
        }
    }

    public boolean check(String p) {
        Stack<Character> s = new Stack<>();

        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '(') {
                s.push(p.charAt(i));
            }
            else {
                if(s.size() > 0) {
                    s.pop();
                }
                else {
                    return false;
                }
            }
        }

        if(s.size() == 0) {
            return true;
        }
        return false;
    }

    public String reverseBracket(String p) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '(') {
                sb.append(')');
            }
            else {
                sb.append('(');
            }
        }
        return sb.toString();
    }
}