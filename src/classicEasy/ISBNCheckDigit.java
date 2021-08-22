package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/isbn-check-digit
class ISBNCheckDigit {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        List<String> failedList = new ArrayList<>();
        for (int i = 0; i < N; i++){
            String isbn = in.nextLine();
            if (!isbn.matches("(^\\d{9}\\w$)|(^\\d{13}$)")) failedList.add(isbn);
            else {
                int prefixSum = 0;
                for (int j = 0; j < isbn.length() - 1; j++)
                    prefixSum += Character.getNumericValue(isbn.charAt(j)) *
                            (isbn.length() == 10 ? (10 - j) : (j % 2 == 0 ? 1 : 3));
                if (isbn.charAt(9) == 'X') {
                    if (prefixSum % 11 != 1) failedList.add(isbn);
                } else if ((Character.getNumericValue(isbn.charAt(isbn.length() - 1)) + prefixSum) %
                        (isbn.length() == 10 ? 11 : 10) != 0) failedList.add(isbn);
            }
        }
        System.out.println(failedList.size() + " invalid:");
        failedList.forEach(System.out::println);
    }
}