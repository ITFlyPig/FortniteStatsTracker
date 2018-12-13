package com.statstracker.forfornitegame.home;

import com.statstracker.forfornitegame.base.BasePresenter;
import com.statstracker.forfornitegame.base.BaseView;

public class MainContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 加载数据
         * @param platform
         * @param playerName
         */
        void loadPlayerData(String platform, String playerName);

    }
}
