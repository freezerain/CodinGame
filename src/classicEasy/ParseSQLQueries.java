package classicEasy;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
//https://www.codingame.com/ide/puzzle/parse-sql-queries
class ParseSQLQueries {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String sqlQuery = in.nextLine();
        int ROWS = Integer.parseInt(in.nextLine());
        List<String> headers = Arrays.asList(in.nextLine().split(" "));
        String[][] db = new String[headers.size()][ROWS];
        for (int i = 0; i < ROWS; i++){
            String[] row = in.nextLine().split(" ");
            for (int j = 0; j < row.length; j++)
                db[j][i] = row[j];
        }
        String[] orderBy = null;
        String[] where = null;
        List<String> select = null;
        Matcher m = Pattern.compile("(?<=SELECT ).*(?= FROM)").matcher(sqlQuery);
        if (m.find()) select = Arrays.asList(m.group().split(", "));
        m.usePattern(Pattern.compile("(?<=WHERE ).*= \\w*"));
        if (m.find()) where = m.group().split(" = ");
        m.usePattern(Pattern.compile("(?<=ORDER BY ).*(?=SC)"));
        if (m.find()) orderBy = m.group().split(" ");
        int[] indexOrder = getOrder(ROWS, headers, db, orderBy);
        indexOrder = filterOrder(headers, db, where, indexOrder);
        List<String> finalSelect = select;
        System.out.println(IntStream.range(0, headers.size())
                                   .filter(s -> finalSelect.contains("*") ||
                                           finalSelect.contains(headers.get(s)))
                                   .mapToObj(headers::get)
                                   .reduce((s1, s2) -> s1 + " " + s2)
                                   .orElse("*"));
        StringBuilder sb = new StringBuilder();
        for (int index: indexOrder){
            sb.setLength(0);
            for (int cols = 0; cols < db.length; cols++)
                if (select.contains("*") || select.contains(headers.get(cols)))
                    sb.append(db[cols][index]).append(" ");
            sb.setLength(sb.length() - 1);
            System.out.println(sb);
        }
    }

    private static int[] filterOrder(List<String> headers, String[][] db, String[] where,
                                     int[] indexOrder) {
        if (where != null) {
            int tableCol = headers.indexOf(where[0]);
            String equal = where[1];
            indexOrder = Arrays.stream(indexOrder)
                    .filter(i1 -> db[tableCol][i1].equals(equal))
                    .toArray();
        }
        return indexOrder;
    }

    private static int[] getOrder(int rows, List<String> headers, String[][] db, String[] orderBy) {
        int[] indexOrder = IntStream.range(0, rows).toArray();
        if (orderBy != null) {
            int tableCol = headers.indexOf(orderBy[0]);
            boolean isAscending = orderBy[1].contains("A");
            indexOrder = Arrays.stream(indexOrder).boxed().sorted((i1, i2) -> {
                if (db[tableCol][0].matches("^-?\\d+(\\.\\d+)?$")) return Double.compare(
                        isAscending ? Double.parseDouble(db[tableCol][i1]) : Double.parseDouble(
                                db[tableCol][i2]),
                        isAscending ? Double.parseDouble(db[tableCol][i2]) : Double.parseDouble(
                                db[tableCol][i1]));
                else return db[tableCol][isAscending ? i1 : i2].compareTo(
                        db[tableCol][isAscending ? i2 : i1]);

            }).mapToInt(i1 -> i1).toArray();
        }
        return indexOrder;
    }
}