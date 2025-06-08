package omnia.adu.ac.ae.ite421_assignment2_1084595;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class The8PuzzleGame extends AppCompatActivity
{

    private ArrayList<TextView> tileViews = new ArrayList<>(); //The textiews representing each tile
    private ArrayList<String> tiles = new ArrayList<>(); //The values of the tiles
    private int moveCount = 0;

    private TextView moveCounterTV;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the8_puzzle_game);

        //Adding all tile textviews by ID
        tileViews.add(findViewById(R.id.tile0));
        tileViews.add(findViewById(R.id.tile1));
        tileViews.add(findViewById(R.id.tile2));
        tileViews.add(findViewById(R.id.tile3));
        tileViews.add(findViewById(R.id.tile4));
        tileViews.add(findViewById(R.id.tile5));
        tileViews.add(findViewById(R.id.tile6));
        tileViews.add(findViewById(R.id.tile7));
        tileViews.add(findViewById(R.id.tile8));

        moveCounterTV = findViewById(R.id.moveCounterTV);

        //Initially, the solvable state is there
        for (int i = 1; i <= 8; i++)
        {
            tiles.add(String.valueOf(i));
        }

        tiles.add(""); //adding the empty tile

        //Randomly shuffling the values then displaying them
        Collections.shuffle(tiles);
        updateTiles();
    }

    private void updateTiles()
    {
        for (int i = 0; i < 9; i++)
        {
            TextView tile = tileViews.get(i);
            String value = tiles.get(i);

            tile.setText(value);
            tile.setGravity(Gravity.CENTER);
            tile.setTextSize(24);

            //Setting the color depending on tile type
            if (value.equals(""))
            {
                tile.setBackgroundColor(Color.GRAY);
            } else
            {
                tile.setBackgroundColor(Color.CYAN);
                tile.setTextColor(Color.BLACK);
            }
        }

        moveCounterTV.setText("Number of moves : " + moveCount);

        //Setting the click listeners to make tvs clickable
        for (int i = 0; i < tileViews.size(); i++)
        {
            int index = i;
            TextView currentTile = tileViews.get(i);

            currentTile.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int emptyIndex = tiles.indexOf("");

                    if (isAdjacent(index, emptyIndex)) //is current next to the empty tile?
                    {
                        Collections.swap(tiles, index, emptyIndex);

                        moveCount++;
                        updateTiles(); //update the display with the swap

                        checkWin();
                    }
                }

            });
        }
    }

    private boolean isAdjacent(int i, int j)
    {
        //getting row and column of each index
        int row1 = i / 3;
        int col1 = i % 3;

        int row2 = j / 3;
        int col2 = j % 3;

        //check if the tiles are adjacent (up/down or left/right)
        boolean sameRow = false;
        boolean sameCol = false;

        if(row1 == row2 && Math.abs(col1 - col2) == 1)
            sameRow = true;

        if(col1 == col2 && Math.abs(row1 - row2) == 1)
            sameCol = true;

        return sameRow || sameCol;
    }

    private void checkWin()
    {
        for (int i = 0; i < 8; i++)
        {
            String correct = i + 1 + "";

            if (!tiles.get(i).equals(correct))
            {
                return; //Puzzle isn't solved yet
            }
        }

        //If we reached here, the puzzle is solved
        moveCounterTV.setText("Puzzle Solved in " + moveCount + " moves!");

        disableAllTiles();
    }

    private void disableAllTiles()
    {
        for (TextView tile : tileViews)
        {
            tile.setOnClickListener(null);
        }
    }
}