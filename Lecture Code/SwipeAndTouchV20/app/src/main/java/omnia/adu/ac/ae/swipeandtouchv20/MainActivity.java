package omnia.adu.ac.ae.swipeandtouchv20;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureDetector detector;
    private static final String TAG = "MainActivity";

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build a simple red TextView to detect gestures on
        tv = new TextView(this);
        tv.setBackgroundColor(0xFFFF0000); // Red
        tv.setText("Touch me!");
        tv.setTextSize(24);
        tv.setPadding(50, 50, 50, 50);

        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 100;
        params.topMargin = 200;
        layout.addView(tv, params);

        setContentView(layout);

        // Set up gesture detector
        detector = new GestureDetector(this, this);
        detector.setOnDoubleTapListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.w(TAG, "Inside onTouchEvent");
        return detector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        long deltaTime = e2.getEventTime( ) - e1.getEventTime( );
        Log.w( TAG, "Inside onFling: deltaTime (in ms) = " + deltaTime );

        Log.w( TAG, "x1 = " + e1.getRawX( ) + "; y1 = " + e1.getRawY( ) );
        Log.w( TAG, "x2 = " + e2.getRawX( ) + "; y2 = " + e2.getRawY( ) );

        Log.w( TAG, "measured vX (in pixels/second) = " + velocityX );
        Log.w( TAG, "measured vY (in pixels/second) = " + velocityY );

        return true;

    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.w(TAG, "Inside onDown");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.w(TAG, "Inside onLongPress");
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.w(TAG, "Inside onScroll");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.w(TAG, "Inside onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.w(TAG, "Inside onSingleTapUp");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.w(TAG, "Inside onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.w(TAG, "Inside onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.w(TAG, "Inside onSingleTapConfirmed");
        return true;
    }
}
