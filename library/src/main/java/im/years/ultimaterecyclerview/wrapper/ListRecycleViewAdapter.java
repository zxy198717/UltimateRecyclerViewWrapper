package im.years.ultimaterecyclerview.wrapper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by alvinzeng on 1/29/16.
 */
public abstract class ListRecycleViewAdapter<VH extends RecyclerViewHolder, T> extends RecycleViewAdapter {

    private ArrayList<T> items;
    private Context context;
    private Class<VH> viewHolderClass;

    public ListRecycleViewAdapter(Context context, ArrayList<T> items, Class<VH> cls) {
        this.context = context;
        this.items = items;
        this.viewHolderClass = cls;
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

    public abstract int itemViewRes();
    public abstract void onBindViewItemHolder(VH holder, T item, int position);

    @Override
    public VH onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(itemViewRes(), viewGroup, false);
        try {
            Constructor con = viewHolderClass.getConstructor(new Class[]{View.class});
            return (VH) con.newInstance(view);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder(View view) {
        try {
            Constructor con = viewHolderClass.getConstructor(new Class[]{View.class});
            return (VH) con.newInstance(view);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getAdapterItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T item = getItem(position);

        if(item == null) {
            Log.d("ListRecycleViewAdapter", "Maybe the list have one header View.");
            return;
        }

        onBindViewItemHolder((VH)holder, item, position);
    }
}
