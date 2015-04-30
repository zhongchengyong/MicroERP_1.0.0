package com.example.MicroERP_1_0_0;


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
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAbTitleBar.setLogoLine(R.drawable.line);
        initTitleRightLayout();

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


        mBottomTabView = (AbBottomTabView) findViewById(R.id.mBottomTabView);

        //如果里面的页面列表不能下载原因：
        //Fragment里面用的AbTaskQueue,由于有多个tab，顺序下载有延迟，还没下载好就被缓存了。改成用AbTaskPool，就ok了。
        //或者setOffscreenPageLimit(0)

        //缓存数量
        mBottomTabView.getViewPager().setOffscreenPageLimit(5);

        //禁止滑动
		/*mBottomTabView.getViewPager().setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}

		});*/

        //mBottomTabView.setOnPageChangeListener(listener);

        SCMFragment page1 = new SCMFragment();
        HRFragment page2 = new HRFragment();
        MoreFragment page3=new MoreFragment();
        SCMFragment page4 = new SCMFragment();
//        FragmentLoad page2 = new FragmentLoad();
//        FragmentRefresh page3 = new FragmentRefresh();
//        FragmentLoad page4 = new FragmentLoad();

        List mFragments = new ArrayList<Fragment>();
        mFragments.add(page1);
        mFragments.add(page2);
        mFragments.add(page3);
        mFragments.add(page4);

        List<String> tabTexts = new ArrayList<String>();
        tabTexts.add("供应链");
        tabTexts.add("人力资源");
        tabTexts.add("更多");
        tabTexts.add("关于");

        //设置样式
        mBottomTabView.setTabTextColor(Color.BLACK);
        mBottomTabView.setTabSelectColor(Color.rgb(255, 255,255));
        mBottomTabView.setTabBackgroundResource(R.drawable.tab_bg2);
        mBottomTabView.setTabLayoutBackgroundResource(R.drawable.tablayout_bg2);

        //注意图片的顺序
        tabDrawables = new ArrayList<Drawable>();
        tabDrawables.add(this.getResources().getDrawable(R.drawable.menu1_n));
        tabDrawables.add(this.getResources().getDrawable(R.drawable.menu1_f));
        tabDrawables.add(this.getResources().getDrawable(R.drawable.menu2_n));
        tabDrawables.add(this.getResources().getDrawable(R.drawable.menu2_f));
        tabDrawables.add(this.getResources().getDrawable(R.drawable.menu3_n));
        tabDrawables.add(this.getResources().getDrawable(R.drawable.menu3_f));
        tabDrawables.add(this.getResources().getDrawable(R.drawable.menu4_n));
        tabDrawables.add(this.getResources().getDrawable(R.drawable.menu4_f));

        //演示增加一组
        mBottomTabView.addItemViews(tabTexts,mFragments,tabDrawables);

        mBottomTabView.setTabPadding(2,10, 2, 2);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//    }

    private void initTitleRightLayout(){

    }


    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }
}
