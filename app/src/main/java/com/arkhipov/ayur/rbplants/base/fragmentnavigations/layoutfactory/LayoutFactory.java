package com.arkhipov.ayur.rbplants.base.fragmentnavigations.layoutfactory;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface LayoutFactory {
    View produceLayout(LayoutInflater inflater, @Nullable ViewGroup container);
}
