package Acmicpc;

import java.util.Arrays;

/*

slowlight50@gmail.com

https://github.com/TheSunBeneathTheSea/AlgoPractice



문제

수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
입력

첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
출력

첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.


*/

public class Acmicpc11053 {
    public static int solve (int[] array) {
        int[] dp = new int[array.length];

        Arrays.fill(dp, 1);
        
        for (int i = 0; i < array.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if(array[j] < array[i] && max <= dp[j]){
                    max = dp[j] + 1;
                }
            }
            dp[i] = max;
        }
        
        int max = 0;

        for (int i : dp) {
            if(i > max){
                max = i;
            }
        }


        return max;
    }
}
