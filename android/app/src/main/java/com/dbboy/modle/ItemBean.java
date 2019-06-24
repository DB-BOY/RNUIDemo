package com.dbboy.modle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DB_BOY on 2019/6/13.</br>
 */
public class ItemBean extends BaseResponse{
    
    private List<ExpertEntity> data;

    public List<ExpertEntity> getData() {
        return data;
    }

    public void setData(List<ExpertEntity> data) {
        this.data = data;
    }

    public static class ExpertEntity implements Serializable {
     
        private String item_cover;
        private String item_name;
        private String item_id;
        private String attention_status;
        private String pinyinName;

        public String getPinyinName() {
            return pinyinName;
        }

        public void setPinyinName(String pinyinName) {
            this.pinyinName = pinyinName;
        }

        public String getItem_cover() {
            return item_cover;
        }

        public void setItem_cover(String item_cover) {
            this.item_cover = item_cover;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getAttention_status() {
            return attention_status;
        }

        public void setAttention_status(String attention_status) {
            this.attention_status = attention_status;
        }
        
        public boolean isFocus(){
            return "1".equals(attention_status);
        }
    
    }

}
