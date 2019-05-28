package com.maijia.QR;

import android.support.v4.app.Fragment;

/**
 * Description:
 * Create by dance, at 2018/12/9
 */
public class PageInfo {
        public String title;
    public Fragment fragment;
//    public List<String> title;
//    public List<Fragment> fragments;

        public PageInfo(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
//    public PageInfo(List<String> title, List<Fragment> fragments) {
//        this.title = title;
//        this.fragments = fragments;
//    }
}
