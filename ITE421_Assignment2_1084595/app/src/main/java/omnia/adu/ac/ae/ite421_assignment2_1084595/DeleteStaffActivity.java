package omnia.adu.ac.ae.ite421_assignment2_1084595;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DeleteStaffActivity extends AppCompatActivity {

    LinearLayout deleteListLayout;
    ArrayList<Staff> staffList;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_staff);

        deleteListLayout = findViewById(R.id.deleteListLayout);

        dbManager = new DatabaseManager(this);

        loadStaffList();
    }

    private void loadStaffList()
    {
        staffList = dbManager.getAllStaff();

        deleteListLayout.removeAllViews(); //To make sure there are no duplicates

        for (Staff staff : staffList)
        {
            //Horizontal layout for staff into + delete button
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setPadding(0, 20, 0, 20);

            //Textview to display staff information
            TextView infoTV = new TextView(this);
            infoTV.setText("ID: " + staff.getId() + " | " + staff.getFullName());
            infoTV.setWidth(600);

            //The delete button
            Button deleteBtn = new Button(this);
            deleteBtn.setText("Delete");

            deleteBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    dbManager.deleteStaff(staff);
                    Toast.makeText(DeleteStaffActivity.this, "Staff deleted", Toast.LENGTH_SHORT).show();
                    loadStaffList(); //refreshing the list
                }
            });

            //Adding the info and button to the horizontal layout
            rowLayout.addView(infoTV);
            rowLayout.addView(deleteBtn);

            //Adding the staff object to the complete list
            deleteListLayout.addView(rowLayout);
        }
    }
}