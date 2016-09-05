package ar.com.osde.esb.component.changelogManager.app.utils;


public class ResponseObject {

    private String status;
    private int code;

    public ResponseObject(int code, String status){
        setCode(code);
        setStatus(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
