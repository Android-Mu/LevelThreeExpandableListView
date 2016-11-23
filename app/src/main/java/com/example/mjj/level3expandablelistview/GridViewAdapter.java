package com.example.mjj.level3expandablelistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Description：嵌套的GridView的适配器
 * <p>
 * Created by Mjj on 2016/11/17.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public GridViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.item_addtab_grid, null);
            holder.textView = (TextView) view.findViewById(R.id.tv_add_item_tab_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(list.get(i));
        return view;
    }

    class ViewHolder {
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textView;
    }
}
