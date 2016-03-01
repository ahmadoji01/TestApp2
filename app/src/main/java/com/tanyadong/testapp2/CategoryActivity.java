package com.tanyadong.testapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelknight123 on 2/29/2016.
 */
public class CategoryActivity extends AppCompatActivity
{

    private List<Category> myCategory = new ArrayList<Category>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        populateForumList("Matematika", R.drawable.icon_math);
        populateForumList("Bahasa Indonesia", R.drawable.icon_indonesia);
        populateForumList("Bahasa Inggris", R.drawable.icon_english);
        populateForumList("Biologi", R.drawable.icon_biology);
        populateForumList("Kimia", R.drawable.icon_chemistry);
        populateForumList("Fisika", R.drawable.icon_physics);
        populateForumList("Sejarah", R.drawable.icon_history);
        populateForumList("Sosiologi", R.drawable.icon_sociology);
        populateForumList("Geografi", R.drawable.icon_geography);
        populateForumList("Ekonomi", R.drawable.icon_economy);
        populateForumList("Bimbingan dan Konseling", R.drawable.icon_gc);
        populateListView();
        registerClickCallback();
    }

    private void populateForumList(String categoryName, int iconID)
    {
        myCategory.add(new Category(categoryName, iconID));
    }

    private void populateListView()
    {
        ArrayAdapter<Category> adapter = new MyListAdapter();
        ListView list = (ListView)findViewById(R.id.lvCategory);
        list.setAdapter(adapter);
    }

    private void registerClickCallback()
    {
        final Intent intent = new Intent(CategoryActivity.this, ForumActivity.class);
        ListView list = (ListView)findViewById(R.id.lvCategory);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Category clickedCategory = myCategory.get(position);
                intent.putExtra("categoryName", clickedCategory.getCategoryName());
                startActivity(intent);
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Category>
    {

        public MyListAdapter()
        {
            super(CategoryActivity.this, R.layout.category_item, myCategory);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.category_item, parent, false);
            }

            Category currentCategory = myCategory.get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.ivCategoryIcon);
            imageView.setImageResource(currentCategory.getIconID());

            TextView categoryText = (TextView)itemView.findViewById(R.id.tvCategoryName);
            categoryText.setText(currentCategory.getCategoryName());

            return itemView;
        }
    }
}
