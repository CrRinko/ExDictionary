package cn.aurora_x.android.exdictionary.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.presenter.DetailsActivityPresenter;
import cn.aurora_x.android.exdictionary.presenter.IDetailsFragmentPresenter;
import cn.aurora_x.android.exdictionary.presenter.MainActivityPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WordDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordDetailsFragment extends Fragment {
    private static final String ARG_WORD = "word";

    private String word;
    private TextView textViewWord;
    private TextView textViewTranslation;
    private TextView textViewParaphrase;
    private TextView textViewExample;
    private IDetailsFragmentPresenter presenter;

    public WordDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param word     The word to show.
     * @return A new instance of fragment WordDetailsFragment.
     */
    public static WordDetailsFragment newInstance(String word) {
        WordDetailsFragment fragment = new WordDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_WORD, word);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            word = getArguments().getString(ARG_WORD);
        } else {
            word = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =null;
        // Inflate the layout for this fragment
        if(word!=null){
            view = inflater.inflate(R.layout.fragment_word_details, container, false);
            textViewWord = (TextView) view.findViewById(R.id.textView_details_word);
            textViewTranslation = (TextView) view.findViewById(R.id.textView_details_translation);
            textViewParaphrase = (TextView) view.findViewById(R.id.textView_details_paraphrase);
            textViewExample = (TextView) view.findViewById(R.id.textView_details_example);
            textViewWord.setText(word);
            presenter.initDetailFragement();
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof DetailsActivity){
            presenter=DetailsActivityPresenter.getDetailsActivityPresenter();
        }else if(context instanceof MainActivity){
            presenter= MainActivityPresenter.getMainActivityPresenter();
        }
        presenter.setDetailFragement(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void setTranslation(String translation){
        if(translation!=null){
            textViewTranslation.setText(translation);
        }else {
            textViewTranslation.setText(R.string.text_null);
        }
    }
    public void setParaphrase(String paraphrase){
        if(paraphrase!=null){
            textViewParaphrase.setText(paraphrase);
        }else {
            textViewParaphrase.setText(R.string.text_null);
        }
    }
    public void setExample(String example) {
        if(example!=null){
            textViewExample.setText(example);
        }else {
            textViewExample.setText(R.string.text_null);
        }
    }
    public String getWord(){
        return word;
    }
}
