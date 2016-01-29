package years.im.ultimaterecyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.ui.DividerItemDecoration;

import java.util.ArrayList;

import im.years.ultimaterecyclerview.wrapper.ListFragment;
import im.years.ultimaterecyclerview.wrapper.ListRecycleViewAdapter;
import im.years.ultimaterecyclerview.wrapper.RecyclerViewHolder;

/**
 * Created by alvinzeng on 1/29/16.
 */
public class HelloFragment extends ListFragment {

    private HelloAdapter helloAdapter;
    private ArrayList<ContentMock> contentMocks = new ArrayList<>();


    @Override
    protected void initViews() {
        super.initViews();

        enableRefresh();
        enableLoadMore();

        setListDivider(R.color.list_divider);

        contentMocks.clear();
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

        helloAdapter = new HelloAdapter(getContext(), contentMocks, ViewHolder.class);
        setAdapter(helloAdapter);
    }

    static class ViewHolder extends RecyclerViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.textView);
        }
    }

    class HelloAdapter extends ListRecycleViewAdapter<ViewHolder, ContentMock> {

        public HelloAdapter(Context context, ArrayList<ContentMock> items, Class<ViewHolder> cls) {
            super(context, items, cls);
        }

        @Override
        public int itemViewRes() {
            return R.layout.item_hello_list;
        }

        @Override
        public void onBindViewItemHolder(ViewHolder holder, final ContentMock item, int position) {
            holder.textView.setText(item.title);
        }

    }
}
