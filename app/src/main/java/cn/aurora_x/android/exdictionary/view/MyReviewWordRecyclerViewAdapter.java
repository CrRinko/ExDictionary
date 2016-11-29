package cn.aurora_x.android.exdictionary.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.view.ReviewWordFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyReviewWordRecyclerViewAdapter extends RecyclerView.Adapter<MyReviewWordRecyclerViewAdapter.ViewHolder> {

    private final List<WordEntity> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final boolean showCheckBox;

    public MyReviewWordRecyclerViewAdapter(List<WordEntity> items, OnListFragmentInteractionListener listener, boolean showCheckBox) {
        mValues = items;
        mListener = listener;
        this.showCheckBox=showCheckBox;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_reviewword, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.wordView.setText(holder.mItem.getWord());
        holder.countView.setText("正确: "+holder.mItem.getCorrect()+", "
                +"复习数: "+holder.mItem.getTotal()+", "
                +"正确率: "+holder.mItem.getAccuracy());
        if(showCheckBox==false){
            holder.checkBoxView.setVisibility(View.GONE);
        }
        holder.checkBoxView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mListener.onListFragmentInteraction(holder.mItem,isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public WordEntity mItem;
        public final TextView wordView;
        public final TextView countView;
        public final CheckBox checkBoxView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mItem=null;
            wordView=(TextView) view.findViewById(R.id.textView_review_words);
            countView=(TextView)view.findViewById(R.id.textView_review_count);
            checkBoxView=(CheckBox)view.findViewById(R.id.checkBox_review);
        }
    }
}
