package com.linstick.timeaxis.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.linstick.timeaxis.R;
import com.linstick.timeaxis.adapters.FeatureAdapter;
import com.linstick.timeaxis.adapters.callback.OnFeatureItemClick;
import com.linstick.timeaxis.beans.Feature;
import com.linstick.timeaxis.views.CustomFeatureView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        OnFeatureItemClick {

    @BindView(R.id.layout_setting)
    DrawerLayout settingLayout;
    @BindView(R.id.side_bar)
    NavigationView sideBar;
    @BindView(R.id.custom_feature_view)
    CustomFeatureView customFeatureView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<Feature> mList;
    private FeatureAdapter featureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        }
        sideBar.setNavigationItemSelectedListener(this);

        mList = new ArrayList<>();
        mList.add(new Feature(R.mipmap.ic_launcher_round, "记录", Feature.TYPE_ADD_RECORD));
        mList.add(new Feature(R.mipmap.ic_launcher_round, "时间轴", Feature.TYPE_BROWSE_RECORD));
        mList.add(new Feature(R.mipmap.ic_launcher, "阅读", Feature.TYPE_READ_ARTICLE));
        mList.add(new Feature(R.mipmap.ic_launcher, "分享", Feature.TYPE_ADD_BOTTLE));
        mList.add(new Feature(R.mipmap.ic_launcher, "拾荒", Feature.TYPE_GET_BOTTLE));
        mList.add(new Feature(R.mipmap.ic_launcher_round, "计划", Feature.TYPE_BROWSE_MEMO));

        featureAdapter = new FeatureAdapter(mList);
        featureAdapter.setOnItemClickListener(this);
        customFeatureView.setAdapter(featureAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_bottle:
                EditTextActivity.startAction(MainActivity.this, EditTextActivity.TYPE_ADD_BOTTLE);
                break;

            case R.id.menu_add_memo:
                EditTextActivity.startAction(MainActivity.this, EditTextActivity.TYPE_ADD_MEMO);
                break;

            case R.id.menu_add_record:
                EditTextActivity.startAction(MainActivity.this, EditTextActivity.TYPE_ADD_RECORD);
                break;

            case R.id.menu_browse_bottle:
                startActivity(new Intent(MainActivity.this, DriftBottleActivity.class));
                break;

            case android.R.id.home:
                settingLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_reset_feature:
                customFeatureView.resetFeatureIconData();
                break;

            case R.id.menu_set_main_bg:
                Toast.makeText(MainActivity.this, " 更换应用背景", Toast.LENGTH_SHORT).show();

                break;
            case R.id.menu_clear_local_data:
                Toast.makeText(MainActivity.this, "清除本地数据", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_clear_remote_data:
                Toast.makeText(MainActivity.this, "清除云端数据", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_feedback:
                Toast.makeText(MainActivity.this, "用户反馈", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_give_prise:
                Toast.makeText(MainActivity.this, "给个好评", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_about_us:
                Toast.makeText(MainActivity.this, "关于我们", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_log_out:
                Toast.makeText(MainActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                break;
        }
        settingLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(int position) {
        int type = mList.get(position).getType();
        switch (type) {
            case Feature.TYPE_ADD_BOTTLE:
                EditTextActivity.startAction(MainActivity.this, EditTextActivity.TYPE_ADD_BOTTLE);
                break;

            case Feature.TYPE_BROWSE_MEMO:
                startActivity(new Intent(MainActivity.this, MemoActivity.class));

                break;

            case Feature.TYPE_GET_BOTTLE:
                Toast.makeText(MainActivity.this, "获取漂流瓶", Toast.LENGTH_SHORT).show();

                break;

            case Feature.TYPE_BROWSE_RECORD:
                startActivity(new Intent(MainActivity.this, LifeRecordActivity.class));
                break;

            case Feature.TYPE_READ_ARTICLE:
                startActivity(new Intent(MainActivity.this, ReadArticleActivity.class));
                break;

            case Feature.TYPE_ADD_RECORD:
                EditTextActivity.startAction(MainActivity.this, EditTextActivity.TYPE_ADD_RECORD);
                break;

        }
    }
}
