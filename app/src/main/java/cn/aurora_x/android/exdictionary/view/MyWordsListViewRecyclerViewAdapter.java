package cn.aurora_x.android.exdictionary.view;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.view.WordsListViewFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link List<String> mValues} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyWordsListViewRecyclerViewAdapter extends RecyclerView.Adapter<MyWordsListViewRecyclerViewAdapter.ViewHolder> {

    private final List<String> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyWordsListViewRecyclerViewAdapter(List<String> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_wordslistview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.wordName = mValues.get(position);
        holder.mWordView.setText(holder.wordName);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.wordName);
                }
            }
        });
        holder.mView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0,R.id.item_update,0,R.string.menu_update).setActionView(holder.mView);
                menu.add(0,R.id.item_delete,1,R.string.menu_del).setActionView(holder.mView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mWordView;
        public String wordName;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mWordView = (TextView) view.findViewById(R.id.word_name);
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "wordName='" + wordName + '\'' +
                    '}';
        }
    }
}
