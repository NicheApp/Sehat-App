package com.MJ.Hack.Sehat;


public class Note {
    private String yoga;
    private String desc;
    private String link;
    public Note() {
        //empty constructor needed
    }
    public Note(String yoga, String desc, String link) {
        this.yoga = yoga;
        this.desc = desc;
        this.link = link;
    }

    public String getYoga() {
        return yoga;
    }

    public void setYoga(String yoga) {
        this.yoga = yoga;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
