import java.io.*;
import java.util.*;

public class Solution {
	static int[][] map;
	static boolean[][] visited;
	static int N, K, answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	K = Integer.parseInt(st.nextToken());
        	
        	map = new int[N][N];
        	visited = new boolean [N][N];
        	
        	int max = 0;
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<N; c++) {
        			map[r][c] = Integer.parseInt(st.nextToken());
        			max = Math.max(max, map[r][c]);
        		}
        	}
        	
        	ArrayList<int[]> starts = new ArrayList<>();
        	for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			if(map[r][c] == max) starts.add(new int[] {r, c});
        		}
        	}
        	
        	answer = 0;
        	for(int[] start : starts) {
        		dfs(start[0], start[1], 0, 1);
        	}
        	sb.append("#" + tc + " " + answer + "\n");
        }
        System.out.println(sb.toString());
    }
    static void dfs(int r, int c, int k, int cnt) {
    	answer = Math.max(answer, cnt);
    	visited[r][c] = true;
    	int[] dx = {1, 0, -1, 0};
    	int[] dy = {0, 1, 0, -1};
    		
		for(int d=0; d<4; d++) {
			int nr = r + dx[d];
			int nc = c + dy[d];
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
			if(map[nr][nc] >= map[r][c]) {
				int temp = map[nr][nc];
				if(k == 0 && map[nr][nc] - K < map[r][c]) {
					if(visited[nr][nc]) continue;
					
					map[nr][nc] = map[r][c] - 1;
					dfs(nr, nc, 1, cnt + 1);
					map[nr][nc] = temp;
				}
			} else {
				if(visited[nr][nc]) continue;
				dfs(nr, nc, k, cnt + 1);
			}
		
		}
		visited[r][c] = false;
	}
}
