package Acmicpc.zero;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Acmicpc01715 {
  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a));

    for (int i = 0; i < N; i++) {
      pq.add(Long.parseLong(br.readLine()));
    }

    long total = 0;
    while (pq.size() > 1) {
      long sum = pq.poll() + pq.poll();
      total += sum;
      pq.add(sum);
    }

    System.out.println(total);
  }
}
