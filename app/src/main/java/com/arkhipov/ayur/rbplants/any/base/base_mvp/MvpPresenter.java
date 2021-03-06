package com.arkhipov.ayur.rbplants.any.base.base_mvp;


public abstract class MvpPresenter<V extends MvpView> {

    private V view;

    public final void attachView(V view) {
        if (view == null) throw new NullPointerException("View can't be null");
        this.view = view;
    }

    public final void detachView() {
        view = null;
    }

    public final boolean viewIsAttached() {
        return view != null;
    }

    public final V getView() {
        return view;
    }

}
