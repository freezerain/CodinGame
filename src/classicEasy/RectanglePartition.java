package classicEasy;

import java.util.Scanner;
//https://www.codingame.com/training/easy/rectangle-partition
public class RectanglePartition {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        int countX = in.nextInt();
        int countY = in.nextInt();
        int[] listX = new int[countX+2];//space for 0, w, h
        int[] listY = new int[countY+2];
        for (int i = 0; i < countX; i++) {
            listX[i+1] = in.nextInt();//shifting data i+1 to have 0 in first index
        }
        for (int i = 0; i < countY; i++) {
            listY[i+1] = in.nextInt();
        }
        listX [countX+1] = w;//Adding w and h to arrays
        listY [countY+1] = h;
        int squares = 0;
        for(int nullI = 0; nullI<countX+1; nullI++){//Move virtual zero X
            int nullX = listX[nullI];
            for(int nullK = 0; nullK<countY+1;nullK++){//Move virtual zero Y
                int nullY = listY[nullK];
                for(int i = nullI+1; i<countX+2; i++){//X-Virtual zero side
                    int x = listX[i];
                    for(int k = nullK+1; k<countY+2; k++){//Y-Virtual zero side
                        int y = listY[k];
                        if(x-nullX == y-nullY) squares++;//Square founded
                    }
                }
            }
        }
        System.out.println(squares);
    }
}
