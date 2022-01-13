package Acmicpc;

import java.io.*;
import java.util.*;

/*

slowlight50@gmail.com

https://github.com/TheSunBeneathTheSea/AlgoPractice


***문제

방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

***입력

첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.

***출력

첫째 줄에 연결 요소의 개수를 출력한다.


*/

// 무향 그래프, BFS
public class Acmicpc11724 {
    public static int solve(ArrayList<String> input) {
        int count = 0;
        HashSet<String> edgeInfo = new HashSet<>();

        String[] temp = input.get(0).split(" ");
        int n = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);

        for (int i = 1; i <= m; i++) {
            edgeInfo.add(input.get(i));

            String[] reverse = input.get(i).split(" ");
            edgeInfo.add(reverse[1] + " " + reverse[0]);
        }

        boolean[] isVisited = new boolean[n + 1];
        ArrayDeque<String> queue = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            if(!isVisited[i]){
                queue.add(String.valueOf(i));
                isVisited[i] = true;
                
                while(!queue.isEmpty()){
                    String cur = queue.poll();

                    for (int j = 1; j <= n; j++) {
                        String edge = cur + " " + j;

                        if(!isVisited[j] && edgeInfo.contains(edge)){
                            isVisited[j] = true;
                            queue.add(String.valueOf(j));
                        }
                    }
                }
                count++;
            }
            
        }
        
        return count;
    }
}
