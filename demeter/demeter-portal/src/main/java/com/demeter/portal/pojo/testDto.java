package com.demeter.portal.pojo;

import java.io.Serializable;

public class testDto implements Serializable {
    private Integer id;
    private Integer pageNum;
    private Integer pageSize;

    public testDto() {
    }

    public testDto(Integer id, Integer pageNum, Integer pageSize) {
        this.id = id;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "testDto{" +
                "id=" + id +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
