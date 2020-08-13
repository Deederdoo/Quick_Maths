package tools;

import android.content.Context;

import controller.PlayScreen;

/**
 *
 * Authors Dustin Horricks, EMBEDONIX.yt
 *
 * Thanks to this video for helping me with this
 * chronometer https://www.youtube.com/watch?v=5RVzknsdknw
 *
* */

public class TimerTool implements Runnable {

    //The amount of milliseconds in a minute and an hour
    public static final long MILLISECONDS_TO_MINUTES = 60000;
    public static final long MILLISECONDS_TO_HOURS = 3600000;

    private Context mContext;
    private long startTime;
    private boolean isRunning;

    public TimerTool(Context context) {

        this.mContext = context;
    }

    /**
    *
     * Method used to start the timer
    *
    * */
    public void startTimer() {

        startTime = System.currentTimeMillis();

        isRunning = true;
    }

    /**
     *
      * Method used to stop the timer
     *
     * */
    public void stopTimer() {

        isRunning = false;
    }

    @Override
    public void run() {

        //Calculates time from given start time
        while(isRunning) {

            long since = System.currentTimeMillis() - startTime;

            int milliseconds = (int) (since % 1000);
            int seconds = (int) ((since / 1000) % 60);
            int minutes = (int) ((since / MILLISECONDS_TO_MINUTES) % 60);
            int hours = (int) ((since / MILLISECONDS_TO_HOURS) % 24);

            //Casting PlayScreen class to mContext then passing the given time
            //to the scoreTimer method with formated string
            ((PlayScreen)mContext).scoreTimer(String.format("%02d:%02d:%02d:%03d"
                    , hours, minutes, seconds, milliseconds), milliseconds, seconds, minutes);
        }
    }
}
