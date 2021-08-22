package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/smooth
class Smooth {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for (int i = 0; i < N; i++) 
            System.out.println(isWinnable(in.nextLong())? "VICTORY" : "DEFEAT");
    }
    
    private static boolean isWinnable(long f){
        if(f==1) return true;
        return(f % 2 == 0 && isWinnable(f / 2)) || (f % 3 == 0 && isWinnable(f / 3)) || (f % 5 == 0 && isWinnable(f / 5));
    }
}