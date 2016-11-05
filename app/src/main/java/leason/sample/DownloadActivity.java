package leason.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by leason on 2016/11/4.
 */
public class DownloadActivity extends Activity {
    EditText urlEditText;
    Button downloadButton;
    ImageView imageView;
    URL downloalUrl;
    Bitmap bitmap;
   static DownloadActivity  instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_activity);

        init();


    }

    private void init() {

instance=this;
        urlEditText=(EditText)findViewById(R.id.urlEditText);
        downloadButton=(Button)findViewById(R.id.downloadbutton);
        imageView=(ImageView)findViewById(R.id.imageView);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(urlEditText.getText().toString().equals("")){


                    AlertDialog dialog=new AlertDialog.Builder(DownloadActivity.instance)
                            .setTitle("錯誤")
                            .setMessage("白癡歐 打網址辣")
                            .setPositiveButton("我是白癡我知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setCancelable(false).create();
                    dialog.show();

                }
               else {
                    try {
                        downloalUrl = new URL(urlEditText.getText().toString());
                        download(downloalUrl);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();

                    }
                }
            }
        });



    }

    private void download(final URL url) {
        DownloadTask task=new DownloadTask();
        task.execute(url);
    }


}
