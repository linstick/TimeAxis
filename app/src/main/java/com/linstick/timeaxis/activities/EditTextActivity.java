package com.linstick.timeaxis.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linstick.timeaxis.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/5/005.
 */

public class EditTextActivity extends AppCompatActivity {

    public static final String TYPE = "activity_type";
    public static final int TYPE_ADD_RECORD = 1;
    public static final int TYPE_ADD_BOTTLE = 2;
    public static final int TYPE_ADD_MEMO = 3;

    @BindView(R.id.ll_record_tip)
    LinearLayout recordTipLayout;
    @BindView(R.id.ll_other_tip)
    LinearLayout otherTipLayout;
    @BindView(R.id.iv_send)
    ImageView sendBtn;
    @BindView(R.id.tv_address)
    TextView addressTv;
    @BindView(R.id.tv_category)
    TextView categoryTv;
    @BindView(R.id.et_input)
    EditText inputEt;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int activityType;

    public static void startAction(Activity source, int type) {
        Intent intent = new Intent(source, EditTextActivity.class);
        intent.putExtra(TYPE, type);
        source.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initLayout();

    }

    private void initLayout() {
        activityType = getIntent().getIntExtra(TYPE, TYPE_ADD_RECORD);
        switch (activityType) {
            case TYPE_ADD_RECORD:
                recordTipLayout.setVisibility(View.VISIBLE);
                otherTipLayout.setVisibility(View.GONE);
                inputEt.setHint("记录生活中的点点滴滴...");
                setTitle("添加记录");
                break;

            case TYPE_ADD_BOTTLE:
                recordTipLayout.setVisibility(View.GONE);
                otherTipLayout.setVisibility(View.VISIBLE);
                inputEt.setHint("写下你的心事，漂向远方的TA...");
                setTitle("添加漂流瓶");
                break;

            case TYPE_ADD_MEMO:
                recordTipLayout.setVisibility(View.GONE);
                otherTipLayout.setVisibility(View.VISIBLE);
                inputEt.setHint("待处理事件...");
                setTitle("添加便笺");
                break;
        }

    }

    @OnClick({
            R.id.ll_record_tip,
            R.id.tv_label,
            R.id.iv_send
    })
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_record_tip:
                Toast.makeText(EditTextActivity.this, "定位", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_label:
                Toast.makeText(EditTextActivity.this, "输入标签", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_send:
                Toast.makeText(EditTextActivity.this, "发送", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
