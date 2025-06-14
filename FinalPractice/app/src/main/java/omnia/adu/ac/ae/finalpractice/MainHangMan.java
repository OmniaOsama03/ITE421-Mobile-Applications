package omnia.adu.ac.ae.finalpractice;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainHangMan extends AppCompatActivity {

    private HangMan game;
    GameControl gameControl;
    GameUpdate gameUpdate;
    GameScore gameScore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hang_man);


        gameControl = new GameControl();
        gameUpdate  = new GameUpdate();
        gameScore = new GameScore();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(R.id.gameUpdateLayout, gameUpdate);
        ft.add(R.id.gameScoreLayout, gameScore);

        ft.commit();

        if(game == null)
            game = new HangMan(HangMan.DEFAULT_GUESSES);


    }

    public HangMan getGame()
    {
        return this.game;
    }
}