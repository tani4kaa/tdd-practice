package edu.mamontova.lab3.response;

public class BaseMetaData {

    private int code = 200;
    private boolean success = true;
    private String errorMessage = null;

    public BaseMetaData() {
    }

    public BaseMetaData(int code, boolean success, String errorMessage) {
        this.code = code;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}