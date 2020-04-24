package com.example.mymultiplebackstack;

import android.app.Application;

import java.util.Stack;

public class MyApplication extends Application {

    public enum STACKS {
        HOME,
        DASHBOARD,
        NOTIFICATIONS,
    }

    public static Stack<Integer> homeStack = new Stack<>();
    public static Stack<Integer> dashboardStack = new Stack<>();
    public static Stack<Integer> notificationsStack = new Stack<>();
    public static Stack<Integer> rootStack = new Stack<>();

    public static STACKS activeStack = STACKS.HOME;

    @Override
    public void onCreate() {
        super.onCreate();

        rootStack.push(R.id.item_navigation_home);
        homeStack.push(R.id.navigation_home);
        dashboardStack.push(R.id.navigation_dashboard);
        notificationsStack.push(R.id.navigation_notifications);

    }

    public static void addToBackStack(int destinationId){
        if (MyApplication.activeStack == MyApplication.STACKS.HOME){
            MyApplication.homeStack.push(destinationId);
        }

        else if (MyApplication.activeStack == MyApplication.STACKS.DASHBOARD){
            MyApplication.dashboardStack.push(destinationId);
        }

        else {
            MyApplication.notificationsStack.push(destinationId);
        }
    }
}
