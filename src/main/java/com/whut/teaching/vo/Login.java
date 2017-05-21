package com.whut.teaching.vo;

/**
 * Created by wpc on 2017/5/19.
 */
public class Login<T extends Object> {
    private String token;
    private T t;

    public Login(String token, T t) {
        this.token = token;
        this.t = t;
    }

    public Login() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
