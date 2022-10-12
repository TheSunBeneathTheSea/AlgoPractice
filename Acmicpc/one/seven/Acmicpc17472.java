package Acmicpc.one.seven;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 뭉쳐있는 땅을 섬으로 그룹화 (BFS)
* 그룹화된 섬 사이에 생성 가능한 에지들을 찾기
* 크루스칼 알고리즘으로 에지 선택
* */

public class Acmicpc17472 {
  static int[][] map;
  static boolean[][] isVisited;
  static int[] islandGroups;
  static ArrayList<Integer[]>[] islands = new ArrayList[7];
  static PriorityQueue<Edge> edges = new PriorityQueue<>();
  static int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    for (int i = 0; i < islands.length; i++) {
      islands[i] = new ArrayList<>();
    }

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());

    map = new int[N][M];

    for (int i = 0; i < N; i++) {
      tokenizer = new StringTokenizer(br.readLine());

      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(tokenizer.nextToken());
      }
    }

    isVisited = new boolean[map.length][map[0].length];
    int groupNo = 1;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == 0 || isVisited[i][j]) continue;
        groupingIslands(new Integer[] {i, j}, groupNo++);
      }
    }

    for (int i = 1; i < islands.length; i++) {
      for (Integer[] cur : islands[i]) {
        findPossibleEdges(cur);
      }
    }

    islandGroups = new int[groupNo];
    for (int i = 1; i < islandGroups.length; i++) {
      islandGroups[i] = i;
    }

    int count = 0;
    int sumOfLength = 0;
    while (!edges.isEmpty() && count < groupNo - 2) {
      Edge cur = edges.poll();

      if (find(cur.from) == find(cur.to)) continue;

      union(cur.from, cur.to);
      count++;
      sumOfLength += cur.length;
    }
    if (count < groupNo - 2 || sumOfLength == 0) {
      System.out.println(-1);
      return;
    }
    System.out.println(sumOfLength);
  }

  public static void groupingIslands(Integer[] start, int groupNo) {
    ArrayDeque<Integer[]> queue = new ArrayDeque<>();

    queue.push(start);
    isVisited[start[0]][start[1]] = true;
    while (!queue.isEmpty()) {
      Integer[] cur = queue.poll();
      map[cur[0]][cur[1]] = groupNo;
      islands[groupNo].add(cur);

      for (int i = 0; i < 4; i++) {
        Integer[] next = {cur[0] + moves[i][0], cur[1] + moves[i][1]};

        if (next[0] < 0 || next[0] >= map.length || next[1] < 0 || next[1] >= map[0].length) continue;
        if (map[next[0]][next[1]] == 0 || isVisited[next[0]][next[1]]) continue;

        queue.push(next);
        isVisited[next[0]][next[1]] = true;
      }
    }
  }

  public static void findPossibleEdges(Integer[] cur) {
    for (int i = 1; i < islands.length; i++) {
      if (i == map[cur[0]][cur[1]]) continue;

      for (Integer[] target : islands[i]) {
        int length;

        if (cur[0] == target[0]) {
          length = Math.abs(cur[1] - target[1]) - 1;

          for (int j = Math.min(cur[1], target[1]) + 1; j < Math.max(cur[1], target[1]); j++) {
            if (map[cur[0]][j] != 0) {
              length = -1;
              break;
            }
          }
        }
        else if (cur[1] == target[1]) {
          length = Math.abs(cur[0] - target[0]) - 1;

          for (int j = Math.min(cur[0], target[0]) + 1; j < Math.max(cur[0], target[0]); j++) {
            if (map[j][cur[1]] != 0) {
              length = -1;
              break;
            }
          }
        }
        else continue;

        if (length < 2) continue;
        edges.add(new Edge(map[cur[0]][cur[1]], map[target[0]][target[1]], length));
      }
    }
  }

  static void union(int a, int b) {
    a = find(a);
    b = find(b);

    islandGroups[b] = a;
  }

  static int find(int a) {
    if (islandGroups[a] == a) return a;
    else return islandGroups[a] = find(islandGroups[a]);
  }

  static class Edge implements Comparable<Edge>{
    int from, to, length;

    @Override
    public int compareTo(Edge o) {
      return this.length - o.length;
    }

    Edge(int from, int to, int length) {
      this.from = from;
      this.to = to;
      this.length = length;
    }
  }
}
