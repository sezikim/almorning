def solution(rows, columns, queries):
    answer = []
    
    board = [[j*columns+i for i in range(1,columns+1)] for j in range(rows)]
    for i in range(len(queries)):
        rotate(board,rows, columns, queries[i])
        answer.append(findMinValueInOtherPlace(board,rows,columns,queries[i]))
        
    return answer

def rotate(board, rows, columns, query):
    min = rows*columns
    x1,y1, x2, y2 = query
    
    north = board[x1-1][:]
    south = board[x2-1][:]
    east = [board[i][y2-1] for i in range(rows)]
    west = [board[i][y1-1] for i in range(rows)]
    

    for i in range(y1,y2):
        board[x1-1][i] = north[i-1]
        board[x2-1][i-1] = south[i]

    
    for i in range(x1,x2):
        board[i][y2-1] = east[i-1]
        board[i-1][y1-1] = west[i]
    

def findMinValueInOtherPlace(board,rows,columns, query):
    minValue = rows*columns
    x1,y1,x2,y2 = query
    for i in range(rows):
        for j in range(columns):
            if ((i == x1-1 or i ==x2-1) and y1-1<=j<=y2-1) or ((j==y1-1 or j==y2-1) and x1-1<=i<=x2-1):
                minValue = min(minValue, board[i][j])
    return minValue
