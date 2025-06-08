package omnia.adu.ac.ae.ite421_assignment2_1084595;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import omnia.adu.ac.ae.ite421_assignment2_1084595.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    DatabaseManager dbManager;
    ArrayList<Staff> staffList;
    LinearLayout staffListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        //Getting the linear layout from the xml
        staffListLayout = findViewById(R.id.staffListLayout);

        dbManager = new DatabaseManager(this);

        //Displaying the GUI
        displayList();
    }

    public void onStart()
    {
        super.onStart();

        displayList();
    }

    private void displayList()
    {
        staffList = dbManager.getAllStaff();

        staffListLayout.removeAllViews(); // so that we don't end up with duplicates

        for (Staff staff : staffList)
        {
            //Creating a text view for each staff
            TextView staffView = new TextView(this);

            staffView.setText(" Name: " + staff.getFullName()
                    + "\n Department: " + staff.getDepartment()
                    + "\n Position: " + staff.getPosition()
                    + "\n Salary: " + staff.getSalary()
                    + "\n------------------------------");

            staffView.setTextSize(18);
            staffView.setPadding(10, 20, 10, 40);

            //adding the staff tv to the linear layout
            staffListLayout.addView(staffView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        if (id == R.id.add)
        {
            Intent i = new Intent(this, AddStaffActivity.class);
            startActivity(i);
            return true;

        } else if (id == R.id.update)
        {
            Intent i = new Intent(this, UpdateStaffActivity.class);
            startActivity(i);
            return true;

        } else if (id == R.id.delete)
        {
            Intent i = new Intent(this, DeleteStaffActivity.class);
            startActivity(i);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}