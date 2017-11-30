package cn.xiwu.recycleviewadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiwu.recycleviewadapter.adapter.MainAdapter;
import cn.xiwu.recycleviewadapter.bean.Teacher;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.recycleview)
    RecyclerView mRecyclerView;
    List<Teacher> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData()
    {
        mData = new ArrayList<>();
        //设置LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 30; i++)
        {
           Teacher temp = new Teacher("name"+i,"id"+i,"sub"+i);
            mData.add(temp);
        }
        MainAdapter mainAdapter = new MainAdapter(this);
        mainAdapter.setDataList(mData);
        mRecyclerView.setAdapter(mainAdapter);
    }
}
