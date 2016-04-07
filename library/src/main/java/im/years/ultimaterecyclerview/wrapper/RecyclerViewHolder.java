package im.years.ultimaterecyclerview.wrapper;

import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

/**
 * Created by alvinzeng on 9/17/15.
 */
public class RecyclerViewHolder extends UltimateRecyclerviewViewHolder implements View.OnClickListener {

    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener ls) {
        this.itemClickListener = ls;
    }

    public RecyclerViewHolder(final View itemView) {
        super(itemView);

        if (itemView != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (RecyclerViewHolder.this.itemClickListener != null) {
                        RecyclerViewHolder.this.itemClickListener.onItemClick(itemView, getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (RecyclerViewHolder.this.itemClickListener != null) {
                        RecyclerViewHolder.this.itemClickListener.onItemLongClick(itemView, getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if (RecyclerViewHolder.this.itemClickListener != null) {
            RecyclerViewHolder.this.itemClickListener.onItemViewClick(v, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View clickedView, int position);
        void onItemLongClick(View clickedView, int position);
        void onItemViewClick(View clickedItemView, int position);
    }
}
