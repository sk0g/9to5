package dev.a3820team.a9to5;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

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
            R.id.me_radio_group_13
    };
    int[] mSelectedOptions = new int[13];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_questions);

        mSubmitButton = (Button) findViewById(R.id.activity_me_submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastScore();
            }
        });
    }

    void toastScore() {
        updateScores();

        boolean containsZero = false;
        for (int i : mSelectedOptions) {
            if (i == -1) { containsZero = true; break; }
        }

        if (containsZero) {
            Toast.makeText(MeQuestionsActivity.this, "Some questions remain unanswered", Toast.LENGTH_LONG).show();
        } else {
            int sum = 0;
            for (int i : mSelectedOptions) { sum += i; }
            Toast.makeText(MeQuestionsActivity.this, Integer.toString(sum), Toast.LENGTH_LONG).show();
        }
    }

    void updateScores() {
        for (int i = 0; i < 13; i++) {
            RadioGroup radioGroup = (RadioGroup) findViewById(mRadioGroupID[i]);

            int buttonID = radioGroup.getCheckedRadioButtonId();

            if (buttonID == -1) { mSelectedOptions[i] = -1; break; }
            else {
                String checkedId = getResources().getResourceEntryName(buttonID);

                if (checkedId.endsWith("1")) { mSelectedOptions[i] = 1; }
                else if (checkedId.endsWith("2")) { mSelectedOptions[i] = 2; }
                else if (checkedId.endsWith("3")) { mSelectedOptions[i] = 3; }
            }
        }
    }
}
