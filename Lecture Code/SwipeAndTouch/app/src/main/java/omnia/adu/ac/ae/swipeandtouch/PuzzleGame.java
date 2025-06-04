package omnia.adu.ac.ae.swipeandtouch;

import android.util.Log;

import java.util.Random;

public class PuzzleGame {
    public static final int NUMBER_PARTS = 7;
    String [] parts ;
    Random random = new Random( );

    public PuzzleGame( ) {
        parts = new String[NUMBER_PARTS];
        parts[0] = "I LOVE";
        parts[1] = "MOBILE";
        parts[2] = "PROGRAMMING";
        parts[3] = "USING";
        parts[4] = "JAVA";
        parts[5] = "AND";
        parts[6] = "FLUTTER";
    }

    public boolean solved( String [] solution )
    {
        if( solution != null && solution.length == parts.length )
        {
            for( int i = 0; i < parts.length; i++ ) //Looping through elements in solution array to see if they match parts array
            {
                if( !solution[i].equals( parts[i] ) )
                    return false;
            }
            return true;
        }
        else
            return false;
    }

    public String [] scramble( ) //Scrambles the pieces in the parts array until they're no longer in the same order
    {
        String [] scrambled = new String[parts.length];

        for( int i = 0; i < parts.length; i++ )
        {
            scrambled[i] = parts[i]; //Copying as is
            Log.w("RANDOM", " Copying " +scrambled[i]);
        }


        while(solved(scrambled ))
        {
            for( int i = 0; i < scrambled.length; i++ )
            {
                int n = random.nextInt(scrambled.length); //generates a random number from 0 - 7 (exclusive)

                //Swapping the current text piece with one at the random index
                String temp = scrambled[i];
                scrambled[i] = scrambled[n];
                scrambled[n] = temp;
            }
        }

        for ( int i = 0 ; i < scrambled.length ; i++) //Just logging the scrambled text
            Log.w("RANDOM", " Randomize " +scrambled[i]);

        return scrambled;
    }

    public int getNumberOfParts( ) {
        return parts.length;
    }
}