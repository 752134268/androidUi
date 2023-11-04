package com.wuang.baselibrary.acticity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.wuang.baselibrary.utils.ToastUtil;

public class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mContext = BaseActivity.this;
    }

    protected void showMsg(@StringRes int strRes) {
        ToastUtil.getInstance().show(getResources().getString(strRes));
    }
}
