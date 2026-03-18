package edu.mamontova.lab3.response;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse<M, D> {

    private M meta;
    private List<D> data = new ArrayList<>();

    public ApiResponse() {
    }

    public ApiResponse(M meta, List<D> data) {
        this.meta = meta;
        this.data = data;
    }

    public M getMeta() {
        return meta;
    }

    public List<D> getData() {
        return data;
    }

    public void setMeta(M meta) {
        this.meta = meta;
    }

    public void setData(List<D> data) {
        this.data = data;
    }
}