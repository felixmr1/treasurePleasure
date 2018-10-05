package treasure.pleasure.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import treasure.pleasure.R;

public class BackpackFragment extends Fragment {

    private ArrayList<String> backpackContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_backpack_test, container, false);
    }

    /*
    //check if the backpack has empty slots
    private boolean backpackNotFull () {
        return (availableSlots > 0);
    }

    // The backpack ui is supposed to show current content and available slots,
    // populate the backpack with images representing space available
    private void populateEmptySlots() {
        for (int i = 0; i < availableSlots; i++) {
            backpackContent.add(new BackpackItemDummy(R.drawable.cobweb, 0));
        }
    }

    public void setBackpackContent(List<BackpackItemDummy> backpackContent) {
        this.backpackContent = backpackContent;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }
    */
}
