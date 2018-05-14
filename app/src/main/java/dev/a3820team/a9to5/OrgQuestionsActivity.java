package dev.a3820team.a9to5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OrgQuestionsActivity extends AppCompatActivity {

    public static final String ME_PREFS_NAME = "dev.a3820team.a9to5.me_questions";

    Button mSubmitButton, mPreviousButton;
    final int[] mRadioGroupID = {
            R.id.org_radio_group_1,  R.id.org_radio_group_2,  R.id.org_radio_group_3,  R.id.org_radio_group_4,
            R.id.org_radio_group_5,  R.id.org_radio_group_6,  R.id.org_radio_group_7,  R.id.org_radio_group_8,
            R.id.org_radio_group_9,  R.id.org_radio_group_10, R.id.org_radio_group_11, R.id.org_radio_group_12,
            R.id.org_radio_group_13, R.id.org_radio_group_14, R.id.org_radio_group_15, R.id.org_radio_group_16,
            R.id.org_radio_group_17, R.id.org_radio_group_18, R.id.org_radio_group_19, R.id.org_radio_group_20
    };

    final int[] mQuestionTextViews = {
            R.id.org_question_1,  R.id.org_question_2,  R.id.org_question_3,  R.id.org_question_4,
            R.id.org_question_5,  R.id.org_question_6,  R.id.org_question_7,  R.id.org_question_8,
            R.id.org_question_9,  R.id.org_question_10, R.id.org_question_11, R.id.org_question_12,
            R.id.org_question_13, R.id.org_question_14, R.id.org_question_15, R.id.org_question_16,
            R.id.org_question_17, R.id.org_question_18, R.id.org_question_19, R.id.org_question_20
    };

    public final int mQuestionAmount = mRadioGroupID.length;

    int[] mSelectedOptions = new int[mQuestionAmount];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_questions);

        attemptLoadingResults();

        mSubmitButton = (Button) findViewById(R.id.activity_org_next_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResults();
            }
        });

        mPreviousButton = (Button) findViewById(R.id.activity_org_previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToPreviousActivity();
            }
        });
    }

    private void attemptLoadingResults() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                ME_PREFS_NAME, Context.MODE_PRIVATE
        );

        String key;
        int checked, radioButtonID;
        for (int i = 0; i > -1; i++) {
            key = "org" + (i + 1);
            if (sharedPref.contains(key)) {
                checked = sharedPref.getInt(key, 0);
                RadioGroup rg = findViewById(mRadioGroupID[i]);
                radioButtonID = rg.getChildAt(checked - 1).getId();
                rg.check(radioButtonID);
            } else {
                break;
            }
        }
    }

    void showResults() {
//        boolean allQuestionsAnswered = updateScores();
//
//        if (!allQuestionsAnswered) {
//            Toast.makeText(OrgQuestionsActivity.this, "Some questions remain unanswered", Toast.LENGTH_LONG).show();
//            addListenersToRadioGroups();
//        } else {
//            saveResults();
//            switchToResultsActivity();
//        }
    }

    void switchToPreviousActivity() {
        Intent resultIntent = new Intent(this, MeResultsActivity.class);
        startActivity(resultIntent);
    }

//    private void switchToResultsActivity() {
//        Intent resultIntent = new Intent(this, MeResultsActivity.class);
//        startActivity(resultIntent);
//    }

    private void saveResults() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                ME_PREFS_NAME, Context.MODE_PRIVATE
        );

        SharedPreferences.Editor editor = sharedPref.edit();
        String key;

        editor.clear();
        for (int i = 0; i < mQuestionAmount; i++) {
            key = "org" + (i + 1);
            editor.putInt(key, mSelectedOptions[i]);
        }

        editor.apply();
    }

    private void addListenersToRadioGroups() {
        for (int rg_num : mRadioGroupID) {
            RadioGroup rg = findViewById(rg_num);

            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    updateScores();
                }
            });
        }
    }

    boolean updateScores() {
        boolean isComplete = true;
        for (int i = 0; i < mQuestionAmount; i++) {
            RadioGroup radioGroup = (RadioGroup) findViewById(mRadioGroupID[i]);

            int buttonID = radioGroup.getCheckedRadioButtonId();

            if (buttonID == -1) { mSelectedOptions[i] = -1; isComplete = false; }
            else {
                String checkedId = getResources().getResourceEntryName(buttonID);

                if (checkedId.endsWith("1")) { mSelectedOptions[i] = 1; }
                else if (checkedId.endsWith("2")) { mSelectedOptions[i] = 2; }
                else if (checkedId.endsWith("3")) { mSelectedOptions[i] = 3; }
            }
        }

        updateTextColours();
        return isComplete;
    }
    
    void updateTextColours() {
        for (int i = 0; i < mQuestionAmount; i++) {
            TextView tv = findViewById(mQuestionTextViews[i]);
            int newTextColour;

            if (mSelectedOptions[i] == -1) {
                newTextColour = getResources().getColor(R.color.colorDangerRed);
            } else {
                newTextColour = getResources().getColor(R.color.colorText);
            }

            tv.setTextColor(newTextColour);
        }
    }
}
