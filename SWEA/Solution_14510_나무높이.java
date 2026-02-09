import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	int N = Integer.parseInt(br.readLine());
        	int[] trees = new int[N];
        	st = new StringTokenizer(br.readLine());
        	int max = 0;
        	for(int i=0; i<N; i++) {
        		trees[i] = Integer.parseInt(st.nextToken());
        		max = Math.max(max, trees[i]);
        	}
        	
        	int one = 0;
        	int two = 0;
        	for(int i=0; i<N; i++) {
        		two += (max - trees[i]) / 2;
        		one += (max - trees[i]) % 2;
        	}
        	
        	int answer = grow(one, two);
        	sb.append("#" + tc + " " + answer + "\n");
        }
        
        System.out.println(sb.toString());
    }
    
    static int grow(int one, int two) {
    	int day = 0;
    	
    	if(one == 0 && two == 0) {
    		return day;
    	}
    	while(true) {
    		day++;
    		
    		int t1 = (day + 1) / 2;
    		int t2 = day / 2;
    		
    		if(t1 < one) continue;
    		
    		int rest = t1 - one;
    		
    		if(t2 + (rest / 2) >= two) {
    			return day;
    		}
    	}
    }
}
