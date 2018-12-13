package com.statstracker.forfornitegame.util.defaulthelper;

import android.view.View;

public interface CommonActItf {
    View getLoadingView();
    int getLoadingViewid();

    View getEmptyView();
    int getEmptyViewId();

    View getErrorView();
    int getErrorViewId();

}
