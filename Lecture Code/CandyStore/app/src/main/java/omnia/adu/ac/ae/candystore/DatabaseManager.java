package omnia.adu.ac.ae.candystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "CandyDB";
    private static final String TABLE_NAME = "candy";
    public DatabaseManager(@Nullable Context context) {
        // old: super(context, name, factory, version);
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table "+ TABLE_NAME+ "(id integer primary key autoincrement, name text, price real)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public void insertCandy(Candy candy) {
        //Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();

        //Construct the string that holds the SQL statement
        String sqlInsert = "insert into " + TABLE_NAME + " values(null, '" + candy.getName() + "', " + candy.getPrice() + ")";

        //Execute the statement
        db.execSQL(sqlInsert);
        Log.w("CANDY INSERT", "The candy " + candy + " has been inserted!");

        //Close the db to avoid keeping it valnerable
        db.close();
    }
}
