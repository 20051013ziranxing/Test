package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.login.LoginUpActivity;
import com.example.login.user;
import com.example.messageEvent.MessageEvent1;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

@Route(path = "/main/MainActivity")
public class MainActivity extends AppCompatActivity {
    @Autowired(name = "key1")
    public String name;
    @Autowired(name = "key3")
    public user user;
    Button button;
    TextView textView2_receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView textView = findViewById(R.id.textView1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginUpActivity.class);
                startActivity(intent);
            }
        });
        ARouter.getInstance().inject(this);
        Log.d("TestTest", name + "");
        if (user != null) {
            Log.d("TestTest", user.getName() + user.getAge());
        }
        button = findViewById(R.id.button_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册事件
                EventBus.getDefault().register(MainActivity.this);
            }
        });
        textView2_receive = findViewById(R.id.textView2_receive);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent1 messageEvent1) {
        textView2_receive.setText(messageEvent1.getMessage());
    }
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onMoonStickyEvent(MessageEvent1 messageEvent1) {
        textView2_receive.setText(messageEvent1.getMessage());
        EventBus.getDefault().removeAllStickyEvents();
    }
}