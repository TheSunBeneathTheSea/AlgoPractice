package Acmicpc;


public class Acmicpc5525{
    public static int solve(int n, int m, String s){
        int targetSize = 2 * n + 1;
        int count = 0;
        int flag = 0;

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'I'){
                if(flag % 2 == 0){
                    flag++;
                }else{
                    flag = 1;
                }
            }else{
                if(flag % 2 == 1){
                    flag++;
                }else{
                    flag = 0;
                }
            }

            if(flag == targetSize){
                count++;
                flag -= 2;
            }
        }

        return count;
    }
}