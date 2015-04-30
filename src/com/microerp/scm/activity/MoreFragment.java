package com.microerp.scm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.ab.fragment.AbFragment;
import com.ab.http.AbHttpListener;
import com.ab.http.AbRequestParams;
import com.ab.util.AbToastUtil;
import com.ab.view.pullview.AbPullToRefreshView;
import com.andbase.demo.adapter.ArticleListAdapter;
import com.andbase.demo.model.Article;
import com.andbase.global.MyApplication;
import com.andbase.web.NetworkWeb;
import com.example.MicroERP_1_0_0.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhongcy on 2015/4/30.
 */
public class MoreFragment extends AbFragment{
    private TextView textView;
    private GridView gridView;
    private MyApplication application;
    private Activity mActivity = null;
    private List<Article> mList = null;
    private AbPullToRefreshView mAbPullToRefreshView = null;
    private ListView mListView = null;
    private int currentPage = 1;
    private ArticleListAdapter myListViewAdapter = null;
    private int total = 50;
    private int pageSize = 5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.scmfragment,container,false);

        //View view=inflater.inflate(R.layout.scmfragment, null);
        gridView= (GridView) rootview.findViewById(R.id.gridView1);
        ArrayList al=new ArrayList();
        HashMap hs1=new HashMap();
        hs1.put("itemImage", R.drawable.app_f);
        hs1.put("itemText", "智能搜索");
        al.add(hs1);
        HashMap hs2=new HashMap();
        hs2.put("itemImage", R.drawable.app_f);
        hs2.put("itemText", "提交建议");
        al.add(hs2);
//        HashMap hs3=new HashMap();
//        hs3.put("itemImage", R.drawable.app_f);
//        hs3.put("itemText", "到 货");
//        al.add(hs3);
//        HashMap hs4=new HashMap();
//        hs4.put("itemImage", R.drawable.app_f);
//        hs4.put("itemText", "入 库");
//        al.add(hs4);
//        HashMap hs5=new HashMap();
//        hs5.put("itemImage", R.drawable.app_f);
//        hs5.put("itemText", "出库申请");
//        al.add(hs5);
//        HashMap hs6=new HashMap();
//        hs6.put("itemImage", R.drawable.app_f);
//        hs6.put("itemText", "出库");
//        al.add(hs6);

        SimpleAdapter adpter = new SimpleAdapter(this.getActivity(),
                al,R.layout.layout_gridview_item,
                new String[]{"itemImage","itemText"},
                new int[]{R.id.imageView_ItemImage,R.id.textView_ItemText});

        gridView.setAdapter(adpter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent0 = new Intent(getActivity(), qinggou.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), caigou.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getActivity(), daohuo.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getActivity(), daohuo.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(getActivity(), chukushenqing.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(getActivity(), chuku.class);
                        startActivity(intent5);
                        break;
                }
            }
        });
//        //设置下拉刷新监听器
//        mAbPullToRefreshView.setOnHeaderRefreshListener(new AbPullToRefreshView.OnHeaderRefreshListener() {
//
//            @Override
//            public void onHeaderRefresh(AbPullToRefreshView view) {
//                refreshTask();
//            }
//        });
        return rootview;
    }
    /**
     * 下载数据
     */
    public void refreshTask() {
        currentPage = 1;
        // 绑定参数
        AbRequestParams params = new AbRequestParams();
        params.put("cityCode", "11");
        params.put("pageSize", String.valueOf(pageSize));
        params.put("toPageNo",String.valueOf(currentPage));
        // 下载网络数据
        NetworkWeb web = NetworkWeb.newInstance(mActivity);
        web.findLogList(params, new AbHttpListener(){

            @Override
            public void onSuccess(List<?> newList) {
                mList.clear();
                if(newList!=null && newList.size()>0){
                    mList.addAll((List<Article>)newList);
                    myListViewAdapter.notifyDataSetChanged();
                    newList.clear();
                }
                mAbPullToRefreshView.onHeaderRefreshFinish();

                //模拟用，真是开发中需要直接调用run内的内容
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        //显示内容
                        showContentView();
                    }

                }, 3000);

            }

            @Override
            public void onFailure(String content) {
                AbToastUtil.showToast(mActivity, content);
                //显示重试的框
                showRefreshView();
            }

        });
    }
}
