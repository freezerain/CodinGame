package classicEasy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
//https://www.codingame.com/training/easy/mime-type
public class MIMEType {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        HashMap<String, String> mimeMap = new HashMap<>();
        ArrayList<String> fileList = new ArrayList<>();
        for (int i = 0; i < N; i++) { //Create map of known mime
            mimeMap.put(in.next().toLowerCase(), in.next());
        }
        in.nextLine();
        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // create list of formats to parse
            if(!FNAME.contains(".")) {
                fileList.add("UNKNOWN");
            }else {
                fileList.add(FNAME.substring(
                        FNAME.lastIndexOf('.') + 1, FNAME.length()).toLowerCase());
            }
        }
        for(String file : fileList){
            System.out.println(mimeMap.getOrDefault(file, "UNKNOWN"));
        }
    }
}
