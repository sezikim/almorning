import java.util.*;

public class Main21608 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int n;
    static HashMap<Integer, HashSet<Integer>> like;
    static int[][] classRoom;
    static int[] students;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        like = new HashMap<>();
        classRoom = new int[n][n];
        students = new int[n * n];

        int cur = 0;
        for (int i = 0; i < n * n; ++i) {
            cur = sc.nextInt();
            students[i] = cur;
            like.put(cur, new HashSet<>());
            for (int j = 0; j < 4; ++j) {
                like.get(cur).add(sc.nextInt());
            }
        }

        for (int i = 0; i < n * n; ++i) {
            int curStudent = students[i];

            int max = 0;
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n; ++x) {
                    if (classRoom[y][x] != 0) continue;
                    max = Math.max(checkPoint(y, x, curStudent), max);
                }
            }

            List<Point> maxPoint = new ArrayList<>();
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < n; ++x) {
                    if (classRoom[y][x] != 0) continue;
                    if (max == checkPoint(y, x, curStudent)) maxPoint.add(new Point(y, x));
                }
            }

            if (maxPoint.size() == 1) {
                Point p = maxPoint.get(0);
                classRoom[p.y][p.x] = curStudent;
                continue;
            }

            int emptyCount = 0;
            for (Point p : maxPoint) {
                emptyCount = Math.max(checkEmpty(p.y, p.x), emptyCount);
            }

            List<Point> emptyPoint = new ArrayList<>();
            for (Point p : maxPoint) {
                if (emptyCount == checkEmpty(p.y, p.x)) emptyPoint.add(new Point(p.y, p.x));
            }

            if (emptyPoint.size() == 1) {
                Point p = emptyPoint.get(0);
                classRoom[p.y][p.x] = curStudent;
                continue;
            }

            Point minY = emptyPoint.get(0);
            for (Point p : emptyPoint) {
                if (p.y < minY.y) {
                    minY = p;
                }
            }

            List<Point> ySelectList = new ArrayList<>();
            for (Point p : emptyPoint) {
                if (p.y == minY.y) {
                    ySelectList.add(p);
                }
            }

            if (ySelectList.size() == 1) {
                Point p = ySelectList.get(0);
                classRoom[p.y][p.x] = curStudent;
                continue;
            }

            ySelectList.sort(Comparator.comparingInt(point -> point.x));
            Point p = ySelectList.get(0);
            classRoom[p.y][p.x] = curStudent;
        }

        int sum = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                switch (checkPoint(i, j, classRoom[i][j])) {
                    case 0:
                        sum += 0;
                        break;
                    case 1:
                        sum += 1;
                        break;
                    case 2:
                        sum += 10;
                        break;
                    case 3:
                        sum += 100;
                        break;
                    case 4:
                        sum += 1000;
                        break;
                }
            }
        }

        System.out.println(sum);
    }

    static int checkPoint(int y, int x, int student) {
        int point = 0;

        for (int i = 0; i < 4; ++i) {
            if (y + dy[i] >= 0
                    && y + dy[i] < n
                    && x + dx[i] >= 0
                    && x + dx[i] < n
                    && like.get(student).contains(classRoom[y + dy[i]][x + dx[i]])) {
                point++;
            }
        }

        return point;
    }

    static int checkEmpty(int y, int x) {
        int empty = 0;

        for (int i = 0; i < 4; ++i) {
            if (y + dy[i] >= 0
                    && y + dy[i] < n
                    && x + dx[i] >= 0
                    && x + dx[i] < n
                    && classRoom[y + dy[i]][x + dx[i]] == 0) {
                empty++;
            }
        }

        return empty;
    }

    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
