package programmers;

public class 행렬테두리회전하기 {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] arr = new int[rows + 1][columns + 1];

        int cnt = 1;
        for(int i = 1; i <= rows; i ++){
            for(int j = 1; j <= columns ; j++){
                arr[i][j] = cnt++;
            }
        }

        for(int q = 0; q < queries.length; q++){
            int r = queries[q][0];
            int c = queries[q][1];
            int temp = arr[r][c];
            int min = arr[r][c];
            for(;r < queries[q][2]; r++){
                arr[r][c] = arr[r+1][c];
                min = Math.min(arr[r][c], min);
            }
            for(; c < queries[q][3] ; c++){
                arr[r][c] = arr[r][c+1];
                min = Math.min(arr[r][c], min);
            }
            for(; r > queries[q][0]; r--){
                arr[r][c] = arr[r-1][c];
                min = Math.min(arr[r][c], min);
            }
            for(; c > queries[q][1]; c--){
                arr[r][c] = arr[r][c-1];
                min = Math.min(arr[r][c], min);
            }
            arr[r][c+1] = temp;
            min = Math.min(arr[r][c+1], min);
            answer[q] = min;
        }
        return answer;
    }
}