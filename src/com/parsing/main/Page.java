package com.parsing.main;

public class Page {
    private String id;
    private String text;
    private String title;
    
    public Page(){}
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString() {
        return "Page:: ID="+this.id+" Text=" + this.text + " Title=" + title;
    }
}