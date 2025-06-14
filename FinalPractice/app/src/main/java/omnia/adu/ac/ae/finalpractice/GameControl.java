package omnia.adu.ac.ae.finalpractice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GameControl extends Fragment {

    TextView status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_control, container, false);

        status = view.findViewById(R.id.guessesCount);
        status.setText("Oh no! Didn't work!");

        return view;
    }

    public void onStart()
    {
        super.onStart();

        MainHangMan main = (MainHangMan) getActivity();

        HangMan game = main.getGame();

        status.setText("Guesses left: " + game.getGuessesLeft());

    }

    public void guessLetter(View view) {
    }
}