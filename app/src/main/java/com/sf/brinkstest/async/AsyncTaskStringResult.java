package com.sf.brinkstest.async;

/**
 * Created by sf on 2/13/17.
 */

public class AsyncTaskStringResult {
    private String result;
    private int codeRequest;

    public AsyncTaskStringResult(String result,int codeRequest) {
        this.result = result;
        this.codeRequest = codeRequest;
    }

    public String getResult() {
        return result;
    }

    public int getCodeRequest() {
        return codeRequest;
    }
}
