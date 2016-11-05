package leason.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
SimpleAdapter simpleAdapter;
        ListView mainListView;
    List<Map<String,String>>  mainLsit;
    Map<String,String>  mainMap;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    private void init() {
mainLsit=new ArrayList<>();
        mainMap= new HashMap<String,String>();
        mainMap.put("item","圖片下載");
        mainLsit.add(mainMap);
        mainMap= new HashMap<String,String>();
        mainMap.put("item","相簿");
        mainLsit.add(mainMap);
        simpleAdapter=new SimpleAdapter(getApplicationContext(),mainLsit,R.layout.listview_item,new String[]{"item"},new int[]{R.id.textView2});
        mainListView=(ListView)findViewById(R.id.main_list);
        mainListView.setAdapter(simpleAdapter);
       mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               switch (position)
               {
                   case 0:
                       intent=new Intent();
                       intent= intent.setClass(MainActivity.this,DownloadActivity.class);
                       startActivity(intent);
                   break;
                   case 1:
                       intent=new Intent();
                       intent.setClass(MainActivity.this,AlbumsActivity.class);
                       startActivity(intent);
                   break;


               }
           }
       });


    }
}
