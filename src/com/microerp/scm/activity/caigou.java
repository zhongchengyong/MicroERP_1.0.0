package com.microerp.scm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ab.activity.AbActivity;
import com.ab.view.titlebar.AbTitleBar;
import com.andbase.demo.adapter.UserDBListAdapter;
import com.andbase.demo.dao.UserSDDao;
import com.andbase.demo.model.LocalUser;
import com.andbase.global.MyApplication;
import com.example.MicroERP_1_0_0.R;
import com.microerp.adapter.PraybillAdapter;
import com.microerp.dao.Praybill;

import java.util.List;

/**
 * Created by zhongcy on 2015/4/24.
 */
public class caigou extends AbActivity {

    private MyApplication application;
    //列表适配器
    private PraybillAdapter myListViewAdapter = null;
    //列表数据
    private List praybilllist = null;
    private ListView mListView = null;
    //定义数据库操作实现类
    private Praybill praybillDao = null;

    //每一页显示的行数
    public int pageSize = 10;
    //当前页数
    public int pageNum = 1;
    //总条数
    public int totalCount = 0;
    //分页栏
    private LinearLayout mListViewForPage;
    //总条数和当前显示的几页
    public TextView total, current;
    //上一页和下一页的按钮
    private Button preView,nextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.scm_qinggou);
        AbTitleBar mTittleBar=this.getTitleBar();
        mTittleBar.setTitleText("请购单");
        mTittleBar.setLogo(R.drawable.button_selector_back);
        mTittleBar.setTitleBarBackground(R.drawable.top_bg);
        mTittleBar.setTitleTextMargin(10, 0, 0, 0);
        mTittleBar.setLogoLine(R.drawable.line);
        mTittleBar.clearRightView();
        mTittleBar.setLogo2(R.drawable.app_panel_add);
        mTittleBar.setLogo2OnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(caigou.this, qinggouAdd.class);
                startActivity(mIntent);
            }
        });
        praybillDao=new Praybill(caigou.this);

        //获取数据库连接
        praybillDao.startReadableDatabase();
        //查询数据保存到praybilllist中
        praybilllist=praybillDao.queryList(null, null, null, null, null, "createtime desc limit "+
                String.valueOf(pageSize)+ " offset " +0, null);
        totalCount=praybillDao.queryCount(null, null);
        //关闭数据库连接
        praybillDao.closeDatabase();

        //获取ListView对象
        mListView = (ListView)this.findViewById(R.id.mListView);
        //分页栏
        mListViewForPage = (LinearLayout) this.findViewById(R.id.mListViewForPage);
        //上一页和下一页的按钮
        preView = (Button) this.findViewById(R.id.preView);
        nextView = (Button) this.findViewById(R.id.nextView);
        //分页栏显示的文本
        total = (TextView)findViewById(R.id.total);
        current = (TextView)findViewById(R.id.current);
        //数据匹配一个适配器，并将listview匹配适配器
        myListViewAdapter=new PraybillAdapter(this,praybilllist);
        mListView.setAdapter(myListViewAdapter);

        //设置是否显示ListViewForPage
        if(praybilllist==null || praybilllist.size()==0){
            mListViewForPage.setVisibility(View.GONE);
        }
        else{
            total.setText("总条数"+String.valueOf(totalCount));
            current.setText("当前页"+String.valueOf(pageNum));
            mListViewForPage.setVisibility(View.VISIBLE);
        }


        //上一页事件
        preView.setOnTouchListener(new Button.OnTouchListener(){
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1){
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        preView();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        //下一页事件
        nextView.setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        nextView();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


    }


    /*
     * 上一页
     */
    private void preView() {
        pageNum--;
        current.setText("当前页:" + String.valueOf(pageNum));
        praybilllist.clear();

        queryData();
    }
    /*
     * 下一页
     */
    private void nextView() {
        pageNum++;
        current.setText("当前页:" + String.valueOf(pageNum));
        praybilllist.clear();

        queryData();
    }

    private void checkPageBar(){
        if(praybilllist == null || praybilllist.size()==0){
            //无数据隐藏分页栏
            mListViewForPage.setVisibility(View.GONE);
        }else{
            queryDataCount();
        }
    }

    /**
     *
     * 描述：查询数据
     * @throws
     */
    public void queryData(){
        //(1)获取数据库
        praybillDao.startReadableDatabase();
        //(2)执行查询
        List ListNew = praybillDao.queryList(null, null, null, null, null, "create_time desc limit "+
                String.valueOf(pageSize)+ " offset " +String.valueOf((pageNum-1)*pageSize), null);
        //(3)关闭数据库
        praybillDao.closeDatabase();

        praybilllist.clear();
        if(ListNew!=null){
            praybilllist.addAll(ListNew);
        }

        myListViewAdapter.notifyDataSetChanged();
        checkPageBar();
    }

    /**
     *
     * 描述：查询数量
     * @throws
     */
    public void queryDataCount(){
        //(1)获取数据库
        praybillDao.startReadableDatabase();
        //(2)执行查询
        totalCount = praybillDao.queryCount(null, null);
        //(3)关闭数据库
        praybillDao.closeDatabase();

        total.setText("总条数:" + String.valueOf(totalCount));
        current.setText("当前页:" + String.valueOf(pageNum));
        mListViewForPage.setVisibility(View.VISIBLE);

    }

}
