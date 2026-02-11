import java.io.*;
import java.util.*;

public class Solution {
	//도는 방향
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};
    static int[][] map;
    static boolean[] used;
    static int answer, N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for(int r=0; r<N; r++) {
            	st = new StringTokenizer(br.readLine());
            	for(int c=0; c<N; c++) {
            		map[r][c] = Integer.parseInt(st.nextToken());
            	}
            }
            answer = 0;
            used = new boolean[101];
            for(int r=0; r<N; r++) {
            	for(int c=0; c<N; c++) {
            		for(int d=0; d<4; d++) {
            			dfs(r, c, r, c, d, 0, 0);
            		}
            	}
            }
            sb.append("#").append(tc).append(" ").append(answer == 0 ? -1 : answer).append("\n");
        }

        System.out.print(sb.toString());
    }
    
    static void dfs(int sr, int sc, int r, int c, int d, int sum, int turnCnt) {
    	if(sum != 0 && sr == r && sc == c && turnCnt == 3) {
    		answer = Math.max(answer, sum);
    		return;
    	}
    	
    	if(turnCnt > 3) {
    		return;
    	}
    	
    	//기존 방향 직진
    	int nr = r + dx[d];
    	int nc = c + dy[d];
    	if(isPossible(nr, nc)) {
    		used[map[nr][nc]] = true;
    		dfs(sr, sc, nr, nc, d, sum + 1, turnCnt);
    		used[map[nr][nc]] = false;
    	}
    	//방향 회전
    	int nd = (d + 1) % 4;
    	nr = r + dx[nd];
    	nc = c + dy[nd];
    	if(isPossible(nr, nc)) {
    		used[map[nr][nc]] = true;
    		dfs(sr, sc, nr, nc, nd, sum + 1, turnCnt + 1);
    		used[map[nr][nc]] = false;
    	}
    }
    
    static boolean isPossible(int r, int c) {
    	//범위를 벗어나면 false
    	if(r < 0 || c < 0 || r >= N || c >= N) return false;
    	//이미 사용한 숫자면 false
    	if(used[map[r][c]]) return false;
    	return true;
    }
}
//1. 사각형은 한쪽 방향으로만 돈다. 오른쪽으로 먼저 도나 왼쪽으로 먼저 도나는 상관 없음
//2. 같은 숫자가 중복 x
//3. 그러면 가능한게 현재 방향과 다음 방향뿐, 범위 밖이면 무조건 꺾거나 사라지거나
