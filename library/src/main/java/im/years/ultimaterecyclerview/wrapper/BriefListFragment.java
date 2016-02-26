package im.years.ultimaterecyclerview.wrapper;

import im.years.ultimaterecyclerview.wrapper.adapter.SparseArrayViewHolder;

/**
 * Created by alvinzeng on 2/26/16.
 */
public abstract class BriefListFragment<T> extends SampleListFragment<SparseArrayViewHolder, T> {
    @Override
    protected Class<SparseArrayViewHolder> viewHolderClass() {
        return SparseArrayViewHolder.class;
    }
}
