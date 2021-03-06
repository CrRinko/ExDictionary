package cn.aurora_x.android.exdictionary.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rinko on 2016/11/9.
 */
public class WordsDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ExDictionary";
    public static final int DATABASE_VERSION = 2;

    public static final String DATABASE_TABLENAME="WordsBook";
    public static final String DATABASE_WORDSBOOK_COLUMN_WORD="word";
    public static final String DATABASE_WORDSBOOK_COLUMN_PARAPHRASE="paraphrase";
    public static final String DATABASE_WORDSBOOK_COLUMN_EXAMPLE="example";
    public static final String DATABASE_WORDBOOKS_COLUMN_TRANSLATION="translation";

    public static final String DATABASE_REVIEW_TABLENAME="ReviewBook";
    public static final String DATABASE_REVIEWBOOK_COLUMN_WORD="word";
    public static final String DATABASE_REVIEWBOOK_COLUMN_HITS="hits";
    public static final String DATABASE_REVIEWBOOK_COLUMN_CORRECT="correct";
    public static final String DATABASE_REVIEWBOOK_COLUMN_TOTAL="total";

    public WordsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "+DATABASE_TABLENAME+"(" +
                DATABASE_WORDSBOOK_COLUMN_WORD+" varchar(30) primary key," +
                DATABASE_WORDSBOOK_COLUMN_PARAPHRASE+" varchar(100)," +
                DATABASE_WORDSBOOK_COLUMN_EXAMPLE+" TEXT," +
                DATABASE_WORDBOOKS_COLUMN_TRANSLATION+" varchar(50)," +
                DATABASE_REVIEWBOOK_COLUMN_CORRECT+" integer,"+
                DATABASE_REVIEWBOOK_COLUMN_TOTAL+" integer"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
