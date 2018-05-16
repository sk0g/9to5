package dev.a3820team.a9to5;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class FinalResultActivity extends AppCompatActivity {

    ImageView mTopSemiCircle, mBottomSemiCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        mTopSemiCircle    = (ImageView) findViewById(R.id.final_result_top_semi_circle);
        mTopSemiCircle.setColorFilter(R.color.customYellow);
        mBottomSemiCircle = (ImageView) findViewById(R.id.final_result_bottom_semi_circle);
        mBottomSemiCircle.setColorFilter(R.color.customGreen);
    }
}
