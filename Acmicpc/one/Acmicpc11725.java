package Acmicpc.one;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Acmicpc11725 {
  public static void main(String[] args) {
    try {
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader br = new BufferedReader(new FileReader("input"));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      int n = Integer.parseInt(br.readLine());

      Node1[] nodes = new Node1[n + 1];

      while (n-- > 1) {
        StringTokenizer token = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(token.nextToken());
        int b = Integer.parseInt(token.nextToken());

        if (nodes[a] == null) {
          Node1 node1 = new Node1(a);
          nodes[node1.cur] = node1;
        }
        if (nodes[b] == null) {
          Node1 node2 = new Node1(b);
          nodes[node2.cur] = node2;
        }

        nodes[a].addConnected(nodes[b]);
        nodes[b].addConnected(nodes[a]);
      }

      nodes[1].checked = true;
      nodes[1].setTree();

      for (int i = 2; i < nodes.length; i++) {
        bw.write(nodes[i].getParentValue() + "\n");
      }
      bw.flush();
      br.close();

      bw.close();
    } catch (IOException e) {

    }
  }
}

class Node1 {
  Node1 parent;
  List<Node1> connected;
  int cur;
  boolean checked;

  public Node1(int cur) {
    this.connected = new ArrayList<>();
    this.cur = cur;
  }

  public void addConnected(Node1 node) {
    this.connected.add(node);
  }

  public int getParentValue() {
    return parent.cur;
  }

  public void setTree() {
    for (Node1 node : connected) {
      if (!node.checked) {
        node.parent = this;
        node.checked = true;
        node.setTree();
      }
    }
  }

}
