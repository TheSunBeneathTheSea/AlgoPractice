package Acmicpc.one;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Acmicpc11003 {
  static ArrayDeque<Node> deque;

  public static void main(String[] args) throws IOException {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(tokenizer.nextToken());
    int L = Integer.parseInt(tokenizer.nextToken());

    deque = new ArrayDeque<>(L + 1);

    int idx = 0;
    tokenizer = new StringTokenizer(br.readLine());
    while (tokenizer.hasMoreTokens()) {
      int num = Integer.parseInt(tokenizer.nextToken());

      while (!deque.isEmpty() && deque.peekLast().val >= num) {
        deque.removeLast();
      }

      deque.addLast(new Node(idx++, num));

      if(!deque.isEmpty() && deque.peek().idx <= idx - L) deque.removeFirst();
      bw.write(deque.peekFirst().val + " ");
    }

    bw.flush();
    bw.close();
    br.close();
  }

  static class Node {
    int idx, val;

    public Node(int idx, int val) {
      this.idx = idx;
      this.val = val;
    }
  }
}
