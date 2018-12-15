package com.statstracker.forfornitegame.detail;

import com.statstracker.forfornitegame.base.BasePresenter;
import com.statstracker.forfornitegame.base.BaseView;
import com.statstracker.forfornitegame.detail.bean.Paiwei;
import com.statstracker.forfornitegame.detail.bean.SeasonDetail;

public class DetailContract {

    interface View extends BaseView<DetailContract.Presenter> {

        void showLoading();

        void dismissLoading();

        void onLoadSucess(Paiwei paiwei);

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
