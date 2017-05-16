package com.pedromoreirareisgmail.noticias;

public class Noticias {

    private int mPage;
    private String mSectionName;
    private String mWebPublicationDate;
    private String mWebTitle;
    private String mWebUrl;
    private String mTrailText;
    private String mThumbnail;

    public Noticias(int page, String sectionName, String webPublicationDate, String webTitle, String webUrl, String trailText, String thumbnail) {
        mPage = page;
        mSectionName = sectionName;
        mWebPublicationDate = webPublicationDate;
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        mTrailText = trailText;
        mThumbnail = thumbnail;
    }

    public int getmPage() {
        return mPage;
    }

    public String getmSectionName() {
        return mSectionName;
    }

    public String getmWebPublicationDate() {
        return mWebPublicationDate;
    }

    public String getmWebTitle() {
        return mWebTitle;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public String getmTrailText() {
        return mTrailText;
    }

    public String getmThumbnail() {
        return mThumbnail;
    }
}
