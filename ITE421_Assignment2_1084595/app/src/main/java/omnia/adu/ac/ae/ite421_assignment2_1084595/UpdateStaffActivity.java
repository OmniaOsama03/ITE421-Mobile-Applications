package omnia.adu.ac.ae.ite421_assignment2_1084595;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class UpdateStaffActivity extends AppCompatActivity
{

    LinearLayout staffListLayout;
    ArrayList<Staff> staffList;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_staff);

        staffListLayout = findViewById(R.id.staffListLayout);

        //getting list of all staff
        dbManager = new DatabaseManager(this);
        staffList = dbManager.getAllStaff();

        displayStaffList();
    }

    private void displayStaffList()
    {

        for (Staff staff : staffList)
        {
            //Creating a vertical container for the information
            LinearLayout rowContainer = new LinearLayout(this);
            rowContainer.setOrientation(LinearLayout.VERTICAL);
            rowContainer.setPadding(0, 0, 0, 40);

            //Creating a row for id, name, department
            LinearLayout rowLayout1 = new LinearLayout(this);
            rowLayout1.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout1.setPadding(0, 20, 0, 10);


            //Creating a row for position, Salary, Update button
            LinearLayout rowLayout2 = new LinearLayout(this);
            rowLayout2.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout2.setPadding(0, 0, 0, 10);


            //Textview for id
            TextView idTV = new TextView(this);
            idTV.setText(String.valueOf(staff.getId()));
            idTV.setWidth(50);

            //Full Name edittext
            EditText nameET = new EditText(this);
            nameET.setText(staff.getFullName());
            nameET.setWidth(500);

            //Department edittext
            EditText deptET = new EditText(this);
            deptET.setText(staff.getDepartment());
            deptET.setWidth(500);

            //Position edittext
            EditText posET = new EditText(this);
            posET.setText(staff.getPosition());
            posET.setWidth(400);

            //Salary editext
            EditText salaryET = new EditText(this);
            salaryET.setText(String.valueOf(staff.getSalary()));
            salaryET.setWidth(400);

            // Update Button
            Button updateBtn = new Button(this);
            updateBtn.setText("Update");

            updateBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //Getting all info
                    String newName = nameET.getText().toString().trim();
                    String newDept = deptET.getText().toString().trim();
                    String newPos = posET.getText().toString().trim();
                    String salaryStr = salaryET.getText().toString().trim();

                    //Making sure no field is empty
                    if (newName.isEmpty() || newDept.isEmpty() || newPos.isEmpty() || salaryStr.isEmpty())
                    {
                        Toast.makeText(UpdateStaffActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //Making sure the salary is valid
                    double newSalary;
                    try
                    {
                        newSalary = Double.parseDouble(salaryStr);
                    } catch (NumberFormatException e)
                    {
                        Toast.makeText(UpdateStaffActivity.this, "Invalid salary", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Staff updatedStaff = new Staff(staff.getId(), newName, newDept, newPos, newSalary);
                    dbManager.updateStaff(updatedStaff);

                    Toast.makeText(UpdateStaffActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
                }
            });

            //Adding a divider- textview
            TextView divider = new TextView(this);
            divider.setBackgroundColor(Color.LTGRAY);
            divider.setHeight(5);
            divider.setPadding(0, 10, 0, 10);

            //putting together the the rows
            rowLayout1.addView(idTV);
            rowLayout1.addView(nameET);
            rowLayout1.addView(deptET);

            rowLayout2.addView(posET);
            rowLayout2.addView(salaryET);
            rowLayout2.addView(updateBtn);

            rowContainer.addView(rowLayout1);
            rowContainer.addView(rowLayout2);
            rowContainer.addView(divider);

            //Adding the whole container to the vertical layout
            staffListLayout.addView(rowContainer);
        }
    }
}