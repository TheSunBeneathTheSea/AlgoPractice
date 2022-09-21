package Acmicpc.zero.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Acmicpc01238 {
  public static int solve(ArrayList<Edge2>[] edgeList, ArrayList<Edge2>[] reverseList, int start) {
    int[] distance = new int[edgeList.length];
    int[] reverseDistance = new int[edgeList.length];
    boolean[] isVisited = new boolean[edgeList.length];

    Arrays.fill(distance, 987654321);
    Arrays.fill(reverseDistance, 987654321);

    distance[start] = 0;
    reverseDistance[start] = 0;

    PriorityQueue<Edge2> pq = new PriorityQueue<>();

    pq.add(new Edge2(start, start, 0));

    while (!pq.isEmpty()) {
      Edge2 cur = pq.poll();

      if (!isVisited[cur.to] || distance[cur.to] > distance[cur.from] + cur.distance) {
        isVisited[cur.to] = true;

        if (distance[cur.to] > distance[cur.from] + cur.distance) {
          distance[cur.to] = distance[cur.from] + cur.distance;
        }

        pq.addAll(edgeList[cur.to]);
      }
    }

    pq.clear();
    isVisited = new boolean[edgeList.length];

    pq.add(new Edge2(start, start, 0));

    while (!pq.isEmpty()) {
      Edge2 cur = pq.poll();

      if (!isVisited[cur.to] || reverseDistance[cur.to] > reverseDistance[cur.from] + cur.distance) {
        isVisited[cur.to] = true;

        if (reverseDistance[cur.to] > cur.distance) {
          reverseDistance[cur.to] = reverseDistance[cur.from] + cur.distance;
        }

        pq.addAll(reverseList[cur.to]);
      }
    }

    int answer = 0;

    for (int i = 1; i < edgeList.length; i++) {
      if (answer < distance[i] + reverseDistance[i]) {
        answer = distance[i] + reverseDistance[i];
      }
    }

    return answer;
  }

  static class Edge2 implements Comparable<Edge2> {
    int from;
    int to;
    int distance;

    public Edge2(int from, int to, int distance) {
      this.from = from;
      this.to = to;
      this.distance = distance;
    }

    @Override
    public int compareTo(Edge2 e) {
      return this.distance - e.distance;
    }
  }
}



