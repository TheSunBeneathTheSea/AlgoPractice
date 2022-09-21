package Acmicpc.zero.five;

import java.io.*;
import java.util.ArrayList;

public class Acmicpc05639 {
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));

      ArrayList<Integer> preOrder = new ArrayList<>();

      while (br.ready()) {
        preOrder.add(Integer.parseInt(br.readLine()));
      }

      BST root = new BST(preOrder.get(0));

      for (int i = 1; i < preOrder.size(); i++) {
        root.constructTree(preOrder.get(i));
      }

      BST.printPostOrder(root);

      br.close();
      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static class BST {
    int key;
    BST parent;
    BST left;
    BST right;

    BST(int key) {
      this.key = key;
    }

    public void constructTree(int val) {
      if (val < this.key) {
        if (this.left == null) {
          this.setLeft(new BST(val));
        } else {
          this.left.constructTree(val);
        }
      } else {
        if (this.right == null) {
          this.setRight(new BST(val));
        } else {
          this.right.constructTree(val);
        }
      }
    }

    public void setLeft(BST left) {
      this.left = left;
      left.parent = this;
    }

    public void setRight(BST right) {
      this.right = right;
      right.parent = this;
    }

    public boolean isLeftChild() {
      if (this.parent == null || this.parent.left == null) {
        return false;
      }
      return this.parent.left.key == this.key;
    }

    public boolean isRightChild() {
      if (this.parent == null || this.parent.right == null) {
        return false;
      }
      return this.parent.right.key == this.key;
    }

    static void printPostOrder(BST root) throws IOException {
      if (root.left != null) {
        printPostOrder(root.left);
      }

      if (root.right != null) {
        printPostOrder(root.right);
      }

      bw.write(root.key + "\n");
    }
  }
}
