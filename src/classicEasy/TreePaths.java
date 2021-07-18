package classicEasy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
//https://www.codingame.com/ide/puzzle/tree-paths
public class TreePaths {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int targetIndex = in.nextInt();
        int M = in.nextInt();
        TpNode root = new TpNode(in.nextInt());
        root.left = new TpNode(in.nextInt());
        root.right = new TpNode(in.nextInt());
        for (int i = 1; i < M; i++){
            int parentIndex = in.nextInt();
            int leftIndex = in.nextInt();
            int rightIndex = in.nextInt();
            TpNode node = getNodeByIndex(root, parentIndex);
            node.left = new TpNode(leftIndex);
            node.right = new TpNode(rightIndex);
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

    private static TpNode getNodeByIndex(TpNode root, int index) {
        Queue<TpNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            TpNode node = nodes.poll();
            if (node.index == index) return node;
            if (node.left != null) nodes.add(node.left);
            if (node.right != null) nodes.add(node.right);
        }
        return new TpNode(0);
    }

    private static boolean buildPath(TpNode root, int index, Stack<String> pathStack) {
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

class TpNode {
    int index;
    TpNode left = null;
    TpNode right = null;

    public TpNode(int index) {
        this.index = index;
    }
}