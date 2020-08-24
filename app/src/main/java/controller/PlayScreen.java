package controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickmaths.R;

import java.text.DecimalFormat;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import tools.EquationGene;
import tools.TimerTool;

import static controller.DifficultyScreen.isEasy;
import static controller.DifficultyScreen.isHard;
import static controller.DifficultyScreen.isInter;
import static controller.DifficultyScreen.isSavant;

public class PlayScreen extends AppCompatActivity {

    public static final String MODE_EASY = "EASY";
    public static final String MODE_INTERMEDIATE = "INTERMEDIATE";
    public static final String MODE_HARD = "HARD";
    public static final String MODE_SAVANT = "SAVANT";

    //Typeface
    private Typeface typeface;

    //Relative Layout
    private RelativeLayout relTop, relCenter;

    private Animation animation;

    private ProgressBar progressBar;
    private Button startButton, button1, button2, button3, button4;
    private TextView timer, equation, countdownTimer;
    private int answer, correctAnswerCount, incorrectAnswerCount, milliseconds, seconds, minutes, countDownCount;
    private int[] eqArray;

    private Context mContext;
    private TimerTool timerTool;
    private Thread timerThread;

    private EquationGene equationGene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mContext = this;

        //Typeface / Font
        typeface = Typeface.createFromAsset(getAssets(), "Cairo-SemiBold.ttf");

        //Relative Layout
        relTop = (RelativeLayout) findViewById(R.id.play_layout_rel_top);
        relCenter = (RelativeLayout) findViewById(R.id.play_layout_rel_center);

        //TextView
        equation = (TextView) findViewById(R.id.text_equation);
        equation.setTypeface(typeface);
        timer = (TextView) findViewById(R.id.text_timer);
        timer.setTypeface(typeface);
        countdownTimer = (TextView) findViewById(R.id.text_timer_countdown);
        countdownTimer.setTypeface(typeface);

        //Buttons
        startButton = (Button) findViewById(R.id.button_start);
        startButton.setTypeface(typeface);
        button1 = (Button) findViewById(R.id.button_ans1);
        button1.setTypeface(typeface);
        button2 = (Button) findViewById(R.id.button_ans2);
        button2.setTypeface(typeface);
        button3 = (Button) findViewById(R.id.button_ans3);
        button3.setTypeface(typeface);
        button4 = (Button) findViewById(R.id.button_ans4);
        button4.setTypeface(typeface);

        //ProgressBar
        progressBar = (ProgressBar) findViewById(R.id.progress_progress);

        //Integers
        correctAnswerCount = 0;
        incorrectAnswerCount = 0;

        /**
        *
         * When the Play button is pressed the timer begins
         * and the players game will begin
        *
        */
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(timerTool == null) {

                    startButton.setVisibility(View.INVISIBLE);
                    countdownAnimation(); // This will also start the timer thread once the countdown is complete

                    equationGene = new EquationGene();
                    eqArray = equationGene.selectDifficulty(getDifficultyMode());
                    equation.setText(generateEquationString(eqArray));
                    answer = eqArray[2];
                }
            }
        });

        /**
         *
         * Buttons 1 - 4 are the answer buttons which the user
         * will be clicking to answer the equation
         *
        * */

        button1.setOnClickListener(new View.OnClickListener() { // Button 1
            @Override
            public void onClick(View view) {

                int buttonAns = Integer.parseInt(button1.getText().toString());

                if(answer == buttonAns) {

                    correctAnswer();

                } else {

                    incorrectAnswer();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() { // Button 2
            @Override
            public void onClick(View view) {

                int buttonAns = Integer.parseInt(button2.getText().toString());

                if(answer == buttonAns) {

                    correctAnswer();

                } else {

                    incorrectAnswer();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() { // Button 3
            @Override
            public void onClick(View view) {

                int buttonAns = Integer.parseInt(button3.getText().toString());

                if(answer == buttonAns) {

                    correctAnswer();

                } else {

                    incorrectAnswer();
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() { // Button 4
            @Override
            public void onClick(View view) {

                int buttonAns = Integer.parseInt(button4.getText().toString());

                if(answer == buttonAns) {

                    correctAnswer();

                } else {

                    incorrectAnswer();
                }
            }
        });
    }

    /**
     *
     * Animation for the countdown textview before game starts
     * This method also starts the timer thread once the countdown is finished
    *
    * */
    private void countdownAnimation() {

        countDownCount = 3;
        countdownTimer.setText(String.valueOf(countDownCount));
        countdownTimer.setVisibility(View.VISIBLE);

        animation = AnimationUtils.loadAnimation(this, R.anim.animation_transition_in_fast_repeat_1);
        countdownTimer.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) {

                if(countDownCount != 1) {

                    countDownCount--;
                    countdownTimer.setText(String.valueOf(countDownCount));
                }else{

                    animation.cancel();
                    countdownTimer.setVisibility(View.INVISIBLE);
                    relTop.setVisibility(View.VISIBLE);
                    relCenter.setVisibility(View.VISIBLE);

                    timerTool = new TimerTool(mContext);
                    timerThread = new Thread(timerTool);
                    timerThread.start();
                    timerTool.startTimer();
                }
            }
        });

        animation.start();
    }

    /**
     *
     * I use this method so if the user presses back on
     * their device it will send them back to the menu
     * and also stop the timer
    *
    * */
    @Override
    public void onBackPressed() {

        if(timerTool != null) {

            timerTool.stopTimer();
        }
        Intent nextActivity = new Intent(getApplicationContext(), MenuController.class);
        startActivity(nextActivity);
    }

    /**
     *
     * This method sets the difficulty based on chosen
     * difficulty using the static boolean values
    *
    * */
    public String getDifficultyMode() {

        if(isEasy){

            return MODE_EASY;

        }else if(isInter){

            return MODE_INTERMEDIATE;

        }else if(isHard){

            return MODE_HARD;

        }else if(isSavant) {

            return MODE_SAVANT;
        }

        return null;
    }

    /**
     *
     * When user correctly answers the equation, this method will
     * be called. Correct answers will be saved
    *
    * */
    public void correctAnswer() {

        correctAnswerCount++;
        eqArray = equationGene.selectDifficulty(getDifficultyMode());
        equation.setText(generateEquationString(eqArray));
        answer = eqArray[2];

        updateProgress(1, getDifficultyMode());
    }


    /**
     *
     * When user incorrectly answers the equation, this method will
     * be called. Incorrect answers will be saved
    *
    * */
    public void incorrectAnswer() {

        incorrectAnswerCount++;
        updateProgress(0, getDifficultyMode());
    }

    /**
     *
     * Updates the progress bar with the given int progress value
     * Difficulty determines the amount of rounds
     * 0 for negative progress
     * 1 for positive progress
     *
     * @param progress
     * @param difficulty
    *
    * */
    private void updateProgress(int progress, String difficulty) {

        int points;
        int currentProgress;
        int totalProgress = 0;

        if(difficulty == MODE_EASY || difficulty == MODE_INTERMEDIATE || difficulty == MODE_HARD || difficulty == MODE_SAVANT) { //THIS IS TEMP MAY CHANGE ALGO-----------------------------------------------NEED TO ADD DIFFERENT POINTS /10 /15 /30

            points = (100 / 10);
            currentProgress = progressBar.getProgress();

            if(progress == 0 && currentProgress != 0) { // Negative progress

                totalProgress = currentProgress - points;

            } else if(progress == 1) { // Positive progress

                totalProgress = points + currentProgress;
            }

            progressBar.setProgress(totalProgress);

            if(totalProgress == 100) {

                timerTool.stopTimer();
                double tempScore = calculateFinalTime();
                Bundle myBundle = new Bundle();
                int[] standing = new int[2]; // holds the correctAnswerCount and incorrectAnswerCount
                standing[0] = correctAnswerCount;
                standing[1] = incorrectAnswerCount;
                Intent nextActivity = new Intent(PlayScreen.this, ResultScreen.class);
                myBundle.putIntArray("standing", standing);
                myBundle.putDouble("finalscore", tempScore);
                nextActivity.putExtras(myBundle);
                startActivity(nextActivity);
            }
        }
    }

    /**
     *
     * Sets the text for the timer on the TextView,
     * also making sure it runs on the ui thread
     *
     * Also assigns integer time variables to be used
     * later for score calculation
     *
     */
    public void scoreTimer(final String time, int milliseconds, int seconds, int minutes) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                timer.setText(time);
            }
        });

        this.milliseconds = milliseconds;
        this.seconds = seconds;
        this.minutes = minutes;
    }

    /**
     *
     * Takes the final time once the user finishes all the
     * equations then converts them into a double value that
     * will be calculated towards the final score
     *
     * @return double
    *
    * */
    public double calculateFinalTime() {

        double modeInt = 0;
        double finalCorrect = 0;
        double timeCalc = 0;
        DecimalFormat deciF = new DecimalFormat("#.##");

        double tempIA = incorrectAnswerCount * 2;

        if(correctAnswerCount > tempIA) {

            for(int i = 0; i < this.minutes; i++) { // Converts minutes to seconds

                this.seconds += 60;
            }

            String tempString = this.seconds + "." + this.milliseconds;
            timeCalc = Double.valueOf(tempString);

            if(isEasy) {

                modeInt = 10;

            }else if(isInter) {

                modeInt = 10;

            }else if(isHard) {

                modeInt = 15;

            }else if(isSavant) {

                modeInt = 15;
            }

            finalCorrect = correctAnswerCount - tempIA;
            Log.e("T", " " + finalCorrect);
            timeCalc *= 0.6;
            Log.e("T", " " + timeCalc);
            finalCorrect *= 100;
            Log.e("T", " " + finalCorrect);
            finalCorrect -= timeCalc;
            Log.e("T", " " + finalCorrect);
            finalCorrect /= modeInt;
            Log.e("T", " " + finalCorrect);

            return Double.valueOf(deciF.format(finalCorrect));
        }

        return 0;
    }

    /**
     *
     * Takes the int array created in the EquationGene.class
     * then converts it in to the correct String
     *
     * @return finalString
     *
    * */
    public String generateEquationString(int[] array) {

        String value = "";
        String firstNumber = String.valueOf(array[0]);
        String secondNumber = String.valueOf(array[1]);
        //String answer = String.valueOf(array[2]);

        generateButtonNumbers(array[2]); // passes the answer to the method for setup

        if(array[3] == 1) {

            value = "+";

        }else if(array[3] == 2) {

            value = "-";

        }else if(array[3] == 3) {

            value = "x";

        }else if(array[3] == 4) {

            value = "/";
        }

        String finalString = firstNumber + " " + value + " " + secondNumber;

        return finalString;
    }

    /**
     *
     * Takes the answer and generates numbers similar to the answer
     * and sets them to the buttons
     *
     * There are 2 parts to this method, the first part generates the answer
     * and the fake answer. The second part generates their respective position
     * on the buttons, this has to be random or the answer would always be in the
     * same position.
     *
     * @param answer
     *
    * */
    public void generateButtonNumbers(int answer) { // n = Random.nextInt(max - min + 1) + min;

        Random random = new Random();
        int max = (answer + 6);
        int min = (answer - 6);

        int[] buttonArray = new int[4];
        int aCounter = 0;

        Set<Integer> aNumb = new LinkedHashSet<>(); // Set allows for non duplicate numbers

        aNumb.add(answer);

        while(aNumb.size() < 4) {

            int tempNum = random.nextInt(max - min + 1) + min;
            aNumb.add(tempNum);
        }

        for(int i : aNumb) {

            buttonArray[aCounter] = i;
            aCounter++;
        }

        //--------------------------------------------- 2nd Half -----------------------------------

        Set<Integer> bNumb = new LinkedHashSet<>(); // Set allows for non duplicate numbers

        while(bNumb.size() < 4) {

            int tempNum = random.nextInt(4 - 0) + 0;
            bNumb.add(tempNum);
        }

        int[] bNumbs = new int[4];
        int bCounter = 0;

        for(int i : bNumb) { // Enhanced for loop to iterate the Set

            bNumbs[bCounter] = i;
            bCounter++;
        }

        button1.setText(String.valueOf(buttonArray[bNumbs[0]]));
        button2.setText(String.valueOf(buttonArray[bNumbs[1]]));
        button3.setText(String.valueOf(buttonArray[bNumbs[2]]));
        button4.setText(String.valueOf(buttonArray[bNumbs[3]]));
    }
}
