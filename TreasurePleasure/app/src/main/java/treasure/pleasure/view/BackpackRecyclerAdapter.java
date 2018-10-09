package treasure.pleasure.view;
/**
 * Support class for RecyclerViewFragment. Responsible for providing views that represent items in a data set.
 * Uses backpack_item_template.xml as a template, and replaces text and resourcepath of that view to represent each item.
 *
 * This is a standard android implementation, to read more visit https://developer.android.com/reference/android/support/v7/widget/RecyclerView
 *  @author David
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

    // Return the size of dataset (invoked by the layout manager)
    @Override
    // each item is represented by two consecutive int´s in the array, {imgResource, value} respectively. Therefore half the array size is the total item count.
    public int getItemCount() {
        return mContentToDisplay.size()/2;
    }
}
