package im.years.ultimaterecyclerview.wrapper;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

/**
 * Created by alvinzeng on 9/17/15.
 */
public abstract class RecycleViewAdapter<VH extends RecyclerViewHolder> extends UltimateViewAdapter<VH> {

    private RecyclerViewHolder.OnItemClickListener itemClickListener;

    public void setItemClickListener(RecyclerViewHolder.OnItemClickListener ls) {
        this.itemClickListener = ls;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup) {
        VH vh = this.onCreateItemViewHolder(viewGroup);
        vh.setItemClickListener(itemClickListener);
        return vh;
    }

    @Override
    public long generateHeaderId(int i) {
        return 0;
    }

    public abstract VH onCreateItemViewHolder(ViewGroup viewGroup);
}
