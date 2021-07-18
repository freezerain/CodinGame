import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int targetIndex = in.nextInt();
        int M = in.nextInt();
        Node root = new Node(in.nextInt());
        root.left = new Node(in.nextInt());
        root.right = new Node(in.nextInt());
        for (int i = 1; i < M; i++){
            int parentIndex = in.nextInt();
            int leftIndex = in.nextInt();
            int rightIndex = in.nextInt();
            Node node = getNodeByIndex(root, parentIndex);
            node.left = new Node(leftIndex);
            node.right = new Node(rightIndex);
        }
        if (root.index == targetIndex) System.out.println("Root");
        else {
            Stack<String> pathStack = new Stack<>();
            buildPath(root, targetIndex, pathStack);
            StringBuilder sb = new StringBuilder();
            while (!pathStack.isEmpty()) {
                sb.append(pathStack.pop()).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }

    private static Node getNodeByIndex(Node root, int index) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (node.index == index) return node;
            if (node.left != null) nodes.add(node.left);
            if (node.right != null) nodes.add(node.right);
        }
        return new Node(0);
    }

    private static boolean buildPath(Node root, int index, Stack<String> pathStack) {
        if (root == null) return false;
        if (root.index == index) return true;
        if (buildPath(root.left, index, pathStack)) {
            pathStack.push("Left");
            return true;
        }
        if (buildPath(root.right, index, pathStack)) {
            pathStack.push("Right");
            return true;
        }
        return false;
    }
}

class Node {
    int index;
    Node left = null;
    Node right = null;

    public Node(int index) {
        this.index = index;
    }
}