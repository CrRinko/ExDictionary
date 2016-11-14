package cn.aurora_x.android.exdictionary.presenter;

import android.telecom.Call;

import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.model.biz.Words;
import cn.aurora_x.android.exdictionary.view.DetailsActivity;
import cn.aurora_x.android.exdictionary.view.WordDetailsFragment;

/**
 * Created by Rinko on 2016/11/12.
 */
public class DetailsActivityPresenter implements IDetailsFragmentPresenter {
    private static DetailsActivityPresenter instance;
    private DetailsActivity detailsActivity;
    private WordDetailsFragment detailsFragment;
    private Words wordsBiz;

    private DetailsActivityPresenter() {
    }

    public static DetailsActivityPresenter getDetailsActivityPresenter() {
        if (instance == null) {
            instance = new DetailsActivityPresenter();
        }
        return instance;
    }

    public void setDetailsActivity(DetailsActivity detailsActivity) {
        this.detailsActivity=detailsActivity;
        wordsBiz=new Words(detailsActivity);
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
