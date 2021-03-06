package dev.a3820team.a9to5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MeQuestionsActivity extends AppCompatActivity {

    public static final String ME_PREFS_NAME = "dev.a3820team.a9to5.me_questions";
    public static final String ME_PREFS_PREFIX = "me";

    Button mSubmitButton, mPreviousButton;
    static final int[] mRadioGroupID = {
            R.id.me_radio_group_1,  R.id.me_radio_group_2,  R.id.me_radio_group_3,  R.id.me_radio_group_4,
            R.id.me_radio_group_5,  R.id.me_radio_group_6,  R.id.me_radio_group_7,  R.id.me_radio_group_8,
            R.id.me_radio_group_9,  R.id.me_radio_group_10, R.id.me_radio_group_11, R.id.me_radio_group_12,
            R.id.me_radio_group_13, R.id.me_radio_group_14, R.id.me_radio_group_15, R.id.me_radio_group_16,
            R.id.me_radio_group_17, R.id.me_radio_group_18, R.id.me_radio_group_19, R.id.me_radio_group_20
    };

    static final int[] mQuestionTextViews = {
            R.id.me_question_1,  R.id.me_question_2,  R.id.me_question_3,  R.id.me_question_4,
            R.id.me_question_5,  R.id.me_question_6,  R.id.me_question_7,  R.id.me_question_8,
            R.id.me_question_9,  R.id.me_question_10, R.id.me_question_11, R.id.me_question_12,
            R.id.me_question_13, R.id.me_question_14, R.id.me_question_15, R.id.me_question_16,
            R.id.me_question_17, R.id.me_question_18, R.id.me_question_19, R.id.me_question_20
    };

    public static final int mQuestionAmount = mRadioGroupID.length;

    int[] mSelectedOptions = new int[mQuestionAmount];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_questions);

        final Context context = this;

        attemptLoadingResults();

        mSubmitButton = (Button) findViewById(R.id.activity_me_next_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResults();
            }
        });

        mPreviousButton = (Button) findViewById(R.id.activity_me_previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog(context);
            }
        });
    }

    private static void startQuizOver(Context context) {
        Intent startQuizOverIntent = new Intent(context, WelcomeActivity.class);
        context.startActivity(startQuizOverIntent);
    }

    private void attemptLoadingResults() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                ME_PREFS_NAME, Context.MODE_PRIVATE
        );

        String key;
        int checked, radioButtonID;
        for (int i = 0; i > -1; i++) {
            key = ME_PREFS_PREFIX + (i + 1);
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
        boolean allQuestionsAnswered = updateScores();

        if (!allQuestionsAnswered) {
            Toast.makeText(MeQuestionsActivity.this, "Some questions remain unanswered", Toast.LENGTH_LONG).show();
            addListenersToRadioGroups();
        } else {
            saveResults();
            switchToResultsActivity();
        }
    }

    private void switchToResultsActivity() {
        Intent resultIntent = new Intent(this, MeResultsActivity.class);
        startActivity(resultIntent);
    }

    private void saveResults() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                ME_PREFS_NAME, Context.MODE_PRIVATE
        );

        SharedPreferences.Editor editor = sharedPref.edit();
        String key;

        for (int i = 0; i < mQuestionAmount; i++) {
            key = ME_PREFS_PREFIX + (i + 1);
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

    public static void showConfirmationDialog(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.start_over_confirmation_title);
        builder.setMessage(R.string.start_over_confirmation_body);

        builder.setPositiveButton(R.string.confirmation_screen_start_over, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startQuizOver(context);
            }
        });

        builder.setNegativeButton(R.string.confirmation_screen_cancel, null);

        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}
