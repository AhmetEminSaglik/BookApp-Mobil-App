package com.ahmeteminsaglik.neo4jbookappandroid.model.response.abstracts;

public class RestApiResponse <T> extends RestApiBaseResponse {
    private boolean success;
    private  T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestApiResponse{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
