package org.codegenerator.entity;

/**
 * Created by LZ on 2017/7/10.
 */
public class DtoResponse {

    private int RESPONSE_CODE_SUCCESS=0x01;
    private int RESPONSE_CODE_ERROR=0x02;

    private int responseCode = RESPONSE_CODE_ERROR;

    private Exception e;

    private String response_data;

    public DtoResponse(Exception e){
        this.responseCode=RESPONSE_CODE_ERROR;
        this.e=e;
    }
    public DtoResponse(String response_data){
        this.responseCode=RESPONSE_CODE_SUCCESS;
        this.response_data=response_data;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    public String getResponse_data() {
        return response_data;
    }

    public void setResponse_data(String response_data) {
        this.response_data = response_data;
    }
}
