import java.util.*;
import java.io.*;

class Solution {
    static int maxConnected, answer, cnt, N;
    static int[][] map;
    static List<int[]> cores;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            maxConnected = 0;
            answer = Integer.MAX_VALUE;
            cores = new ArrayList<>();
            cnt = 0;
            for(int r=0; r<N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    //가장자리가 아닌 코어 위치 리스트 저장
                    if (map[r][c] == 1 && (r != 0 && c != 0 && r != N -1 && c != N - 1)) {
                        cnt++;
                        cores.add(new int[]{r, c});
                    }
                }
            }
            //1번 인덱스부터 연결 시도
            dfs(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }
    static void dfs(int idx, int connected, int sum) {
        //지금껄 연결해도 최대 연결로 갱신 되지 않는지 확인하기: 의미 없으니 원복
        if(maxConnected > connected + (cnt - idx)) {
            return;
        }

        if(idx == cnt) {
            if (connected > maxConnected) {
                maxConnected = connected;
                answer = sum;
            } else if (connected == maxConnected) {
                answer = Math.min(answer, sum);
            }
            return;
        }
        int r = cores.get(idx)[0];
        int c = cores.get(idx)[1];
        //상우하좌 순으로 탐색: 연결 시도
        for (int i=0; i<4; i++) {
            int cnt = 0;
            boolean flag = true;
            while(true) {
                cnt++;
                int nr = r + dx[i] * cnt;
                int nc = c + dy[i] * cnt;

                if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    cnt--;
                    break;
                }
                if(map[nr][nc] != 0) { // 코어 혹은 전선과 만나는 연결
                    flag = false;
                    break;
                }
            }
            if(flag) {
                //전선 처리
                for(int j=1; j<=cnt; j++) {
                    int nr = r + dx[i] * j;
                    int nc = c + dy[i] * j;
                    map[nr][nc] = 2;
                }
                dfs(idx + 1, connected + 1, sum + cnt);
                //원복하기
                for(int j=1; j<=cnt; j++) {
                    int nr = r + dx[i] * j;
                    int nc = c + dy[i] * j;
                    map[nr][nc] = 0;
                }
            }
        }

        //연결 안하는 상태도 탐색하기
        dfs(idx + 1, connected, sum);
    }
}
