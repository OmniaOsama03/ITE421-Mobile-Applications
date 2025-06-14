package omnia.adu.ac.ae.finalpractice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GameScore extends Fragment {

    TextView result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_score, container, false);
        result = view.findViewById(R.id.gameResult);

        result.setText("Good Luck! :D");

        return view;
    }
}