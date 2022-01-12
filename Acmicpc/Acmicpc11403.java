package Acmicpc;

/*

slowlight50@gmail.com

https://github.com/TheSunBeneathTheSea/AlgoPractice


***문제

가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 경로가 있는지 없는지 구하는 프로그램을 작성하시오.

***입력

첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다. i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재한다는 뜻이고, 0인 경우는 없다는 뜻이다. i번째 줄의 i번째 숫자는 항상 0이다.

***출력

총 N개의 줄에 걸쳐서 문제의 정답을 인접행렬 형식으로 출력한다. 정점 i에서 j로 가는 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0으로 출력해야 한다.


*/

// 방향 그래프, 플로이드 와셜
public class Acmicpc11403 {
    public static int[][] solve(boolean[][] graph){
        int size = graph.length;
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if(i == j || i == k){
                        if(graph[j][k]){
                            result[j][k] = 1;
                        }
                    }
                    if(graph[j][i] && graph[i][k]){
                        graph[j][k] = true;
                        result[j][k] = 1;
                    }
                }
            }
        }

        return result;
    }
}
