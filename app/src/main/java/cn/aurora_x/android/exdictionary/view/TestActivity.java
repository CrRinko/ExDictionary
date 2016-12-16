package cn.aurora_x.android.exdictionary.view;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.presenter.ReviewActivityPresenter;

public class TestActivity extends AppCompatActivity implements TestFragment.OnListFragmentInteractionListener {
    private ReviewActivityPresenter presenter;
    private Menu optionsMenu;
    private String resultMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = ReviewActivityPresenter.getInstance();
        presenter.setTestActivity(this);
        resultMsg=null;
        setContentView(R.layout.activity_test);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_test_activity, menu);
        optionsMenu=menu;
        menu.setGroupVisible(R.id.group_tested, false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_finish_test:
                presenter.onTestFinish();
                break;
            case R.id.item_cancel_test:
                finish();
                break;
            case R.id.item_show_result:
                showResultDialog();
                break;
            case R.id.item_finish_result:
                presenter.onResultFinish();
                break;
        }
        return true;
    }

    public void showResultMenu() {
        optionsMenu.setGroupVisible(R.id.group_testing,false);
        optionsMenu.setGroupVisible(R.id.group_tested,true);
    }

    public void showResultDialog(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.title_result_dialog)
                .setMessage(resultMsg)
                .setPositiveButton(R.string.btn_ok,null).show();
    }
    public void setResultMsg(String msg){
        resultMsg=msg;
    }
    @Override
    public void onListFragmentInteraction(WordEntity item) {

    }
}
