package classicEasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//https://www.codingame.com/training/easy/bank-robbers
public class BankRobbers {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int robberAmount = in.nextInt();
        int vaultAmount = in.nextInt();
        int [] vaultArr = new int [vaultAmount];//Time to break the vault
        for (int i = 0; i < vaultAmount; i++) {
            int combinationSize = in.nextInt();
            int firstDigits = in.nextInt();
            int digitsCombs = (int) Math.pow(10, firstDigits);
            int charsCombs = (int) Math.pow(5, combinationSize-firstDigits);
            vaultArr[i] = (digitsCombs > 0 ? digitsCombs : 1) * (charsCombs > 0 ? charsCombs : 1);
        }
        ArrayList<Integer> robberTimer = new ArrayList<>(Collections.nCopies(robberAmount, 0));//Time spent robbing
        for (int vault : vaultArr) {
            int robberForTheJob = robberTimer.indexOf(Collections.min(robberTimer));//Find robber with least time spent
            robberTimer.set(robberForTheJob, robberTimer.get(robberForTheJob) + vault);
        }
        System.out.println(Collections.max(robberTimer));//Max time spend by the robber
    }
}
