package im.years.ultimaterecyclerview.wrapper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import im.years.ultimaterecyclerview.R;

public class ListEmptyView extends LinearLayout {

    public TextView emptyTextView;

    public ListEmptyView(Context context) {
        super(context);
        setup();
    }

    public ListEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public void setup() {
        LayoutInflater.from(getContext()).inflate(R.layout.az_view_list_empty_view, this);
        emptyTextView = (TextView)findViewById(R.id.emptyTextView);
    }

    public TextView getEmptyTextView() {
        return emptyTextView;
    }
}
