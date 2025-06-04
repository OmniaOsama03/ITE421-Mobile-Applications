package omnia.adu.ac.ae.lab2_1084505;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class DatabaseManager extends SQLiteOpenHelper
{

    private static final String DB_NAME = "SentencesDB";
    private static final String TABLE_NAME = "Sentences";

    public DatabaseManager(@Nullable Context context)
    {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //First, created the table with one text column
        String createTable = "CREATE TABLE "+ TABLE_NAME +" (sentence TEXT)";
        db.execSQL(createTable);

        //Then, added sentences into an arraylist
        ArrayList<String> sentences = new ArrayList<>();

        sentences.add("Dr.Modafar teaches Mobile Applications!");
        sentences.add("This is a random sentence!");
        sentences.add("Friday is the best day of the week");
        sentences.add("Mobile Applications is a fun course taught by Dr.Modafar");
        sentences.add("Omnia is a senior Software Engineering student");
        sentences.add("Java is the best programming language");
        sentences.add("Omnia is twenty one years of age");

        for(int i = 0; i < sentences.size(); i++)
        {
            String query = "INSERT INTO " + TABLE_NAME + " VALUES ('" + sentences.get(i) + "')";
            db.execSQL(query);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public void insertSentence(String newSentence)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        newSentence = newSentence.replace("'", "''"); //Just in case the user enters '.
        String query = "INSERT INTO Sentences VALUES ('" + newSentence + "')";
        db.execSQL(query);

        db.close();
    }

    public String getRandomSentence() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAllQuery = "SELECT * FROM Sentences";
        Cursor selectAllcursor = db.rawQuery(selectAllQuery, null);

        String getCountQuery = "SELECT COUNT(*) FROM Sentences";
        Cursor countCursor = db.rawQuery(getCountQuery, null);

        int count = 0;

        if(countCursor.moveToNext())
        {
            count = Integer.parseInt(countCursor.getString(0));
        }

        if (count == 0)
        {
            return "No sentence found.";
        }

        Random random = new Random();

        int index = random.nextInt(count);

        String sentence = "";

        int i = 0;
        while (selectAllcursor.moveToNext())
        {
            if (i == index)
            {
                sentence = selectAllcursor.getString(0);
                break;
            }

            i++;
        }

        db.close();
        return sentence;
    }
}
