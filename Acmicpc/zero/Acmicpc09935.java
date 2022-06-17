package Acmicpc.zero;

import java.io.*;

public class Acmicpc09935 {
  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String text = br.readLine();
      String bomb = br.readLine();
      br.close();

      StringBuilder check = new StringBuilder();

      for (int i = 0; i < text.length(); i++) {
        check.append(text.charAt(i));

        if (check.length() >= bomb.length()) {
          boolean flag = true;
          for (int j = 0; j < bomb.length(); j++) {
            flag &= bomb.charAt(j) == check.charAt(check.length() - bomb.length() + j);
          }

          if (flag) {
            check.delete(check.length() - bomb.length(), check.length());
          }
        }
      }
      String answer = check.length() == 0 ? "FRULA" : check.toString();

      bw.write(answer);
      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
