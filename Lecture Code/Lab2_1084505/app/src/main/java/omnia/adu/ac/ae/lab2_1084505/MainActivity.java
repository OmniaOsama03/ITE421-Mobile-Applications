package omnia.adu.ac.ae.lab2_1084505;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        implements View.OnTouchListener
{
    public static int STATUS_BAR_HEIGHT = 24; // in dp

    private PuzzleGameView puzzleView;
    private PuzzleGame puzzle;
    private static final String MA ="Mobile";


    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        //getting the sentence the user selected
        Intent intent = getIntent();
        String sentence = intent.getStringExtra("sentence");

        String[] words = "This is a default sentence!".split(" ");

        if(sentence != null && !sentence.isEmpty())
            words = sentence.split(" ");

        puzzle = new PuzzleGame(words);

        Point size = new Point( );
        getWindowManager( ).getDefaultDisplay( ).getSize( size );
        int screenHeight = size.y;
        int puzzleWidth = size.x;

        Resources res = getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        float pixelDensity = metrics.density;

        TypedValue tv = new TypedValue( );


        int statusBarHeight = ( int ) ( pixelDensity * STATUS_BAR_HEIGHT );

        int resourceId =
                res.getIdentifier( "status_bar_height", "dimen", "android" );
        if( resourceId != 0 )  // found resource for status bar height
            statusBarHeight = res.getDimensionPixelSize(resourceId);



       int puzzleHeight = screenHeight - statusBarHeight;
        puzzleView = new PuzzleGameView( this, puzzleWidth, puzzleHeight, puzzle.getNumberOfParts( ) );
        String [] scrambled = puzzle.scramble( );
        puzzleView.fillGui( scrambled );
        puzzleView.enableListener( this );
        setContentView( puzzleView );
    }

    public boolean onTouch( View v, MotionEvent event ) {
        int index = puzzleView.indexOfTextView( v );

        int action = event.getAction( );

        switch( action )
        {
            case MotionEvent.ACTION_DOWN:

                puzzleView.updateStartPositions( index, ( int ) event.getY( ) );
                // bring v to front
                puzzleView.bringChildToFront( v );
                break;

            case MotionEvent.ACTION_MOVE:

                // update y position of TextView being moved
                puzzleView.moveTextViewVertically( index, ( int ) event.getY( ) );
                break;

            case MotionEvent.ACTION_UP:
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