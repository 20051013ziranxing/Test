package com.example.test1;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.librouter.Router;



public class MyApplication extends Application {
    //ARouter调用开关
    private boolean isDebugARouter=true;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebugARouter) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
    /*@Override
    public void onCreate() {
        super.onCreate();
        Log.d("TestTest", "我进行了注册");
        Router.getInstance().register("/main/MainActivity", MainActivity.class);
        Router.getInstance().register("/loginUp/LoginUpActivity", LoginUpActivity.class);
        ARouter.init(this);
    }*/
}
