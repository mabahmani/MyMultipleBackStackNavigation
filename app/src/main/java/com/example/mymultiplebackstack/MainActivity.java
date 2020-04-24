package com.example.mymultiplebackstack;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemReselectedListener {
    BottomNavigationView navView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(this);
        navView.setOnNavigationItemReselectedListener(this);

        Navigation.findNavController(this, R.id.nav_host_fragment).addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                Log.d(TAG, MyApplication.activeStack + " ");
            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");

        if (MyApplication.activeStack == MyApplication.STACKS.HOME) {
            if (MyApplication.homeStack.size() > 1) {
                MyApplication.homeStack.pop();
                navigateFragment(MyApplication.homeStack);
            }
            else {
                performRootBack();
            }
        }
        else if (MyApplication.activeStack == MyApplication.STACKS.DASHBOARD) {
            if (MyApplication.dashboardStack.size() > 1) {
                MyApplication.dashboardStack.pop();
                navigateFragment(MyApplication.dashboardStack);

            }
            else {
                performRootBack();
            }
        }
        else {
            if (MyApplication.notificationsStack.size() > 1) {
                MyApplication.notificationsStack.pop();
                navigateFragment(MyApplication.notificationsStack);
            }
            else {
                performRootBack();
            }
        }

        Log.d(TAG, MyApplication.rootStack.toString());
    }

    private void routeToDestinationHome(int itemId) {
        MyApplication.activeStack = MyApplication.STACKS.HOME;
        pushToRootStack(itemId);
        navigateFragment(MyApplication.homeStack);
        Log.d(TAG, MyApplication.rootStack.toString());
    }

    private void routeToDestinationDashboard(int itemId) {
        MyApplication.activeStack = MyApplication.STACKS.DASHBOARD;
        pushToRootStack(itemId);
        navigateFragment(MyApplication.dashboardStack);
        Log.d(TAG, MyApplication.rootStack.toString());
    }

    private void routeToDestinationNotifications(int itemId) {

        MyApplication.activeStack = MyApplication.STACKS.NOTIFICATIONS;
        pushToRootStack(itemId);
        navigateFragment(MyApplication.notificationsStack);
        Log.d(TAG, MyApplication.rootStack.toString());
    }

    private void navigateFragment(Stack<Integer> stack){
        if (!Navigation.findNavController(this, R.id.nav_host_fragment).popBackStack(stack.peek(), false)) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(stack.peek());
        }
    }

    private void performRootBack() {

        if (MyApplication.rootStack.size() > 1) {
            MyApplication.rootStack.pop();
            navView.setSelectedItemId(MyApplication.rootStack.peek());
        } else {
//            MyApplication.rootStack.pop();
//            MyApplication.rootStack.push(R.id.item_navigation_home);
//            navView.setSelectedItemId(R.id.item_navigation_home);
            finish();
        }
    }

    private void pushToRootStack(int itemId) {
        int elementIndex = MyApplication.rootStack.indexOf(itemId);
        Log.d(TAG, elementIndex + "");
        if (elementIndex == -1) {
            MyApplication.rootStack.push(itemId);
        } else {
            MyApplication.rootStack.removeElementAt(elementIndex);
            MyApplication.rootStack.push(itemId);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected");
        Log.d(TAG, MyApplication.rootStack.toString());

        switch (item.getItemId()) {
            case R.id.item_navigation_home:
                routeToDestinationHome(item.getItemId());
                break;
            case R.id.item_navigation_dashboard:
                routeToDestinationDashboard(item.getItemId());
                break;
            case R.id.item_navigation_notifications:
                routeToDestinationNotifications(item.getItemId());
                break;
        }
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        Log.d(TAG, "onNavigationItemReselected");

    }
}
