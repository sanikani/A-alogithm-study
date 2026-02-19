import java.io.*;
import java.util.*;

public class Solution {
	static int N, answer;
	static int[][] S;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	S = new int[N][N];
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<N; c++) {
        			S[r][c] = Integer.parseInt(st.nextToken());
        		}
        	}
        	int mid = N / 2;
        	answer = Integer.MAX_VALUE;
        	combi(mid);
        	
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb.toString());
    }
    
    static void combi(int n) {
    	
    	for(int mask=0; mask<(1<<N); mask++) {
    		if(Integer.bitCount(mask) != n) continue;
    		
    		List<Integer> s1 = new ArrayList<>();
    		List<Integer> s2 = new ArrayList<>();
    		
    		for(int i=0; i<N; i++) {
    			if((mask & (1 << i)) != 0) {
    				s1.add(i);
    			} else {
    				s2.add(i);
    			}
    		}
    		answer = Math.min(answer, Math.abs(count(s1) - count(s2)));
    	}
    }
    
    static int count(List<Integer> s) {
    	int sum = 0;
    	for(int i=0; i<s.size(); i++) {
    		for(int j=0; j<s.size(); j++) {
    			if(i == j) continue;
    			sum += S[s.get(i)][s.get(j)];
    		}
    	}
    	return sum;
    }

}
//1. 두명의 손님에게 N /2 개씩 나누어 요리를 진행, N은 짝수
//2. 비슷한 음식을 만들기 위해 A, B가 배분이 중요
//3. 시너지가 존재함, i,j가  시너지의 차가 최소가 되어야 한다.
