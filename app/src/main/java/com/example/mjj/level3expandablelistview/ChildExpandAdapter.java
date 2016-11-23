package com.example.mjj.level3expandablelistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：一级ExpandableListView的ClildView适配器,也就是嵌套的ExpandableListView
 * <p>
 * Created by Mjj on 2016/11/17.
 */
public class ChildExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> parentList;

    public ChildExpandAdapter(Context context, List<String> parentList) {
        this.context = context;
        this.parentList = parentList;
    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder parentHolder;
        if (null == convertView) {
            parentHolder = new ParentViewHolder();
            convertView = View.inflate(context, R.layout.fire_tab_child_group_item, null);
            parentHolder.parentTv = (TextView) convertView.findViewById(R.id.childGroupTV);
            parentHolder.parentIv = (ImageView) convertView.findViewById(R.id.iv_fire_tab_child_indicator);
            convertView.setTag(parentHolder);
        } else {
            parentHolder = (ParentViewHolder) convertView.getTag();
        }
        parentHolder.parentTv.setText(parentList.get(groupPosition));
        if (isExpanded) {
            parentHolder.parentIv.setImageResource(R.drawable.icon_arrow_up);
        } else {
            parentHolder.parentIv.setImageResource(R.drawable.icon_arrow_down);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childHolder;
        if (null == convertView) {
            childHolder = new ChildViewHolder();
            convertView = View.inflate(context, R.layout.item_child_child, null);
            childHolder.gridView = (GridView) convertView.findViewById(R.id.gv_child_child_item);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildViewHolder) convertView.getTag();
        }
        childHolder.gridView.setAdapter(new GridViewAdapter(context, getList()));
        childHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "GridView - " + position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ParentViewHolder {
        TextView parentTv;
        ImageView parentIv;
    }

    class ChildViewHolder {
        // 此ExpandableListView的ClildView又是一个GridView
        GridView gridView;
    }

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("And " + i);
        }
        return list;
    }
}
