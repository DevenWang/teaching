package com.whut.teaching.vo;

/**
 * Created by wpc on 2017/5/19.
 */
public class VO<T extends Object> {

    public static final VO SUCCESS = new VO<>(null);

    //    用户级
    public static final VO WRONG_UID_PSWD = new VO<>(1001, "wrong_uid_pswd", null);
    public static final VO EXPIRED_PSWD = new VO<>(1002, "expired_pswd", null);
    public static final VO INVALID_TOKEN = new VO<>(401, "invalid_token", null);
    public static final VO ACTION_FORBIDDEN = new VO<>(1004, "action_forbidden", null);


    //////////////////////////////////////////////////////////////////
    private int status;
    private String msg;
    private T data;

    public VO(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public VO(T data) {
        this.status = 200;
        this.msg = "success";
        this.data = data;
    }

    public VO() {
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
