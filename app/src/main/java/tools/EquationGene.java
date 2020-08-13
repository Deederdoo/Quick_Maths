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

            return null;

        }else if(difficulty == MODE_HARD) {

            return null;

        }else if(difficulty == MODE_SAVANT) {

            return null;
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
}
