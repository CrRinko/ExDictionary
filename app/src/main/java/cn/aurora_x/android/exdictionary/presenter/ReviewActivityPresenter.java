package cn.aurora_x.android.exdictionary.presenter;

import android.util.Log;

import java.util.List;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.model.biz.Review;
import cn.aurora_x.android.exdictionary.view.ReviewActivity;
import cn.aurora_x.android.exdictionary.view.ReviewWordFragment;
import cn.aurora_x.android.exdictionary.view.TestActivity;
import cn.aurora_x.android.exdictionary.view.TestFragment;

/**
 * Created by Rinko on 2016/11/29.
 */
public class ReviewActivityPresenter {
    private ReviewWordFragment reviewWordFragment;
    private ReviewActivity activity;
    private static ReviewActivityPresenter instance = null;
    private Review reviewBiz;
    private TestActivity testActivity;
    private TestFragment testFragment;

    private ReviewActivityPresenter() {
    }

    public static ReviewActivityPresenter getInstance() {
        if (instance == null) {
            instance = new ReviewActivityPresenter();
        }
        return instance;
    }

    public void setActivity(ReviewActivity activity) {
        this.activity = activity;
        reviewBiz = new Review(activity);
    }

    public void setReviewWordFragment(ReviewWordFragment fragment) {
        this.reviewWordFragment = fragment;
    }

    public void initFragment() {
        List<WordEntity> list = reviewBiz.getList();
        reviewWordFragment.setWordEntityList(list);
        reviewWordFragment.refresh();
    }

    public void onCreateReview() {
        reviewWordFragment.showCheckBox();
        activity.showPanel();
    }

    public void onButtonCancelClick() {
        activity.hidePanel();
        reviewWordFragment.hideCheckBox();
    }

    public void onButtonOKClick() {
        activity.hidePanel();
        reviewWordFragment.hideCheckBox();
        activity.goToTestActivity();
    }

    public void setTestActivity(TestActivity activity) {
        this.testActivity = activity;
    }

    public void setTestFragment(TestFragment fragment) {
        this.testFragment = fragment;
    }

    public void initTestFragment() {
        testFragment.setTestList(activity.getCheckedList());
        testFragment.refresh();
    }

    public void onTestFinish(){
        List<String> answers=testFragment.getAnswerList();
        List<WordEntity> excepted=activity.getCheckedList();
        int correctCount=0;
        for (int i=0;i<excepted.size();i++){
            String answer=answers.get(i).toLowerCase();
            String correct=excepted.get(i).getWord().toLowerCase();
            if(answer.equals(correct)){
                correctCount++;
                testFragment.setResponse(i,true);
                reviewBiz.update(excepted.get(i).getWord(),true);
            }else {
                testFragment.setResponse(i,false);
                reviewBiz.update(excepted.get(i).getWord(),false);
            }
        }
        testActivity.showResultMenu();
        String correctMsg=testActivity.getResources().getString(R.string.text_correct);
        String totalMsg=testActivity.getResources().getString(R.string.text_total);
        String accuracyMsg=testActivity.getResources().getString(R.string.text_accuracy);
        String resultMsg=correctMsg+": "+correctCount+"\n"
                +totalMsg+": "+answers.size()+"\n"
                +accuracyMsg+": "+WordEntity.getAccuracy(correctCount,answers.size());
        testActivity.setResultMsg(resultMsg);
        testActivity.showResultDialog();
    }
    public void onResultFinish(){
        testActivity.finish();
        initFragment();
    }
}
