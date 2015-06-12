package com.example.MicroERP_1_0_0;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import com.ab.activity.AbActivity;
import com.ab.view.sliding.AbBottomTabView;
import com.ab.view.slidingmenu.SlidingMenu;
import com.ab.view.titlebar.AbTitleBar;
import com.andbase.main.MainMenuFragment;
import com.goodtime.about.auditAdvise;
import com.microerp.scm.activity.AboutFragment;
import com.microerp.scm.activity.HRFragment;
import com.microerp.scm.activity.MoreFragment;
import com.microerp.scm.activity.SCMFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongcy on 2015/4/14.
 */
public class MainActivity extends AbActivity {
    private AbBottomTabView mBottomTabView;
    private List<Drawable> tabDrawables = null;
    private SlidingMenu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.erp_main);

        AbTitleBar mAbTitleBar = this.getTitleBar();
        mAbTitleBar.setTitleText(R.string.tab_bottom_name);
        mAbTitleBar.setLogo(R.drawable.button_selector_menu);
        mAbTitleBar.setLogo2(R.drawable.button_more);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAbTitleBar.setLogoLine(R.drawable.line);

        //SlidingMenu的配置
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        //menu视图的Fragment添加
        MainMenuFragment mMainMenuFragment=new MainMenuFragment();
        menu.setMenu(R.layout.sliding_menu_menu);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.menu_frame, mMainMenuFragment).commit();

        //设置mAbTittleBar的点击监听事件
        mAbTitleBar.getLogoView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (menu.isMenuShowing()) {
                    menu.showContent();
                } else {
                    menu.showMenu();
                }
            }
        });

        mAbTitleBar.getLogoView2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adviseintent=new Intent(MainActivity.this,auditAdvise.class);
                startActivity(adviseintent);
            }
        });

    }
}
