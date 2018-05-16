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

public class OrgResultsActivity extends AppCompatActivity {

    int mTotalScore;
    TextView mResultBox, mFortuneType, mResultText, mResultQuestion;
    Button mPreviousButton, mNextButton;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_results);

        mTotalScore = getTotalOrgScore(getApplicationContext());
        displayResults();
    }

    private void displayResults() {

        mPreviousButton = (Button) findViewById(R.id.activity_org_results_previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToOrgQuiz();
            }
        });
        mNextButton     = (Button) findViewById(R.id.activity_org_results_next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToFinalResults();
            }
        });


        mResultBox      = (TextView) findViewById(R.id.org_results_box);
        mFortuneType    = (TextView) findViewById(R.id.org_fortune_type);
        mResultText     = (TextView) findViewById(R.id.org_results);
        mResultQuestion = (TextView) findViewById(R.id.org_results_question);

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
            resultTextID = R.string.org_fortune_very_low_text;
        } else if (mTotalScore <= 40) {
            resultTextID = R.string.org_fortune_low_text;
        } else if (mTotalScore <= 50) {
            resultTextID = R.string.org_fortune_medium_text;
        } else {
            resultTextID = R.string.org_fortune_high_text;
        }

        mFortuneType.setText(getResources().getString(fortuneBoxTitleID));
        mResultText.setText(getResources().getString(resultTextID));
        mResultQuestion.setText(getResources().getString(R.string.post_me_text));

        mFortuneType.setTextColor(colour);
        mResultBox.setTextColor(colour);

        ImageView drawRectangle = findViewById(R.id.me_results_drawable);
        drawRectangle.setColorFilter(colour);
    }

    private void switchToFinalResults() {
        Intent quizIntent = new Intent(this, FinalResultActivity.class);
        startActivity(quizIntent);
    }

    private void switchToOrgQuiz() {
        Intent quizIntent = new Intent(this, OrgQuestionsActivity.class);
        startActivity(quizIntent);
    }

    private static int getTotalOrgScore(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                ME_PREFS_NAME, Context.MODE_PRIVATE
        );

        int totalScore = 0;
        String key;
        for (int i = 0; i > -1; i++) {
            key = "org" + (i + 1);
            if (sharedPref.contains(key)) {
                totalScore += sharedPref.getInt(key, 0);
            } else {
                break;
            }
        }

        return totalScore;
    }
}
