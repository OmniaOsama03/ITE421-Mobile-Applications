package omnia.adu.ac.ae.candystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        //P.S Written like that for readibility.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    //P.S Dr didn't make this one yet
    public void insertCandy(Candy candy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", candy.getName());
        values.put("price", candy.getPrice());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
