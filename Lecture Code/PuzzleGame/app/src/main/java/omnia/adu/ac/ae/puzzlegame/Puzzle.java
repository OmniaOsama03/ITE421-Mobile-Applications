package omnia.adu.ac.ae.puzzlegame;

import android.util.Log;

import java.util.Random;

public class Puzzle {
  public static final int NUMBER_PARTS = 5;
  String [] parts;
  Random random = new Random( );

  public Puzzle( ) {
    parts = new String[NUMBER_PARTS];
    parts[0] = "I LOVE";
    parts[1] = "MOBILE";
    parts[2] = "PROGRAMMING";
    parts[3] = "USING";
    parts[4] = "JAVA";
  }

  //^You cannot directly shuffle this bc we want to use it as a comparison point to see if the user's order is correct
  //What's we'll do is create a copy and shuffle/scramble that copy

  public boolean solved( String [] solution ) {
    if( solution != null && solution.length == parts.length )
    {
      for( int i = 0; i < parts.length; i++ )
      {
        if( !solution[i].equals( parts[i] ) ) //If a certain element doesn't match the expected element
          return false;
      }
      return true;
    }
    else
      return false;
  }

  public String [] scramble( ) {
    String [] scrambled = new String[parts.length];

    for( int i = 0; i < scrambled.length; i++ ) //Copying each element, one by one. This type of copying is deep copying
      scrambled[i] = parts[i];

    /* Can we do the following to copy? No! It's shallow copying, so
    any change we do to scrambled array will mess up the parts array */

    //scrambled = parts;

    while( solved( scrambled ) ) //
    {
      for( int i = 0; i < scrambled.length; i++ ) {
        int n = random.nextInt( scrambled.length - i ) + i; // We added i to make it random on the correct range.
        String temp = scrambled[i];
        scrambled[i] = scrambled[n];
        scrambled[n] = temp;
      }
    }

    for(int i =  0 ; i < scrambled.length; i++)
      Log.w("Puzzle", scrambled[i]);

    return scrambled;
  }

  public int getNumberOfParts( ) {
    return parts.length;
  }

  public String wordToChange( ) {
    return "MOBILE";
  }

  public String replacementWord( ) {
    return "ANDROID";
  }
}
