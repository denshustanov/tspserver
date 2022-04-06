package com.company.tspserver.service.impl.fcm;

public class PushNotifyConf {
    private String title;
    private String body;
    private String icon;
    private String ttlInSeconds;

    public PushNotifyConf() {
    }

    public PushNotifyConf(String title, String body, String icon, String ttlInSeconds) {
        this.title = title;
        this.body = body;
        this.icon = icon;
        this.ttlInSeconds = ttlInSeconds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTtlInSeconds() {
        return ttlInSeconds;
    }

    public void setTtlInSeconds(String ttlInSeconds) {
        this.ttlInSeconds = ttlInSeconds;
    }
}
