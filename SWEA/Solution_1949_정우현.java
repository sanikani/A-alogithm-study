package juh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1949_정우현 {
	private static int N,K,maxH,maxL;
	private static int[][] mountine;
	private static boolean[][] visited;
	private static boolean isUsed;
	private static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			maxH = 0;
			maxL = 0;
			mountine = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					mountine[i][j] = Integer.parseInt(st.nextToken());
					maxH = Math.max(maxH, mountine[i][j]);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(mountine[i][j] == maxH) {
						visited = new boolean[N][N];
						isUsed = false;
						visited[i][j] = true;
						findPath(i,j,1);
					}
				}
			}
			
			System.out.println("#"+test+" "+maxL);
			
		}
	}
	
	public static void findPath(int x, int y, int step) {	
		for(int i=0; i<4; i++) {
			int nx = x+deltas[i][0];
			int ny = y+deltas[i][1];
			if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny] && mountine[nx][ny] < mountine[x][y]) {
				visited[nx][ny] = true;
				step++;
				findPath(nx,ny,step);
				step--;
				visited[nx][ny] = false;
			} else {
				if(!isUsed) {
					for(int k=1; k<=K; k++) {
						if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny] && mountine[nx][ny]-k < mountine[x][y]) {
							visited[nx][ny] = true;
							mountine[nx][ny] -= k;
							isUsed = true;
							step++;
							findPath(nx,ny,step);
							step--;
							mountine[nx][ny] += k;
							isUsed = false;
							visited[nx][ny] = false;
						}
					}
				}
			}
		}
		maxL=Math.max(step, maxL);
		
	}

}
