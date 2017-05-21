package com.pedromoreirareisgmail.noticias.noticia;

public class Noticias {

    private String mSectionName;
    private String mWebPublicationDate;
    private String mWebTitle;
    private String mWebUrl;
    private String mTrailText;
    private String mThumbnail;

    public Noticias(String sectionName, String webPublicationDate, String webTitle, String webUrl, String trailText, String thumbnail) {
        mSectionName = sectionName;
        mWebPublicationDate = webPublicationDate;
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        mTrailText = trailText;
        mThumbnail = thumbnail;
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
