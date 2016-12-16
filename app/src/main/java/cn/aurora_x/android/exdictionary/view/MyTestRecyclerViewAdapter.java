package cn.aurora_x.android.exdictionary.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.view.TestFragment.OnListFragmentInteractionListener;
import cn.aurora_x.android.exdictionary.view.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTestRecyclerViewAdapter extends RecyclerView.Adapter<MyTestRecyclerViewAdapter.ViewHolder> {

    private final List<WordEntity> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final List<ViewHolder> viewHolders;

    public MyTestRecyclerViewAdapter(List<WordEntity> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        viewHolders=new ArrayList<ViewHolder>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.hits.setText("Q"+(position+1)+": "+holder.mItem.getTranslation());
        holder.check.setVisibility(View.INVISIBLE);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
        viewHolders.add(holder);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public List<String> getAnswerList(){
        ArrayList<String> answerList=new ArrayList<String>();
        for(ViewHolder holder:viewHolders){
            answerList.add(holder.answer.getText().toString());
        }
        return answerList;
    }
    public void setResponse(int index,boolean result){
        ViewHolder holder=viewHolders.get(index);
        holder.answer.setEnabled(false);
        if(result){
            holder.check.setVisibility(View.VISIBLE);
            holder.check.setImageResource(R.drawable.sign_check_icon);
        }else {
            holder.check.setVisibility(View.VISIBLE);
            holder.check.setImageResource(R.drawable.sign_error_icon);
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView hits;
        public final EditText answer;
        public final ImageView check;
        public WordEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            hits=(TextView) view.findViewById(R.id.textView_hits);
            answer=(EditText) view.findViewById(R.id.editText_answer);
            check=(ImageView)view.findViewById(R.id.imageView_check);
        }
    }
}
