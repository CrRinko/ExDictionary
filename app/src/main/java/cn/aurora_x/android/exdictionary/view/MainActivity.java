package cn.aurora_x.android.exdictionary.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements WordsListViewFragment.OnListFragmentInteractionListener {
    private static final String TAG = "CRRINKO";
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = MainActivityPresenter.getMainActivityPresenter();
        presenter.setMainActivity(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Dialog dialog = null;
        switch (item.getItemId()) {
            case R.id.item_search:
                dialog = new SearchWordDialog(this);
                break;
            case R.id.item_add:
                dialog = new AddWordDialog(this);
                break;
            case R.id.item_review:
                goToReviewActivity();
                break;
        }
        if (dialog != null) {
            dialog.show();
        }
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        TextView textView = (TextView) item.getActionView().findViewById(R.id.word_name);
        final String wordName = textView.getText().toString();
        switch (item.getItemId()) {
            case R.id.item_delete:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.menu_del)
                        .setMessage(R.string.alert_del_confirm)
                        .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                presenter.deleteWord(wordName);
                            }
                        })
                        .setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
                break;
            case R.id.item_update:
                UpdateWordDialog dialog = new UpdateWordDialog(this, wordName);
                dialog.show();
                break;
        }
        return true;
    }

    @Override
    public void onListFragmentInteraction(String wordName) {
        presenter.wordItemClick(wordName);
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void goToDetailsActivity(String word) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(MainAndDetailsActivityContract.KEY_WORD, word);
        startActivity(intent);
    }

    public void switchDetailsFragment(String word) {
        WordDetailsFragment detailsFragment = WordDetailsFragment.newInstance(word);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_details_land, detailsFragment).commit();
    }

    public void goToReviewActivity(){
        Intent intent=new Intent(this,ReviewActivity.class);
        startActivity(intent);
    }
}
