package com.statstracker.forfornitegame.detail;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.statstracker.forfornitegame.detail.bean.Paiwei;
import com.statstracker.forfornitegame.detail.bean.PlayerInfo;
import com.statstracker.forfornitegame.detail.bean.SeasonDetail;
import com.statstracker.forfornitegame.detail.bean.SeasonsBean;
import com.statstracker.forfornitegame.util.ConstantValue;
import com.statstracker.forfornitegame.util.ServerUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class DetailModle {
    /**
     * 请求玩家的信息
     *
     * @param view
     * @param playerName
     */
    public void getPlayerInfo(final DetailContract.View view, String playerName, final String region) {
        String url = ServerUrls.getQueryPlayerInfoUrl(playerName, region);
        if (TextUtils.isEmpty(url)) {
            if (view != null) {
                view.onLoadFail();
            }
            return;

        }

        if (view != null) {
            view.onLoadStart();
        }
        OkGo.<String>get(url)
                .headers("Accept", "application/json")
                .headers("Content-Type", "application/json")
                .headers("Authorization", "Bearer " + ConstantValue.Key.URL_KEY)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String respStr = response.body();
                            if (!TextUtils.isEmpty(respStr)) {
                                PlayerInfo playerInfo = null;
                                try {
                                    playerInfo = JSON.parseObject(respStr, PlayerInfo.class);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                if (playerInfo != null && playerInfo.data != null && playerInfo.data.size() > 0) {
                                    PlayerInfo.Player data = playerInfo.data.get(0);
                                    getSeasons(view, region, data.id);
                                    return;
                                }

                            }

                        }
                        if (view != null)
                            view.onLoadFail();

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (view != null)
                            view.onLoadFail();
                    }
                });
    }


    /**
     * 获取赛季信息
     *
     * @param view
     */
    public void getSeasons(final DetailContract.View view, final String region, final String playerId) {
        String url = ServerUrls.getSeasonUrl(region);
        if (TextUtils.isEmpty(url)) {
            if (view != null) {
                view.onLoadFail();
            }
            return;

        }
        OkGo.<String>get(url)
                .headers("Accept", "application/json")
                .headers("Content-Type", "application/json")
                .headers("Authorization", "Bearer " + ConstantValue.Key.URL_KEY)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String respStr = response.body();
                            if (!TextUtils.isEmpty(respStr)) {
                                SeasonsBean seasonsBean = JSON.parseObject(respStr, SeasonsBean.class);

                                String seasonId = null;
                                if (seasonsBean.data != null) {
                                    for (SeasonsBean.Data dt : seasonsBean.data) {
                                        if (dt.attributes != null && dt.attributes.isCurrentSeason) {
                                            seasonId = dt.id;
                                            break;
                                        }

                                    }

                                }

                                if (TextUtils.isEmpty(seasonId)) {
                                    if (view != null) {
                                        view.onLoadFail();
                                    }
                                    return;

                                }


                                if (seasonsBean != null) {
                                    getDetailInfo(view, playerId, seasonId, region);
                                    return;

                                }

                            }

                        }
                        if (view != null) {
                            view.onLoadFail();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (view != null)
                            view.onLoadFail();
                    }
                });

    }

    /***
     * 获取具体的赛季的信息
     * @param view
     * @param playerId
     * @param seasonId
     * @param region
     */
    public void getDetailInfo(final DetailContract.View view, String playerId, String seasonId, final String region) {
        if (TextUtils.isEmpty(playerId) || TextUtils.isEmpty(seasonId) || TextUtils.isEmpty(region)) {
            if (view != null) {
                view.onLoadFail();
            }
            return;
        }
        String url = ServerUrls.getDetailInfo(playerId, seasonId, region);
        if (TextUtils.isEmpty(url)) {
            if (view != null) {
                view.onLoadFail();
            }
            return;
        }
        OkGo.<String>get(url)
                .headers("Accept", "application/json")
                .headers("Content-Type", "application/json")
                .headers("Authorization", "Bearer " + ConstantValue.Key.URL_KEY)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String resp = response.body();
                            SeasonDetail seasonDetail = SeasonDetail.parse(resp);
                            if (seasonDetail != null) {
                                if (view != null) {
//                                    view.onLoadSucess(seasonDetail);
                                    return;
                                }


                            }

                        }

                        if (view != null) {
                            view.onLoadFail();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (view != null) {
                            view.onLoadFail();
                        }
                    }
                });
    }


    /**
     * 加载堡垒之夜玩家的对战信息
     *
     * @param view
     * @param playerName
     * @param platform
     */
    public void loadFortnitetrackerPlayerInfo(final DetailContract.View view, String playerName, String platform) {
        if (TextUtils.isEmpty(platform) || TextUtils.isEmpty(platform)) {
            return;
        }

        String url = ServerUrls.getFortnitetrackerUrl(playerName, platform);
        if (TextUtils.isEmpty(url)) {
            return;
        }

        if (view != null) {
            view.showLoading();
        }
        OkGo.<String>get(url)
                .headers("TRN-Api-Key", "2bb4ef9d-c8f3-4274-be88-f1db053e0d7d")
                .headers("Accept", "application/json")
                .headers("Content-Type", "application/json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response != null) {
                            String resp = response.body();
                            Log.d("wyl", "返回的数据：" + resp);
//                            SeasonDetail seasonDetail = SeasonDetail.parse(resp);
//                            if (seasonDetail != null) {
//                                if (view != null) {
//                                    view.onLoadSucess(seasonDetail);
//                                    return;
//                                }
//                            }
                            if (TextUtils.isEmpty(resp)) {
                                if (view != null) {
                                    view.onLoadFail();
                                }
                                return;
                            }

                            JSONObject jsonObject = JSONObject.parseObject(resp);
                            Paiwei paiwei = Paiwei.parseForfornitegame(jsonObject);
                            if (paiwei == null) {
                                if (view != null) {
                                    view.onLoadFail();
                                }
                                return;
                            }

                            view.onLoadSucess(paiwei);
                            return;

                        }

                        if (view != null) {
                            view.onLoadFail();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        if (view != null) {
                            view.onLoadFail();
                        }
                    }
                });


    }


}
