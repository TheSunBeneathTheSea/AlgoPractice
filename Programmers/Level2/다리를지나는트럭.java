package Programmers.Level2;

import java.util.ArrayDeque;

public class 다리를지나는트럭 {
  public int solution(int bridge_length, int weight, int[] truck_weights) {
    ArrayDeque<Integer[]> queue = new ArrayDeque<>();

    int time = 1;
    int currentWeight = 0;
    for (int i = 0; i < truck_weights.length;) {
      if (currentWeight + truck_weights[i] <= weight) {
        queue.add(new Integer[] {truck_weights[i], time});
        currentWeight += truck_weights[i];
        time++;
        i++;
      } else {
        time = queue.peek()[1] + bridge_length;
      }

      if (queue.peek()[1] + bridge_length == time) {
        Integer[] head = queue.poll();
        currentWeight -= head[0];
      }
    }

    return queue.peekLast()[1] + bridge_length;
  }
}
