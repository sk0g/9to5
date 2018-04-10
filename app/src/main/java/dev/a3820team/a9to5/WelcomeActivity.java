package dev.a3820team.a9to5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sk0g on 10/04/2018.
 */

public class WelcomeActivity extends AppCompatActivity {

    Button mStartQuizButton;
    TextView mMainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        mMainText = (TextView) findViewById(R.id.main_screen_long_text);
        mMainText.setText(getText(R.string.welcome_page_middle_text));

        mStartQuizButton = (Button) findViewById(R.id.start_quiz_button);
        mStartQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToQuiz();
            }
        });
    }

    void switchToQuiz() {
        setContentView(R.layout.activity_me_questions);
    }
}
