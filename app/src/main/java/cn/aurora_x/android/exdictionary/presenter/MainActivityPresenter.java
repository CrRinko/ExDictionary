package cn.aurora_x.android.exdictionary.presenter;

import android.content.res.Configuration;
import android.os.Handler;
import android.util.Log;

import java.util.List;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.model.biz.Words;
import cn.aurora_x.android.exdictionary.view.AddWordDialog;
import cn.aurora_x.android.exdictionary.view.IWordDialog;
import cn.aurora_x.android.exdictionary.view.MainActivity;
import cn.aurora_x.android.exdictionary.view.SearchWordDialog;
import cn.aurora_x.android.exdictionary.view.UpdateWordDialog;
import cn.aurora_x.android.exdictionary.view.WordDetailsFragment;
import cn.aurora_x.android.exdictionary.view.WordsListViewFragment;

/**
 * Created by Rinko on 2016/11/10.
 */
public class MainActivityPresenter implements IDetailsFragmentPresenter{
    private static MainActivityPresenter instance;
    private MainActivity mainActivity;
    private WordsListViewFragment listViewFragment;
    private WordDetailsFragment detailsFragment;
    private Words wordsBiz;
    private Handler uiHandler;

    public static MainActivityPresenter getMainActivityPresenter() {
        if (instance == null) {
            instance = new MainActivityPresenter();
        }
        return instance;
    }

    private MainActivityPresenter() {
        uiHandler = new Handler();
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        wordsBiz = new Words(mainActivity.getApplicationContext());
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setListViewFragment(WordsListViewFragment listViewFragment) {
        this.listViewFragment = listViewFragment;
    }

    public void initListView() {
        listViewFragment.setWordsList(wordsBiz.getSimpleWordsList());
        listViewFragment.refresh();
    }

    public void wordDialogConfrimed(final IWordDialog wordDialog) {
        String word = wordDialog.getWord();
        String paraphrase = wordDialog.getParaphrase();
        String example = wordDialog.getExample();
        if (wordDialog instanceof AddWordDialog) {
            if (word != null && !word.equals("")) {
                wordsBiz.add(word, paraphrase, example, new Words.OnAddWordListener() {
                    @Override
                    public void success() {
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                initListView();
                            }
                        });
                    }
                    @Override
                    public void fail() {
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mainActivity.showToast(mainActivity.getResources().getString(R.string.text_network_error));
                            }
                        });
                    }
                });
            }
        } else if (wordDialog instanceof UpdateWordDialog) {
            wordsBiz.update(word, paraphrase, example);
        } else if (wordDialog instanceof SearchWordDialog) {
            List<String> list = wordsBiz.searchWords(word);
            listViewFragment.setWordsList(list);
            listViewFragment.refresh();
        }
    }

    public void createUpdateDialog(UpdateWordDialog updateWordDialog) {
        WordEntity wordEntity = wordsBiz.find(updateWordDialog.getWord());
        updateWordDialog.setParaphrase(wordEntity.getParaphrase());
        updateWordDialog.setExample(wordEntity.getExample());
    }

    public void deleteWord(String wordName) {
        wordsBiz.delete(wordName);
        initListView();
    }

    public void wordItemClick(String word) {
        if(mainActivity.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            mainActivity.switchDetailsFragment(word);
        }else {
            mainActivity.goToDetailsActivity(word);
        }
    }

    @Override
    public void initDetailFragement() {
        WordEntity wordEntity=wordsBiz.find(detailsFragment.getWord());
        detailsFragment.setTranslation(wordEntity.getTranslation());
        detailsFragment.setParaphrase(wordEntity.getParaphrase());
        detailsFragment.setExample(wordEntity.getExample());
    }

    @Override
    public void setDetailFragement(WordDetailsFragment detailsFragement) {
        this.detailsFragment=detailsFragement;
    }
}
