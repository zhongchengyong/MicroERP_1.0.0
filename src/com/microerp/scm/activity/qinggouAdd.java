package com.microerp.scm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.ab.activity.AbActivity;
import com.ab.util.AbToastUtil;
import com.ab.view.titlebar.AbTitleBar;
import com.example.MicroERP_1_0_0.R;
import com.microerp.dao.Praybill;
import com.microerp.model.SCMPraybill;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhongcy on 2015/4/25.
 */
public class qinggouAdd extends AbActivity{
    private Praybill praybilldao=null;
    private SCMPraybill praybillvo=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.qinggouadd);
        AbTitleBar mTittleBar=this.getTitleBar();
        mTittleBar.setTitleText("正在新增请购单");
        mTittleBar.setLogo(R.drawable.button_selector_back);
        mTittleBar.setTitleBarBackground(R.drawable.top_bg);
        mTittleBar.setTitleTextMargin(10, 0, 0, 0);
        mTittleBar.setLogoLine(R.drawable.line);
        mTittleBar.clearRightView();
        mTittleBar.setLogo2(R.drawable.ok_f);


        mTittleBar.setLogo2OnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product= ((EditText) findViewById(R.id.product)).getText().toString();
                Double reqnum= Double.parseDouble(((EditText) findViewById(R.id.reqnum)).getText().toString());
                String reqdept=((EditText) findViewById(R.id.reqdept)).getText().toString();
                String produce=((EditText)findViewById(R.id.produce)).getText().toString();
                //intilize database operation
                praybilldao=new Praybill(qinggouAdd.this);
                if(product==null || product.equals("")){
                    AbToastUtil.showToast(qinggouAdd.this,"请输入产品信息");
                }
                else if(reqnum.toString().equals("")){
                    AbToastUtil.showToast(qinggouAdd.this,"请输入请购数量");
                }
                else if(reqdept==null || reqdept.equals("")){
                    AbToastUtil.showToast(qinggouAdd.this,"请输入请购部门");
                }
                else if(produce==null || produce.equals("")){
                    AbToastUtil.showToast(qinggouAdd.this,"请输入生产厂商");
                }
                else {
                    String creator="zhongcy";
                    Date date=new Date();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    String creattime=sdf.format(date);
                    boolean isorder=false;
                    praybillvo=new SCMPraybill();
                    praybillvo.setCreatetime(creattime);
                    praybillvo.setCreator(creator);
                    praybillvo.setProduct(product);
                    praybillvo.setReqdept(reqdept);
                    praybillvo.setNum(reqnum);
                    praybillvo.setProductory(produce);
                    praybillvo.setIsorderbill(isorder);

                    //(1)get databaseHelper
                    praybilldao.startReadableDatabase();
                    //(2)insert data
                    long id=praybilldao.insert(praybillvo);

                    //close database
                    praybilldao.closeDatabase();
                    //showToast("插入数据成功,ID:"+id);
                    AbToastUtil.showToast(qinggouAdd.this,"id的值"+id);

                    //跳转到请购单界面
                    Intent back=new Intent(qinggouAdd.this,qinggou.class);
                    startActivity(back);
                    finish();

                }
            }
        });
    }

}
