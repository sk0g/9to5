package dev.a3820team.a9to5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MeQuestionsActivity extends AppCompatActivity {

    RadioGroup[] mAnswerRadioGroups = new RadioGroup[13];

//    TextView mQuestionText1  = (TextView) findViewById(R.id.me_question_1);
//    RadioGroup mAnswerGroup1 = (RadioGroup) findViewById(R.id.me_radio_group_1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_questions);

        int num = 1;
        String radioGroupName;
        int radioGroupNum;
        for (int i = 0; i < 13; i++) {
            radioGroupName = "R.id.me_radio_group_" + num;
            radioGroupNum = getResources().getIdentifier(radioGroupName, "id", getPackageName());

            mAnswerRadioGroups[i] = (RadioGroup) findViewById(radioGroupNum);

            num += 1;
        }
    }
}
