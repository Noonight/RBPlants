package com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.layoutfactory;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arkhipov.ayur.rbplants.R;
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.AndroidUtils;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.widget.LinearLayout.VERTICAL;

public final class NavigationLayoutFactory implements LayoutFactory {
    private final boolean includeToolbar;
    private final boolean includeBottomBar;
    private final LayoutFactory origin;

    public NavigationLayoutFactory(boolean includeToolbar, boolean includeBottomBar, LayoutFactory origin) {
        this.includeToolbar = includeToolbar;
        this.includeBottomBar = includeBottomBar;
        this.origin = origin;
    }

    @Override
    public View produceLayout(LayoutInflater inflater, @Nullable ViewGroup container) {
        LinearLayout parent = new LinearLayout(inflater.getContext());
        parent.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        parent.setOrientation(VERTICAL);

        View child = origin.produceLayout(inflater, parent);
        LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        if (includeBottomBar) {
            childParams.weight = 1;
        }

        if (includeToolbar) {
            inflater.inflate(R.layout.toolbar, parent);
        }
        parent.addView(child, childParams);
        if (includeBottomBar) {
            AHBottomNavigation bottomNavigation = new AHBottomNavigation(parent.getContext());
            bottomNavigation.setId(R.id.bottomNavigation);
            parent.addView(
                bottomNavigation,
                new LinearLayout.LayoutParams(MATCH_PARENT, (int) AndroidUtils.dp(parent.getContext(), 56)));
        }

        return parent;
    }
}
