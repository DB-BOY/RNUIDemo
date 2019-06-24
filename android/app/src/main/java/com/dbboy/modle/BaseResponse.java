package com.dbboy.modle;

import java.io.Serializable;

/**
 * 网络请求基类
 */
public class BaseResponse implements Serializable {

     private  int   status;
     private  String   message;
     private  String   mess;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", mess='").append(mess).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
