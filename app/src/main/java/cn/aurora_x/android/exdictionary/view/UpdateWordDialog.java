package cn.aurora_x.android.exdictionary.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.presenter.MainActivityPresenter;

/**
 * Created by Rinko on 2016/11/12.
 */
public class UpdateWordDialog extends Dialog implements IWordDialog {
    private EditText editTextParaphrase;
    private EditText editTextExample;
    private Button buttonOK;
    private Button buttonCancel;
    private final String wordName;
    private MainActivityPresenter presenter;

    public UpdateWordDialog(Context context, String wordName) {
        super(context);
        presenter = MainActivityPresenter.getMainActivityPresenter();
        setContentView(R.layout.update_word_dialog);
        editTextParaphrase = (EditText) findViewById(R.id.editText_update_dialog_paraphrase);
        editTextExample = (EditText) findViewById(R.id.editText_update_dialog_example);
        buttonOK = (Button) findViewById(R.id.button_update_dialog_ok);
        buttonCancel = (Button) findViewById(R.id.button_update_dialog_cencel);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.wordDialogConfrimed(UpdateWordDialog.this);
                dismiss();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        buttonCancel = (Button) findViewById(R.id.button_update_dialog_cencel);
        this.wordName = wordName;
        setTitle(wordName);
        presenter.createUpdateDialog(this);
    }

    public void setParaphrase(String paraphrase) {
        editTextParaphrase.setText(paraphrase);
    }

    public void setExample(String example) {
        editTextExample.setText(example);
    }

    @Override
    public String getParaphrase() {
        return editTextParaphrase.getText().toString();
    }

    @Override
    public String getExample() {
        return editTextExample.getText().toString();
    }

    @Override
    public String getWord() {
        return wordName;
    }
}
