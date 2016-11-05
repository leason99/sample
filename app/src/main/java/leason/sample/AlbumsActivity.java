package leason.sample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leason on 2016/11/4.
 */
public class AlbumsActivity extends Activity {

    SimpleAdapter simpleAdapter;
    List<Map<String, Bitmap>> list;
    Map<String,Bitmap> map;
    InputStream fo;

    GridView gridView;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albums_activity);

        init();
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/mysimpledownload");


        File files[] =file.listFiles();
for (int i=0;i<files.length;i++) {

    map = new HashMap<String, Bitmap>();
    try {


        Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(files[i]));

        map.put("image", bitmap);
        list.add(map);


    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}

        simpleAdapter=new SimpleAdapter(getApplicationContext(),list,R.layout.grid_view_item,new String[]{"image"},new int[]{R.id.imageView2});
    simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {


            if(view instanceof ImageView && data instanceof Bitmap){
                ImageView i = (ImageView)view;

                i.setImageBitmap((Bitmap) data);
                return true;
            }

            return false;
        }
    });


        gridView.setAdapter(simpleAdapter);
    }


    private void init() {
        list=new ArrayList<>();
        gridView = (GridView) findViewById(R.id.gridView);
    }

}
