package goteborgsuniversitet.maptestapp.ui.backpackStuff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import goteborgsuniversitet.maptestapp.R;

public class BackpackRecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //because the constructor takes no arguments these are setable. Can also be solved using a factory method.
    private ArrayList<BackpackItemDummy> backpackContent;
    private int totalBackpackSlots;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_backpack_test, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        //improves performance (requires that changes in content do not change the layout size of the RecyclerView)
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //add images for available slots.
        if (backpackNotFull()) { populateEmptySlots(); }

        mAdapter = new BackpackRecyclerAdapter(backpackContent);
        mRecyclerView.setAdapter(mAdapter);

        //TODO refactor methods

        return rootView;
    }

    //check if the backpack has empty slots
    private boolean backpackNotFull () {
        return ((totalBackpackSlots-backpackContent.size())> 0);
    }

    // The backpack ui is supposed to show current content and available slots,
    // populate the backpack with images representing space available
    private void populateEmptySlots() {
        int availableBackpackSlots = totalBackpackSlots-backpackContent.size();
        for (int i = 0; i < availableBackpackSlots; i++) {
            backpackContent.add(new BackpackItemDummy(R.drawable.cobweb, 0));
        }
    }


    public void setBackpackContent(ArrayList<BackpackItemDummy> backpackContent) {
        this.backpackContent = backpackContent;
    }

    public void setTotalBackpackSlots(int totalBackpackSlots) {
        this.totalBackpackSlots = totalBackpackSlots;
    }


}
