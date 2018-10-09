package treasure.pleasure.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import treasure.pleasure.data.Tuple;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.presenter.BackpackPresenter;

import treasure.pleasure.R;
import treasure.pleasure.presenter.TreasurePleasurePresenter;

/**
 * Creates a viewfragment showing the backpack.
 * @author David
 */
public class BackpackFragment extends Fragment {

    private TreasurePleasurePresenter mPresenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_backpack, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        setupRecyclerView();
        mPresenter.retrieveAndDisplayContent();

        return rootView;
    }

    private void setupRecyclerView() {
        //improves performance (requires that changes in content do not change the layout size of the RecyclerView)
        mRecyclerView.setHasFixedSize(true);
        //set layout style
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    //creates an adapter that provides a view for each item represented in contentList. Each item is represented by two consecutive int´s in the array, {imgResource, value} respectively.
    public void displayContent(List<Tuple<Integer, String>> contentList) {
        //hook up adapter and set content
        mAdapter = new BackpackRecyclerAdapter(contentList);
        mRecyclerView.setAdapter(mAdapter);
    }

    //Create and reference presenter. Pass model to presenter.
    public void setPresenter(TreasurePleasurePresenter presenter) {
        mPresenter = presenter;
        presenter.setBackpackView(this);
    }

    @Override
    public void onDestroy() {
        //mPresenter.detachView();
        super.onDestroy();
    }
}
