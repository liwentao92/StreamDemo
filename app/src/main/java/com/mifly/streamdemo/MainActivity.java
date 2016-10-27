package com.mifly.streamdemo;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mifly.streamdemo.fragment.FavoritesFragment;
import com.mifly.streamdemo.fragment.FriendsFragment;
import com.mifly.streamdemo.fragment.NearbyFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FavoritesFragment mFavoritesFragment;
    private NearbyFragment mNearbyFragment;
    private FriendsFragment mFriendsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        setTabSelection(0);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_favorites) {
                    // The tab with id R.id.tab_favorites was selected,
                    // change your content accordingly.
                    setTabSelection(0);
                }else if (tabId == R.id.tab_friends){
                    setTabSelection(2);
                }else {
                    setTabSelection(1);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /*
   * 根据传入的index，来设置开启的tab页面
   * @param index
   */
    public void setTabSelection(int index) {
        // TODO Auto-generated method stub
        //开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //隐藏所有的fragment，防止有多个界面显示在界面上
        hideFragments(transaction);
        switch (index) {
            case 0:
                Fragment fragment = fragmentManager.findFragmentByTag("zero");
                if (fragment == null) {
                    mFavoritesFragment = new FavoritesFragment();
                    transaction.add(R.id.frame, mFavoritesFragment, "zero");
                } else {
                    transaction.show(mFavoritesFragment);
                }
                break;
            case 1:
                Fragment fragment1 = fragmentManager.findFragmentByTag("one");
                if (fragment1 == null) {
                    mNearbyFragment = new NearbyFragment();
                    transaction.add(R.id.frame, mNearbyFragment, "one");
                } else {
                    transaction.show(mNearbyFragment);
                }
                break;
            case 2:
                Fragment fragment2 = fragmentManager.findFragmentByTag("two");
                if (fragment2 == null) {
                    mFriendsFragment = new FriendsFragment();
                    transaction.add(R.id.frame, mFriendsFragment, "two");
                } else {
                    transaction.show(mFriendsFragment);
                }
                break;

        }
        transaction.commitAllowingStateLoss();
    }

    /*
  * 隐藏所有的fragment
  * @param transaction
  *     用于对fragment进行操作的事务
  */
    private void hideFragments(FragmentTransaction transaction) {
        // TODO Auto-generated method stub
        if (mFavoritesFragment != null) {
            transaction.hide(mFavoritesFragment);
        }

        if (mNearbyFragment != null) {
            transaction.hide(mNearbyFragment);
        }

        if (mFriendsFragment != null){
            transaction.hide(mFriendsFragment);
        }

    }

}
