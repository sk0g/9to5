package dev.a3820team.a9to5;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import static dev.a3820team.a9to5.MeQuestionsActivity.ME_PREFS_NAME;

public class MeResultsActivity extends AppCompatActivity {

    int mTotalScore;
//    TextView mResultBox, mFortuneType, mShortResults, mLongResults;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_results);

        getTotalMeScore();
        displayResults();
    }

    private void displayResults() {

        TextView mResultBox    = (TextView) findViewById(R.id.me_results_box);
        TextView mFortuneType  = (TextView) findViewById(R.id.me_fortune_type);
        TextView mShortResults = (TextView) findViewById(R.id.me_result_short);
        TextView mLongResults  = (TextView) findViewById(R.id.me_results_long);

        mResultBox.setText(Integer.toString(mTotalScore));

        int colour;
        if (mTotalScore <= 35) {
            colour = getResources().getColor(R.color.customRed);
        } else if (mTotalScore <= 50) {
            colour = getResources().getColor(R.color.customYellow);
        } else {
            colour = getResources().getColor(R.color.customGreen);
        }

        mResultBox.setTextColor(colour);

        ImageView drawRectangle = findViewById(R.id.me_results_drawable);
        drawRectangle.setColorFilter(colour);
    }

    private void getTotalMeScore() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                ME_PREFS_NAME, Context.MODE_PRIVATE
        );

        String key;
        for (int i = 0; i > -1; i++) {
            key = "me" + (i + 1);
            if (sharedPref.contains(key)) {
                mTotalScore += sharedPref.getInt(key, 0);
            } else {
                break;
            }
        }
    }
}
