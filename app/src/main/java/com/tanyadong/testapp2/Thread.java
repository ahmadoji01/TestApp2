package com.tanyadong.testapp2;

/**
 * Created by michaelknight123 on 2/28/2016.
 */
public class Thread
{
    private String postBody;
    private String postVote;
    public int iconID;
    private String postUsername;
    private String postDate;

    public Thread(String postBody, String postVote, int iconID, String postUsername, String postDate)
    {
        super();
        this.postBody = postBody;
        this.postVote = postVote;
        this.iconID = iconID;
        this.postUsername = postUsername;
        this.postDate = postDate;
    }

    public String getPostBody() { return this.postBody; }

    public String getPostVote()
    {
        return this.postVote;
    }

    public int getIconID() { return this.iconID; }

    public String getPostUsername()
    {
        return this.postUsername;
    }

    public String getPostDate() { return this.postDate; }

}
