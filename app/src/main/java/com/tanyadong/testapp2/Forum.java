package com.tanyadong.testapp2;

/**
 * Created by michaelknight123 on 2/24/2016.
 */
public class Forum
{

    private String threadTitle;
    private String threadOverview;
    public int iconID;
    private String threadStarter;
    private String threadId;

    public Forum(String threadTitle, String threadOverview, int iconID, String threadStarter, String threadId)
    {
        super();
        this.threadTitle = threadTitle;
        this.threadOverview = threadOverview;
        this.iconID = iconID;
        this.threadStarter = threadStarter;
        this.threadId = threadId;
    }

    public String getThreadTitle() { return this.threadTitle; }

    public String getThreadOverview()
    {
        return this.threadOverview;
    }

    public int getIconID() { return this.iconID; }

    public String getThreadStarter()
    {
        return this.threadStarter;
    }

    public String getThreadID() { return this.threadId; }

}
