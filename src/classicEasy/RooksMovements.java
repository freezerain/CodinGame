package classicEasy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//https://www.codingame.com/ide/puzzle/rooks-movements
class RooksMovements {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String rookPosition = in.next();
        List<String> pieceList = IntStream.range(0, in.nextInt())
                .mapToObj(i -> (in.nextInt() == 0 ? "Y" : "Z") + in.next())
                .collect(Collectors.toList());
        char finalR = rookPosition.charAt(0);
        char finalC = rookPosition.charAt(1);
        char startRow = pieceList.stream()
                .filter(s -> s.indexOf(finalC) != -1 && s.charAt(1) <= finalR)
                .reduce((s1, s2) -> s1.charAt(1) > s2.charAt(1) ? s1 : s2)
                .orElse(" a ")
                .charAt(1);
        char endRow = pieceList.stream()
                .filter(s -> s.indexOf(finalC) != -1 && s.charAt(1) >= finalR)
                .reduce((s1, s2) -> s1.charAt(1) < s2.charAt(1) ? s1 : s2)
                .orElse(" h ")
                .charAt(1);
        char startCol = pieceList.stream()
                .filter(s -> s.indexOf(finalR) != -1 && s.charAt(2) <= finalC)
                .reduce((s1, s2) -> s1.charAt(2) > s2.charAt(2) ? s1 : s2)
                .orElse("  1")
                .charAt(2);
        char endCol = pieceList.stream()
                .filter(s -> s.indexOf(finalR) != -1 && s.charAt(2) >= finalC)
                .reduce((s1, s2) -> s1.charAt(2) < s2.charAt(2) ? s1 : s2)
                .orElse("  8")
                .charAt(2);
        List<String> attackList = new ArrayList<>();
        for (char i = startRow; i <= endRow; i++)
            for (char j = startCol; j <= endCol; j++){
                if (rookPosition.charAt(0) != i == (rookPosition.charAt(1) != j)) continue;
                char finalI = i;
                char finalJ = j;
                if (pieceList.stream()
                        .anyMatch(s -> s.indexOf(finalI) != -1 && s.indexOf(finalJ) != -1 &&
                                       s.indexOf('Y') != -1)) continue;
                boolean isAttack = pieceList.stream()
                        .anyMatch(s -> s.indexOf(finalI) != -1 && s.indexOf(finalJ) != -1 &&
                                       s.indexOf('Z') != -1);
                if (isAttack) attackList.add("R" + rookPosition + "x" + i + j);
                else System.out.println("R" + rookPosition + "-" + i + j);
            }
        attackList.forEach(System.out::println);
    }
}