package Acmicpc.zero.one;

import java.io.*;
import java.util.*;

/*위상정렬시 진입차수를 갱신할 때 해당 노드까지 도달할 수 있는 최장 시간을 기록
*
* */
public class Acmicpc01948 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());

    ArrayList<Edge>[] edgeList = new ArrayList[N + 1];
    ArrayList<Edge>[] reverseEdgeList = new ArrayList[N +1];
    int[][] inDegree = new int[N + 1][2];

    for (int i = 1; i <= N; i++) {
      edgeList[i] = new ArrayList<>();
      reverseEdgeList[i] = new ArrayList<>();
    }

    while (M-- > 0) {
      StringTokenizer tokenizer = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(tokenizer.nextToken());
      int to = Integer.parseInt(tokenizer.nextToken());
      int weight = Integer.parseInt(tokenizer.nextToken());

      edgeList[from].add(new Edge(to, weight));
      reverseEdgeList[to].add(new Edge(from, weight));
      inDegree[to][0]++;
    }

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(tokenizer.nextToken());
    int end = Integer.parseInt(tokenizer.nextToken());

    boolean[] isVisited = new boolean[N + 1];
    int next = start;
    while (N-- > 0) {
      isVisited[next] = true;

      for (Edge e : edgeList[next]) {
        inDegree[e.to][0]--;
        if (inDegree[e.to][1] < inDegree[next][1] + e.weight) {
          inDegree[e.to][1] = inDegree[next][1] + e.weight;
        }
      }

      for (int i = 1; i < inDegree.length; i++) {
        if (!isVisited[i] && inDegree[i][0] == 0) {
          next = i;
          break;
        }
      }
    }

    int edgeCount = 0;
    Arrays.fill(isVisited, false);
    Queue<Integer> queue = new ArrayDeque<>();
    queue.add(end);
    while (!queue.isEmpty()) {
      int cur = queue.poll();
      if (isVisited[cur]) continue;

      isVisited[cur] = true;

      for (Edge e : reverseEdgeList[cur]) {
        if (inDegree[e.to][1] == inDegree[cur][1] - e.weight) {
          edgeCount++;
          queue.add(e.to);
        }
      }
    }
    System.out.println(inDegree[end][1]);
    System.out.println(edgeCount);

  }

  static class Edge {
    int to, weight;

    public Edge(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }


  }
}
