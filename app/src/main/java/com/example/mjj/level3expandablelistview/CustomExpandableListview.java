package com.example.mjj.level3expandablelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Description：自定义ExpandableListView,解决嵌套时显示不全问题
 * <p>
 * Created by Mjj on 2016/11/17.
 */

public class CustomExpandableListview extends ExpandableListView {

    public CustomExpandableListview(Context context) {
        super(context);
    }

    public CustomExpandableListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomExpandableListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
