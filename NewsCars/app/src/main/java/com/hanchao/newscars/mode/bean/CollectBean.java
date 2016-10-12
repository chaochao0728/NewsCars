package com.hanchao.newscars.mode.bean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/10/10.
 * 收藏的实体类
 */
@Table("collect")
public class CollectBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;


    private int newsId;
    private String imagurl;
    private String content;

    public CollectBean() {
    }

    public CollectBean(int id, int newsId, String imagurl, String content) {
        this.id = id;
        this.newsId = newsId;
        this.imagurl = imagurl;
        this.content = content;
    }

    public CollectBean(int newsId, String imagurl, String content) {
        this.newsId = newsId;
        this.imagurl = imagurl;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagurl() {
        return imagurl;
    }

    public void setImagurl(String imagurl) {
        this.imagurl = imagurl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
}
