package com.example.mjj.level3expandablelistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：ExpandableListView嵌套ExpandableListView,再嵌套GridView
 * <p>
 * Created by Mjj on 2016/11/17.
 */

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private List<String> groupList = new ArrayList<>();
    private ParentExpandAdapter parentExpandAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 11; i++) {
            groupList.add("Android工程师之版权 " + (1 + i));
        }
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.expand_main);
        parentExpandAdapter = new ParentExpandAdapter(MainActivity.this, groupList);
        expandableListView.setAdapter(parentExpandAdapter);

        // 被展开组的监听
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                // 保证每次只有一个组是展开状态
                for (int i = 0; i < parentExpandAdapter.getGroupCount(); i++) {
                    if (i != groupPosition) {
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });

    }
}
