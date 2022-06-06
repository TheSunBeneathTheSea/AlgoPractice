package Acmicpc;

import Programmers.단체사진찍기;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Acmicpc01991 {
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      HashMap<String, Node> map = new HashMap<>();
      int n = Integer.parseInt(br.readLine());

      while (n-- > 0) {
        StringTokenizer token = new StringTokenizer(br.readLine());

        String cur = token.nextToken();
        String left = token.nextToken();
        String right = token.nextToken();

        Node root = map.getOrDefault(cur, new Node(cur));
        map.putIfAbsent(cur, root);

        if (!left.equals(".")) {
          Node leftChild = new Node(left);
          root.setLeftChild(leftChild);
          map.put(left, leftChild);
        }
        if (!right.equals(".")) {
          Node rightChild = new Node(right);
          root.setRightChild(rightChild);
          map.put(right, rightChild);
        }
      }

      Node root = map.get("A");

      bw.write(preorderTraversal(root) + "\n");
      sb = new StringBuilder();
      bw.write(inorderTraversal(root) + "\n");
      sb = new StringBuilder();
      bw.write(postorderTraversal(root) + "\n");

      bw.flush();
      br.close();

      bw.close();
    } catch (IOException e) {

    }
  }

  static String preorderTraversal(Node root) {
    if (root == null) {
      return "";
    }
    sb.append(root.getValue());
    preorderTraversal(root.leftChild);
    preorderTraversal(root.rightChild);
    return sb.toString();
  }

  static String inorderTraversal(Node root) {
    if (root == null) {
      return "";
    }
    inorderTraversal(root.leftChild);
    sb.append(root.getValue());
    inorderTraversal(root.rightChild);
    return sb.toString();
  }

  static String postorderTraversal(Node root) {
    if (root == null) {
      return "";
    }
    postorderTraversal(root.leftChild);
    postorderTraversal(root.rightChild);
    sb.append(root.getValue());
    return sb.toString();
  }

  static class Node {
    String val;
    Node leftChild;
    Node rightChild;

    Node(String val) {
      this.val = val;
    }

    void setLeftChild(Node child) {
      this.leftChild = child;
    }

    void setRightChild(Node child) {
      this.rightChild = child;
    }

    String getValue() {
      return String.valueOf(this.val);
    }
  }

}
