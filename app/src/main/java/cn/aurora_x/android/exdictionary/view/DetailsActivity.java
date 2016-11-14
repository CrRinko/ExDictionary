package cn.aurora_x.android.exdictionary.view;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.presenter.DetailsActivityPresenter;

public class DetailsActivity extends AppCompatActivity {
    private TextView textViewWord;
    private TextView textViewTranslation;
    private TextView textViewParaphrase;
    private TextView textViewExample;
    private DetailsActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=DetailsActivityPresenter.getDetailsActivityPresenter();
        presenter.setDetailsActivity(this);
        Intent intent=getIntent();
        String word=intent.getStringExtra(MainAndDetailsActivityContract.KEY_WORD);
        WordDetailsFragment detailsFragment=WordDetailsFragment.newInstance(word);
        setContentView(R.layout.activity_word_details);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_details,detailsFragment).commit();
    }
}
