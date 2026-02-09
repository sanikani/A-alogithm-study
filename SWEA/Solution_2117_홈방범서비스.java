import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static int[][] map;
	static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=1; tc<=T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());

        	map = new int[N][N];
        	
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<N; c++) {
        			map[r][c] = Integer.parseInt(st.nextToken());
        		}
        	}
        	answer = 0;
        	for(int i=1; i<=N + 1; i++) {
        		find(i);
        	}
        	sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }
    
    static void find(int k) {
    	int price = k * k + (k - 1) * (k - 1);
    	
    	for(int r=0; r<N; r++) {
    		for(int c=0; c<N; c++) {
    			int count = build(r, c, k);
    			if((count * M) - price >= 0) {
    				answer = Math.max(answer, count);
    			}
    		}
    	}
    }
    
    static int build(int r, int c, int k) {
    	int count = 0;
    	for(int i=0; i<k; i++) {
    		for(int j=-(k - 1 - i); j <= (k - 1 - i); j++) {
    			if(isPossible(r + j, c + i)) {
    				if(map[r + j][c + i] == 1) count++; 
    			}
    			if(i != 0) {
    				if(isPossible(r + j, c - i)) {
        				if(map[r + j][c - i] == 1) count++;
        			}	
    			}
    		}
    	}
    	return count;
    }
    static boolean isPossible(int r, int c) {
    	return r >= 0 && c >= 0 && r < N && c < N;
    }
}


//1. 비용: K * K + (K - 1) * (K - 1)
//2. 손해를 보면 안됨
//3. 손해가 아닌 상태에서 서비스 가능한 최대 집의 수
//4.- (k -1) ~ (K -1) 범위에서 (k - 1 - i) 만큼 상하로 퍼지기
//5. K의 제한을 어케해야할까? 최대 비용이 몇인가? N의 크기가 5 이상 ~ 20 이하다.
