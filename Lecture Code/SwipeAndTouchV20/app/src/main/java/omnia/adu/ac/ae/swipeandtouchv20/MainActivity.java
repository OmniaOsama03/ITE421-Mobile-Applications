package omnia.adu.ac.ae.swipeandtouchv20;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureDetector detector;
    private String MA = "Main Activity";


    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        detector = new GestureDetector( this, this );
        detector.setOnDoubleTapListener( this );
    }

    public boolean onTouchEvent( MotionEvent event ) {
        Log.w( MA, "Inside onTouchEvent" );
        detector.onTouchEvent( event );
        return true;
    }

    public boolean onFling( MotionEvent e1, MotionEvent e2,
                            final float velocityX, final float velocityY )  {
        Log.w( MA, "Inside onFling" );
        return true;
    }

    public boolean onDown( MotionEvent e ) {
        Log.w( MA, "Inside onDown" );
        return true;
    }

    public void onLongPress( MotionEvent e ) {
        Log.w( MA, "Inside onLongPress" );
    }

    public boolean onScroll( MotionEvent e1, MotionEvent e2,
                             float distanceX, float distanceY ) {
        Log.w( MA, "Inside onScroll" );
        return true;
    }

    public void onShowPress( MotionEvent e ) {
        Log.w( MA, "Inside onShowPress" );
    }

    public boolean onSingleTapUp( MotionEvent e ) {
        Log.w( MA, "Inside onSingleTapUp" );
        return true;
    }

    public boolean onDoubleTap( MotionEvent e ) {
        Log.w( MA, "Inside onDoubleTap" );
        return true;
    }

    public boolean onDoubleTapEvent( MotionEvent e ) {
        Log.w( MA, "Inside onDoubleTapEvent" );
        return true;
    }

    public boolean onSingleTapConfirmed( MotionEvent e ) {
        Log.w( MA, "Inside onSingleTapConfirmed" );
        return true;
    }
}
