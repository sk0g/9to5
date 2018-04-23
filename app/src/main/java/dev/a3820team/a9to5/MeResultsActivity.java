package dev.a3820team.a9to5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static dev.a3820team.a9to5.MeQuestionsActivity.ME_PREFS_NAME;

public class MeResultsActivity extends AppCompatActivity {

    int mTotalScore;
    TextView mResultBox, mFortuneType, mResultText, mResultQuestion;
    Button mPreviousButton, mNextButton;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_results);

        getTotalMeScore();
        displayResults();
    }

    private void displayResults() {

        mPreviousButton = (Button) findViewById(R.id.activity_me_results_previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToMeQuiz();
            }
        });
        mNextButton     = (Button) findViewById(R.id.activity_me_results_next_button);
        // TODO: Listener to switch to next quiz activity

        mResultBox      = (TextView) findViewById(R.id.me_results_box);
        mFortuneType    = (TextView) findViewById(R.id.me_fortune_type);
        mResultText     = (TextView) findViewById(R.id.me_results);
        mResultQuestion = (TextView) findViewById(R.id.me_results_question);

        mResultBox.setText(Integer.toString(mTotalScore));

        int colour, fortuneBoxTitleID, resultTextID;
        if (mTotalScore <= 35) {
            colour = getResources().getColor(R.color.customRed);
            fortuneBoxTitleID = R.string.misfortune_title;
        } else if (mTotalScore <= 50) {
            colour = getResources().getColor(R.color.customYellow);
            fortuneBoxTitleID = R.string.midfortune_title;
        } else {
            colour = getResources().getColor(R.color.customGreen);
            fortuneBoxTitleID = R.string.fortune_title;
        }

        if (mTotalScore <= 30) {
            resultTextID = R.string.me_fortune_very_low_text;
        } else if (mTotalScore <= 40) {
            resultTextID = R.string.me_fortune_low_text;
        } else if (mTotalScore <= 50) {
            resultTextID = R.string.me_fortune_medium_text;
        } else {
            resultTextID = R.string.me_fortune_high_text;
        }

        mFortuneType.setText(getResources().getString(fortuneBoxTitleID));
        mResultText.setText(getResources().getString(resultTextID));
        mResultQuestion.setText(getResources().getString(R.string.post_me_text));

        mFortuneType.setTextColor(colour);
        mResultBox.setTextColor(colour);

        ImageView drawRectangle = findViewById(R.id.me_results_drawable);
        drawRectangle.setColorFilter(colour);
    }

    private void switchToMeQuiz() {
        Intent quizIntent = new Intent(this, MeQuestionsActivity.class);
        startActivity(quizIntent);
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
