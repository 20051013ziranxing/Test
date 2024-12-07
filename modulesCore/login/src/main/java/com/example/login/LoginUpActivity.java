package com.example.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.librouter.Router;
import com.example.messageEvent.MessageEvent1;

import org.greenrobot.eventbus.EventBus;

public class LoginUpActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        button = findViewById(R.id.button1_sendMessage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageEvent1 messageEvent1 = new MessageEvent1("你接收到了来自登录界面通过EventBus所传递的消息");
                EventBus.getDefault().post(messageEvent1);
               /* finish();*/
            }
        });
        textView = findViewById(R.id.TextView2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Log.d("TestTest", "我进入了返回的点击事件");
                Router.getInstance().startActivity(LoginUpActivity.this, "/main/MainActivity");*/
                ARouter.getInstance().build("/main/MainActivity")
                                .withString("key1","我是传过来的字符串")
                        .withBoolean("key2", false)
                        .withSerializable("key3", new user("海绵宝宝", 5))
                        .navigation();
            }
        });
    }
}