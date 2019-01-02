package com.statstracker.forfornitegame.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.statstracker.forfornitegame.R;
import com.statstracker.forfornitegame.detail.bean.Paiwei;
import com.statstracker.forfornitegame.util.ConstantValue;
import com.statstracker.forfornitegame.util.ToastUtil;
import com.statstracker.forfornitegame.util.defaulthelper.CommonActivityViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;

public class DetailActivity extends AppCompatActivity implements DetailContract.View, View.OnClickListener {


    @BindView(R.id.iv_left_icon)
    ImageView ivLeftIcon;
    @BindView(R.id.titletext)
    TextView titletext;
    @BindView(R.id.titlebar)
    RelativeLayout titlebar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_region_middle_title)
    TextView tvRegionMiddleTitle;
    @BindView(R.id.iv_region_right)
    ImageView ivRegionRight;
    @BindView(R.id.rl_region)
    RelativeLayout rlRegion;
    @BindView(R.id.tv_mode_middle_title)
    TextView tvModeMiddleTitle;
    @BindView(R.id.iv_mode_right)
    ImageView ivModeRight;
    @BindView(R.id.rl_mode)
    RelativeLayout rlMode;
    @BindView(R.id.score)
    TextView score;
    @BindView(R.id.wins)
    TextView wins;
    @BindView(R.id.win_lv)
    TextView winLv;
    @BindView(R.id.top10)
    TextView top10;
    @BindView(R.id.kills)
    TextView kills;
    @BindView(R.id.kd)
    TextView kd;
    @BindView(R.id.matchesPlayed)
    TextView matchesPlayed;
    @BindView(R.id.rl_done)
    RelativeLayout rlDone;
    @BindView(R.id.picker)
    NumberPickerView picker;
    @BindView(R.id.ll_pick)
    LinearLayout llPick;
    @BindView(R.id.rl_default)
    RelativeLayout rlDefault;
    @BindView(R.id.Top3s)
    TextView Top3s;
    @BindView(R.id.Top5s)
    TextView Top5s;
    @BindView(R.id.Top6s)
    TextView Top6s;
    @BindView(R.id.Top12s)
    TextView Top12s;
    @BindView(R.id.Top25s)
    TextView Top25s;
    private DetailContract.Presenter mPresenter;
    private CommonActivityViewHelper mCommonHelper;
    private String mRegion;
    private String mPlayerName;
    private String mMode;
    private String mPlatform;
    private String[] mRegionArray = new String[]{"pc-as", "pc-eu", "pc-jp", "pc-kakao", "pc-krjp", "pc-na", "pc-oc"
            , "pc-ru", "pc-sa", "pc-sea", "pc-tournament"};
    private String[] mRegionNamesArray = new String[]{"Asia", "Europe", "Japan", "Kakao", "Korea", "North America", "Oceania"
            , "Russia", "South and Central America", "South East Asia", "Tournaments"};
    private String[] mModeArray = new String[]{"solo", "squad", "duo"};
    private String[] mModeNameArray = new String[]{"单排", "双排", "多排"};
    private String[] mPlatformArray = new String[]{"pc", "xbl", "psn"};

    private int mSelectRegionIndex = -1;
    private int mSelectModeIndex = -1;
    private int mSelectPlatformIndex = -1;
    private boolean isRegion = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);


        mPlayerName = getIntent().getStringExtra(ConstantValue.IntentKey.PLAYER_NAME);

        mCommonHelper = new CommonActivityViewHelper(rlDefault);


        mPresenter = new DetailPresenter(this);
        rlRegion.setOnClickListener(this);
        rlMode.setOnClickListener(this);
        rlDone.setOnClickListener(this);
        llPick.setOnClickListener(this);
        ivLeftIcon.setOnClickListener(this);
        titletext.setOnClickListener(this);
    }


    /**
     * 加载数据
     */
    private void load() {
//        mRegion = mRegionArray[0];
//        mPlayerName = "White-Mickey";
//        mPlayerName = "xl_0";
        mPresenter.loadFortnitetrackerPlayerInfo(mPlayerName, mPlatform);
    }

    @Override
    public void showLoading() {
        mCommonHelper.showLoading();

    }

    @Override
    public void dismissLoading() {
        mCommonHelper.hiddenLoading();

    }

    @Override
    public void onLoadSucess(Paiwei paiwei) {
        dismissLoading();
        ToastUtil.showToas("网络请求成功");
        updateUi(paiwei);


    }


    private void updateUi(Paiwei paiwei) {
        if (paiwei != null) {
            score.setText(paiwei.score);
            wins.setText(paiwei.winsStr);
            winLv.setText(paiwei.winLv);
            top10.setText(paiwei.top10sStr);
            Top3s.setText(paiwei.top3s);
            Top5s.setText(paiwei.top5s);
            Top6s.setText(paiwei.top6s);
            Top12s.setText(paiwei.top12s);
            Top25s.setText(paiwei.top25s);
            kills.setText(paiwei.killsStr);
            kd.setText(paiwei.kd);
            matchesPlayed.setText(paiwei.matchesPlayed);
        } else {
            score.setText("0");
            wins.setText("0");
            winLv.setText("0%");
            top10.setText("0");
            Top3s.setText("0");
            Top5s.setText("0");
            Top6s.setText("0");
            Top12s.setText("0");
            Top25s.setText("0");
            kills.setText("0");
            kd.setText("0");
            matchesPlayed.setText("0");
        }


    }


    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onLoadStart() {
        showLoading();

    }

    @Override
    public void onLoadFail() {

        Thread.dumpStack();
        dismissLoading();
        ToastUtil.showToas("网络请求错误");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_region:
                isRegion = true;
                llPick.setVisibility(View.VISIBLE);
                picker.refreshByNewDisplayedValues(mPlatformArray);
                break;
            case R.id.rl_mode:
                isRegion = false;
                llPick.setVisibility(View.VISIBLE);
                picker.refreshByNewDisplayedValues(mModeNameArray);
                break;
            case R.id.rl_done:
                llPick.setVisibility(View.INVISIBLE);
                mSelectPlatformIndex = picker.getPickedIndexRelativeToRaw();
//                if (isRegion) {
//                    mSelectRegionIndex = picker.getPickedIndexRelativeToRaw();
//                    if (mSelectRegionIndex >= 0 && mSelectRegionIndex < mRegionArray.length) {
//                        mRegion = mRegionArray[mSelectRegionIndex];
//                    }
//
//                } else {
//                    mSelectModeIndex = picker.getPickedIndexRelativeToRaw();
//                    if (mSelectModeIndex >= 0 && mSelectModeIndex < mModeArray.length) {
//                        mMode = mModeArray[mSelectModeIndex];
//                    }
//
//                }

                if (mSelectPlatformIndex >= 0 && mSelectPlatformIndex < mPlatformArray.length) {
                    mPlatform = mPlatformArray[mSelectPlatformIndex];
                    tvRegionMiddleTitle.setText(mPlatform);
                    load();
                }

                break;
            case R.id.iv_left_icon:
            case R.id.titletext:
                finish();
                break;
        }
    }

}
