import java.text.DecimalFormat;
import java.util.*;
//https://www.codingame.com/ide/puzzle/equivalent-resistance-circuit-building
class Solution {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        HashMap<String, Integer> resistorMap = new HashMap();
        for (int i = 0; i < N; i++){
            String name = in.next();
            int R = in.nextInt();
            resistorMap.put(name, R);
        }
        in.nextLine();
        String circuit = in.nextLine();
        circuit = circuit.trim();
        Stack<Boolean> operatorStack = new Stack<>();
        Stack<Double> answerStack = new Stack<>();
        String[] elementList = circuit.split(" ");
        for (String s: elementList){
            if (s.equals("[")) {
                operatorStack.push(true);
                answerStack.push(0.0);
            } else if (s.equals("(")) {
                operatorStack.push(false);
                answerStack.push(0.0);
            } else if (s.equals("]") || s.equals(")")) {
                operatorStack.pop();
                if (!(operatorStack.isEmpty())) {
                    double b = answerStack.pop();
                    double a = answerStack.pop();
                    if (a == 0.0) answerStack.push(b);
                    else if (operatorStack.peek()) answerStack.push(1.0 / ((1.0 / a) + (1.0 / b)));
                    else answerStack.push(a + b);
                }
            } else if (answerStack.peek().equals(0.0)) {
                answerStack.pop();
                answerStack.push((double) resistorMap.get(s));
            } else {
                double a = answerStack.pop();
                double b = resistorMap.get(s);
                if (operatorStack.peek()) answerStack.push(1.0 / ((1.0 / a) + (1.0 / b)));
                else answerStack.push(a + b);
            }
        }
        DecimalFormat df = new DecimalFormat("0.0");
        System.out.println(df.format(answerStack.pop()));
    }
}