package cn.aurora_x.android.exdictionary.model.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class WordsContentProvider extends ContentProvider {
    public static final int URI_CODE_WORDS = 1;
    SQLiteDatabase db;

    public WordsContentProvider() {
    }

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(UriContract.AUTHORITY, UriContract.WORDS_PATH, URI_CODE_WORDS);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int status=0;
        switch (getType(uri)){
            case UriContract.WORDS_PATH:
                status=db.delete(WordsDBHelper.DATABASE_TABLENAME,selection,selectionArgs);
                getContext().getContentResolver().notifyChange(uri,null);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri:" + uri);
        }
        return status;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_CODE_WORDS:
                return UriContract.WORDS_PATH;
            default:
                throw new IllegalArgumentException("Unknown Uri:" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (getType(uri)) {
            case UriContract.WORDS_PATH:
                String word = values.getAsString(WordsDBHelper.DATABASE_WORDSBOOK_COLUMN_WORD);
                if (db.insert(WordsDBHelper.DATABASE_TABLENAME, null, values) > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }else {
                    throw new SQLException("Insert failed (URI="+uri+")");
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri:" + uri);
        }
        return uri;
    }

    @Override
    public boolean onCreate() {
        db=new WordsDBHelper(getContext()).getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor=null;
        switch (getType(uri)){
            case UriContract.WORDS_PATH:
                cursor=db.query(WordsDBHelper.DATABASE_TABLENAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri:" + uri);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int status=0;
        switch (getType(uri)){
            case UriContract.WORDS_PATH:
                status=db.update(WordsDBHelper.DATABASE_TABLENAME,values,selection,selectionArgs);
                getContext().getContentResolver().notifyChange(uri,null);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri:" + uri);
        }
        return status;
    }
}
