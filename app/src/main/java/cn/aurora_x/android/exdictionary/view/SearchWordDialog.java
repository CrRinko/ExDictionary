package cn.aurora_x.android.exdictionary.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.presenter.MainActivityPresenter;

/**
 * Created by Rinko on 2016/11/13.
 */
public class SearchWordDialog extends Dialog implements IWordDialog{
    private EditText editTextSearch;
    private Button buttonOK;
    private Button buttonCancel;
    private MainActivityPresenter presenter;
    private static String searchWord;

    public SearchWordDialog(Context context) {
        super(context);
        setContentView(R.layout.search_word_dialog);
        setTitle(R.string.text_search);
        presenter = MainActivityPresenter.getMainActivityPresenter();
        editTextSearch = (EditText) findViewById(R.id.editText_search);
        editTextSearch.setText(searchWord);
        buttonOK = (Button) findViewById(R.id.button_search_dialog_ok);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.wordDialogConfrimed(SearchWordDialog.this);
                searchWord=getWord();
                dismiss();
            }
        });
        buttonCancel = (Button) findViewById(R.id.button_search_dialog_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWord=null;
                presenter.initListView();
                dismiss();
            }
        });
    }

    @Override
    public String getWord() {
        return editTextSearch.getText().toString();
    }

    @Override
    public String getParaphrase() {
        return null;
    }

    @Override
    public String getExample() {
        return null;
    }
}
