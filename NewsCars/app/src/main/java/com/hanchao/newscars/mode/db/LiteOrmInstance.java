package com.hanchao.newscars.mode.db;

import com.hanchao.newscars.mode.bean.CollectBean;
import com.hanchao.newscars.ui.app.NewsCarsApp;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.List;
import java.util.Objects;

/**
 * Created by dllo on 16/10/10.
 * 数据库的单例类
 */
public class LiteOrmInstance {
    private static LiteOrmInstance instance;
    /**
     * 数据库的名字
     */
    private static final String DB_NAME = "collect.db";
    /**
     * liteOrm对象
     */
    private LiteOrm liteOrm;

    private LiteOrmInstance() {
        liteOrm = LiteOrm.newSingleInstance(NewsCarsApp.getContext(), DB_NAME);
    }

    public static LiteOrmInstance getInstance() {
        if (instance == null) {
            synchronized (LiteOrmInstance.class) {
                if (instance == null) {
                    instance = new LiteOrmInstance();
                }
            }
        }
        return instance;
    }

    //插入一条数据
    public void insert(CollectBean collectBean) {
        liteOrm.insert(collectBean);
    }

    //查询所有的数据
    public List<CollectBean> queryAll() {
        return liteOrm.query(CollectBean.class);
    }

    //按条件查询
    public List<CollectBean> quryById(String Id) {
        QueryBuilder<CollectBean> queryBuilder = new QueryBuilder<>(CollectBean.class);
        queryBuilder.where(" newsId = ? ", new Object[]{Id});
        return liteOrm.query(queryBuilder);
    }

    //删除所有
    public void deleteAll() {
        liteOrm.deleteAll(CollectBean.class);
    }

    //按条件删除
    public void deletedTitle(String Id) {
        WhereBuilder wb = new WhereBuilder(CollectBean.class);
        wb.where("newsId = ? ", new Object[]{Id});
        liteOrm.delete(wb);
    }
}
