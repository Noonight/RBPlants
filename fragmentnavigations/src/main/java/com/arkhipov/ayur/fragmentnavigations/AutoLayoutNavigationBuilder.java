package com.arkhipov.ayur.fragmentnavigations;

import android.view.View;

import com.arkhipov.ayur.fragmentnavigations.NavigationDefaults.NavigationDefaultsHolder;
import com.arkhipov.ayur.fragmentnavigations.layoutfactory.DummyLayoutFactory;
import com.arkhipov.ayur.fragmentnavigations.layoutfactory.IdLayoutFactory;
import com.arkhipov.ayur.fragmentnavigations.layoutfactory.LayoutFactory;
import com.arkhipov.ayur.fragmentnavigations.layoutfactory.NavigationLayoutFactory;

public final class AutoLayoutNavigationBuilder extends NavigationBuilder<AutoLayoutNavigationBuilder> {
    private boolean includeToolbar;
    private boolean includeBottomBar;

    public static AutoLayoutNavigationBuilder navigation(int id) {
        return new AutoLayoutNavigationBuilder(new IdLayoutFactory(id));
    }

    public static AutoLayoutNavigationBuilder navigation(View view) {
        return new AutoLayoutNavigationBuilder(new DummyLayoutFactory(view));
    }

    public AutoLayoutNavigationBuilder(LayoutFactory layoutFactory) {
        super(layoutFactory, NavigationDefaultsHolder.navigationDefaults());
    }

    @Override
    protected AutoLayoutNavigationBuilder getThis() {
        return this;
    }

    @Override
    public LayoutFactory layoutFactory() {
        return new NavigationLayoutFactory(includeToolbar, includeBottomBar, super.layoutFactory());
    }

    public AutoLayoutNavigationBuilder includeToolbar() {
        this.includeToolbar = true;
        return this;
    }

    public AutoLayoutNavigationBuilder excludeToolbar() {
        this.includeToolbar = false;
        return this;
    }

    public AutoLayoutNavigationBuilder includeBottomNavigation() {
        this.includeBottomBar = true;
        return this;
    }

    public AutoLayoutNavigationBuilder excludeBottomNavigation() {
        this.includeBottomBar = false;
        return this;
    }

    public CustomLayoutNavigationBuilder custom() {
        return new CustomLayoutNavigationBuilder(super.layoutFactory());
    }
}
