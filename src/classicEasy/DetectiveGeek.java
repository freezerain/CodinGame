package classicEasy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//https://www.codingame.com/ide/puzzle/detective-geek
class DetectiveGeek {

    public static void main(String args[]) {
        List<String> months = new ArrayList<>(
                List.of("jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov",
                        "dec"));
        Scanner in = new Scanner(System.in);
        String time = String.format("%04d", Integer.parseInt(in.nextLine()
                .chars()
                .map(c -> c == '#' ? 1 : 0)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining()), 2));
        time = new StringBuilder(time).insert(2, ':').toString();
        String address = Arrays.stream(in.nextLine().split(" "))
                .mapToInt(s -> months.indexOf(s.substring(0, 3)) * 12 +
                               months.indexOf(s.substring(3)))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(time);
        System.out.println(address);
    }
}
