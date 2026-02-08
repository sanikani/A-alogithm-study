package ssafy.solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_14510_최동준 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
//		int t = 30;
		StringBuilder sb = new StringBuilder();
		
		for(int test=1; test<=t; test++) {			
			int N = Integer.parseInt(br.readLine()); //초기 나무의 개수
			
			int max = 0;
			int[] tree = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) { // 초기 나무 값 한줄로
				tree[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, tree[i]);
			}
			
//			홀수와 짝수를 체크하기
//			같아지게 하기 위해서는 각 나무마다 최대 높이가 되게 하기 위해서는 얼마나 최소 값으로 할 수 있는지 구하기?
//			하루에 한 나무에 물을 줄 수 있음.(홀수 날에는 1, 짝수날에는 2 가능)
//			얼마나 최소 값으로 할지는 홀수면 홀수+ 나머지 짝수로, 짝수면 걍 짝수로(만약 홀수인게 없으면 홀수날을 2개씩 합쳐 짝수로 판단)
//			현재 홀수가 짝수개로 필요해서 3일에 4일이아닌 3일이 되어리는 경우 발생
//			필요한 짝수날 - 홀수날
			
			int ans1 = calc(tree, N, max);
			int ans2 = calc(tree, N, max + 1);
			
			
//			계속 돌릴 수는 없으니까 어떤 정보를 얻고 어떻게 조합해야할지 생각 필요
//			1. 홀수인지 짝수 인지 판단(홀수면 홀수날을 투자해야함.)
//			2. 2로 나눴을 때 2가 몇개 필요한지 확인(몫이 나오면 그게 필요한 날짜(짝수날))
//			3. 투자해야하는 짝수날+(짝수날)
//			총 날짜 
//			짝수날*2 => 홀수날을 아예 안쓴다는 가정
//			짝수날/2 => 홀수날을 2개 모으면 짝수날과 동일
//			저 두개를 적절히 조합해야함(어케 조합할까? 3일마다 4 성장이 생기는 거니까 총 성장 필요한 값 / 4를 하고 나머지를 알아서 조합 시키면?)
			
			sb.append("#").append(test).append(" ").append(Math.min(ans1, ans2)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int calc(int[] tree, int N, int target) {
	    int need1 = 0;
	    int need2 = 0;

	    for (int i = 0; i < N; i++) {
	        int diff = target - tree[i];
	        need2 += diff / 2;
	        need1 += diff % 2;
	    }

	    int day = 0;
	    while (true) {
	        int odd = (day + 1) / 2;
	        int even = day / 2;

	        if (odd >= need1 && even + (odd - need1) / 2 >= need2) {
	            return day;
	        }
	        day++;
	    }
	}

}
