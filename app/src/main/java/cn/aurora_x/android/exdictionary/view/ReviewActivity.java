package cn.aurora_x.android.exdictionary.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.presenter.ReviewActivityPresenter;

public class ReviewActivity extends AppCompatActivity implements ReviewWordFragment.OnListFragmentInteractionListener {
    private ReviewActivityPresenter presenter;
    private LinearLayout buttonPanel;
    private Button buttonOK;
    private Button buttonCancel;
    private Menu optionsMenu;
    private Set<WordEntity> wordEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = ReviewActivityPresenter.getInstance();
        presenter.setActivity(this);
        setContentView(R.layout.activity_review);
        buttonPanel = (LinearLayout) findViewById(R.id.panel_review);
        buttonPanel.setVisibility(View.INVISIBLE);
        buttonOK = (Button) findViewById(R.id.button_review_OK);
        buttonCancel = (Button) findViewById(R.id.button_review_Cancel);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonOKClick();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonCancelClick();
            }
        });
        wordEntities = new HashSet<WordEntity>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        wordEntities.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_review_activity, menu);
        optionsMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_create_review:
                presenter.onCreateReview();
                break;
        }
        return true;
    }

    public void showPanel() {
        buttonPanel.setVisibility(View.VISIBLE);
        for (int i = 0; i < optionsMenu.size(); i++) {
            optionsMenu.getItem(i).setVisible(false);
            optionsMenu.getItem(i).setEnabled(false);
        }
    }

    public void hidePanel() {
        buttonPanel.setVisibility(View.INVISIBLE);
        for (int i = 0; i < optionsMenu.size(); i++) {
            optionsMenu.getItem(i).setVisible(true);
            optionsMenu.getItem(i).setEnabled(true);
        }
    }

    public List<WordEntity> getCheckedList(){
        List<WordEntity> list=new LinkedList<WordEntity>();
        list.addAll(wordEntities);
        return list;
    }

    @Override
    public void onListFragmentInteraction(WordEntity item, boolean isChecked) {
        if (isChecked) {
            wordEntities.add(item);
        } else {
            wordEntities.remove(item);
        }
    }
    public void goToTestActivity(){
        Intent intent=new Intent(this,TestActivity.class);
        startActivity(intent);
    }
}
