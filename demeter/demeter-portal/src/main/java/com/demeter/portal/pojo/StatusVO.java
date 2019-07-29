package com.demeter.portal.pojo;

public class StatusVO {
    private Integer status;
    private String msg;

    public StatusVO() {
    }

    public StatusVO(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "StatusVO{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
