package im.years.ultimaterecyclerview.wrapper;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.marshalchen.ultimaterecyclerview.divideritemdecoration.HorizontalDividerItemDecoration;
import com.marshalchen.ultimaterecyclerview.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import im.years.ultimaterecyclerview.R;

public abstract class ListFragment extends Fragment {

    protected UltimateRecyclerView recyclerView;
    LinearLayout listHeaderLayout;
    RecycleViewAdapter adapter;
    Integer currentPage = 0;
    boolean isLoadMoreEnabled;

    RelativeLayout customLoadMoreView;
    LinearLayout llFooter;
    ListEmptyView listEmptyView;
    View currentHeaderView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.az_fragment_list, container, false);

        recyclerView = (UltimateRecyclerView) v.findViewById(R.id.recyclerView);
        listHeaderLayout = (LinearLayout) v.findViewById(R.id.listHeaderLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        customLoadMoreView = (RelativeLayout) inflater.inflate(R.layout.az_view_custom_bottom_progressbar, null);
        llFooter = (LinearLayout) v.findViewById(R.id.listFooterLayout);
        listEmptyView = (ListEmptyView) v.findViewById(R.id.listEmptyView);
        this.initViews();

        return v;
    }

    protected void initViews() {
        //Empty
    }

    protected void addPageHeader(View v) {
        listHeaderLayout.addView(v);
    }

    protected void setAdapter(RecycleViewAdapter adapter) {
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new RecyclerViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View clickedView, int position) {
                ListFragment.this.onItemClick(clickedView, position);
            }

            @Override
            public void onItemViewClick(View clickedItemView, int position) {
                ListFragment.this.onItemViewClick(clickedItemView, position);
            }
        });
    }

    protected UltimateViewAdapter getAdapter() {
        return this.adapter;
    }

    protected void enableLoadMore() {
        isLoadMoreEnabled = true;
        recyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int i, int i1) {
                ListFragment.this.onLoadMore();
            }
        });
    }

    protected void disableLoadMore() {
        recyclerView.disableLoadmore();
        getAdapter().notifyDataSetChanged();

        getAdapter().setCustomLoadMoreView(customLoadMoreView);

        customLoadMoreView.findViewById(R.id.bottomProgressBar).setVisibility(View.INVISIBLE);
        customLoadMoreView.findViewById(R.id.bottomProgressText).setVisibility(View.VISIBLE);
    }

    protected void enableRefresh() {

        recyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ListFragment.this.onRefresh();
            }
        });
    }

    protected void setListDivider(@ColorRes int color) {
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .colorResId(color)
                        .build());
    }

    protected View getScrolledView() {
        return (recyclerView == null) ? null : recyclerView.mRecyclerView;
    }

    protected void enableStickyHeader() {
        StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(this.adapter);
        recyclerView.addItemDecoration(headersDecor);
    }

    protected void endLoading(boolean success, boolean isMore) {
        if (success) {
            if (isMore) {
                currentPage++;
            } else {
                currentPage = 1;
                if (isLoadMoreEnabled) {

                    customLoadMoreView.findViewById(R.id.bottomProgressBar).setVisibility(View.VISIBLE);
                    customLoadMoreView.findViewById(R.id.bottomProgressText).setVisibility(View.INVISIBLE);
                    recyclerView.reenableLoadmore(customLoadMoreView);

                    //Try to get next page, detect load more.
                    ListFragment.this.onLoadMore();
                }
            }
            //detect emptyView
            int itemCount = adapter.getItemCount();
            int hfCount = 0;
            if (currentHeaderView != null) {
                hfCount++;
            }

            if (getAdapter().getCustomLoadMoreView() != null) {
                hfCount++;
            }

            if (itemCount - hfCount <= 0) {

                int marginTop = 0;
                marginTop += listHeaderLayout.getHeight();
                if (currentHeaderView != null) {
                    marginTop += currentHeaderView.getHeight();
                }

                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) listEmptyView.getLayoutParams();
                lp.topMargin = marginTop;
                listEmptyView.setVisibility(View.VISIBLE);
                listEmptyView.setLayoutParams(lp);
            } else {
                listEmptyView.setVisibility(View.GONE);
            }
        }

        recyclerView.setRefreshing(false);
    }

    protected void addHeader(View view) {
        currentHeaderView = view;
        recyclerView.setNormalHeader(view);
    }

    protected Integer getCurrentPage() {
        return currentPage;
    }

    protected void onLoadMore() {
    }

    protected void onRefresh() {
    }

    protected void onItemClick(View clickedView, int position) {
    }

    protected void onItemViewClick(View clickedItemView, int position) {
    }

    protected void addFooterView(View view) {
        llFooter.addView(view);
        llFooter.setVisibility(View.VISIBLE);
    }

    protected void setHeaderVisiable(boolean isVisiable) {
        currentHeaderView.setVisibility(isVisiable ? View.VISIBLE : View.GONE);
    }
}
