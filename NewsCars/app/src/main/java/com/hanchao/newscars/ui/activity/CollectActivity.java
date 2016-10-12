package com.hanchao.newscars.ui.activity;

import android.widget.ListView;

import com.hanchao.newscars.R;
import com.hanchao.newscars.mode.bean.CollectBean;
import com.hanchao.newscars.mode.db.LiteOrmInstance;
import com.hanchao.newscars.ui.adapter.CollectAdapter;

import java.util.List;

/**
 * Created by dllo on 16/10/9.
 * 收藏界面
 */
public class CollectActivity extends AbsBaseActivity {
    private ListView listView;
    private CollectAdapter adapter;
    List<CollectBean> data;

    @Override
    protected int setLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.activity_collect_list_view);
    }

    @Override
    protected void initDatas() {
        adapter = new CollectAdapter(this);
        listView.setAdapter(adapter);
        data = LiteOrmInstance.getInstance().queryAll();
        adapter.setData(data);
    }
}
