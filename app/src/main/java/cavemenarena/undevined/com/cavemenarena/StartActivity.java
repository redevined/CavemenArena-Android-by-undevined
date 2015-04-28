package cavemenarena.undevined.com.cavemenarena;

import cavemenarena.undevined.com.cavemenarena.classes.Sprites.DemoSprite;
import cavemenarena.undevined.com.cavemenarena.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.DialogInterface.OnClickListener;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class StartActivity extends Activity implements OnClickListener {
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


    private class SpriteAnimation extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ImageView view = (ImageView)findViewById(R.id.demo_sprite);
                    Bitmap frame = sprite.getFrame();
                    view.setImageBitmap(frame);
                }
            });
        }

    }


    private DemoSprite sprite;
    private Timer spriteTimer;
    private final int fps = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        View startView = findViewById(R.id.startView);
        startView.setVisibility(View.VISIBLE);

        sprite = new DemoSprite(getResources());

        this.spriteTimer = new Timer();
        this.spriteTimer.scheduleAtFixedRate(new SpriteAnimation(), 0, 1000 / fps);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
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
            //mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    /**
     * Ruft den Dialog auf, in dem der Benutzer die Schwierigkeitsstufe wählen und das Spiel starten kann
     *
     * @param v
     */
    public void gotoGame(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.difficultyLevel).setItems(R.array.levels, this);
        builder.create();
        builder.show();
    }

    /**
     * Öffnet die HowTo - Seite
     *
     * @param view
     */
    public void gotoHowTo(View view) {
        this.startActivity(new Intent(this, HowToActivity.class));
    }

    /**
     * Wird nach der Auswahl der Schwierigkeitsstufe aufgerufen und startet das Spiel
     *
     * @param dialog Dialog, von dem der Benutzer die Schwierigkeitsstufe ausgewählt hat
     * @param level  Vom Benutzer gewählte Schwierigeitsstufe
     */
    public void onClick(DialogInterface dialog, int level)
    {
        Intent gameActivityIntent = new Intent(this, GameActivity.class);
        // Schwierigkeitsstufe (von 1 bis 3) -> 0 ist 1
        gameActivityIntent.putExtra("level", level + 1);

        this.startActivity(gameActivityIntent);
    }
}
