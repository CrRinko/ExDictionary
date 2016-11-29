package cn.aurora_x.android.exdictionary.presenter;

import android.util.Log;

import java.util.List;

import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.model.biz.Review;
import cn.aurora_x.android.exdictionary.model.database.WordsDBManager;
import cn.aurora_x.android.exdictionary.view.ReviewActivity;
import cn.aurora_x.android.exdictionary.view.ReviewWordFragment;

/**
 * Created by Rinko on 2016/11/29.
 */
public class ReviewActivityPresenter {
    private ReviewWordFragment reviewWordFragment;
    private ReviewActivity activity;
    private static ReviewActivityPresenter instance=null;
    private Review reviewBiz;

    private ReviewActivityPresenter(){}

    public static ReviewActivityPresenter getInstance(){
        if(instance==null){
            instance=new ReviewActivityPresenter();
        }
        return instance;
    }

    public void setActivity(ReviewActivity activity){
        this.activity=activity;
        reviewBiz=new Review(activity);
    }

    public void setReviewWordFragment(ReviewWordFragment fragment){
        this.reviewWordFragment=fragment;
    }

    public void initFragment(){
        List<WordEntity>list=reviewBiz.getList();
        reviewWordFragment.setWordEntityList(list);
        reviewWordFragment.refresh();
    }
    public void onCreateReview(){
        reviewWordFragment.showCheckBox();
        activity.showPanel();
    }
    public void onButtonCancelClick(){
        activity.hidePanel();
        reviewWordFragment.hideCheckBox();
    }
    public void onButtonOKClick(){
        List<WordEntity> list=activity.getCheckedList();
        for(WordEntity word:list){
            Log.d("CRRINKO",word.getWord());
        }
    }
}
