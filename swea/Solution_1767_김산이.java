package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1767_김산이 {
	
	static int[][] board;
	static List<int[]> cores;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int N;
	static int maxConnected;
	static int result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			cores = new ArrayList<>();
			maxConnected = 0;
			result = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 1) {
						if (!(i == 0 || i == N-1 || j == 0 || j == N-1)) {
					        cores.add(new int[]{i, j});
					    }
					}
				}
			}
			
			dfs(0, 0, 0);
			sb.append('#').append(test_case).append(' ').append(result).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int l, int connected, int wireSize) {
		
		//모두 확인한 경우
		if(l==cores.size()) {
			if (connected > maxConnected) {
			    maxConnected = connected;
			    result = wireSize;
			//같다면 전선 수 최소
			} else if (connected == maxConnected) {
			    result = Math.min(result, wireSize);
			}
			return;
		}
		
		int[] core = cores.get(l);

		//나머지 모든 코어를 연결해도 최대값보다 작으면 return
		if(connected + (cores.size() - l) < maxConnected) return;
		
		//4방향 확인하며 연결 가능하면 연결
	    for (int d = 0; d < 4; d++) {
	    	if(!canConnect(core, dr[d], dc[d])) continue;
            int wire = connect(core, dr[d], dc[d]);
            dfs(l + 1, connected + 1, wireSize + wire);
            disconnect(core, dr[d], dc[d]);
	    }
	    
	    //아무것도 연결 안한 경우
	    dfs(l + 1, connected, wireSize);
	}

	private static void disconnect(int[] core, int dr, int dc) {
		int nr = core[0] + dr;
		int nc = core[1] + dc;
		while(canMove(nr, nc)) {
			board[nr][nc] = 0;
			nr += dr;
			nc += dc;
		}
	}

	private static int connect(int[] core, int dr, int dc) {
		int nr = core[0] + dr;
		int nc = core[1] + dc;
		int cnt = 0;
		
		while(canMove(nr, nc)) {
			board[nr][nc] = 1;
			nr += dr;
			nc += dc;
			cnt++;
		}
		return cnt;
	}

	private static boolean canMove(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

	private static boolean canConnect(int[] core, int dr, int dc) {
		int nr = core[0] + dr;
		int nc = core[1] + dc;
		
		while(canMove(nr, nc)) {
			if(board[nr][nc] == 1) return false;
			nr += dr;
			nc += dc;
		}
		return true;
	}

	static boolean isConnected(int[] core) {
		return core[0] == 0 || core[0] == N - 1 || core[1] == 0 || core[1] == N - 1;
	}
}
