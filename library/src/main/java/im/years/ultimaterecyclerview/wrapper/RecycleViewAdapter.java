package im.years.ultimaterecyclerview.wrapper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

/**
 * Created by alvinzeng on 9/17/15.
 */
public abstract class RecycleViewAdapter<VH extends RecyclerViewHolder> extends UltimateViewAdapter<VH> {

    private RecyclerViewHolder.OnItemClickListener itemClickListener;
    private RecyclerView.ViewHolder footerViewHolder;

    private static final int TYPE_FOOTER = Integer.MAX_VALUE - 1;

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
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return (VH) footerViewHolder;
        }
        return super.onCreateViewHolder(parent, viewType);
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

    @Override
    public int getItemCount() {
        return hasFooter() ? super.getItemCount() + 1 : super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (hasFooter() && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    public void setFooterView(View foot) {
        if (footerViewHolder == null || foot != footerViewHolder.itemView) {
            footerViewHolder = new RecyclerViewHolder(foot);
            notifyDataSetChanged();
        }
    }

    public void removeFooter() {
        if (footerViewHolder != null) {
            footerViewHolder = null;
            notifyDataSetChanged();
        }
    }

    public boolean hasFooter() {
        return footerViewHolder != null;
    }

    public abstract VH onCreateItemViewHolder(ViewGroup viewGroup);
}
