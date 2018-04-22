package dev.a3820team.a9to5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MeQuestionsActivity extends AppCompatActivity {

    Button mSubmitButton;
    final int[] mRadioGroupID = {
            R.id.me_radio_group_1,
            R.id.me_radio_group_2,
            R.id.me_radio_group_3,
            R.id.me_radio_group_4,
            R.id.me_radio_group_5,
            R.id.me_radio_group_6,
            R.id.me_radio_group_7,
            R.id.me_radio_group_8,
            R.id.me_radio_group_9,
            R.id.me_radio_group_10,
            R.id.me_radio_group_11,
            R.id.me_radio_group_12,
            R.id.me_radio_group_13,
            R.id.me_radio_group_14,
            R.id.me_radio_group_15,
            R.id.me_radio_group_16,
            R.id.me_radio_group_17,
            R.id.me_radio_group_18,
            R.id.me_radio_group_19,
            R.id.me_radio_group_20
    };

    final int[] mQuestionTextViews = {
            R.id.me_question_1,
            R.id.me_question_2,
            R.id.me_question_3,
            R.id.me_question_4,
            R.id.me_question_5,
            R.id.me_question_6,
            R.id.me_question_7,
            R.id.me_question_8,
            R.id.me_question_9,
            R.id.me_question_10,
            R.id.me_question_11,
            R.id.me_question_12,
            R.id.me_question_13,
            R.id.me_question_14,
            R.id.me_question_15,
            R.id.me_question_16,
            R.id.me_question_17,
            R.id.me_question_18,
            R.id.me_question_19,
            R.id.me_question_20
    };

    int mQuestionAmount = mRadioGroupID.length;

    int[] mSelectedOptions = new int[mQuestionAmount];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_questions);

        mSubmitButton = (Button) findViewById(R.id.activity_me_next_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastScore();
            }
        });
    }

    void toastScore() {
        boolean containsZero = updateScores();

        if (containsZero) {
            Toast.makeText(MeQuestionsActivity.this, "Some questions remain unanswered", Toast.LENGTH_LONG).show();
        } else {
            int sum = 0;
            for (int i : mSelectedOptions) { sum += i; }
            Toast.makeText(MeQuestionsActivity.this, Integer.toString(sum), Toast.LENGTH_LONG).show();
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
            int colour;

            if (mSelectedOptions[i] == -1) {
                colour = getResources().getColor(R.color.colorDangerRed);
            } else {
                colour = getResources().getColor(android.R.color.primary_text_light);
            }

            tv.setTextColor(colour);
        }
    }
}
