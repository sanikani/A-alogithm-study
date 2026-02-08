import java.util.*;
import java.io.*;

public class Test2_박소영 {

    static int T,N,ans;
    static boolean[] visited;
    static int[] arr;
    static int[] maxHeight = new int[2];
 
    public static void f(int cnt, int water, int d, int n, int[] arr2){

        if(arr2[cnt] == maxHeight[0]) { // 지금 물 준 나무가 최대높이 도달하면
            visited[cnt] = true;    // 더 이상 물주면 x
            n++;
        }


        // 모든 나무의 높이가 같아지면 종료
        // (어떤 나무가 최대 높이와 같아지면 n++을 했으므로, n == N 이란 건 N개의 모든 나무가 최대높이 도달했다는 뜻)
        if(n == N) {
            ans = Math.min(d, ans); // 기존의 최소일수(ans)와 현재 계산한 일수(d)를 비교해 더 작은 값으로 갱신
            return;
        }

        for(int i = 0; i < N; i++){
            if(visited[i]) continue;
            

            if(water == 1){
                if(d % 2 == 1 && arr2[i]+1 <= maxHeight[0] ) {
                    arr2[i] += 1;
                    f(i, 1, d+1, n, arr2); // 물을 주고     날짜 지남
                    f(i, 0, d+1, n, arr2); // 물을 주지 않고 날짜 지남
                    arr2[i] -= 1;
                }
                if(d % 2 == 0 && arr2[i]+2 <= maxHeight[0] ) {
                    arr2[i] += 2;
                    f(i,1, d+1, n, arr2); // 물을 주고     날짜 지남
                    f(i,0, d+1, n, arr2); // 물을 주지 않고 날짜 지남
                    arr2[i] -= 2;
                }

            }
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine());
    
        for(int tcase=1; tcase<=T; tcase++){
            st = new StringTokenizer(br.readLine());

            N= Integer.parseInt(st.nextToken()); // 나무 갯수
            maxHeight[0] = 0; // 인덱스 0 : 최대 나무높이, 인덱스 1: 최대인 나무의 위치
            maxHeight[1] = -1;
            ans = Integer.MAX_VALUE; // 최소 일 수 (초기값은 MAX)
            arr = new int[N];
            visited = new boolean[N];
            
            st = new StringTokenizer(br.readLine());
            // 나무높이들을 배열에 저장할때, 최댓값도 구해놓기
            for(int i=0; i<N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                if(arr[i] > maxHeight[0]) {
                    maxHeight[0] = arr[i];
                    maxHeight[1] = i;
                }
            }
            
            visited[maxHeight[1]] = true; // i번째 나무는 최대높이이므로 물을 주면 안됨

            f(0,1,1,1,arr); // 물 O, d=1일부터 시작, 최대높이나무 수 n=1
            f(0,0,1,1,arr); // 물 X, d=1일부터 시작, 최대높이나무 수 n=1

            System.out.printf("#%d %d\n",tcase,ans);
        }
        
    }
}