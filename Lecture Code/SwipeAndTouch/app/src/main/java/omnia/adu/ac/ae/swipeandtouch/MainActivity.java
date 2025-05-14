package omnia.adu.ac.ae.swipeandtouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnTouchListener{
    public static int STATUS_BAR_HEIGHT = 24; // in dp
    public static int ACTION_BAR_HEIGHT = 56; // in dp
    private PuzzleGameView puzzleView;
    private PuzzleGame puzzle;
    private static final String MA ="Mobile";
    int count = 1;
    int index1, index2;

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        puzzle = new PuzzleGame( );

        Point size = new Point( );
        getWindowManager( ).getDefaultDisplay( ).getSize( size );
        int screenHeight = size.y;
        int puzzleWidth = size.x;

        Resources res = getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        float pixelDensity = metrics.density;

        TypedValue tv = new TypedValue( );
        int actionBarHeight = ( int ) ( pixelDensity * ACTION_BAR_HEIGHT );
        if( getTheme( ).resolveAttribute( android.R.attr.actionBarSize,
                tv, true ) )
            actionBarHeight = TypedValue.complexToDimensionPixelSize( tv.data,
                    metrics );

        int statusBarHeight = ( int ) ( pixelDensity * STATUS_BAR_HEIGHT );
        Toast.makeText(this, " values is " + statusBarHeight, Toast.LENGTH_SHORT).show();
        int resourceId =
                res.getIdentifier( "status_bar_height", "dimen", "android" );
        if( resourceId != 0 )  // found resource for status bar height
            statusBarHeight = res.getDimensionPixelSize(resourceId);



        //  int puzzleHeight = screenHeight - statusBarHeight - actionBarHeight;
        int puzzleHeight = screenHeight - statusBarHeight -actionBarHeight;
        puzzleView = new PuzzleGameView( this, puzzleWidth, puzzleHeight,
                puzzle.getNumberOfParts( ) );
        String [] scrambled = puzzle.scramble( );
        puzzleView.fillGui( scrambled );
        puzzleView.enableListener( this );
        setContentView( puzzleView );
    }

    public boolean onTouch( View v, MotionEvent event ) {
        int index = puzzleView.indexOfTextView( v );
        int action = event.getAction( );
        switch( action ) {
            case MotionEvent.ACTION_DOWN:
                // initialize data before move
                Log.w(MA,"DOWN " + event.getX() +" " +
                        event.getY());
                puzzleView.updateStartPositions( index, ( int ) event.getY( ) );
                // bring v to front
                puzzleView.bringChildToFront( v );
                break;
            case MotionEvent.ACTION_MOVE:
                Log.w(MA,"SWIPING " + event.getX() +" " +
                        event.getY());
                // update y position of TextView being moved
                puzzleView.moveTextViewVertically( index, ( int ) event.getY( ) );
                break;
            case MotionEvent.ACTION_UP:
                Log.w(MA,"UP " + event.getX() +" " +
                        event.getY());
                // move is complete: swap the 2 TextViews
                int newPosition = puzzleView.tvPosition( index );
                puzzleView.placeTextViewAtPosition( index, newPosition );
                // if user just won, disable listener to stop the game
                if( puzzle.solved( puzzleView.currentSolution( ) ) )
                    puzzleView.disableListener( );
                break;
        }
        return true;
    }
}