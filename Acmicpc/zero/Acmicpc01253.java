package Acmicpc.zero;

import java.io.*;
import java.util.Arrays;

public class Acmicpc01253 {
  public static void main(String[] args) throws IOException {
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    int n = Integer.parseInt(br.readLine());
    int[] sortedNum = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

    int goodCount = 0;

    for (int i = 0; i < sortedNum.length; i++) {
      int target = sortedNum[i];
      int j = 0, k = sortedNum.length - 1;

      while (j < k) {
        if (i == j) {
          j++;
          continue;
        }
        if (i == k) {
          k--;
          continue;
        }
        int sum = sortedNum[j] + sortedNum[k];
        if (target == sum) {
          goodCount++;
          break;
        }else if(target > sum) {
          j++;
        } else {
          k--;
        }
      }
    }
    System.out.println(goodCount);
  }
}
