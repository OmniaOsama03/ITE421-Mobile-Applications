package omnia.adu.ac.ae.candystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    public ArrayList<Candy> selectAll()
    {
        //Prepare the database for writing
        SQLiteDatabase db = this.getWritableDatabase( );

        //Construct the query
        String sqlQuery = "select * from " + TABLE_NAME;

        //Pass the query
        Cursor cursor = db.rawQuery( sqlQuery, null );

        //Create an arraylist and iterate through it to create and add Candy objects
        ArrayList<Candy> candies = new ArrayList<>( );
        while ( cursor.moveToNext( ) )
        {
            //Create the object based on the info in the current row
            Candy currentCandy  = new Candy(
                    Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ),
                    cursor.getFloat( 2 ) );

            //Add the candy object to the arraylist.
            candies.add(currentCandy );
        }

        //Close the db to avoid vulnerabilities and return the arraylist of objects
        db.close( );
        return candies;
    }

    public Candy selectById( int id ) {
        String sqlQuery = "select * from " + TABLE_NAME + " where id " + " = " + id;

        //Open the database for writing and pass the query to Cursor
        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        //Use moveToFirst() to move to the first row of the result (if exists) and return it
        Candy candy = null;

        if( cursor.moveToFirst( ) )
            candy = new Candy(
                    Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ),
                    cursor.getFloat( 2 ) );

        return candy;
    }

    public void deleteById( int id )
    {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_NAME + " where id " + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }

    public void updateById(int id, String name, double price) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "UPDATE " + TABLE_NAME + " SET name = '" + name + "', price = " + price + " WHERE id = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }

}
