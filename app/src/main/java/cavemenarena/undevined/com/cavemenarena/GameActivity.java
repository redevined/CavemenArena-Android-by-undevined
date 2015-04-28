package cavemenarena.undevined.com.cavemenarena;

import android.graphics.Bitmap;

import cavemenarena.undevined.com.cavemenarena.classes.Actions;
import cavemenarena.undevined.com.cavemenarena.classes.Cave;
import cavemenarena.undevined.com.cavemenarena.classes.Caveman;
import cavemenarena.undevined.com.cavemenarena.classes.Sprites.Sprite;
import cavemenarena.undevined.com.cavemenarena.util.SystemUiHider;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class GameActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;


    /**
     * Main animation routine.
     */
    private class SpriteAnimation extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ImageView view1 = (ImageView)findViewById(R.id.player1);
                    Bitmap frame1 = player1Sprite.getFrame();
                    view1.setImageBitmap(frame1);

                    ImageView view2 = (ImageView)findViewById(R.id.player2);
                    Bitmap frame2 = player2Sprite.getFrame();
                    view2.setImageBitmap(frame2);
                }
            });
        }
    }


    private Cave game;
    private Sprite player1Sprite;
    private Sprite player2Sprite;
    private Timer spriteTimer;
    private final int fps = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);

        this.game = new Cave("Player", "CPU", 2);
        Caveman player1 = this.game.getPlayer1();
        Caveman player2 = this.game.getPlayer2();

        this.player1Sprite = new Sprite(player1, player2, getResources());
        this.player2Sprite = new Sprite(player2, player1, getResources());

        //Bitmap spritesheet = BitmapFactory.decodeResource(getResources(), R.drawable.spritesheet_caveman_32x32);
        //this.player1Sprite = new CompactSprite(player1, player2, spritesheet);
        //this.player2Sprite = new CompactSprite(player2, player1, spritesheet);

        this.spriteTimer = new Timer();
        this.spriteTimer.scheduleAtFixedRate(new SpriteAnimation(), 0, 1000 / 3);

        /*
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });
        */

    }

    private void checkGameFinish() {
        if (this.game.finished()) {
            findViewById(R.id.button_layout).setVisibility(View.GONE);
            findViewById(R.id.finish_layout).setVisibility(View.VISIBLE);

            int text_id;
            if (this.game.getPlayer1().isDead()) {
                text_id = R.string.lose_button;
            } else {
                text_id = R.string.win_button;
            }
            ((Button)findViewById(R.id.finish_button)).setText(text_id);
        }
    }

    private void updateSharpnessCounter() {
        TextView sharpness_text = (TextView)findViewById(R.id.sharpness_counter);
        int sharpness = this.game.getPlayer1().getStickSharpness();
        sharpness_text.setText("" + sharpness);
    }

    private void triggerAction(int action) {
        this.game.setPlayerAction(action);
        this.player1Sprite.animate(action);
        this.player2Sprite.animate(this.game.getPlayer2().getNextAction());

        this.updateSharpnessCounter();
        this.checkGameFinish();
    }

    public void sharpenAction(View v) {
        triggerAction(Actions.SHARPEN);
    }

    public void pokeAction(View v) {
        triggerAction(Actions.POKE);
    }

    public void blockAction(View v) {
        triggerAction(Actions.BLOCK);
    }

    public void exit(View v) {
        this.spriteTimer.cancel();
        finish();
    }

    @Override
    public boolean onKeyDown(int key, KeyEvent event) {
        if (key == KeyEvent.KEYCODE_BACK) {
            this.spriteTimer.cancel();
            finish();
        }
        return super.onKeyDown(key, event);
    }

    /*
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }



     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.

    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };


     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    */
}
