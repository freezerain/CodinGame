package classicEasy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
//https://www.codingame.com/ide/puzzle/master-of-mayhem
class MasterOfMayhem {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int cyborgCount = Integer.parseInt(in.nextLine());
        Map<String, String[]> cyborgMap = new HashMap<>();
        for (int i = 0; i < cyborgCount; i++) cyborgMap.put(in.nextLine(), new String[4]);
        int mayhemReportCount = Integer.parseInt(in.nextLine());
        String[] mayhemReport = new String[4];
        for (int i = 0; i < mayhemReportCount; i++){
            String report = in.nextLine();
            int type = report.contains("hat is a") ? 0 : report.contains(
                    "neckwear is a") ? 1 : report.contains("companion is a") ? 2 : 3;
            mayhemReport[type] = type != 3 ? report.substring(report.indexOf("is a") + 5)
                    .trim() : report.substring(report.indexOf("\"") + 1, report.lastIndexOf("\""));
        }
        int cyborgReportCount = Integer.parseInt(in.nextLine());
        for (int i = 0; i < cyborgReportCount; i++){
            String report = in.nextLine();
            int type = report.contains("hat is a") ? 0 : report.contains(
                    "neckwear is a") ? 1 : report.contains("companion is a") ? 2 : 3;
            cyborgMap.get(report.substring(0, report.indexOf("'")))[type] =
                    type != 3 ? report.substring(report.indexOf("is a") + 5)
                            .trim() : report.substring(report.indexOf("\"") + 1,
                                                       report.lastIndexOf("\""));
        }
        List<String> suspects = cyborgMap.entrySet()
                .stream()
                .filter(e -> ((mayhemReport[0] == null || e.getValue()[0] == null ||
                               e.getValue()[0].equals(mayhemReport[0])) &&
                              (mayhemReport[1] == null || e.getValue()[1] == null ||
                               e.getValue()[1].equals(mayhemReport[1])) &&
                              (mayhemReport[2] == null || e.getValue()[2] == null ||
                               e.getValue()[2].equals(mayhemReport[2])) &&
                              (mayhemReport[3] == null || e.getValue()[3] == null ||
                               e.getValue()[3].contains(mayhemReport[3]))))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(suspects.size() == 1 ? suspects.get(0) :
                                   suspects.size() > 1 ? "INDETERMINATE" : "MISSING");
    }
}