import java.io.*;
import java.util.*;

public class Solution {
	static int M, A;
	static BC[] map;
	static int[] dx = {0, 0, 1, 0, -1};
	static int[] dy = {0, -1, 0, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	M = Integer.parseInt(st.nextToken());
        	A = Integer.parseInt(st.nextToken());
        	int answer = 0;
        	int[][] move = new int[2][M + 1];
        	move[0][0] = 0;
        	move[1][0] = 0;
        	for(int i=0; i<2; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=1; j<M + 1; j++) {
        			move[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	map = new BC[A + 1];
        	for(int i=1; i<=A; i++) {
        		st = new StringTokenizer(br.readLine());
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		int d = Integer.parseInt(st.nextToken());
        		int p = Integer.parseInt(st.nextToken());
        		
        		map[i] = new BC(i, x, y, d, p);
        	}
        	List<Integer> userA = new ArrayList<>();
        	List<Integer> userB = new ArrayList<>();
        	int ax = 1, ay = 1;
        	int bx = 10, by = 10;
        	for(int i=0; i<M + 1; i++) {
        		userA.clear();
        		userB.clear();
        		for(int j=0; j<2; j++) {
        			if(j == 0) { //userA
        				ax += dx[move[j][i]];
        				ay += dy[move[j][i]];
        				
        				for(int k=1; k<=A; k++) {
        					int d = Math.abs(ax - map[k].x) + Math.abs(ay - map[k].y);
        					if(d <= map[k].d) {
        						userA.add(map[k].n);
        					}
        				}
        			} else { //userB
        				bx += dx[move[j][i]];
        				by += dy[move[j][i]];
        				for(int k=1; k<=A; k++) {
        					int d = Math.abs(bx - map[k].x) + Math.abs(by - map[k].y);
        					if(d <= map[k].d) {
        						userB.add(map[k].n);
        					}
        				}
        			}
        		}
        		if(userA.isEmpty()) {
        			userA.add(-1);
        		}
        		if(userB.isEmpty()) {
        			userB.add(-1);
        		}
        		
        		int max = combi(userA, userB);
        		answer += max;
        	}
        	
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb.toString());
    }
    
    static int combi(List<Integer> userA, List<Integer> userB) {
    	int max = 0;
    	for(int i=0; i<userA.size(); i++) {
    		for(int j=0; j<userB.size(); j++) {
    			int a = userA.get(i);
    			int b = userB.get(j);
    			int sum = 0;
    			if(a == - 1 && b == - 1) {
    				continue;
    			} else if(a == -1) {
    				sum += map[b].p;
    			} else if(b == - 1) {
    				sum += map[a].p;
    			} else if(a == b) {
    				sum += map[a].p;
    			} else {
    				sum += map[a].p;
    				sum += map[b].p;
    			}
    			max = Math.max(max, sum);
    		}
    	}
    	return max;
    }
    static class BC {
    	int n, x, y, d, p;
    	
    	BC (int n, int x, int y, int d, int p){
    		this.n = n;
    		this.x = x;
    		this.y = y;
    		this.d = d;
    		this.p = p;
    	}
    }
}
//1. 좌표 놀음이라고 생각하기
//2. |X0 - X1| + |Y0 - Y1| = D 가 핵심
//3. 겹칠 때 처리를 어떻게 할것인지가 핵심
//4. 유저 리스트에 BC를 담아둔다.
//5. 조합을 만든다? 이게 단순은 함. visited 같은걸로 계산하고
//6. 정렬을 굳이 해야할까? 차피 조합은 해봐야 하는 거 아님?
//7. 정렬을 해서 A먼저 고르고 남은 거중에 B고르면 되는데 만약에 B가 하나밖에 선택지가 없으면 그걸 골라야 함
//8. 정렬을 해보자.
