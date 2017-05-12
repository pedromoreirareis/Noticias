package com.pedromoreirareisgmail.noticias;

public class Noticias {

    private String mWebTitle;
    private String mWebUrl;
    private String mTrailText;
    private String mThumbnail;

    public Noticias(String webTitle, String webUrl, String trailText, String thumbnail) {
        mWebTitle = webTitle;
        mWebUrl = webUrl;
        mTrailText = trailText;
        mThumbnail = thumbnail;
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
