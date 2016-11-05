package leason.sample;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by leason on 2016/11/4.
 */
public class DownloadTask extends AsyncTask<URL,Void,Bitmap>{
    Bitmap  bitmap;
    URL url;
 ProgressDialog progerssdialog;
    @Override
    protected Bitmap doInBackground(URL... url) {

        this.url=url[0];
        try {
            HttpURLConnection connection= (HttpURLConnection) url[0].openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");

            connection.setConnectTimeout(50000);

            connection.connect();
            if(connection.getResponseCode()==200){
              bitmap= BitmapFactory.decodeStream( connection.getInputStream());
            }






        } catch (IOException e) {
            e.printStackTrace();
        }


        return bitmap;



    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

      DownloadActivity.instance.imageView.setImageBitmap(bitmap);

        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/mysimpledownload");
        if (!dir.exists()){

            dir.mkdir();
        }
        if (dir.exists()){

            Toast.makeText(DownloadActivity.instance.getApplicationContext(),"mkdir",Toast.LENGTH_LONG).show();
        }

        File file  =new File(dir.getAbsolutePath(),url.getFile().substring(url.getFile().lastIndexOf("/")));
        OutputStream os=null;
        try {

            file.createNewFile();

         os = new BufferedOutputStream(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()){
         Toast.makeText(DownloadActivity.instance.getApplicationContext(),"saved",Toast.LENGTH_LONG).show();

        }


        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        progerssdialog.dismiss();
        super.onPostExecute(bitmap);
    }

    @Override
    protected void onPreExecute() {
        progerssdialog =new ProgressDialog(DownloadActivity.instance);
        progerssdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progerssdialog.setTitle("下載中");
        progerssdialog.setMessage("下載得慢 是你的雷網路 \n好東西執得等待 請稍候");
        progerssdialog.show();

    }
}
