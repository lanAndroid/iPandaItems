package com.example.ipandaitems.model.entry;

import java.util.List;

/**
 * Created by 1 on 2017/8/29.
 */

public class TopListBean {
    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * num : 1
         * datatype : article
         * id : ARTImgDE0kXMom5JObH4nK5f170619
         * title : 四川将推全球唯一大熊猫国际生态旅游线 成都要建“熊猫王国”
         * videolength :
         * guid :
         * picurl : http://p1.img.cctvpic.com/photoworkspace/2017/06/19/2017061914593259084.jpg
         * picurl2 :
         * picurl3 :
         * url : http://panview.ipanda.com/2017/06/19/ARTImgDE0kXMom5JObH4nK5f170619.shtml
         * focus_date : 1497855590000
         * isaixiyou : 0
         */

        private int num;
        private String datatype;
        private String id;
        private String title;
        private String videolength;
        private String guid;
        private String picurl;
        private String picurl2;
        private String picurl3;
        private String url;
        private long focus_date;
        private String isaixiyou;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getDatatype() {
            return datatype;
        }

        public void setDatatype(String datatype) {
            this.datatype = datatype;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideolength() {
            return videolength;
        }

        public void setVideolength(String videolength) {
            this.videolength = videolength;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getPicurl2() {
            return picurl2;
        }

        public void setPicurl2(String picurl2) {
            this.picurl2 = picurl2;
        }

        public String getPicurl3() {
            return picurl3;
        }

        public void setPicurl3(String picurl3) {
            this.picurl3 = picurl3;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getFocus_date() {
            return focus_date;
        }

        public void setFocus_date(long focus_date) {
            this.focus_date = focus_date;
        }

        public String getIsaixiyou() {
            return isaixiyou;
        }

        public void setIsaixiyou(String isaixiyou) {
            this.isaixiyou = isaixiyou;
        }
    }
}
