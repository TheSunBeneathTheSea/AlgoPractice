package Acmicpc.zero.one;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Acmicpc01976 {
  static int[] group;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    group = new int[N + 1];

    for (int i = 1; i < group.length; i++) {
      group[i] = i;
    }

    for (int i = 0; i < N; i++) {
      int[] adj = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

      for (int j = 0; j < N; j++) {
        if (j == i) continue;
        if (adj[j] == 0) continue;

        union(i + 1, j + 1);
      }
    }
    Set<String> target = new HashSet<>();
    String[] targetCities = br.readLine().split(" ");

    for (String city : targetCities) {
      target.add(city);
    }

    int start = Integer.parseInt(targetCities[0]);
    for (String city : target) {
      int cur = Integer.parseInt(city);

      if (find(start) != find(cur)) {
        System.out.println("NO");
        return;
      }

      start = cur;
    }

    System.out.println("YES");
  }

  public static void union(int a, int b) {
    a = find(a);
    b = find(b);

    if (a != b) {
      group[b] = a;
    }
  }

  public static int find(int a) {
    if (group[a] == a) return a;

    return group[a] = find(group[a]);
  }
}
