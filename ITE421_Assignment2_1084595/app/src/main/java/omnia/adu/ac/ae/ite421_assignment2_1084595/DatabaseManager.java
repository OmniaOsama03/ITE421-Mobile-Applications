package omnia.adu.ac.ae.ite421_assignment2_1084595;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import androidx.annotation.Nullable;


import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper
{

    private static final String DB_NAME = "StaffDB";
        private static final String TABLE_NAME = "Staff";
    private static final int DB_VERSION = 2;

    public DatabaseManager(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTable =
                "CREATE TABLE "+ TABLE_NAME + " (" +
                "staff_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "full_name TEXT, " +
                "department_name TEXT, " +
                "position TEXT, " +
                "salary REAL)";

        db.execSQL(createTable);

        //Adding sample staff
        addSampleStaff();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addStaff(Staff newStaff)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //Creating the insert query
        String insert = "INSERT INTO " + TABLE_NAME + " VALUES (null, " +
                "'" + newStaff.getFullName() + "', '" + newStaff.getDepartment() + "', '" + newStaff.getPosition() + "', " + newStaff.getSalary() + ")";

        db.execSQL(insert);

        db.close();
    }

    public void updateStaff(Staff staff)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String update = "UPDATE " + TABLE_NAME + " SET " +
                "full_name='" + staff.getFullName() + "', " +
                "department_name='" + staff.getDepartment() + "', " +
                "position='" + staff.getPosition() + "', " +
                "salary=" + staff.getSalary() +
                " WHERE staff_id=" + staff.getId();

        db.execSQL(update);
        db.close();
    }

        public void deleteStaff(Staff staff)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String delete = "DELETE FROM " + TABLE_NAME + " WHERE staff_id=" + staff.getId();
        db.execSQL(delete);

        db.close();
    }

    public ArrayList<Staff> getAllStaff()
    {
        ArrayList<Staff> staffList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.moveToFirst())
        {
            do
            {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String dept = cursor.getString(2);
                String pos = cursor.getString(3);
                double salary = cursor.getDouble(4);

                Staff s = new Staff(id, name, dept, pos, salary);
                staffList.add(s);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return staffList;
    }

    private void addSampleStaff()
    {
        addStaff(new Staff(0, "Sara Al Nahyan", "HR", "Manager", 12000));
        addStaff(new Staff(0, "Omnia Osama", "IT", "Developer", 10000));
        addStaff(new Staff(0, "Noor Al Suwaidi", "Marketing", "Specialist", 8500));
        addStaff(new Staff(0, "Khalid Al Nuaimi", "Finance", "Accountant", 9500));
        addStaff(new Staff(0, "Mariam Al Mazrouei", "Design", "Lead", 11000));
        addStaff(new Staff(0, "Aala Osama Ahmed", "HR", "Manager", 12000));
        addStaff(new Staff(0, "Arwa Osama Ahmed", "IT", "Developer", 10000));
        addStaff(new Staff(0, "Sara Imad Hamdan", "Marketing", "Specialist", 8500));
        addStaff(new Staff(0, "Noura Adnan Alhosani", "Finance", "Accountant", 9500));
        addStaff(new Staff(0, "Nancy Amr Kotb", "Design", "Lead", 11000));
    }

}
