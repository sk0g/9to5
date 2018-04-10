package dev.a3820team.a9to5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * Created by sk0g on 10/04/2018.
 */

public class WelcomeActivity extends AppCompatActivity {

    TextView mMainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        mMainText = (TextView) findViewById(R.id.main_screen_long_text);
        mMainText.setText(getText(R.string.welcome_page_middle_text));
    }
}
