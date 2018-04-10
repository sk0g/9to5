package dev.a3820team.a9to5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MeQuestionsActivity extends AppCompatActivity {

    Button mSubmitButton;
    RadioGroup[] mAnswerRadioGroups = new RadioGroup[13];

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

    void toastScore() {
        Toast.makeText(MeQuestionsActivity.this, "this will show the score one day", Toast.LENGTH_LONG).show();
    }
}
