import java.util.*;
import java.io.*;

public class Solution_5643_키순서
{
    static int ans, N;
    static List<List<Integer>> li = new ArrayList<>(); // 정방향 인접리스트
    static List<List<Integer>> li2 = new ArrayList<>(); // 역방향 인접리스트
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visited; 

    public static void bfs(int start) {
        int small = 0; // 자신보다 작은 학생 수
        int tall = 0; // 자신보다 큰 학생 수

        Arrays.fill(visited,false);
        q.clear();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int x = q.poll();
            for(int i = 0; i < li.get(x).size(); i++) {
                int y = li.get(x).get(i);
                if(!visited[y]) {
                    q.offer(y);
                    visited[y] = true;
                    tall += 1;
                }
            }
        }


        // 역방향 그래프 탐색
        Arrays.fill(visited,false);
		q.clear();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int x = q.poll();
            for(int i = 0; i < li2.get(x).size(); i++) {
                int y = li2.get(x).get(i);
                if(!visited[y]) {
                    q.offer(y);
                    visited[y] = true;
                    small += 1;
                }
            }
        }

        // 순서가 확정된 학생 count + 1
        if(tall+small == N-1) ans++;
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++){
            ans = 0;
            N = Integer.parseInt(br.readLine()); // 학생 수
            int M = Integer.parseInt(br.readLine()); // 키 비교 횟수
            visited = new boolean[N+1];
            
            li.clear();
			li2.clear();
			for(int i=0; i<=N; i++){ // 인덱스 주의
			    li.add(new ArrayList<>());
    			li2.add(new ArrayList<>());
			}

            
            // 인접리스트 2개에 저장
            for(int m=0; m<M; m++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                li.get(a).add(b);
                li2.get(b).add(a);            
            }

            // 모든 학생에 대해, 자신보다 큰/작은 학생 수를 bfs로 탐색
            for(int i=1; i<=N; i++){
                bfs(i);
            }

            System.out.printf("#%d %d\n",t,ans);
        }
    }
}
