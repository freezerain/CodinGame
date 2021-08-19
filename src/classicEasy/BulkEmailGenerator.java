package classicEasy;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//https://www.codingame.com/ide/puzzle/bulk-email-generator
class BulkEmailGenerator {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine());
        String result = "";
        for (int i = 0; i < N; i++) result += in.nextLine() + "\n";
        Matcher m = Pattern.compile("(?<=\\()([\\s\\S]*?\\|?[\\s\\S]*?)(?=\\))").matcher(result);
        int counter= 0;
        while(m.find()){
            String[] split = m.group().split("\\|",-1);
            result = result.substring(0,m.start()-1) + split[counter++%split.length] + result.substring(m.end()+1);
            m.reset(result);
        }
        System.out.println(result);
    }
}