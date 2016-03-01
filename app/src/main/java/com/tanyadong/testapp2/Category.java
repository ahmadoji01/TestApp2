package com.tanyadong.testapp2;

/**
 * Created by michaelknight123 on 2/24/2016.
 */
public class Category
{

    private String categoryName;
    private int iconID;

    public Category(String categoryName, int iconID)
    {
        super();
        this.categoryName = categoryName;
        this.iconID = iconID;
    }

    public String getCategoryName() { return this.categoryName; }

    public int getIconID() { return this.iconID; }

}
