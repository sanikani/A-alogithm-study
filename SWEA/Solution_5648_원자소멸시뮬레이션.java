import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    // 0 상, 1 하, 2 좌, 3 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            int N = Integer.parseInt(br.readLine());

            Queue<Atom> q = new ArrayDeque<>();
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                //0.5초 구간을 잡기 위해서 좌표를 두배로 확장
                q.offer(new Atom((x + 1000) * 2, (y + 1000) * 2, d, e));
            }

            int sum = 0;
            Map<String, List<Atom>> map = new HashMap<>();
            //좌표가 두배로 확장된만큼 시간도 두배로 확장
            //기존은 최대 2000초 -> 4000초
            for(int t=0; t < 4000; t++) {
                map.clear();
                if(q.isEmpty()) break;

                while(!q.isEmpty()) {
                    Atom a = q.poll();
                    a.x += dx[a.d];
                    a.y += dy[a.d];

                    //범위 밖을 벗어난 원자를 패스
                    if(a.x < 0 || a.y < 0 || a.x > 4000 || a.y > 4000) continue;
                    //key가 절대 중복될 수 없게 만드는 것이 포인트
                    String key = a.x + ", " + a.y;
                    map.computeIfAbsent(key, k -> new ArrayList<>()).add(a);
                }

                for (Map.Entry<String, List<Atom>> entry : map.entrySet()) {
                    if(entry.getValue().size() > 1) {
                        for(Atom atom : entry.getValue()) {
                            sum += atom.e;
                        }
                    } else {
                        q.offer(entry.getValue().get(0));
                    }
                }
            }
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        System.out.println(sb.toString());
    }

    static class Atom {
        int x, y, d, e;

        Atom(int x, int y, int d, int e) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.e = e;
        }
    }
}
