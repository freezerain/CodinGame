package classicEasy;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/monday-tuesday-happy-days
class MondayTuesdayHappyDays {

    public static void main(String args[]) {
        final List<String> months = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
                                                  "Aug", "Sep", "Oct", "Nov", "Dec");
        final List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday",
                                                "Friday", "Saturday", "Sunday");
        int[] monthLength = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        Scanner in = new Scanner(System.in);
        if (in.nextInt() == 1) monthLength[1] = 29;
        int weekday = days.indexOf(in.next());
        int month = months.indexOf(in.next());
        int date = in.nextInt();
        int targetMonth = months.indexOf(in.next());
        int targetDate = in.nextInt();
        boolean isFuture = targetMonth > month || (targetMonth == month && targetDate > date);
        int daysToTarget = isFuture ? monthLength[month] - date : date;
        while (month != targetMonth) {
            month = (month + (isFuture ? 1 : -1)) % 12;
            daysToTarget += monthLength[month];
        }
        daysToTarget -= isFuture ? (monthLength[month] - targetDate) : targetDate;
        System.out.println(
                days.get(Math.abs(daysToTarget % 7 + (isFuture ? weekday : -weekday)) % 7));
    }
}