import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
  greedy algorithms
  첫 번째 -> 50개중 42개 틀림 -> 47분 걸림..
  이거 테케 틀린 이유가 아마 1이 이랑 2가 많아서 그런 듯 -> 즉 1과 2의 황밸이 필요함..
  틀린 테케 보니깐 1의 개수 : 9개 2의 개수 : 4개 임. 즉 1과 2의 불균형 때문인듯
  1시간 22분 -> gemini 돌림 이걸 생각해낼 수가 있나?..
 */

public class Solution_14510{
    static int[] tree;
    static int max, ans, sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int cnt1 = 0, cnt2 = 0;
            tree = new int[n];
            max = Integer.MIN_VALUE;
            ans = 0;
            sum = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                tree[i] = num;
                max = Math.max(max, num);
            }

            for(int i = 0; i < n; i++) {
                int diff = max - tree[i];
                if(diff != 0){
                    cnt1 += diff % 2;
                    cnt2 += diff / 2;
                }
            }
            
            while(cnt2 > cnt1 + 1){
                cnt2--;
                cnt1 += 2;
            }

            if(cnt1 > cnt2){
                ans = cnt1 * 2 - 1;
            }else{
                ans = cnt2*2;
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }
}
