package cn.aurora_x.android.exdictionary.model.biz;

import android.content.Context;

import java.util.List;

import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.model.database.WordsDBManager;

/**
 * Created by Rinko on 2016/11/29.
 */
public class Review {
    private WordsDBManager dbManager;

    public Review(Context context){
        dbManager=new WordsDBManager(context);
    }

    public List<WordEntity> getList(){
        return dbManager.getAll();
    }
    public void update(String word,boolean check){
        WordEntity newWord=dbManager.findByName(word);
        int correct=newWord.getCorrect();
        int total=newWord.getTotal();
        if(check){
            correct++;
        }
        total++;
        newWord.setCorrect(correct);
        newWord.setTotal(total);
        dbManager.updateByName(word,newWord);
    }
}
