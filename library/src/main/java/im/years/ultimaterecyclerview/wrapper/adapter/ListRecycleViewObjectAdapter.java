package im.years.ultimaterecyclerview.wrapper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import im.years.ultimaterecyclerview.wrapper.RecycleViewAdapter;
import im.years.ultimaterecyclerview.wrapper.RecyclerViewHolder;

/**
 * Created by alvinzeng on 1/29/16.
 */
public abstract class ListRecycleViewObjectAdapter<T, V extends View> extends RecycleViewAdapter {

    private ArrayList<T> items;
    private Constructor viewConstructor;

    public ListRecycleViewObjectAdapter(Context context, ArrayList<T> items, Class<V> viewCls) {
        this.items = items;

        try {
            viewConstructor = viewCls.getConstructor(new Class[]{Context.class});
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public T getItem(int position) {
        if (items != null && position < getItemCount() && (customHeaderView != null ? position <= items.size() : position < items.size()) && (customHeaderView != null ? position > 0 : true)) {
            return items.get(customHeaderView != null ? position - 1 : position);
        }
        return null;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void appendItems(ArrayList<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void insertItems(ArrayList<T> items) {
        this.items.addAll(0, items);
        notifyDataSetChanged();
    }

    protected int itemViewRes() {
        return 0;
    }

    protected int itemViewBackground() {
        return 0;
    }

    public abstract void onBindViewItemHolder(V view, T item, int position);

    @Override
    public RecyclerViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View realItemView = null;
        try {
            realItemView = (View) viewConstructor.newInstance(viewGroup.getContext());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        View view = realItemView;

        if (itemViewBackground() > 0) {
            LinearLayout linearLayout = new LinearLayout(viewGroup.getContext());
            linearLayout.setBackgroundColor(viewGroup.getContext().getResources().getColor(itemViewBackground()));
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(realItemView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            view = linearLayout;
        }

        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));

        return new ViewHolder(realItemView, view);
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        return new RecyclerViewHolder(view);
    }

    @Override
    public int getAdapterItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T item = getItem(position);

        if (item == null) {
            Log.d("ListRecycleViewAdapter", "Maybe the list have one header View.");
            return;
        }

        onBindViewItemHolder((V) (((ViewHolder)holder).realItemView), item, position);
    }

    class ViewHolder extends RecyclerViewHolder {

        public View realItemView;

        public ViewHolder(View realItemView, View itemView) {
            super(itemView);
            this.realItemView = realItemView;
        }
    }
}
