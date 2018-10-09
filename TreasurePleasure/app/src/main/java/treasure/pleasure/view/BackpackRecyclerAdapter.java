package treasure.pleasure.view;
/**
 * @author David
 * This is a support class for RecyclerViewFragment
 * Creates a card for each item and populates the view
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import treasure.pleasure.R;
import treasure.pleasure.data.Tuple;

public class BackpackRecyclerAdapter extends RecyclerView.Adapter<BackpackRecyclerAdapter.MyViewHolder> {

    private final List<Tuple<Integer,String>> mContentToDisplay;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.backpackItemImageView);
            this.mTextView = itemView.findViewById(R.id.backpackItemTextView);
        }
    }

    public BackpackRecyclerAdapter(List<Tuple<Integer,String>> content) {
        this.mContentToDisplay = content;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.backpack_item_template, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // replace the contents of the view with that element
        Tuple<Integer, String> itemTuple =  mContentToDisplay.get(position);
        holder.mImageView.setImageResource(itemTuple.getArg1());
        holder.mTextView.setText(itemTuple.getArg2());

    }

    // Return the size of your dataset (invoked by the layout manager)
    // each item is represented by two consecutive int´s in the array. Therefore half the size for item count.
    @Override
    public int getItemCount() {
        return mContentToDisplay.size()/2;
    }
}
