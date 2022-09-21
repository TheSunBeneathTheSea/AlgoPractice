package Acmicpc.zero.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Acmicpc01504 {
  public static ArrayList<Integer[]>[] edgeList;
  public static final int INF = 222222222;
  public static int[] distFrom1;
  public static int[] distFromN;
  public static int shortestPathBetweenTargets = INF;

  public static int solve(ArrayList<Integer[]>[] edges, int target1, int target2) {
    edgeList = edges;

    dijkstra(false, 1, target2);
    dijkstra(false, edges.length - 1, target2);
    dijkstra(true, target1, target2);

    int result = Math.min(
            distFrom1[target1] + shortestPathBetweenTargets + distFromN[target2],
            distFrom1[target2] + shortestPathBetweenTargets + distFromN[target1]
    );

    return result >= INF ? -1 : result;
  }

  public static void dijkstra(boolean findingPathBetweenTargets, int start, int target2) {
    int[] distances = new int[edgeList.length];

    Arrays.fill(distances, INF);
    distances[start] = 0;

    PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
      @Override
      public int compare(Integer[] o1, Integer[] o2) {
        return o1[2] - o2[2];
      }
    });

    pq.addAll(edgeList[start]);

    while (!pq.isEmpty()) {
      Integer[] cur = pq.poll();
      int from = cur[0];
      int to = cur[1];
      int dist = cur[2];

      if (distances[to] > distances[from] + dist) {
        distances[to] = distances[from] + dist;

        for (Integer[] edge : edgeList[to]) {
          if (distances[edge[1]] > distances[edge[0]] + edge[2]) {
            pq.add(edge);
          }
        }
      }
    }

    if (findingPathBetweenTargets) {
      shortestPathBetweenTargets = distances[target2];
    } else if (start == 1) {
      distFrom1 = distances;
    } else if (start == edgeList.length - 1) {
      distFromN = distances;
    }
  }
}
