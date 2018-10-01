package goteborgsuniversitet.maptestapp.ui.backpackStuff;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import goteborgsuniversitet.maptestapp.R;

public class BackpackRecyclerAdapter extends RecyclerView.Adapter<BackpackRecyclerAdapter.MyViewHolder> {
    private List<BackpackItemDummy> mItemsInBackPack;

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

    public BackpackRecyclerAdapter(List<BackpackItemDummy> itemsInBackpackList) {
        this.mItemsInBackPack = itemsInBackpackList;
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
        // - get element from your dataset at this position
        BackpackItemDummy currentItem = mItemsInBackPack.get(position);
        // - replace the contents of the view with that element
        holder.mTextView.setText(String.valueOf(currentItem.getmValue()));
        holder.mImageView.setImageResource(currentItem.getmImageResource());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mItemsInBackPack.size();
    }
}
