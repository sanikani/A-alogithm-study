import java.io.*;
import java.util.*;

public class Solution {
	static int[][] map;
	static int N, X, answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	X = Integer.parseInt(st.nextToken());
        	
        	map = new int[N][N];
        	answer = 0;
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<N; c++) {
        			map[r][c] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	for(int i=0; i<N; i++) {
        		int[] row = new int[N];
        		int[] col = new int[N];
        		for(int j=0; j<N; j++) {
        			row[j] = map[i][j];
        			col[j] = map[j][i];
        		}
        		if(solution(row)) answer++;
        		if(solution(col)) answer++;
        	}
            
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb.toString());
    }
    
    static boolean solution(int[] line) {
    	boolean[] used = new boolean[N];
    	
    	for(int i=0; i<N-1; i++) {
    		int cur = line[i];
    		int next = line[i + 1];
    		int diff = next - cur;
    		
    		if(diff == 0) continue;
    		
    		if(Math.abs(diff) > 1) return false;
    		
    		if(diff == 1) {//오르막
    			//X칸만큼 확인하기
    			for(int j=0; j<X; j++) {
    				int idx = i - j;
    				if(idx < 0) return false;
    				if(line[idx] != cur) return false;
    				if(used[idx]) return false;
    				used[idx] = true;
    			}
    		}
    		
    		if(diff == -1) {//내리막
    			for(int j=1; j<=X; j++) {
    				int idx = i + j;
    				if(idx >= N) return false;
    				if(line[idx] != next) return false;
    				if(used[idx]) return false;
    				used[idx] = true;
    			}
    		}
    	}
    	return true;
    }
}
//1. 같은 구간을 세야 함, 내려가면 그동안의 카운트를 날리고, 같은 구간이 2번 반복되어야 함, 단 1만 내려가야 함.
//2. 올라가면 그동안 쌓은 카운트가 2칸인지 체크하고 업카운트를 내려야 함
//3. 내려가면 날린 거에서 다시 세야 함
