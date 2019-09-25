package com.tourcool.ui.mvp.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.frame.library.core.manager.RxJavaManager;
import com.frame.library.core.util.FrameUtil;
import com.frame.library.core.util.StringUtil;
import com.frame.library.core.util.ToastUtil;
import com.frame.library.core.widget.titlebar.TitleBarView;
import com.tourcool.core.module.mvp.BaseMvpTitleActivity;
import com.tourcool.core.module.mvp.BasePresenter;
import com.tourcool.library.frame.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.tourcool.core.constant.TimeConstant.COUNT;
import static com.tourcool.core.constant.TimeConstant.ONE_SECOND;

/**
 * @author :JenkinsZhou
 * @description :
 * @company :途酷科技
 * @date 2019年09月24日17:42
 * @Email: 971613168@qq.com
 */
public class LoginActivity extends BaseMvpTitleActivity implements View.OnClickListener {
    private TextView tvGetCode;
    private TextView tvChangeLoginType;
    private boolean loginByPass = true;
    private LinearLayout llLoginVcode;
    private LinearLayout llLoginPass;
    private Handler mHandler = new Handler();
    private List<Disposable> disposableList = new ArrayList<>();
    private int timeCount = COUNT;

    @Override
    protected void loadPresenter() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        findViewById(R.id.tvLogin).setOnClickListener(this);
        tvGetCode = findViewById(R.id.tvGetCode);
        tvGetCode.setOnClickListener(this);
        tvChangeLoginType = findViewById(R.id.tvChangeLoginType);
        tvChangeLoginType.setOnClickListener(this);
        llLoginPass = findViewById(R.id.llLoginPass);
        llLoginVcode = findViewById(R.id.llLoginVcode);
        findViewById(R.id.tvSkipRegister).setOnClickListener(this);
        ImageView ivClearPhone = findViewById(R.id.ivClearPhone);
        ImageView ivClearPass = findViewById(R.id.ivClearPass);
        ImageView ivPhoneValid = findViewById(R.id.ivPhoneValid);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText etPhone = findViewById(R.id.etPhone);
        listenInput(etPhone, ivClearPhone);
        listenInput(etPassword, ivClearPass);
        listenInputPhoneValid(etPhone,ivPhoneValid);
        changeLoginType();
    }

    @Override
    public void setTitleBar(TitleBarView titleBar) {
        titleBar.setTitleMainText("登录");
    }


    private void listenInput(EditText editText, ImageView imageView) {
        setViewGone(imageView, !TextUtils.isEmpty(editText.getText().toString()));
        imageView.setOnClickListener(v -> editText.setText(""));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setViewGone(imageView, s.length() != 0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvChangeLoginType:
                changeLoginType();
                break;
            case R.id.tvGetCode:
                //验证码发送成功开始，倒计时
                showLoading("发送中...");
                mHandler.postDelayed(() -> {
                    closeLoading();
                    ToastUtil.showSuccess("发送成功");
                    countDownTime();
                }, 1000);

                break;
            case R.id.tvSkipRegister:
                Intent intent = new Intent();
                intent.setClass(mContext, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tvLogin:
                Intent intent1 = new Intent();
                intent1.setClass(mContext,IdentifyLevel1Activity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }


    private void changeLoginType() {
        if (loginByPass) {
            setViewGone(llLoginPass, true);
            setViewGone(llLoginVcode, false);
            loginByPass = false;
            tvChangeLoginType.setText("验证码登录");
        } else {
            setViewGone(llLoginPass, false);
            setViewGone(llLoginVcode, true);
            loginByPass = true;
            tvChangeLoginType.setText("密码登录");
        }
    }


    /**
     * 倒计时
     */
    private void countDownTime() {
        reset();
        setClickEnable(false);
        mHandler.postDelayed(() -> tvGetCode.setTextColor(FrameUtil.getColor(R.color.grayA2A2A2)), ONE_SECOND);
        RxJavaManager.getInstance().doEventByInterval(ONE_SECOND, new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposableList.add(d);
            }


            @Override
            public void onNext(Long aLong) {
                --timeCount;
                setText("还有" + timeCount + "秒");
                if (aLong >= COUNT - 1) {
                    onComplete();
                }
            }

            @Override
            public void onError(Throwable e) {
                cancelTime();
            }

            @Override
            public void onComplete() {
                reset();
                cancelTime();
            }
        });
    }


    private void reset() {
        setClickEnable(true);
        timeCount = COUNT;
        setText("发送验证码");
        tvGetCode.setTextColor(FrameUtil.getColor(R.color.blue55A9FF));
    }


    private void setClickEnable(boolean clickEnable) {
        tvGetCode.setEnabled(clickEnable);
    }

    private void setText(String text) {
        tvGetCode.setText(text);
    }


    private void cancelTime() {
        if (disposableList != null && !disposableList.isEmpty()) {
            Disposable disposable;
            for (int i = 0; i < disposableList.size(); i++) {
                disposable = disposableList.get(i);
                if (disposable != null && !disposable.isDisposed()) {
                    disposable.dispose();
                    disposableList.remove(disposable);
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        cancelTime();
        super.onDestroy();
    }


    private void listenInputPhoneValid(EditText editText, ImageView imageView) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setViewGone(imageView, StringUtil.isPhoneNumber(s.toString()));
            }
        });
    }

}