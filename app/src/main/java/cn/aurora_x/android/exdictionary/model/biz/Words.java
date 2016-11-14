package cn.aurora_x.android.exdictionary.model.biz;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.model.database.WordsDBManager;
import cn.aurora_x.android.exdictionary.util.HttpRequestUtil;
import cn.aurora_x.android.exdictionary.util.YoudaoQueryContract;

/**
 * Created by Rinko on 2016/11/10.
 */
public class Words {
    private WordsDBManager dbManager;

    public Words(Context context) {
        dbManager = new WordsDBManager(context);
    }

    public WordEntity find(String word) {
        return dbManager.findByName(word);
    }

    public void add(final String word, final String paraphrase,final String example,final OnAddWordListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final WordEntity wordEntity = new WordEntity(word, paraphrase, example, null);
                String data = HttpRequestUtil.sendJsonRequest(YoudaoQueryContract.getURL(word));
                JSONTokener jsonTokener = new JSONTokener(data);
                try {
                    JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
                    int errorCode=jsonObject.getInt(YoudaoQueryContract.JSON_ERROR_CODE);
                    if(errorCode==0){
                        JSONArray translations=jsonObject.getJSONArray(YoudaoQueryContract.JSON_TRANSLATION);
                        StringBuilder translationBuilder=new StringBuilder();
                        for(int i=0;i<translations.length();i++){
                            translationBuilder.append(translations.getString(i)+";");
                        }
                        translationBuilder.deleteCharAt(translationBuilder.length()-1);
                        wordEntity.setTranslation(translationBuilder.toString());
                        dbManager.add(wordEntity);
                        listener.success();
                    }else {
                        listener.fail();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }).start();
    }

    public List<String> getSimpleWordsList() {
        return dbManager.getWordsList();
    }

    public void delete(String word) {
        dbManager.deleteByName(word);
    }

    public void update(final String word, String paraphrase, String example) {
        WordEntity wordEntity = dbManager.findByName(word);
        wordEntity.setParaphrase(paraphrase);
        wordEntity.setExample(example);
        dbManager.updateByName(word, wordEntity);
    }

    public List<String> searchWords(String queryWord) {
        List<WordEntity> entityList = dbManager.findAllByName(queryWord);
        List<String> simpleList = new ArrayList<String>();
        for (WordEntity entity : entityList) {
            simpleList.add(entity.getWord());
        }
        return simpleList;
    }

    public interface OnAddWordListener {
        public void success();

        public void fail();
    }
}
