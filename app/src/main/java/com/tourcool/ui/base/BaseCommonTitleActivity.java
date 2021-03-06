package com.tourcool.ui.base;


import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.aries.ui.util.StatusBarUtil;
import com.frame.library.core.log.TourCooLogUtil;
import com.frame.library.core.module.activity.FrameTitleActivity;
import com.frame.library.core.util.FrameUtil;
import com.frame.library.core.util.SizeUtil;
import com.frame.library.core.widget.titlebar.TitleBarView;
import com.tourcool.smartcity.R;

import static com.tourcool.core.config.AppConfig.TITLE_MAIN_TITLE_SIZE;


/**
 * @author :JenkinsZhou
 * @description :普通蓝色渐变背景标题栏
 * @company :途酷科技
 * @date 2019年09月25日15:02
 * @Email: 971613168@qq.com
 */
public abstract class BaseCommonTitleActivity extends FrameTitleActivity {

    @Override
    public void setTitleBar(TitleBarView titleBar) {
        if (titleBar != null) {
            setMarginTop(titleBar);
            titleBar.setBgResource(R.drawable.bg_gradient_title_common);
            titleBar.setLeftTextDrawable(R.drawable.ic_back_white);
            titleBar.setTitleMainTextColor(FrameUtil.getColor(R.color.whiteCommon));
            titleBar.getMainTitleTextView().setTextSize(TITLE_MAIN_TITLE_SIZE);
        }
    }


    protected void setMarginTop(TitleBarView titleBarView) {
        if (titleBarView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = titleBarView.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) layoutParams).setMargins(0, getMaginTop(), 0, 0);
        } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) layoutParams).setMargins(0, getMaginTop(), 0, 0);
        } else if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).setMargins(0, getMaginTop(), 0, 0);
        }
    }


    protected  void  skipActivity(String activityUrl){
        ARouter.getInstance().build(activityUrl).navigation();
    }
}
