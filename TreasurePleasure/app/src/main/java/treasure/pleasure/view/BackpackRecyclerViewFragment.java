package treasure.pleasure.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import treasure.pleasure.model.TreasurePleasure;
import treasure.pleasure.presenter.BackpackPresenterImpl;

import treasure.pleasure.R;

/**
 * @author David
 */
public class BackpackRecyclerViewFragment extends Fragment {

    private BackpackPresenterImpl backpackPresenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TreasurePleasure model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //make reference to presenter
        backpackPresenter = new BackpackPresenterImpl(this, model);

        View rootView = inflater.inflate(R.layout.fragment_backpack_test, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        setupRecyclerView();
        //
        return rootView;
    }

    private void setupRecyclerView() {
        //improves performance (requires that changes in content do not change the layout size of the RecyclerView)
        mRecyclerView.setHasFixedSize(true);
        //set layout style
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //hook up the adapter.
        //mAdapter = new BackpackRecyclerAdapter(backpackContent);
        //TODO refactor here?
        mAdapter = new BackpackRecyclerAdapter(backpackPresenter.convertBackPackContent());
        mRecyclerView.setAdapter(mAdapter);
    }

    //TODO alternative ways of establishing communicaiton?
    public void setModel(TreasurePleasure model) {
        this.model = model;
    }
}
