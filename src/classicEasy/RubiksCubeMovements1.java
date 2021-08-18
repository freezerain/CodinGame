package classicEasy;

import java.util.Arrays;
import java.util.Scanner;
//https://www.codingame.com/ide/puzzle/111-rubiks-cube-movements
class RubiksCubeMovements1 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Character[] rotation = Arrays.stream(in.nextLine().split(" "))
                .map(s -> (s.length() > 1 ? Character.toUpperCase(s.charAt(0)) : s.charAt(0)))
                .toArray(Character[]::new);
        Rubik r = new Rubik();
        for (char c: rotation) r.rotate(Character.toUpperCase(c), Character.isLowerCase(c));
        System.out.println(r.getFace(in.nextLine().charAt(0)));
        System.out.println(r.getFace(in.nextLine().charAt(0)));
    }

    static class Rubik {
        char[] faces;

        public Rubik() {
            faces = new char[]{'F', 'B', 'U', 'D', 'L', 'R'};
        }

        public char getFace(char face) {
            int faceDirection = 0;
            for (int i = 0; i < faces.length; i++)
                if (faces[i] == face) {
                    faceDirection = i;
                    break;
                }
            switch (faceDirection) {
                case 0:
                    return 'F';
                case 1:
                    return 'B';
                case 2:
                    return 'U';
                case 3:
                    return 'D';
                case 4:
                    return 'L';
                case 5:
                    return 'R';
                default:
                    return '?';
            }
        }

        public void rotate(char axis, boolean isClockwise) {
            if (axis == 'X') roll(isClockwise);
            else if (axis == 'Y') yaw(isClockwise);
            else if (axis == 'Z') pitch(isClockwise);
        }

        private void pitch(boolean isClockwise) {
            // Around Z (front-back)
            char temp = faces[2];
            if (isClockwise) {
                faces[2] = faces[4];
                faces[4] = faces[3];
                faces[3] = faces[5];
                faces[5] = temp;
            } else {
                faces[2] = faces[5];
                faces[5] = faces[3];
                faces[3] = faces[4];
                faces[4] = temp;
            }
        }

        private void yaw(boolean isClockwise) {
            // Around Y (up-down)
            char temp = faces[0];
            if (isClockwise) {
                faces[0] = faces[5];
                faces[5] = faces[1];
                faces[1] = faces[4];
                faces[4] = temp;
            } else {
                faces[0] = faces[4];
                faces[4] = faces[1];
                faces[1] = faces[5];
                faces[5] = temp;
            }
        }

        private void roll(boolean isClockwise) {
            // Around X (left-right)
            char temp = faces[0];
            if (isClockwise) {
                faces[0] = faces[3];
                faces[3] = faces[1];
                faces[1] = faces[2];
                faces[2] = temp;
            } else {
                faces[0] = faces[2];
                faces[2] = faces[1];
                faces[1] = faces[3];
                faces[3] = temp;
            }
        }
    }
}