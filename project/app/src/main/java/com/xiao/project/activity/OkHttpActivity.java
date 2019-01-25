package com.xiao.project.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiao.project.R;
import com.xiao.project.bean.DemoEntity;
import com.xiao.project.bean.User;
import com.xiao.project.http.JSZPOkgoHttpUtils;

public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_post;
    private TextView tv_text;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1001:
                    DemoEntity obj = (DemoEntity) msg.obj;
                    Log.d("OkHttpActivity", obj.getDemo().getDemoStringTwo());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        bt_post = findViewById(R.id.bt_post);
        tv_text = findViewById(R.id.tv_text);
    }

    private void initData() {

    }

    private void initListener() {
        bt_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_post:
//                BaseRequestEntity requestEntity = new BaseRequestEntity();
//                requestEntity.setUserId("xiaoyuan");
                User user = new User();
                user.setName("xiaoyuan");
                JSZPOkgoHttpUtils.postString("http://10.0.2.2:8080/users/user", this,
                        user, mHandler, 1001, DemoEntity.class);
//                OkGo.<String>get("http://10.0.2.2:8080/hello")
//                    .tag(this)
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onSuccess(Response<String> response) {
//                                Log.d(getClass().getSimpleName(),response.body());
//                            }
//                        });
                break;
        }
    }
}