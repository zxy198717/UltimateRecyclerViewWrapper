package im.years.ultimaterecyclerview.wrapper;

import android.content.Context;
import android.support.annotation.ColorRes;

import java.util.ArrayList;

/**
 * Created by alvinzeng on 2/26/16.
 */
public abstract class SampleListFragment<VH extends RecyclerViewHolder, T> extends ListFragment {
    protected abstract int itemViewRes();
    protected abstract Class<VH> viewHolderClass();
    public abstract void onBindViewItemHolder(VH holder, T item, int position);

    protected ArrayList<T> items = new ArrayList<>();
    protected SampleListAdapter sampleListAdapter;

    @Override
    protected void initViews() {
        super.initViews();
        setAdapter(sampleListAdapter = new SampleListAdapter(getContext(), items, viewHolderClass()));
    }

    protected ArrayList<T> getItems() {
        return items;
    }

    protected T getItem(int position) {
        return sampleListAdapter.getItem(position);
    }

    protected void reloadData() {
        sampleListAdapter.notifyDataSetChanged();
    }

    protected @ColorRes int itemViewBackground() {
        return 0;
    }

    class SampleListAdapter extends ListRecycleViewAdapter<VH, T>{

        public SampleListAdapter(Context context, ArrayList<T> items, Class<VH> cls) {
            super(context, items, cls);
        }

        @Override
        public int itemViewRes() {
            return SampleListFragment.this.itemViewRes();
        }

        @Override
        protected int itemViewBackground() {
            return SampleListFragment.this.itemViewBackground();
        }

        @Override
        public void onBindViewItemHolder(VH holder, T item, int position) {
            SampleListFragment.this.onBindViewItemHolder(holder, item, position);
        }
    }
}
