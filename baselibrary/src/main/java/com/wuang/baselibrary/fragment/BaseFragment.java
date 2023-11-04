package com.wuang.baselibrary.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.wuang.baselibrary.utils.ToastUtil;

public class BaseFragment extends Fragment {
    protected Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = requireContext();

    }
    protected void showMsg(@StringRes int strRes) {
        ToastUtil.getInstance().show(getResources().getString(strRes));
    }

}
