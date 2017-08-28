package com.example.ipandaitems.model.entry.pandalive;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PLAmaPhotoes {

    /**
     * videoset : {"0":{"vsid":"VSET100167216881","relvsid":"","name":"熊猫频道-精彩一刻","img":"http://p5.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809214479325.jpg","enname":"其他","url":"http://tv.cntv.cn/videoset/VSET100167216881","cd":"","zy":"","bj":"","dy":"","js":"","nf":"","yz":"","fl":"","sbsj":"2013-05-01","sbpd":"其他","desc":"精彩一刻栏目关注人气较高的熊猫个体，精选每日直播中最吸引人的画面，呈现熊猫生活中最精彩的状态。","playdesc":"","zcr":"","fcl":""},"count":"4756"}
     * video : [{"vsid":"VSET100167216881","order":"4759","vid":"11d0eae8922c421f86c1ca031c06c35b","t":"《精彩一刻》 20170825 这都是我的新朋友~","url":"http://tv.cntv.cn/video/VSET100167216881/11d0eae8922c421f86c1ca031c06c35b","ptime":"2017-08-25 12:18:11","img":"http://p4.img.cctvpic.com/fmspic/2017/08/25/11d0eae8922c421f86c1ca031c06c35b-33.jpg?p=2&h=120","len":"00:00:46","em":"CM01"},{"vsid":"VSET100167216881","order":"4757","vid":"561f0600b8964e99ad981a89cfa2c55c","t":"《精彩一刻》 20170825 给大家敬个礼吧~","url":"http://tv.cntv.cn/video/VSET100167216881/561f0600b8964e99ad981a89cfa2c55c","ptime":"2017-08-25 12:15:37","img":"http://p2.img.cctvpic.com/fmspic/2017/08/25/561f0600b8964e99ad981a89cfa2c55c-31.jpg?p=2&h=120","len":"00:00:42","em":"CM01"},{"vsid":"VSET100167216881","order":"4760","vid":"d6aaeb52114347cfb83dcc807c49d744","t":"《精彩一刻》 20170825 蔓越煤：奶爸，你就给我吧","url":"http://tv.cntv.cn/video/VSET100167216881/d6aaeb52114347cfb83dcc807c49d744","ptime":"2017-08-25 12:14:41","img":"http://p1.img.cctvpic.com/fmspic/2017/08/25/d6aaeb52114347cfb83dcc807c49d744-20.jpg?p=2&h=120","len":"00:00:20","em":"CM01"},{"vsid":"VSET100167216881","order":"4758","vid":"c14ab8db92db45eb840dc78b13617506","t":"《精彩一刻》 20170825 宝宝：蛋糕当然是拿来玩的","url":"http://tv.cntv.cn/video/VSET100167216881/c14ab8db92db45eb840dc78b13617506","ptime":"2017-08-25 12:13:05","img":"http://p4.img.cctvpic.com/fmspic/2017/08/25/c14ab8db92db45eb840dc78b13617506-33.jpg?p=2&h=120","len":"00:00:45","em":"CM01"},{"vsid":"VSET100167216881","order":"4756","vid":"4dc943a75cf843d9b165ce10b60b3330","t":"《精彩一刻》 20170825 大熊猫玩儿杂技的日常练习","url":"http://tv.cntv.cn/video/VSET100167216881/4dc943a75cf843d9b165ce10b60b3330","ptime":"2017-08-25 11:14:00","img":"http://p1.img.cctvpic.com/fmspic/2017/08/25/4dc943a75cf843d9b165ce10b60b3330-22.jpg?p=2&h=120","len":"00:00:38","em":"CM01"},{"vsid":"VSET100167216881","order":"4753","vid":"f9b5840ce6e34569aca17bf14bb29a68","t":"《精彩一刻》 20170825 来啊〜互相伤害嘛！","url":"http://tv.cntv.cn/video/VSET100167216881/f9b5840ce6e34569aca17bf14bb29a68","ptime":"2017-08-25 11:11:42","img":"http://p4.img.cctvpic.com/fmspic/2017/08/25/f9b5840ce6e34569aca17bf14bb29a68-20.jpg?p=2&h=120","len":"00:00:31","em":"CM01"},{"vsid":"VSET100167216881","order":"4755","vid":"437988da23b5407eb440605e53fb5571","t":"《精彩一刻》 20170825 大熊猫听到\u201c拍照\u201d时的反应","url":"http://tv.cntv.cn/video/VSET100167216881/437988da23b5407eb440605e53fb5571","ptime":"2017-08-25 11:10:04","img":"http://p1.img.cctvpic.com/fmspic/2017/08/25/437988da23b5407eb440605e53fb5571-9.jpg?p=2&h=120","len":"00:00:17","em":"CM01"}]
     */

    private VideosetBean videoset;
    private List<VideoBean> video;

    public VideosetBean getVideoset() {
        return videoset;
    }

    public void setVideoset(VideosetBean videoset) {
        this.videoset = videoset;
    }

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public static class VideosetBean {
        /**
         * 0 : {"vsid":"VSET100167216881","relvsid":"","name":"熊猫频道-精彩一刻","img":"http://p5.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809214479325.jpg","enname":"其他","url":"http://tv.cntv.cn/videoset/VSET100167216881","cd":"","zy":"","bj":"","dy":"","js":"","nf":"","yz":"","fl":"","sbsj":"2013-05-01","sbpd":"其他","desc":"精彩一刻栏目关注人气较高的熊猫个体，精选每日直播中最吸引人的画面，呈现熊猫生活中最精彩的状态。","playdesc":"","zcr":"","fcl":""}
         * count : 4756
         */

        @SerializedName("0")
        private _$0Bean _$0;
        private String count;

        public _$0Bean get_$0() {
            return _$0;
        }

        public void set_$0(_$0Bean _$0) {
            this._$0 = _$0;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public static class _$0Bean {
            /**
             * vsid : VSET100167216881
             * relvsid :
             * name : 熊猫频道-精彩一刻
             * img : http://p5.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809214479325.jpg
             * enname : 其他
             * url : http://tv.cntv.cn/videoset/VSET100167216881
             * cd :
             * zy :
             * bj :
             * dy :
             * js :
             * nf :
             * yz :
             * fl :
             * sbsj : 2013-05-01
             * sbpd : 其他
             * desc : 精彩一刻栏目关注人气较高的熊猫个体，精选每日直播中最吸引人的画面，呈现熊猫生活中最精彩的状态。
             * playdesc :
             * zcr :
             * fcl :
             */

            private String vsid;
            private String relvsid;
            private String name;
            private String img;
            private String enname;
            private String url;
            private String cd;
            private String zy;
            private String bj;
            private String dy;
            private String js;
            private String nf;
            private String yz;
            private String fl;
            private String sbsj;
            private String sbpd;
            private String desc;
            private String playdesc;
            private String zcr;
            private String fcl;

            public String getVsid() {
                return vsid;
            }

            public void setVsid(String vsid) {
                this.vsid = vsid;
            }

            public String getRelvsid() {
                return relvsid;
            }

            public void setRelvsid(String relvsid) {
                this.relvsid = relvsid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getEnname() {
                return enname;
            }

            public void setEnname(String enname) {
                this.enname = enname;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public String getZy() {
                return zy;
            }

            public void setZy(String zy) {
                this.zy = zy;
            }

            public String getBj() {
                return bj;
            }

            public void setBj(String bj) {
                this.bj = bj;
            }

            public String getDy() {
                return dy;
            }

            public void setDy(String dy) {
                this.dy = dy;
            }

            public String getJs() {
                return js;
            }

            public void setJs(String js) {
                this.js = js;
            }

            public String getNf() {
                return nf;
            }

            public void setNf(String nf) {
                this.nf = nf;
            }

            public String getYz() {
                return yz;
            }

            public void setYz(String yz) {
                this.yz = yz;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getSbsj() {
                return sbsj;
            }

            public void setSbsj(String sbsj) {
                this.sbsj = sbsj;
            }

            public String getSbpd() {
                return sbpd;
            }

            public void setSbpd(String sbpd) {
                this.sbpd = sbpd;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPlaydesc() {
                return playdesc;
            }

            public void setPlaydesc(String playdesc) {
                this.playdesc = playdesc;
            }

            public String getZcr() {
                return zcr;
            }

            public void setZcr(String zcr) {
                this.zcr = zcr;
            }

            public String getFcl() {
                return fcl;
            }

            public void setFcl(String fcl) {
                this.fcl = fcl;
            }
        }
    }

    public static class VideoBean {
        /**
         * vsid : VSET100167216881
         * order : 4759
         * vid : 11d0eae8922c421f86c1ca031c06c35b
         * t : 《精彩一刻》 20170825 这都是我的新朋友~
         * url : http://tv.cntv.cn/video/VSET100167216881/11d0eae8922c421f86c1ca031c06c35b
         * ptime : 2017-08-25 12:18:11
         * img : http://p4.img.cctvpic.com/fmspic/2017/08/25/11d0eae8922c421f86c1ca031c06c35b-33.jpg?p=2&h=120
         * len : 00:00:46
         * em : CM01
         */

        private String vsid;
        private String order;
        private String vid;
        private String t;
        private String url;
        private String ptime;
        private String img;
        private String len;
        private String em;

        public String getVsid() {
            return vsid;
        }

        public void setVsid(String vsid) {
            this.vsid = vsid;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLen() {
            return len;
        }

        public void setLen(String len) {
            this.len = len;
        }

        public String getEm() {
            return em;
        }

        public void setEm(String em) {
            this.em = em;
        }
    }
}
