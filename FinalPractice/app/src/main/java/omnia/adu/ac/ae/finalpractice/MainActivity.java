package omnia.adu.ac.ae.finalpractice;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import omnia.adu.ac.ae.finalpractice.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Fragment firstFrag, secondFrag;
    Button firstBtn, secondBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        firstFrag = new FirstFragment();
        secondFrag = new SecondFragment();

        firstBtn = findViewById(R.id.firstBtn);
        secondBtn = findViewById(R.id.secondBtn);


    }

    public void changeFragment(View view)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if(view == findViewById(R.id.firstBtn))
        {
            ft.replace(R.id.emptyFrag, firstFrag);
        }
        else if (view == findViewById(R.id.secondBtn))
        {
            ft.replace(R.id.emptyFrag, secondFrag);
        }

        ft.commit();
    }

}