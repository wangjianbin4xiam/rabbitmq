package com.zuche.mq;

import java.io.Serializable;
import java.util.Objects;

/**
 * @title:
 * @Description:
 * @author: wangjb
 * @create: 2019-03-21 09:45
 */
public class CardNodeObject implements Serializable {

    private static final long serialVersionUID = -5989948385980248518L;
    /**
     * 消息类型
     */
    private String type;

    /**
     * 消息提示数据
     */
    private String message;

    /**
     * 附带的数据对象{}
     */
    private Object data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
