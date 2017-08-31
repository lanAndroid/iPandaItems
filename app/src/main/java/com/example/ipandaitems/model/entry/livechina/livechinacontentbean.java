package com.example.ipandaitems.model.entry.livechina;

import java.util.List;

/**
 * Created by 张豫耀 on 2017/8/29.
 */

public class livechinacontentbean {


    private List<LiveBean> live;

    public List<LiveBean> getLive() {
        return live;
    }

    public void setLive(List<LiveBean> live) {
        this.live = live;
    }

    public static class LiveBean {
        /**
         * title : 婺源江岭一
         * brief : 江岭，位于婺源县东北部，距县城45公里，总面积38平方公里，黄灿灿的万亩梯田油菜花，使江岭成为婺源春天的宠儿，同时这里也是全国最具视觉冲击力的油菜花观赏地。
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2016/3/7/1457343989096_4.jpg
         * id : wygjt1
         * order : 1
         */

        private String title;
        private String brief;
        private String image;
        private String id;
        private String order;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
}
