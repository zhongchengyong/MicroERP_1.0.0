package com.microerp.scm.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ab.fragment.AbFragment;
import com.example.MicroERP_1_0_0.R;

/**
 * Created by zhongcy on 2015/4/30.
 */
public class AboutFragment extends AbFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.about, container, false);
        return rootview;
    }
}
