import java.util.*;

class 파일명정렬 {
    public String[] solution(String[] files) {
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < files.length; ++i) {
            String file = files[i];
            List<String> curFileList = new ArrayList<>();

            int numberIndex = 0;
            for (int j = 0; j < file.length(); ++j) {
                if (file.charAt(j) >= '0' && file.charAt(j) <= '9') {
                    curFileList.add(file.substring(0,j));
                    numberIndex = j;
                    break;
                }
            }

            int tailIndex = 0;
            boolean check = true;
            for (int x = numberIndex; x < numberIndex + 5 && x < file.length(); ++x) {
                if (!(file.charAt(x) >= '0' && file.charAt(x) <= '9')) {
                    curFileList.add(file.substring(numberIndex, x));
                    tailIndex = x;
                    break;
                }

                if (x == file.length() - 1) {
                    curFileList.add(file.substring(numberIndex, x + 1));
                    tailIndex = x + 1;
                    break;
                }

                if (x == numberIndex + 4) {
                    curFileList.add(file.substring(numberIndex, numberIndex + 5));
                    tailIndex = numberIndex + 5;
                }
            }

            curFileList.add(file.substring(tailIndex));
            list.add(curFileList);
        }

        list.sort((list1, list2) -> {
            if (list1.get(0).equalsIgnoreCase(list2.get(0)))  {
                return Integer.parseInt(list1.get(1)) - Integer.parseInt(list2.get(1));
            } else {
                return list1.get(0).compareToIgnoreCase(list2.get(0));
            }
        });

        String[] answer = new String[files.length];
        for (int i = 0; i < files.length; ++i) {
            StringBuilder cur = new StringBuilder();
            for (String parse : list.get(i)) {
                cur.append(parse);
            }
            answer[i] = cur.toString();
        }

        return answer;
    }
}