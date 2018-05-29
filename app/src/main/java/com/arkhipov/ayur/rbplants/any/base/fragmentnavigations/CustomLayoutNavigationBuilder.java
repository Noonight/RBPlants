package com.arkhipov.ayur.rbplants.any.base.fragmentnavigations;

import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.layoutfactory.LayoutFactory;
import com.arkhipov.ayur.rbplants.any.base.fragmentnavigations.NavigationDefaults.NavigationDefaultsHolder;

public final class CustomLayoutNavigationBuilder extends NavigationBuilder<CustomLayoutNavigationBuilder> {

    public CustomLayoutNavigationBuilder(LayoutFactory layoutFactory) {
        super(layoutFactory, NavigationDefaultsHolder.navigationDefaults());
    }

    @Override
    protected CustomLayoutNavigationBuilder getThis() {
        return this;
    }

    public CustomLayoutNavigationBuilder toolbarId(int toolbarId) {
        this.toolbarId = toolbarId;
        return this;
    }

    public CustomLayoutNavigationBuilder bottomBarId(int bottomBarId) {
        this.bottomBarId = bottomBarId;
        return this;
    }

    public AutoLayoutNavigationBuilder auto() {
        return new AutoLayoutNavigationBuilder(layoutFactory());
    }
}
