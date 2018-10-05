package treasure.pleasure.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import goteborgsuniversitet.maptestapp.model.Backpack;
import treasure.pleasure.presenter.BackpackPresenterImpl;

import goteborgsuniversitet.maptestapp.R;

/**
 * @author David
 */
public class BackpackRecyclerViewFragment extends Fragment {

    //TODO to be replace by model
    private Backpack mBackpack;
    //private Model mModel;

    private BackpackPresenterImpl backpackPresenter;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //because the constructor cannot take arguments these are setable.
    //private Model mModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //make reference to presenter
        backpackPresenter = new BackpackPresenterImpl(this, mBackpack);

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
        mAdapter = new BackpackRecyclerAdapter(backpackPresenter.returnBackPackContents());
        mRecyclerView.setAdapter(mAdapter);
    }

    //TODO to be replaced with model
    public void setBackpack(Backpack backpack) {
        this.mBackpack = backpack;
    }
}
