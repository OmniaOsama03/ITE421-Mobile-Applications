package omnia.adu.ac.ae.candystore;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import omnia.adu.ac.ae.candystore.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    //^Understand the concepts, things like binding.

    private DatabaseManager dbManager;
    private double total;
    private int buttonWidth;

    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        scrollView = findViewById(R.id.scrollView);

        //Added code from BB, but it isn't there
        updateView();

    }

    public void updateView()
    {
        ArrayList<Candy> candies = dbManager.selectAll();

        //Added code from BB, but it isn't there
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.addItem)
        {
            //Create a notification
            Toast.makeText(this, "ADD is selected", Toast.LENGTH_LONG).show();

            //Create an intent
            Intent insertIntent = new Intent(this, InsertActivity.class);
            startActivity(insertIntent);

            return true;
        }else if (item.getItemId() == R.id.updateItem)
        {
            Toast.makeText(this, "UPDATE is selected", Toast.LENGTH_LONG).show();

            Intent updateIntent = new Intent(this, UpdateActivity.class);
            this.startActivity(updateIntent);

            return true;
        }else if (item.getItemId() == R.id.deleteItem)
        {
            Toast.makeText(this, "DELETE is selected", Toast.LENGTH_LONG).show();

            Intent deleteIntent =  new Intent(this, DeleteActivity.class);
            this.startActivity(deleteIntent);

            return true;

        }else //Last one left is exit item
        {
            System.exit(0); //To allow the system to exit with no error
        }

        //Note- don't use switch here. it won't work.

        return super.onOptionsItemSelected(item);
    }

    public void onStart()
    {
        super.onStart();
        updateView();
    }

    private class ButtonHandler implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            total += ((CandyButton)v).getPrice();

            double formattedTotal = (int) (total * 100)/100.0;

            Toast.makeText(MainActivity.this, "New total is: AED" + total, Toast.LENGTH_LONG).show();
            //Because we're in an inner class, the context must be explicitly mentioned.

            updateView();
        }
    }


}