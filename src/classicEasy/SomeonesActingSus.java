package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/demo/893086f8ca8b575ab9e5b035ffc91e20a46c18
//This puzzle still not published
class SomeonesActingSus {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = Integer.parseInt(in.nextLine());
        String F = in.nextLine();
        int N = in.nextInt();
        int K = Integer.parseInt(in.nextLine().trim());
        for (int i = 0; i < N; i++) {
            String s = in.nextLine();
            int counter = 1;
            boolean isSus= false;
            int lastIndex = -1;
            for (int j = 0; j < K; j++){
                if(s.charAt(j)=='#') counter++;
                else {
                    int currentIndex = F.indexOf(s.charAt(j));
                    if (lastIndex != -1) {
                        isSus = !(Math.abs(currentIndex - lastIndex) <= counter ||
                                  L - Math.abs(currentIndex - lastIndex) <= counter);
                        if (isSus) 
                            break;
                        counter = 1;
                    }
                    lastIndex = currentIndex;
                }
            }
            System.out.println(isSus? "SUS" : "NOT SUS");
        }
    }
}