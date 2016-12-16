package cn.aurora_x.android.exdictionary.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.aurora_x.android.exdictionary.R;
import cn.aurora_x.android.exdictionary.model.bean.WordEntity;
import cn.aurora_x.android.exdictionary.presenter.ReviewActivityPresenter;
import cn.aurora_x.android.exdictionary.view.dummy.DummyContent;
import cn.aurora_x.android.exdictionary.view.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TestFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private ReviewActivityPresenter presenter;
    private List<WordEntity> testList;
    private RecyclerView recyclerView;
    private MyTestRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TestFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TestFragment newInstance(int columnCount) {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            presenter.initTestFragment();
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        presenter = ReviewActivityPresenter.getInstance();
        presenter.setTestFragment(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setTestList(List<WordEntity> wordEntityList) {
        this.testList = wordEntityList;
    }

    public void refresh() {
        adapter = new MyTestRecyclerViewAdapter(testList, mListener);
        recyclerView.setAdapter(adapter);
    }

    public List<String> getAnswerList() {
        return adapter.getAnswerList();
    }
    public void setResponse(int index,boolean result){
        adapter.setResponse(index,result);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(WordEntity item);
    }
}
