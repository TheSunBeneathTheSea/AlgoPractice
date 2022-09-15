import java.util.*;
import java.io.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


public class Main {
    public static void main(String[] args) {
        try{
            // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br = new BufferedReader(new FileReader("input"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int[][] b = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
            int[][] t = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
            HashMap<String, Integer> map = new HashMap<>();
            String aa = "pepE yp  ypp";


            aa.substring(2, 2);
            br.close();
            int ii = 2;
            int ee = 6;
            long[] lll = LongStream.rangeClosed(3, 5).toArray();
            long[] lll2 = LongStream.rangeClosed(5, 3).toArray();
            int count = 0;
            for (char c : aa.toLowerCase().toCharArray()) {
                if (c != 'p' || c != 'y') continue;

                count += c == 'p' ? 1 : -1;
            }
            char cc = 'a';
            String s = "a bc aZaZ";



            System.out.println(count);

            int[] appearance = new int[52];

            //A-Za-z
            StringBuilder sb = new StringBuilder();

            int nnnn = 25;
            s.chars().forEach(
                    c -> {
                        if (Character.isAlphabetic(c)) {
                            if (!Character.isAlphabetic(c + nnnn)) {
                                sb.append((char)(c + nnnn - 26));
                            } else {
                                sb.append((char)(c + nnnn));
                            }
                        } else {
                            sb.append((char)c);
                        }
                    }
            );


            var temp = s.codePoints().map(c -> {
                if (Character.isLowerCase(c)) {
                    if (!Character.isLowerCase(c + nnnn)) {
                        return c + nnnn - 26;
                    } else {
                        return c + nnnn;
                    }
                } else if (Character.isUpperCase(c)){
                    if (!Character.isUpperCase(c + nnnn)) {
                        return c + nnnn - 26;
                    } else {
                        return c + nnnn;
                    }
                } else {
                    return c;
                }
            }).collect(
                    StringBuilder::new,
                    StringBuilder::appendCodePoint,
                    StringBuilder::append
            ).toString();

            int ll = 3292;
            long eee = 189032l;
            Arrays.stream(String.valueOf(ll).split("")).mapToInt(Integer::parseInt).average().orElse(0);
            LongStream.of(eee);

            Math.sqrt(eee);
            int[] check = new int[0];



            sb.append(3);
            sb.append(1);
            sb.append(7);



            sb.reverse();
            int left = 13;
            int right = 18;
            int answer = 0;
            for (int i = left; i <= right; i++) {
                int divisor = (int)Math.sqrt(i);
                if(i == (int)Math.pow(i, divisor) || i == (int)Math.pow(i, divisor + 1)) answer += i;
            }

            int[] numbers = {1, 2, 3, 5};
            boolean[] possibleSums = new boolean[201];

            for (int i = 0; i < numbers.length; i++) {
                for (int j = 0; j < numbers.length; j++) {
                    if(i == j) continue;

                    possibleSums[i + j] = true;
                }
            }

            ArrayList<Integer> answer2 = new ArrayList<>();
            answer2.sort(Comparator.naturalOrder());

            for (int i = 0; i < possibleSums.length; i++) {
                if(possibleSums[i]) answer2.add(i);
            }

            int[] cccc = answer2.stream().mapToInt(Integer::intValue).toArray();


            String[] sur = {"AN", "CF", "MJ", "RT", "NA"};
            int[] q = {5, 3, 2, 7, 5, 8, 10};
            int n = 1;

            Arrays.sort(sur, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.charAt(n) == o2.charAt(n)) {
                        return o1.compareTo(o2);
                    }
                    return o1.charAt(n) - o2.charAt(n);
                }
            });

            int ququ = 27 + ((q[0] % 2 == 1) ? 1 : -1);
            System.out.println(solution(sur, q));;

            int[] num = {1, 1, 3, 3, 9, 3, 0, 1};
            int[] num2 = {1, 1, 3, 3, 9, 3, 0, 1};

            Arrays.sort(num);

            boolean bb = true;
            HashSet<Integer> set = new HashSet<>();




            int[] nn = Arrays.stream(num).distinct().toArray();
            bw.write("");

            bw.flush();            
            bw.close();
        }catch(IOException e){

        }
    }
    public static String solution(String[] survey, int[] choices) {
        HashMap<String, Integer> status = new HashMap<>();
        status.put("RT", 0);
        status.put("CF", 0);
        status.put("JM", 0);
        status.put("AN", 0);

        StringBuilder sb;
        for (int i = 0; i < survey.length; i++) {
            if (choices[i] == 4) continue;

            sb = new StringBuilder(survey[i]);
            if (status.containsKey(survey[i])) {
                int val = status.get(survey[i]);

                status.put(survey[i], val - 4 + choices[i]);
            } else {
                int val = status.get(sb.reverse().toString());

                status.put(sb.reverse().toString(), val + 4 - choices[i]);
            }
        }

        sb = new StringBuilder();

        if (status.get("RT") <= 0) sb.append("R");
        else sb.append("T");
        if (status.get("CF") <= 0) sb.append("C");
        else sb.append("F");
        if (status.get("JM") <= 0) sb.append("J");
        else sb.append("M");
        if (status.get("AN") <= 0) sb.append("A");
        else sb.append("N");

        return sb.toString();
    }
}
