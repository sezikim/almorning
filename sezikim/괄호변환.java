import java.util.*;

class 괄호변환 {
    static String answer = "";
    public String solution(String p) {
        
        if (isRight(p)) return p;
        else return solve(p);
    }
    
    String solve(String p) {
        if (p.equals("")) return "";
        
        String[] arr = parse(p);
        String u = arr[0];
        String v = arr[1];

        if (isRight(u)) {
            answer += u;
            solve(v);
        } else {
            answer += "(";
            solve(v);
            answer += ")";
            for (int i = 1; i < u.length() - 1; ++i) {
                if (answer.charAt(i) == ')')
                    answer += '(';
                else
                    answer += ')';
            }
        }

        return answer;
    }
    
    String[] parse(String p) {
        String[] parseStringArr = new String[2];
        char[] arr = p.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();

        String u = "";
        for (int i = 0; i < arr.length; ++i) {
            if (map.containsKey(arr[i]))
                map.replace(arr[i], map.get(arr[i]) + 1);
            else map.put(arr[i], 1);
            u += arr[i];
            if (map.containsKey('(') && map.containsKey(')') && map.get('(').equals(map.get(')')))  {
                parseStringArr[0] = u;
                parseStringArr[1] = p.substring(i+1);
                break;
            }
        }

        return parseStringArr;
    }
    
    boolean isRight(String p) {
        Stack<Character> stack = new Stack<>();
        char[] arr = p.toCharArray();

        for (char c : arr) {
            stack.push(c);
            if (stack.size() > 1) {
               if (stack.get(stack.size() - 1) == ')' && stack.get(stack.size() - 2) == '(') {
                   stack.pop();
                   stack.pop();
               }
            }
        }

        return stack.isEmpty();
    }
}