package im.years.ultimaterecyclerview.wrapper;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.view.View;

import java.util.ArrayList;

import im.years.ultimaterecyclerview.wrapper.adapter.ListRecycleViewObjectAdapter;

public abstract class EasyListFragment<T, V extends View> extends ListFragment {
    public abstract void onBindViewItemHolder(V view, T item, int position);

    protected abstract Class<V> viewClass();

    protected ArrayList<T> items = new ArrayList<>();
    protected SampleListAdapter sampleListAdapter;


    @Override
    protected void initViews() {
        super.initViews();
        setAdapter(sampleListAdapter = new SampleListAdapter(getContext(), items, viewClass()));
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

    protected void listeningClick(View v, final int position) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemViewClick(v, position);
            }
        });
    }

    protected
    @ColorRes
    int itemViewBackground() {
        return 0;
    }

    class SampleListAdapter extends ListRecycleViewObjectAdapter<T, V> {


        public SampleListAdapter(Context context, ArrayList<T> items, Class<V> viewCls) {
            super(context, items, viewCls);
        }

        @Override
        protected int itemViewBackground() {
            return EasyListFragment.this.itemViewBackground();
        }

        @Override
        public void onBindViewItemHolder(V view, T item, int position) {
            EasyListFragment.this.onBindViewItemHolder(view, item, position);
        }
    }
}
