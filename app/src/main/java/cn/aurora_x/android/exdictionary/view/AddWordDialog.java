package cn.aurora_x.android.exdictionary.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.presenter.MainActivityPresenter;

/**
 * Created by Rinko on 2016/11/11.
 */
public class AddWordDialog extends Dialog implements IWordDialog{
    private EditText editTextWord;
    private EditText editTextParaphrase;
    private EditText editTextExample;
    private Button buttonOK;
    private Button buttonCancel;
    private MainActivityPresenter presenter;

    public AddWordDialog(Context context) {
        super(context);
        this.presenter = MainActivityPresenter.getMainActivityPresenter();
        setContentView(R.layout.add_word_dialog);
        editTextWord = (EditText) findViewById(R.id.editText_add_dialog_word);
        editTextParaphrase = (EditText) findViewById(R.id.editText_add_dialog_paraphrase);
        editTextExample = (EditText) findViewById(R.id.editText_add_dialog_example);
        buttonOK = (Button) findViewById(R.id.button_add_dialog_ok);
        buttonCancel = (Button) findViewById(R.id.button_add_dialog_cencel);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.wordDialogConfrimed(AddWordDialog.this);
                dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        this.setTitle(R.string.dialog_add_title);
    }

    @Override
    public String getWord() {
        return editTextWord.getText().toString();
    }

    @Override
    public String getParaphrase() {
        return editTextParaphrase.getText().toString();
    }

    @Override
    public String getExample() {
        return editTextExample.getText().toString();
    }
}
