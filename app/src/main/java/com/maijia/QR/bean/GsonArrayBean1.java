package com.maijia.QR.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author：created by zhaoliang
 * @date 2019/3/9 18:23
 * @desc：
 */
public class GsonArrayBean1 implements Serializable {

    private List<List<ResultBean>> result;

    public List<List<ResultBean>> getResult() {
        return result;
    }

    public void setResult(List<List<ResultBean>> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * title : 直属销售额
         * value : 0
         */

        private int id;
        private String title;
        private String value;

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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
