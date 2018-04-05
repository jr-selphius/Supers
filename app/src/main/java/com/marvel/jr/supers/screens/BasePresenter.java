package com.marvel.jr.supers.screens;

public class BasePresenter<VIEW> {

    protected VIEW view;

    public void setView(VIEW view) {
        this.view = view;
    }

    public void release() {
        view = null;
    }
}
