package dev.a3820team.a9to5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static dev.a3820team.a9to5.MeQuestionsActivity.ME_PREFS_NAME;

/**
 * Created by sk0g on 10/04/2018.
 */

public class WelcomeActivity extends AppCompatActivity {

    Button mStartQuizButton, mMenuButton;
    TextView mMainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        deletePreviousResults();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_welcome_screen, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.welcome_screen_read_code_button) {
            showCodeReadScreen();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void switchToQuiz() {
        Intent quizIntent = new Intent(this, MeQuestionsActivity.class);
        startActivity(quizIntent);
    }

    private void deletePreviousResults() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                ME_PREFS_NAME, Context.MODE_PRIVATE
        );

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }

    public void showCodeReadScreen() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        setTheme(R.style.CustomColourScheme);
        builder.setTitle("Restore Progress from Code");
        builder.setMessage("Please enter the save code below");

        builder.setPositiveButton("Read Code", null);
        builder.setNegativeButton(R.string.confirmation_screen_cancel, null);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}
