package scut.com.testprogressbar;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressBar pb_Horizontal,pb_Circle;
    private TimeHandler timeHandler = new TimeHandler();
    private Button btn_pd;
    private int progress = 0,secondProgress = 0;
    private CustomProgressDialog progressDialog;
    private String[] MESSAGES = {"加载中","加载中.","加载中..","加载中..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb_Horizontal = (ProgressBar) findViewById(R.id.pb_Horizontal);
//        pb_Horizontal.setBackgroundColor(Color.RED);
        pb_Circle = (ProgressBar) findViewById(R.id.pb_Circle);
        btn_pd = (Button) findViewById(R.id.btn_pd);
        btn_pd.setOnClickListener(this);
        progressDialog = new CustomProgressDialog(this);
        progressDialog.setMessage("加载中");
        timeHandler.sendEmptyMessage(1);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pd:
                showDialog();
                progress = 0;
//                pb_Circle.setVisibility(View.INVISIBLE);

                System.out.println("width : " + pb_Circle.getWidth());
                System.out.println("height : " + pb_Circle.getHeight());
                break;
        }

    }

    private void showDialog(){
//        Window window = progressDialog.getWindow();
//        WindowManager.LayoutParams wp = window.getAttributes();
//        wp.alpha = 0.5f;
//        window.setAttributes(wp);

//        progressDialog.setMessage("正在加载");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                System.out.println("cancel");
//            }
//        });
//        progressDialog.show();


        progressDialog.show();
    }


    class TimeHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if (progress < 100){
                        pb_Horizontal.setProgress(progress);
                        pb_Horizontal.setSecondaryProgress(secondProgress);
                        pb_Circle.setProgress(progress);

                        progressDialog.setProgress(progress);
                        progress+= 4;
                        progressDialog.setMessage(MESSAGES[progress / 4 % 4]);
                        secondProgress += 5;

                    }else {
                        progress = 0;
                        secondProgress = 0;
                    }
                    sendEmptyMessageDelayed(1,1000);
                    break;

            }
        }
    }
}
