package com.igameguide.pubg.detail;

import com.igameguide.pubg.base.BasePresenter;
import com.igameguide.pubg.base.BaseView;
import com.igameguide.pubg.detail.bean.SeasonDetail;

public class DetailContract {

    interface View extends BaseView<DetailContract.Presenter> {

        void showLoading();

        void dismissLoading();

        void onLoadSucess(SeasonDetail seasonDetail);

        void onLoadFail();

    }

    interface Presenter extends BasePresenter<DetailContract.View> {
        /**
         * 查询战绩
         *
         * @param region     服务器
         * @param playerName 角色ID
         * @param type       类型（单排、双排等）
         */
        void loadPlayerData(String region, String playerName, String type);


        /**
         * 加载堡垒之夜的玩家对战信息
         *
         * @param playerName
         * @param platform
         */
        void loadFortnitetrackerPlayerInfo(String playerName, String platform);

    }
}
