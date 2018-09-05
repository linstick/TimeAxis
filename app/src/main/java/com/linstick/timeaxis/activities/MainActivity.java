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
import android.view.View;
import android.widget.Toast;

import com.linstick.timeaxis.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.layout_setting)
    DrawerLayout settingLayout;
    @BindView(R.id.side_bar)
    NavigationView sideBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
    }

    @OnClick({
            R.id.ll_add_bottle,
            R.id.ll_get_bottle,
            R.id.ll_add_record,
            R.id.ll_browse_record,
            R.id.ll_browse_memo,
            R.id.btn_read_article

    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_add_bottle:
                EditTextActivity.startAction(MainActivity.this, EditTextActivity.TYPE_ADD_BOTTLE);
                break;

            case R.id.ll_get_bottle:
                Toast.makeText(MainActivity.this, "获取漂流瓶", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ll_add_record:
                EditTextActivity.startAction(MainActivity.this, EditTextActivity.TYPE_ADD_RECORD);
                break;

            case R.id.ll_browse_record:
                startActivity(new Intent(MainActivity.this, LifeRecordActivity.class));
                break;

            case R.id.ll_browse_memo:
                startActivity(new Intent(MainActivity.this, MemoActivity.class));
                break;

            case R.id.btn_read_article:
                startActivity(new Intent(MainActivity.this, ReadArticleActivity.class));
                break;

        }
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
        settingLayout.closeDrawer(GravityCompat.END);
        return true;
    }
}
