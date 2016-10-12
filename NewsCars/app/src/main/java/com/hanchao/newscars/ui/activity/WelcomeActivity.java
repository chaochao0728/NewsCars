package com.hanchao.newscars.ui.activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.hanchao.newscars.R;
/**
 * Created by dllo on 16/8/13.
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView welIma;
    private TextView welTv;
    //用来控制点击时候不再跳转第二次的
    private boolean go = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        welIma = (ImageView) findViewById(R.id.welcome_activity_ima);
        welTv = (TextView) findViewById(R.id.welcome_activity_tv);
        welTv.setOnClickListener(this);
        //这个是有顺序的
        new TimeAnsybcTASK().execute(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_activity_tv:
                go = true;
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    public class TimeAnsybcTASK extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {
            int all = params[0];
            int time = 3;
            while (time > all) {
                publishProgress(time);
                time--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "马上跳转";
        }

        //实时显示 刷新异步任务给出的数据
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        //显示追后的结果
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            welTv.setText(s);
            if (go == false) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }

    }
}
