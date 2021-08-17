package classicEasy;

import java.util.Scanner;

//https://www.codingame.com/ide/puzzle/disordered-first-contact
class DisorderedFirstContact {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        String MESSAGE = in.nextLine();
        for (int i = 0; i < Math.abs(N); i++) MESSAGE = N > 0 ? decode(MESSAGE) : encode(MESSAGE);
        System.out.println(MESSAGE);
    }

    private static String decode(String s) {
        int deepness = 0;
        int sum = 0;
        for (int i = 1; i < s.length(); i++){
            int nextSum = (int) (i * ((i + 1) / 2.0));
            if (nextSum > s.length()) break;
            deepness = i;
            sum = nextSum;
        }
        String result = "";
        if (s.length() > sum) {
            result = deepness % 2 == 0 ? s.substring(sum) : s.substring(0, s.length() - sum);
            s = deepness % 2 == 0 ? s.substring(0, sum) : s.substring(s.length() - sum);
        }
        for (int i = deepness; i > 0; i--){
            result = (i % 2 == 0 ? s.substring(0, i) : s.substring(s.length() - i)) + result;
            s = i % 2 == 0 ? s.substring(i) : s.substring(0, s.length() - i);
        }
        return result;
    }

    private static String encode(String s) {
        String result = "";
        for (int i = 0, j = 1; i < s.length(); i += j, j++)
            result = j % 2 == 0 ?
                    s.substring(i, Math.min(i + j, s.length())) + result :
                    result + s.substring(i, Math.min(i + j, s.length()));
        return result;
    }
}