package com.example.android.viewpager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("You have unfollowed these sites: "+"\n\n");

        NewsAdapter mNewsAdapter = new NewsAdapter(getApplicationContext(),null);
        ArrayList<String> list1 = mNewsAdapter.getList();
        if(list1!=null) {
            String[] names = new String[list1.size()];
            for (int i = 0; i < list1.size(); i++) {
                names[i] = list1.get(i);
                int p = i+1;
                textView.append("\n" +"\t" + p+". "+names[i]);
            }
        }else {
            textView.append("\n"+ "0 ");
        }
    }
}
