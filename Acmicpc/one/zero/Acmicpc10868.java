package Acmicpc.one.zero;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Acmicpc10868 {
  static int[] segTree;

  public static void main(String[] args) throws Exception {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer tokenizer = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(tokenizer.nextToken());
    int M = Integer.parseInt(tokenizer.nextToken());

    int treeSize = 1;

    while (treeSize < N * 2) {
      treeSize *= 2;
    }

    segTree = new int[treeSize];

    Arrays.fill(segTree, Integer.MAX_VALUE);

    for (int i = 0; i < N; i++) {
      segTree[treeSize / 2 + i] = Integer.parseInt(br.readLine());
    }

    for (int i = treeSize / 2 - 1; i > 0 ; i--) {
      segTree[i] = Math.min(segTree[i * 2], segTree[i * 2 + 1]);
    }

    while (M-- > 0) {
      tokenizer = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(tokenizer.nextToken());
      int b = Integer.parseInt(tokenizer.nextToken());

      bw.write(getMinValue(a, b) + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
  public static int getMinValue(int startIdx, int endIdx) {
    int min = Integer.MAX_VALUE;
    startIdx = segTree.length / 2 + startIdx - 1;
    endIdx = segTree.length / 2 + endIdx - 1;

    while (startIdx <= endIdx) {
      if (startIdx % 2 == 1) {
        min = Math.min(min, segTree[startIdx]);
      }
      if (endIdx % 2 == 0) {
        min = Math.min(min, segTree[endIdx]);
      }
      startIdx = (startIdx + 1) / 2;
      endIdx = (endIdx - 1) / 2;
    }

    return min;
  }
}
