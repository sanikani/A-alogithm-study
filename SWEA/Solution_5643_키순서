import java.io.*;
import java.util.*;

public class Solution {
	static ArrayList<int[]>[] graph;
	static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	int M = Integer.parseInt(br.readLine());
        	
        	graph = new ArrayList[N + 1];
        	for(int i=1; i<=N; i++) {
        		graph[i] = new ArrayList<>();
        	}
        	
        	for(int i=0; i<M; i++) {
        		st = new StringTokenizer(br.readLine());
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		//0 : in, 1 : out
        		graph[a].add(new int[] {b, 0});
        		graph[b].add(new int[] {a, 1});
        	}
        	
        	int answer = 0;
        	for(int i=1; i<=N; i++) {
        		if(isPossible(i)) {
        			answer++;
        		}
        	}
        	sb.append("#" + tc + " " + answer).append("\n");
        }
        System.out.println(sb.toString());
    }
    
    static boolean isPossible(int num) {
    	Queue<int[]> q = new ArrayDeque<int[]>();
    	boolean[] visited = new boolean[N + 1];
    	q.offer(new int[] {num, 0});
    	q.offer(new int[] {num, 1});
    	visited[num] = true;
    	
    	while(!q.isEmpty()) {
    		int[] cur = q.poll();
    		int n = cur[0];
    		int s = cur[1];
    		
    		for(int[] next : graph[n]) {
    			if(next[1] != s) continue;
    			if(visited[next[0]]) continue;
    			
    			q.offer(next);
    			visited[next[0]] = true;
    		}
    	}
    	
    	for(int i=1; i<=N; i++) {
    		if(!visited[i]) return false;
    	}
    	return true;
    }
}
