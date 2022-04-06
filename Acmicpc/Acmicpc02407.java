package Acmicpc;

import java.math.BigInteger;

public class Acmicpc02407 {
    public static BigInteger solve(int n, int m) {
        BigInteger from = BigInteger.valueOf(n);
        BigInteger pick = BigInteger.valueOf(m);
        BigInteger result = BigInteger.ONE;

        while(from.intValue() > n - m && pick.intValue() > 1){
            result = result.multiply(from);
            
            if(result.mod(pick) == BigInteger.ZERO){
                result = result.divide(pick);
                pick = pick.subtract(BigInteger.ONE);
            }

            from = from.subtract(BigInteger.ONE);
        }
        
        while(from.intValue() > n - m){
            result = result.multiply(from);
            from = from.subtract(BigInteger.ONE);
        }

        while(pick.intValue() > 1){
            result = result.divide(pick);
            pick = pick.subtract(BigInteger.ONE);
        }
        

        return result;
    }
    
}
