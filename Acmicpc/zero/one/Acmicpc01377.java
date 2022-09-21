package Acmicpc.zero.one;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Acmicpc01377 {
  public static void main(String[] args) throws Exception{
    //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br = new BufferedReader(new FileReader("input"));

    int n = Integer.parseInt(br.readLine());

    Element[] array = new Element[n];

    int count = 1;
    for (int i = 0; i < n; i++) {
      array[i] = new Element(i, Integer.parseInt(br.readLine()));
    }

    Arrays.sort(array);
    for (int i = 0; i < array.length; i++) {
      int idxDiff = array[i].originIndex - i;
      count = Math.max(count, idxDiff);
    }

    System.out.println(count);
  }

  static class Element implements Comparable<Element>{
    int originIndex, value;

    public Element(int originIndex, int value) {
      this.originIndex = originIndex;
      this.value = value;
    }

    @Override
    public int compareTo(Element o) {
      return Integer.compare(this.value, o.value);
    }
  }
}
