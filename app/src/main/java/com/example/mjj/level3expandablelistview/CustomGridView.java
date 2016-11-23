package com.example.mjj.level3expandablelistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Description：自定义GridView,解决嵌套时显示不全问题
 * <p>
 * Created by Mjj on 2016/11/17.
 */

public class CustomGridView extends GridView {

    public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridView(Context context) {
        super(context);
    }

    /**
     * 其中onMeasure函数决定了组件显示的高度与宽度；
     * MeasureSpec.makeMeasureSpec函数中第一个参数指布局空间的大小，第二个参数是布局模式
     * MeasureSpec.AT_MOST的意思就是子控件需要多大的控件就扩展到多大的空间
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
