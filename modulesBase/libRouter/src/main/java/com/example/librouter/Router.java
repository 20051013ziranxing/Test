package com.example.librouter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Router {
    static final Map<String, Map<String, Class<?>>> groupMap = new HashMap<>();
    final Map<String, Class<?>> routeMap = new HashMap<>();
    public Router() {
    }
    public final static class Holder {
        static Router INSTANCE = new Router();
    }
    public static Router getInstance() {
        return Holder.INSTANCE;
    }
    //进行注册操作
    public void register(String path, Class<?> clz) {
        //我们所要存储的就是不同的Activity与相应的路径与包名，包名是包括在路径之下的，因此所要传进的就是一个字符串，一个class，我们要将路径下的包名解析出来所以使用'/'，在进行注册的时候：
        //Router.getInstance().register("/main/MainActivity", MainActivity.class);
        String[] strArray = path.split("/");
        if (strArray.length > 2) {
            String groupName = strArray[1];
            Map<String, Class<?>> routeMap = null;
            if (groupMap.containsKey(groupName)) {
                routeMap = groupMap.get(groupName);
            } else {
                routeMap = new HashMap<>();
                groupMap.put(groupName, routeMap);
            }
            routeMap.put(groupName, clz);
        }
    }
    //进行开启活动操作
    public void startActivity(Activity activity, String path) {
        String[] strArray = path.split("/");
        if (strArray.length > 2) {
            String groupName = strArray[1];
            String routeName = path;
            Map<String, Class<?>> group = null;
            if (groupMap.containsKey(groupName)) {
                group = groupMap.get(groupName);
            }
            if (group != null && group.containsKey(routeName)) {
                Class<?> clz = group.get(routeName);
                activity.startActivity(new Intent(activity, clz));
            }
        }
    }
}
