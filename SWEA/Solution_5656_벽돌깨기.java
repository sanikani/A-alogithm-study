import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, W, H;
    static int[][] map;
    static int answer;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            answer = W * H;
            map = new int[H][W];
            for (int r = 0; r < H; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < W; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, map);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void dfs(int idx, int[][] map) {
        //남은 벽돌 세기 & 최솟값 갱신
        if(idx == N) {
            int count = 0;
            for (int[] row : map) {
                for (int num : row) {
                    if (num != 0) {
                        count++;
                    }
                }
            }
            answer = Math.min(answer, count);
            return;
        }

        for (int i = 0; i < W; i++) {
            //1. 복사본 만들기
            int[][] copy = copyArray(map);
            //2. 구슬 떨어뜨리기
            boolean isHit = crash(i, copy);

            if(!isHit) {
                dfs(idx + 1, copy);
                continue; //중력 필요 없이 다음 걸로 진행
            }
            //3. 벽돌 내리기
            applyGravity(copy);
            dfs(idx + 1, copy);
        }
    }

    static boolean crash(int i, int[][] map) {
        int startR = -1;

        //처음 만나는 지점을 찾아서 시작점에 넣는다.
        for (int r = 0; r < H; r++) {
            if(map[r][i] != 0) {
                startR = r;
                break;
            }
        }

        if (startR == -1) return false;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startR, i, map[startR][i]});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int p = cur[2];

            for (int d = 0; d < 4; d++) {
                for (int k = 0; k < p; k++) {
                    int nr = r + dx[d] * k;
                    int nc = c + dy[d] * k;

                    //범위를 벗어나면 다른 방향 탐색
                    if(nr < 0 || nc < 0 || nr >= H || nc >= W) break;
                    if(map[nr][nc] > 0) {
                        //1보다 큰 벽돌과 부딪히면 연쇄 동작을 위해 큐 추가
                        if (map[nr][nc] > 1) {
                            q.offer(new int[]{nr, nc, map[nr][nc]});
                        }
                        map[nr][nc] = 0;
                    }
                }
            }
        }
        return true;
    }

    static void applyGravity(int[][] map) {
        for (int c = 0; c < W; c++) {
            int writeIdx = H - 1; //바닥부터

            //바닥부터 위로 올라가며 벽돌을 찾음
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] > 0) {
                    int temp = map[r][c];
                    map[r][c] = 0;
                    map[writeIdx][c] = temp;
                    writeIdx--;
                }
            }
        }
    }

    static int[][] copyArray(int[][] original) {
        int[][] res = new int[H][W];
        for (int r = 0; r < H; r++) {
            res[r] = Arrays.copyOf(original[r], original[r].length);
        }
        return res;
    }
}
//1. N번만 구슬을 쏠 수 있다.
//2. W x H 배열로 벽돌 정보
//3. 0은 빈공간, 이 외의 숫자는 벽돌
//4. 구슬은 좌우로만 움직임! 맨 위에 있는 벽돌만 깨뜨릴 수 있다. *
//5. 구슬이 명중한 벽돌은 상화좌우 (벽돌에 적힌 -1) 칸만큼 같이 제거된다. *
//6. 벽돌을 가장 많이 부시는 경우를 찾아야 함, 좌우로 움직이니까, 최대 12 ^ 4 승이므로 완탐시 문제 없어 보임
//7. 중복 조합으로 조합을 구해서 시뮬레이션 해보자!


