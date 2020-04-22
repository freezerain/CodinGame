package classicEasy;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
//https://www.codingame.com/training/easy/defibrillators
public class Defibrillators {

    public static void main(String args[]) throws ParseException { //Take note of exception
        Scanner in = new Scanner(System.in);
        //we can parse comma numbers using FRANCE locale
        NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
        double userLon = nf.parse(in.next()).doubleValue();
        double userLat = nf.parse(in.next()).doubleValue();
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        ArrayList<String> defList = new ArrayList<>(); //list of defibrillators
        for (int i = 0; i < N; i++) {
            defList.add(in.nextLine());
        }
        ArrayList<String> defParam; //List of one defibrillator parameters
        String closestName = "";
        double closestDistance = 0.0;
        double tempDistance = 0.0;
        for(String def : defList){
            defParam = new ArrayList<>(Arrays.asList(def.split(";")));
            tempDistance = getDistance(userLon, userLat,
                    nf.parse(defParam.get(4)).doubleValue(), nf.parse(defParam.get(5)).doubleValue());
            if(closestName.isEmpty() || tempDistance<closestDistance){
                closestName = defParam.get(1);
                closestDistance = tempDistance;
            }
        }
        System.out.println(closestName);
    }
    static double getDistance(double longA, double latA, double longB, double latB){
        //Formula to find distance
        double x = (longB - longA) * Math.cos( (latA + latB) / 2 );
        double y = latB-latA;
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2)) * 6371;
    }
}
