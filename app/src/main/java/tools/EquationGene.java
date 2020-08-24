package tools;

import android.util.Log;

import java.util.Random;

import static controller.PlayScreen.MODE_EASY;
import static controller.PlayScreen.MODE_INTERMEDIATE;
import static controller.PlayScreen.MODE_HARD;
import static controller.PlayScreen.MODE_SAVANT;

public class EquationGene {

    public int[] selectDifficulty(String difficulty) {

        if(difficulty == MODE_EASY) {

            return easyEquation();

        }else if(difficulty == MODE_INTERMEDIATE) {

            return intermediateEquation();

        }else if(difficulty == MODE_HARD) {

            return hardEquation();

        }else if(difficulty == MODE_SAVANT) {

            return savantEquation();
        }

        return null;
    }

    public int[] easyEquation() {

        int answer;

        int number1 = new Random().nextInt(11);
        int number2 = new Random().nextInt(11);

        int plusOrMinus = new Random().nextInt(3 - 1) + 1;

        if(plusOrMinus == 1) { // 1 = + Addition

            int[] pArray = new int[4]; // [Number 1], [Number 2], [Answer], [Type]

            answer = (number1 + number2);
            pArray[0] = number1;
            pArray[1] = number2;
            pArray[2] = answer;
            pArray[3] = 1;

            return pArray;

        }else if(plusOrMinus == 2){ // 2 = - Subtraction

            int[] sArray = new int[4]; // [Number 1], [Number 2], [Answer], [Type]

            answer = (number1 - number2);
            sArray[0] = number1;
            sArray[1] = number2;
            sArray[2] = answer;
            sArray[3] = 2;

            return sArray;
        }

        return null;
    }

    public int[] intermediateEquation() {

        int answer;

        int number1 = new Random().nextInt(66);
        int number2 = new Random().nextInt(66);

        int plusOrMinus = new Random().nextInt(3 - 1) + 1;

        if(plusOrMinus == 1) { // 1 = + Addition

            int[] pArray = new int[4]; // [Number 1], [Number 2], [Answer], [Type]

            answer = (number1 + number2);
            pArray[0] = number1;
            pArray[1] = number2;
            pArray[2] = answer;
            pArray[3] = 1;

            return pArray;

        }else if(plusOrMinus == 2){ // 2 = - Subtraction

            int[] sArray = new int[4]; // [Number 1], [Number 2], [Answer], [Type]

            answer = (number1 - number2);
            sArray[0] = number1;
            sArray[1] = number2;
            sArray[2] = answer;
            sArray[3] = 2;

            return sArray;
        }

        return null;
    }

    public int[] hardEquation() {

        int answer;

        int number1 = new Random().nextInt(16);
        int number2 = new Random().nextInt(16);

        int plusOrMinusOrMulti = new Random().nextInt(4 - 1) + 1;

        if(plusOrMinusOrMulti == 1) { // 1 = + Addition

            int[] pArray = new int[4]; // [Number 1], [Number 2], [Answer], [Type]

            answer = (number1 + number2);
            pArray[0] = number1;
            pArray[1] = number2;
            pArray[2] = answer;
            pArray[3] = 1;

            return pArray;

        }else if(plusOrMinusOrMulti == 2){ // 2 = - Subtraction

            int[] sArray = new int[4]; // [Number 1], [Number 2], [Answer], [Type]

            answer = (number1 - number2);
            sArray[0] = number1;
            sArray[1] = number2;
            sArray[2] = answer;
            sArray[3] = 2;

            return sArray;

        }else if(plusOrMinusOrMulti == 3){ // 3 = * Multiplication

            int[] mArray = new int[4]; // [Number 1], [Number 2], [Answer], [Type]

            answer = (number1 * number2);
            mArray[0] = number1;
            mArray[1] = number2;
            mArray[2] = answer;
            mArray[3] = 3;

            return mArray;
        }

        return null;
    }

    public int[] savantEquation() {

        int answer;

        int number1 = new Random().nextInt(16);
        int number2 = new Random().nextInt(16);

        int plusOrMinusOrMulti = new Random().nextInt(4 - 1) + 1;

        if(plusOrMinusOrMulti == 1) { // 1 = + Addition

            int[] pArray = new int[4]; // [Number 1], [Number 2], [Answer], [Type]

            answer = (number1 + number2);
            pArray[0] = number1;
            pArray[1] = number2;
            pArray[2] = answer;
            pArray[3] = 1;

            return pArray;

        }else if(plusOrMinusOrMulti == 2) { // 2 = - Subtraction

            int[] sArray = new int[4]; // [Number 1], [Number 2], [Answer], [Type]

            answer = (number1 - number2);
            sArray[0] = number1;
            sArray[1] = number2;
            sArray[2] = answer;
            sArray[3] = 2;

            return sArray;

        }else if(plusOrMinusOrMulti == 3) { // 3 = * Multiplication

            int[] mArray = new int[4]; // [Number 1], [Number 2], [Answer], [Type]

            answer = (number1 * number2);
            mArray[0] = number1;
            mArray[1] = number2;
            mArray[2] = answer;
            mArray[3] = 3;

            return mArray;
        }

        return null;
    }
}
