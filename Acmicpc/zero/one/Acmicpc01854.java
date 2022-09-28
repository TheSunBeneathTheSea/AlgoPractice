package Acmicpc.zero.one;

import java.io.*;
import java.util.*;

public class Acmicpc01854 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());
    int K = Integer.parseInt(tokenizer.nextToken());

    ArrayList<Node>[] adjList = new ArrayList[N + 1];

    for (int i = 1; i < adjList.length; i++) {
      adjList[i] = new ArrayList<>();
    }

    while (M-- > 0) {
      tokenizer = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(tokenizer.nextToken());
      int to = Integer.parseInt(tokenizer.nextToken());
      int weight = Integer.parseInt(tokenizer.nextToken());

      adjList[from].add(new Node(to, weight));
    }

    PriorityQueue<Integer>[] pathQueue = new PriorityQueue[N + 1];

    for (int i = 1; i < pathQueue.length; i++) {
      pathQueue[i] = new PriorityQueue<>(Comparator.reverseOrder());
    }
    PriorityQueue<Node> pq = new PriorityQueue<>();

    pq.add(new Node(1, 0));
    pathQueue[1].add(0);
    while (!pq.isEmpty()) {
      Node cur = pq.poll();

      for (Node n : adjList[cur.no]) {
        int nextWeight = cur.weight + n.weight;

        if (pathQueue[n.no].size() < K) {
          pathQueue[n.no].add(nextWeight);
        } else if (pathQueue[n.no].peek() > nextWeight) {
          pathQueue[n.no].poll();
          pathQueue[n.no].add(nextWeight);
        } else continue;
        pq.add(new Node(n.no, nextWeight));
      }
    }

    for (int i = 1; i <= N; i++) {
      if (pathQueue[i].size() == K) bw.write(pathQueue[i].peek() + "\n");
      else bw.write("-1\n");
    }
    bw.flush();
    bw.close();
    br.close();
  }

  static class Node implements Comparable<Node>{
    int no, weight;

    Node(int no, int weight) {
      this.no = no;
      this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
      return this.weight - o.weight;
    }
  }
}
