package Acmicpc.zero;

import java.io.*;
import java.util.*;

public class Acmicpc02263 {
  static int[] inorder;
  static int[] postorder;
  static int[] idx;
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));


      int n = Integer.parseInt(br.readLine());
      inorder = new int[n];
      postorder = new int[n];
      idx = new int[n + 1];

      StringTokenizer iTraversal = new StringTokenizer(br.readLine());
      StringTokenizer pTraversal = new StringTokenizer(br.readLine());

      for (int i = 0; i < n; i++) {
        inorder[i] = Integer.parseInt(iTraversal.nextToken());
        postorder[i] = Integer.parseInt(pTraversal.nextToken());
        idx[inorder[i]] = i;
      }
      br.close();

      divideAndConquer(0, n - 1, 0, n - 1);

      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void divideAndConquer(int inStart, int inEnd, int poStart, int poEnd) throws IOException {
    if (inStart > inEnd || poStart > poEnd) {
      return;
    }
    int root = postorder[poEnd];

    bw.write(root + " ");

    int pivot = idx[root];

    if (pivot != inStart) {
      divideAndConquer(inStart, pivot - 1, poStart, poStart + pivot - inStart - 1);
    }
    if (pivot != inEnd) {
      divideAndConquer(pivot + 1, inEnd, poStart + pivot - inStart, poEnd - 1);
    }
  }
}
