package com.statstracker.forfornitegame.base;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.google.android.gms.ads.MobileAds;
import com.lzy.okgo.OkGo;
import com.statstracker.forfornitegame.R;
import com.umeng.analytics.MobclickAgent;

public class PubgApplication extends MultiDexApplication {

    private static PubgApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initNetwork();

        //友盟
        MobclickAgent.setDebugMode(true);
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, getString(R.string.umeng_key), "release", MobclickAgent.EScenarioType.E_UM_NORMAL, true));

        //谷歌广告
        MobileAds.initialize(this);



    }


    /**
     * 初始化网络框架
     */
    private void initNetwork(){
        OkGo.getInstance().init(this);
    }


    public static PubgApplication getInstance() {
        return mInstance;
    }


}
