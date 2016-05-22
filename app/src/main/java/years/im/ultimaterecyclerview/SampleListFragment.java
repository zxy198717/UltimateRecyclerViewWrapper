package years.im.ultimaterecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import im.years.ultimaterecyclerview.wrapper.BriefListFragment;
import im.years.ultimaterecyclerview.wrapper.ListFragment;
import im.years.ultimaterecyclerview.wrapper.RecyclerViewHolder;
import im.years.ultimaterecyclerview.wrapper.adapter.SparseArrayViewHolder;

/**
 * Created by alvinzeng on 2/26/16.
 */
public class SampleListFragment extends BriefListFragment<ContentMock> {


    @Override
    protected void initViews() {
        super.initViews();
        //enableLoadMore();
        ArrayList<ContentMock> contentMocks = getItems();

        ContentMock contentMock = new ContentMock("未命名", "ddddddddddd");
        contentMocks.add(contentMock);
        contentMock = new ContentMock("ceshiaa", "ddddddddddddddd");
        contentMocks.add(contentMock);
        contentMock = new ContentMock("ceshiaa", "ddddddddddddddd");
        contentMocks.add(contentMock);
        contentMock = new ContentMock("ceshiaa", "ddddddddddddddd");
        contentMocks.add(contentMock);
        contentMock = new ContentMock("ceshiaa", "ddddddddddddddd");
        contentMocks.add(contentMock);

        View footer = LayoutInflater.from(getContext()).inflate(R.layout.view_footer, null);
        setFooterView(footer);
    }

    @Override
    protected int itemViewRes() {
        return R.layout.item_hello_list;
    }

    @Override
    public void onBindViewItemHolder(SparseArrayViewHolder holder, ContentMock item, int position) {
        holder.setText(R.id.textView, item.title);
        holder.listeningClick(R.id.textView);
    }

    @Override
    protected void onItemViewClick(View clickedItemView, int position) {
        super.onItemViewClick(clickedItemView, position);
        Toast.makeText(getContext(), "Click Title: " + getItem(position).title, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onItemClick(View clickedView, int position) {
        super.onItemClick(clickedView, position);
        Toast.makeText(getContext(), getItem(position).title, Toast.LENGTH_SHORT).show();
    }
}
