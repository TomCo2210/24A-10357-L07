package dev.tomco.a24a_10357_l07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textview.MaterialTextView;

import dev.tomco.a24a_10357_l07.Interfaces.StepCallback;
import dev.tomco.a24a_10357_l07.Utilities.BackgroundSound;
import dev.tomco.a24a_10357_l07.Utilities.StepDetector;

public class MainActivity extends AppCompatActivity {

    private MaterialTextView main_LBL_stepsX;
    private MaterialTextView main_LBL_stepsY;
    private StepDetector stepDetector;

    private BackgroundSound backgroundSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initStepDetector();
    }

    private void initStepDetector() {
        stepDetector = new StepDetector(this, new StepCallback() {
            @Override
            public void stepX() {
                main_LBL_stepsX.setText(String.valueOf(stepDetector.getStepCountX()));
            }

            @Override
            public void stepY() {
                main_LBL_stepsY.setText(String.valueOf(stepDetector.getStepCountY()));
            }
        });
    }

    private void findViews() {
        main_LBL_stepsX = findViewById(R.id.main_LBL_stepsX);
        main_LBL_stepsY = findViewById(R.id.main_LBL_stepsY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundSound = new BackgroundSound(this, R.raw.lifelike);
        backgroundSound.playSound();
        stepDetector.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundSound.stopSound();
        stepDetector.stop();
    }
}