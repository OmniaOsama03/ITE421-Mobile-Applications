package omnia.adu.ac.ae.ite421_assignment2_1084595;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddStaffActivity extends AppCompatActivity
{

    EditText fullNameET, departmentET, positionET, salaryET;
    Button addButton;

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        fullNameET = findViewById(R.id.fullNameET);
        departmentET = findViewById(R.id.departmentET);
        positionET = findViewById(R.id.positionET);
        salaryET = findViewById(R.id.salaryET);
        addButton = findViewById(R.id.addButton);

        dbManager = new DatabaseManager(this);
    }

    public void submit(View v)
    {
        //Getting all the info + trimming whitespaces
        String name = fullNameET.getText().toString().trim();
        String dept = departmentET.getText().toString().trim();
        String pos = positionET.getText().toString().trim();
        String salaryStr = salaryET.getText().toString().trim();

        //Making sure none of the fields are empty
        if (name.isEmpty() || dept.isEmpty() || pos.isEmpty() || salaryStr.isEmpty())
        {
            Toast.makeText(AddStaffActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double salary;

        try //To make sure salary is valid
        {
            salary = Double.parseDouble(salaryStr);

        } catch (NumberFormatException e)
        {
            Toast.makeText(AddStaffActivity.this, "Invalid salary", Toast.LENGTH_SHORT).show();
            return;
        }

        Staff newStaff = new Staff(0, name, dept, pos, salary);
        dbManager.addStaff(newStaff);

        Toast.makeText(AddStaffActivity.this, "Staff added!", Toast.LENGTH_SHORT).show();

        finish(); //returns to MainActivity
    }


}