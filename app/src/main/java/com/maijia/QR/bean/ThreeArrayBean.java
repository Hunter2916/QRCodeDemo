package com.maijia.QR.bean;

import java.io.Serializable;
import java.util.List;

public class ThreeArrayBean implements Serializable {

    private List<Data2Bean> Data2;

    public List<Data2Bean> getData2() {
        return Data2;
    }

    public void setData2(List<Data2Bean> Data2) {
        this.Data2 = Data2;
    }

    public static class Data2Bean {
        /**
         * Data : [{"Id":1,"Title":"奖金池分红","Value":0}]
         * Title : 奖金池
         */

        private String Title;
        private List<DataBean> Data;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class DataBean {
            /**
             * Id : 1
             * Title : 奖金池分红
             * Value : 0
             */

            private int Id;
            private String Title;
            private int Value;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public int getValue() {
                return Value;
            }

            public void setValue(int Value) {
                this.Value = Value;
            }
        }
    }
}
