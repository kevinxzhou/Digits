package amita.safecracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Twins on 24/07/2015.
 */
public class Home extends Activity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "yBo3DqaGXDbcE3cGugMDRQwKK";
    private static final String TWITTER_SECRET = "LQWF2vrV1e0bPzEfPVF8bLhBj6fstEQraSwGs8RrrjrkGcdZJz";

    Context context;//needed to start a new intent;
    Button start;
    Button multiplayer;
   Button instructions;
    ImageView pic;

int imageHeight;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_home);
        start = (Button) findViewById(R.id.startGame);
        multiplayer = (Button) findViewById(R.id.multiplayer);
        instructions =(Button) findViewById(R.id.Instructions);
        context=this;//activity is a subclass of context
        Intent i = getIntent();

        //declaring fonts
        Typeface tf_light = Typeface.createFromAsset(getAssets(),
                "fonts/font_light.ttf");

        //find screen width
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //scale picture and text
        pic = (ImageView) findViewById(R.id.imageView3);
        scaleImage(pic,metrics.widthPixels);

        //to center the pic in code
        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(pic.getLayoutParams());

        marginParams.setMargins((int)(0.05*getWidth()), (int)(0.05*getHeight()), (int)(0.05*getWidth()), (int)(0.05*getHeight()));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
        //layoutParams.setMargins(0, (int)(0.08*getHeight()), 0, (int)(0.05*getHeight()));
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //pic.setLayoutParams(marginParams);
        pic.setLayoutParams(layoutParams);



        //creating the new buttons
        start.setTextSize((int) (0.025 * getWidth()));
        start.setTypeface(tf_light);
        multiplayer.setTextSize((int) (0.025 * getWidth()));
        multiplayer.setTypeface(tf_light);
        instructions.setTextSize((int) (0.025 * getWidth()));
        instructions.setTypeface(tf_light);
        click1();
        click2();
        click3();
        //added code
        start.setGravity(Gravity.CENTER);
        start.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (0.05 * imageHeight));
        instructions.setGravity(Gravity.CENTER);
        instructions.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (0.05 * imageHeight));
       multiplayer.setGravity(Gravity.CENTER);
       multiplayer.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) (0.05 * imageHeight));
    }

    private void scaleImage(ImageView view, int boundBoxInDp)
    {
        // Get the ImageView and its bitmap
        Drawable drawing = view.getDrawable();
        Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();

        // Get current dimensions
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) boundBoxInDp) / width;
        float yScale = ((float) boundBoxInDp) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;

        // Create a matrix for the scaling and add the scaling data
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);
        width = scaledBitmap.getWidth();
        height = scaledBitmap.getHeight();

        // Apply the scaled bitmap
        view.setImageDrawable(result);

        // Now change ImageView's dimensions to match the scaled image
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
        imageHeight=params.height;

    }
    private int dpToPx(int dp)
    {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }



    public float getWidth(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        return screenWidth;

    }

    public float getHeight()
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        return height;
    }

    public void click1() {
        //Select a specific button to bundle it with the action you want
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cal = new Intent(getApplicationContext(), Instructions5.class);
                // passing array index
                startActivity(cal);
                //finish();
            }
        });
    }


    public void click2() {
        //Select a specific button to bundle it with the action you want
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cal = new Intent(getApplicationContext(), Instructions2.class);
                // passing array index
                startActivity(cal);
               // finish();
            }
        });
    }

    public void click3() {
        //Select a specific button to bundle it with the action you want
        multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cal = new Intent(getApplicationContext(), Instructions5.class);
                // passing array index
                startActivity(cal);
                // finish();
            }
        });
    }

}
