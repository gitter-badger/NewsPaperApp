package com.example.android.viewpager;

public class News {
    private String mArticle;
    private String mImg;
    private String mNewspaperName;
    private CharSequence mTime;
    private String mUrlLink;

    public News(String Img, String Article, String newspaperName, String Time, String UrlLink){
        mImg = Img;
        mArticle = Article;
        mNewspaperName = newspaperName;
        mTime = Time;
        mUrlLink = UrlLink;
    }

    public String getImg(){
        return mImg;
    }

    public String getArticle(){
        return mArticle;
    }
    public CharSequence getTime(){
        return mTime;
    }
    public String getNewspaperName(){
        return mNewspaperName;
    }
    public String getUrlLink(){
        return mUrlLink;
    }


    public News() {
        this.mArticle = "";
        this.mUrlLink = "";
        this.mImg = "";
        this.mTime = "";
        this.mNewspaperName="";
    }

    public void setArticle(String article) {
        this.mArticle = article;
    }

    public void setImg(String img) {
        this.mImg = img;
    }
    public void setTime(CharSequence time){
        this.mTime = time;
    }
    public void setNewspaperName(String newspaperName){
        this.mNewspaperName = newspaperName;
    }
    public void setUrlLink(String urlLink){
        this.mUrlLink = urlLink;
    }

}
