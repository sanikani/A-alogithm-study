import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, R, C, L;
	static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	R = Integer.parseInt(st.nextToken());
        	C = Integer.parseInt(st.nextToken());
        	L = Integer.parseInt(st.nextToken());
        	map = new int[N][M];
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<M; c++) {
        			map[r][c] = Integer.parseInt(st.nextToken());
        		}
        	}
        	//1: 상하좌우, 2: 상하, 3: 좌우, 4: 상우, 5: 하우, 6:하좌, 7: 상좌
        	int answer = bfs();
        	sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }
    static int bfs() {
    	int count = 0;
    	 boolean[][] visited = new boolean[N][M];
    	
    	Queue<int[]> q = new ArrayDeque<>();
    	q.offer(new int[] {R, C, 1});
    	visited[R][C] = true;
    	count++;
    	int[] dx = {-1, 0, 1, 0};
    	int[] dy = {0, -1, 0, 1};
    	int[][] directions = {
    			{},
    			{0, 1, 2, 3},//1 상좌하우
    			{0, 2},//2 상하
    			{1, 3},//3 좌우
    			{0, 3},//4 상우
    			{2, 3},//5 하우
    			{1, 2},//6 하좌
    			{0, 1}//7 상좌
    			};
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int r = cur[0];
    		int c = cur[1];
    		int t = cur[2];
    		
    		if(t == L) {
    			continue;
    		}
    		
    		int[] direction = directions[map[r][c]];
    		for(int i=0; i<direction.length; i++) {
    			int nr = r + dx[direction[i]];
    			int nc = c + dy[direction[i]];
    			
    			if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
    			if(map[nr][nc] == 0) continue;
    			if(visited[nr][nc]) continue;
    			
    			int[] nDirection = directions[map[nr][nc]];
    			boolean flag = false;
    			for(int j=0; j<nDirection.length; j++) {
					if(nDirection[j] == (direction[i] + 2) % 4) {
						flag = true;
						break;
					}
    			}
    			
    			if(flag) {
    				visited[nr][nc] = true;
        			q.offer(new int[] {nr, nc, t + 1});
        			count++;
    			}
    		}
    	}
    	return count;
    }
}
