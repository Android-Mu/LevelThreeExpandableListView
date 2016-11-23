package com.example.mjj.level3expandablelistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Description：一级ExpandableListView适配器
 * <p>
 * Created by Mjj on 2016/11/17.
 */
public class ParentExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> parentList;

    public ParentExpandAdapter(Context context, List<String> parentList) {
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
            convertView = View.inflate(context, R.layout.fire_tab_parent_group_item, null);
            parentHolder.parentTv = (TextView) convertView.findViewById(R.id.parentGroupTV);
            parentHolder.parentIv = (ImageView) convertView.findViewById(R.id.iv_parent_group_indicator);
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
        final ChildViewHolder childHolder;
        if (null == convertView) {
            childHolder = new ChildViewHolder();
            convertView = View.inflate(context, R.layout.item_expand_group_child, null);
            childHolder.childExpandLv = (ExpandableListView) convertView.findViewById(R.id.expand_group_item);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildViewHolder) convertView.getTag();
        }

        final ChildExpandAdapter childExpandAdapter = new ChildExpandAdapter(context, parentList.subList(3, 7));
        childHolder.childExpandLv.setAdapter(childExpandAdapter);
        childHolder.childExpandLv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                // 保证每次只有一个组是展开状态
                for (int i = 0; i < childExpandAdapter.getGroupCount(); i++) {
                    if (i != groupPosition) {
                        childHolder.childExpandLv.collapseGroup(i);
                    }
                }
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
        // 二级视图又是一个ExpandableListView
        ExpandableListView childExpandLv;
    }

}
