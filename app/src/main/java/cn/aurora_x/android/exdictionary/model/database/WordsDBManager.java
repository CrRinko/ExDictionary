package cn.aurora_x.android.exdictionary.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.aurora_x.android.exdictionary.model.bean.WordEntity;

/**
 * Created by Rinko on 2016/11/9.
 */
public class WordsDBManager {
    private WordsDBHelper helper;
    private SQLiteDatabase db;
    public WordsDBManager(Context context){
        helper=new WordsDBHelper(context);
        db=helper.getWritableDatabase();
    }
    public List<WordEntity> getAll(){
        ArrayList<WordEntity> words=new ArrayList<WordEntity>();
        Cursor cursor=db.rawQuery("select * from WordsBook",null);
        while(cursor.moveToNext()){
            WordEntity wordEntity=new WordEntity();
            wordEntity.setWord(cursor.getString(cursor.getColumnIndex("word")));
            if(!cursor.isNull(cursor.getColumnIndex("paraphrase"))) {
                wordEntity.setParaphrase(cursor.getString(cursor.getColumnIndex("paraphrase")));
            }
            if(!cursor.isNull(cursor.getColumnIndex("example"))){
                wordEntity.setExample(cursor.getString(cursor.getColumnIndex("example")));
            }
            if(!cursor.isNull(cursor.getColumnIndex("translation"))){
                wordEntity.setTranslation(cursor.getString(cursor.getColumnIndex("translation")));
            }
            words.add(wordEntity);
        }
        return  words;
    }
    public void add(WordEntity wordEntity){
        ContentValues values=new ContentValues();
        values.put("word",wordEntity.getWord());
        if (wordEntity.getParaphrase()!=null){
            values.put("paraphrase",wordEntity.getParaphrase());
        }else {
            values.putNull("paraphrase");
        }
        if(wordEntity.getExample()!=null){
            values.put("example",wordEntity.getExample());
        }else{
            values.putNull("example");
        }
        if(wordEntity.getTranslation()!=null){
            values.put("translation",wordEntity.getTranslation());
        }else {
            values.putNull("translation");
        }
        db.insert(WordsDBHelper.DATABASE_TABLENAME,null,values);
    }
    public void deleteByName(String wordName){
        String whereClause="word=?";
        String[] whereArgs={wordName};
        db.delete(WordsDBHelper.DATABASE_TABLENAME,whereClause,whereArgs);
    }
    public void updateByName(String wordName,WordEntity newWord){
        ContentValues values=new ContentValues();
        values.put("word",newWord.getWord());
        if (newWord.getParaphrase()!=null){
            values.put("paraphrase",newWord.getParaphrase());
        }else {
            values.putNull("paraphrase");
        }
        if(newWord.getExample()!=null){
            values.put("example",newWord.getExample());
        }else{
            values.putNull("example");
        }
        if(newWord.getTranslation()!=null){
            values.put("translation",newWord.getTranslation());
        }else {
            values.putNull("translation");
        }
        String whereClause="word=?";
        String[] whereArgs={wordName};
        db.update(WordsDBHelper.DATABASE_TABLENAME,values,whereClause,whereArgs);
    }
    public WordEntity findByName(String wordName){
        Cursor cursor=db.query(WordsDBHelper.DATABASE_TABLENAME,
                new String[]{"word","paraphrase","example","translation"},
                "word=?",new String[]{wordName},null,null,null);
        if (cursor.moveToNext()) {
            String word = cursor.getString(cursor.getColumnIndex("word"));
            String paraphrase = null;
            String example = null;
            String translation = null;
            if (!cursor.isNull(cursor.getColumnIndex("paraphrase"))) {
                paraphrase = cursor.getString(cursor.getColumnIndex("paraphrase"));
            }
            if (!cursor.isNull(cursor.getColumnIndex("example"))) {
                example = cursor.getString(cursor.getColumnIndex("example"));
            }
            if (!cursor.isNull(cursor.getColumnIndex("translation"))) {
                translation = cursor.getString(cursor.getColumnIndex("translation"));
            }
            return new WordEntity(word, paraphrase, example, translation);
        }else {
            return null;
        }
    }
    public List<WordEntity> findAllByName(String wordName){
        Cursor cursor=db.query(WordsDBHelper.DATABASE_TABLENAME,
                new String[]{"word","paraphrase","example","translation"},
                "word like ?",new String[]{"%"+wordName+"%"},null,null,null);
        List<WordEntity> entityList=new ArrayList<WordEntity>();
        while(cursor.moveToNext()) {
            String word = cursor.getString(cursor.getColumnIndex("word"));
            String paraphrase = null;
            String example = null;
            String translation = null;
            if (!cursor.isNull(cursor.getColumnIndex("paraphrase"))) {
                paraphrase = cursor.getString(cursor.getColumnIndex("paraphrase"));
            }
            if (!cursor.isNull(cursor.getColumnIndex("example"))) {
                example = cursor.getString(cursor.getColumnIndex("example"));
            }
            if (!cursor.isNull(cursor.getColumnIndex("translation"))) {
                translation = cursor.getString(cursor.getColumnIndex("translation"));
            }
            entityList.add(new WordEntity(word, paraphrase, example, translation));
        }
        return entityList;
    }
    public List<String> getWordsList(){
        ArrayList<String> words=new ArrayList<String>();
        Cursor cursor=db.rawQuery("select word from "+WordsDBHelper.DATABASE_TABLENAME,null);
        while (cursor.moveToNext()){
            words.add(cursor.getString(cursor.getColumnIndex("word")));
        }
        return words;
    }
}
