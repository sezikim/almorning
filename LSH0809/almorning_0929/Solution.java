class Solution {
    public int balanced(String str) {
        int count = 0; // 왼쪽 괄호의 개수
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                count += 1;
            else
                count -= 1;
            if (count == 0)
                return i;
        }
        return -1;
    }

    public boolean check(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                count += 1;
            else {
                if (count == 0) return false;
                count -= 1;
            }
        }
        return true;
    }

    public String solution(String str) {
        String answer = "";
        if (str.equals(""))
            return answer;
        int idx = balanced(str);
        String x = str.substring(0, idx + 1);
        String y = str.substring(idx + 1);

        if (check(x))
            answer = x + solution(y);
        else {
            answer = "(";
            answer += solution(y);
            answer += ")";
            x = x.substring(1, x.length() - 1);
            String temp = "";
            for (int i = 0; i < x.length(); i++) {
                if (x.charAt(i) == '(')
                    temp += ")";
                else
                    temp += "(";
            }
            answer += temp;
        }
        return answer;
    }
}
