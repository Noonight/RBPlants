package com.arkhipov.ayur.rbplants.any.base.cash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkhipov.ayur.rbplants.App;
import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpPresenter;
import com.arkhipov.ayur.rbplants.any.base.base_mvp.MvpView;
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationFragment;

import javax.inject.Inject;

public class NavigationMvpFragment<T extends MvpPresenter<V>, V extends MvpView>
    extends NavigationFragment {
    //@Inject
    protected T presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //App.Companion.get(this).component.inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
