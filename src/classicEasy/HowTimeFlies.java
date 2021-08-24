package classicEasy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/how-time-flies
class HowTimeFlies {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        LocalDate begin = LocalDate.parse(in.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate  end = LocalDate.parse(in.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        int years = (int)ChronoUnit.YEARS.between(begin, end);
        int months = (int)ChronoUnit.MONTHS.between(begin, end)%12;
        int days = (int)ChronoUnit.DAYS.between(begin, end);
        System.out.println((years>1? years + " years, " : years>0? years + " year, ": "") + (months>1? months + " months, " : months>0? months + " month, ": "") 
                           + "total " + days + " days");
    }
}