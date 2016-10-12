package com.hanchao.newscars.mode.bean;

import java.util.List;

/**
 * Created by dllo on 16/10/11.
 */
public class RoateBean {

    /**
     * returncode : 0
     * message :
     * result : {"list":[{"id":9803,"title":"1010","shorttitle":"双11","url":"http://m.1111.autohome.com.cn/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g11/M14/6B/3E/autohomecar__wKgH0lf7SamAcPMQAAHjn-vyRJ0354.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":9808,"title":"1011","shorttitle":"购车节","url":"http://m.mall.autohome.com.cn/topic/2016/9/gcj/?isapp=1#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g16/M0F/6A/F3/autohomecar__wKgH11f7U0iAAyD0AAJTMu2PiLQ808.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":9805,"title":"10.11分期推广","shorttitle":"通用","url":"http://hd.j.autohome.com.cn/loan/loan/sqty?type=ty#pvareaid=106574","imgurl":"http://app2.autoimg.cn/appdfs/g10/M00/6E/A4/autohomecar__wKjBzVf7UZSAe4YWAAG-ppSKuI8046.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":9801,"title":"1011","shorttitle":"东南","url":"http://m.mall.autohome.com.cn/topic/2016/7/dx7/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g12/M0E/6F/AC/autohomecar__wKjBy1f7PDOAebO_AAKoged9GLU280.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":9799,"title":"1011","shorttitle":"昂科威四驱 全国","url":"http://m.mall.autohome.com.cn/detail/142062-0-0.html#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g21/M00/4F/0F/autohomecar__wKgFWlf7Nu2AJiRVAAJGWrnybaI029.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0}]}
     */

    private int returncode;
    private String message;
    private ResultBean result;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 9803
         * title : 1010
         * shorttitle : 双11
         * url : http://m.1111.autohome.com.cn/#pvareaid=104735
         * imgurl : http://app2.autoimg.cn/appdfs/g11/M14/6B/3E/autohomecar__wKgH0lf7SamAcPMQAAHjn-vyRJ0354.jpg
         * urlscheme :
         * type : 2
         * appicon :
         * siteindex : 0
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String title;
            private String shorttitle;
            private String url;
            private String imgurl;
            private String urlscheme;
            private int type;
            private String appicon;
            private int siteindex;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShorttitle() {
                return shorttitle;
            }

            public void setShorttitle(String shorttitle) {
                this.shorttitle = shorttitle;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getUrlscheme() {
                return urlscheme;
            }

            public void setUrlscheme(String urlscheme) {
                this.urlscheme = urlscheme;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAppicon() {
                return appicon;
            }

            public void setAppicon(String appicon) {
                this.appicon = appicon;
            }

            public int getSiteindex() {
                return siteindex;
            }

            public void setSiteindex(int siteindex) {
                this.siteindex = siteindex;
            }
        }
    }
}
