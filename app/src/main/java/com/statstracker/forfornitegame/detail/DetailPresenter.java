package com.statstracker.forfornitegame.detail;

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View mView;
    private DetailModle mModle;


    @Override
    public void loadPlayerData(String region, String playerName, String type) {

        mModle.getPlayerInfo(mView, playerName, region);

    }

    @Override
    public void loadFortnitetrackerPlayerInfo(String playerName, String platform) {
        mModle.loadFortnitetrackerPlayerInfo(mView, playerName, platform);
    }

    @Override
    public void start() {

    }

    public DetailPresenter(DetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mModle = new DetailModle();

    }




}
