package com.example.michal.asisstantv04.Models;

public class Argument {

    public int argId;
    public int requestId;
    public String argContent;
    public String type;

    public Argument(int argId, int requestId, String argContent, String type) {
        this.argId = argId;
        this.requestId = requestId;
        this.argContent = argContent;
        this.type = type;
    }

    public Argument() {

    }

    public int getArgId() {
        return argId;
    }

    public void setArgId(int argId) {
        this.argId = argId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getArgContent() {
        return argContent;
    }

    public void setArgContent(String argContent) {
        this.argContent = argContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return argContent + " " + type;
    }
}
