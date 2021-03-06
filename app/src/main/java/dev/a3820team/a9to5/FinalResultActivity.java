package dev.a3820team.a9to5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static dev.a3820team.a9to5.MeQuestionsActivity.showConfirmationDialog;
import static dev.a3820team.a9to5.MeResultsActivity.getTotalMeScore;
import static dev.a3820team.a9to5.OrgResultsActivity.getTotalOrgScore;
import static dev.a3820team.a9to5.SaveCode.generateCode;

public class FinalResultActivity extends AppCompatActivity {

    ImageView mTopSemiCircle, mBottomSemiCircle;
    TextView mResultColourDescriptor, mResultLongText;
    Button mPreviousButton, mStartOverButton;
    int mMeScore, mOrgScore;

    final int[] mDescriptorTitles = {
            R.string.green_on_green_title,
            R.string.green_on_yellow_title,
            R.string.green_on_red_title,
            R.string.yellow_on_green_title,
            R.string.yellow_on_yellow_title,
            R.string.yellow_on_red_title,
            R.string.red_on_green_title,
            R.string.red_on_yellow_title,
            R.string.red_on_red_title
    };

    final int[] mDescriptorTexts = {
            R.string.green_on_green_text,
            R.string.green_on_yellow_text,
            R.string.green_on_red_text,
            R.string.yellow_on_green_text,
            R.string.yellow_on_yellow_text,
            R.string.yellow_on_red_text,
            R.string.red_on_green_text,
            R.string.red_on_yellow_text,
            R.string.red_on_red_text
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        mTopSemiCircle    = (ImageView) findViewById(R.id.final_result_top_semi_circle);
        mBottomSemiCircle = (ImageView) findViewById(R.id.final_result_bottom_semi_circle);

        mResultColourDescriptor = (TextView) findViewById(R.id.final_result_colour_descriptor);
        mResultLongText = (TextView) findViewById(R.id.final_result_long_text);

        mMeScore  = getTotalMeScore(getApplicationContext());
        mOrgScore = getTotalOrgScore(getApplicationContext());

        mPreviousButton = (Button) findViewById(R.id.final_result_activity_previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToOrgResults();
            }
        });

        final Context context = this;

        mStartOverButton = (Button) findViewById(R.id.final_result_start_over);
        mStartOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog(context);
            }
        });

        updateTextFields();
        updateSemiCircleColours();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_final_results, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.final_result_view_code_button) {
            showCodeScreen();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateTextFields() {
        int descriptorIndices = getIndex();

        mResultColourDescriptor.setText(
                getResources().getText(mDescriptorTitles[descriptorIndices])
        );

        mResultLongText.setText(
                getResources().getText(mDescriptorTexts[descriptorIndices])
        );
    }

    private void updateSemiCircleColours() {
        int topColourID, bottomColourID;

        topColourID = getBoxColour(mMeScore);
        bottomColourID = getBoxColour(mOrgScore);

        mTopSemiCircle.setColorFilter(getResources().getColor(topColourID));
        mBottomSemiCircle.setColorFilter(getResources().getColor(bottomColourID));
    }

    private int getBoxColour(int score) {
        int colourID;
        if (score <= 35) { colourID = R.color.customRed; }
        else if (score <= 50) { colourID = R.color.customYellow; }
        else { colourID = R.color.customGreen; }

        return colourID;
    }

    private int getIndex() {
        // Returns the correct index for descriptor_title and descriptor_text arrays
        /* Where g == green, y == yellow and r == red, first is MeScore, and second is OrgScore
           gxg = 0, gxy = 1, gxr = 2,
           yxg = 3, yxy = 4, yxr = 5,
           rxg = 6, rxy = 7, rxr = 8
         */
        int result = 0;

        if (mMeScore <= 35) { result += 6; }
        else if (mMeScore <= 50) { result += 3; }

        if (mOrgScore <= 35) { result += 2; }
        else if (mOrgScore <= 50) { result += 1; }

        return result;
    }

    private void switchToOrgResults() {
        Intent quizIntent = new Intent(this, OrgResultsActivity.class);
        startActivity(quizIntent);
    }

    public void showCodeScreen() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(generateCode(this));
        builder.setMessage(getString(R.string.final_result_popup_body_text));

        builder.setPositiveButton("Close", null);

        final AlertDialog dialog = builder.create();
        dialog.show();
    }
}
