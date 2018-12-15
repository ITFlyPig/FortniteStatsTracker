package com.statstracker.forfornitegame.detail.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 排位的信息
 */
public class Paiwei {

    public float assists;
    public float bestRankPoint;
    public float boosts;
    public float dBNOs;
    public float dailyKills;
    public float dailyWins;
    public float damageDealt;
    public float days;
    public float headshotKills;
    public float heals;
    public float killPoints;
    public float kills;
    public float longestKill;
    public float longestTimeSurvived;
    public float losses;
    public float maxKillStreaks;
    public float mostSurvivalTime;
    public float rankPoints;
    public float revives;
    public float rideDistance;
    public float roadKills;
    public float roundMostKills;
    public float roundsPlayed;
    public float suicides;
    public float swimDistance;
    public float teamKills;
    public float timeSurvived;
    public float top10s;
    public float vehicleDestroys;
    public float walkDistance;
    public float weaponsAcquired;
    public float weeklyKills;
    public float weeklyWins;
    public float winPoints;
    public float wins;
    //Score(score) ,wins(wins) ,Win%(winLv) ,Top 10(top10s),Kills(kills),K/d(kd),Matches Played(matchesPlayed)
    public String score = "0";
    public String winLv = "0%";//就是Win%
    public String kd = "0";
    public String matchesPlayed = "0";
    public String winsStr = "0";
    public String top10sStr = "0";
    public String killsStr = "0";


    public static Paiwei parseForfornitegame(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        Paiwei paiwei = new Paiwei();
        JSONArray jsonArray = jsonObject.getJSONArray("lifeTimeStats");
        if (jsonArray == null) {
            return paiwei;
        }
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
           String key = obj.getString("key");
           String value = obj.getString("value");
           if (key.equals("Score")) {
               paiwei.score = value;
           } else if (key.equals("Wins")) {
               paiwei.winsStr = value;
           } else if (key.equals("Win%")) {
               paiwei.winLv = value;
           } else if (key.equals("Top 10")) {
               paiwei.top10sStr = value;
           } else if (key.equals("Kills")) {
               paiwei.killsStr = value;
           } else if (key.equals("K/d")) {
               paiwei.kd = value;
           } else if (key.equals("Matches Played")) {
               paiwei.matchesPlayed = value;
           }

        }
        return paiwei;
    }
}


