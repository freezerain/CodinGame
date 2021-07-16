package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
//https://www.codingame.com/ide/puzzle/container-terminal

public class ContainerTerminal {
    public static void main(String args[]) {
        //Receiving and sending data
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) in.nextLine();
        for (int i = 0; i < N; i++) System.out.println(stackCargo(in.nextLine()));
    }

    //If last char in stack is same or less then char in cargo - then put, else create new stack
    static int stackCargo(String s) {
        List<Stack<Character>> stackList = new ArrayList<>();
        mainLoop:
        for (char cargo: s.toCharArray()){
            for (Stack<Character> stack: stackList)
                if (stack.peek() >= cargo) {
                    stack.push(cargo);
                    continue mainLoop;
                }
            Stack<Character> newStack = new Stack<>();
            newStack.push(cargo);
            stackList.add(newStack);
        }
        return stackList.size();
    }
}