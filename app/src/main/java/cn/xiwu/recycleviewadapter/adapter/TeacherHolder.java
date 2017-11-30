package cn.xiwu.recycleviewadapter.adapter;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import cn.xiwu.recycleviewadapter.R;
import cn.xiwu.recycleviewadapter.adapter.base.RecycleBaseHolder;

/**
 * Created by zuzu on 2017/11/30.
 */
public class TeacherHolder extends RecycleBaseHolder
{
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.sub)
    TextView sub;

    public TeacherHolder(View view)
    {
        super(view);
    }

    public static int getLayoutId()
    {
        return R.layout.item_teacher;
    }
}
