package Acmicpc.one.one;

import java.io.*;
import java.util.StringTokenizer;

public class Acmicpc11505 {
  static long[] segTree;
  static final long MOD = 1_000_000_007;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());
    int K = Integer.parseInt(tokenizer.nextToken());

    int treeSize = 1;

    while (treeSize < N * 2) {
      treeSize *= 2;
    }

    segTree = new long[treeSize];

    for (int i = 0; i < N; i++) {
      segTree[treeSize / 2 + i] = Long.parseLong(br.readLine());
    }

    for (int i = treeSize / 2 - 1; i > 0 ; i--) {
      segTree[i] = segTree[i * 2] * segTree[i * 2 + 1] % MOD;
    }

    int count = M + K;
    while (count-- > 0) {
      tokenizer = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(tokenizer.nextToken());
      int b = Integer.parseInt(tokenizer.nextToken());

      if (a == 1) {
        long c = Long.parseLong(tokenizer.nextToken());
        setLeafNode(b, c);
      } else {
        int c = Integer.parseInt(tokenizer.nextToken());
        bw.write(getMultiplication(b, c) + "\n");
      }
    }

    bw.flush();
    bw.close();
    br.close();
  }

  public static void setLeafNode(int idx, long val) {
    int curIdx = segTree.length / 2 + idx - 1;
    segTree[curIdx] = val;

    while (curIdx > 1) {
      segTree[curIdx / 2] = segTree[curIdx / 2 * 2] * segTree[curIdx / 2 * 2 + 1] % MOD;

      curIdx /= 2;
    }
  }

  public static long getMultiplication(int startIdx, int endIdx) {
    long result = 1;
    startIdx = segTree.length / 2 + startIdx - 1;
    endIdx = segTree.length / 2 + endIdx - 1;

    while (startIdx <= endIdx) {
      if (startIdx % 2 == 1) {
        result *= segTree[startIdx];
        result %= MOD;
      }
      if (endIdx % 2 == 0) {
        result *= segTree[endIdx];
        result %= MOD;
      }
      startIdx = (startIdx + 1) / 2;
      endIdx = (endIdx - 1) / 2;
    }

    return result;
  }
}
