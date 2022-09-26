package Programmers.Level2;

public class H_Index {
  static int[] temp;

  public int solution(int[] citations) {
    temp = new int[citations.length];

    mergeSort(citations, 0, citations.length - 1);

    int h = 0;
    for (int i = 0; i < citations.length ; i++) {
      if (i + 1 <= citations[i]) {
        h = i + 1;
      } else break;
    }

    return h;
  }

  public void mergeSort(int[] origin, int start, int end) {
    if (end - start < 1) return;
    int mid = start + (end - start) / 2;

    mergeSort(origin, start, mid);
    mergeSort(origin, mid + 1, end);

    for (int i = start; i <= end; i++) {
      temp[i] = origin[i];
    }

    int resultIdx = start;
    int leftIdx = start;
    int rightIdx = mid + 1;

    while (leftIdx <= mid && rightIdx <= end) {
      if (temp[leftIdx] >= temp[rightIdx]) origin[resultIdx++] = temp[leftIdx++];
      else origin[resultIdx++] = temp[rightIdx++];
    }

    while (leftIdx <= mid) {
      origin[resultIdx++] = temp[leftIdx++];
    }
    while (rightIdx <= end) {
      origin[resultIdx++] = temp[rightIdx++];
    }
  }
}
