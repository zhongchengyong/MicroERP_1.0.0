package com.example.MicroERP_1_0_0;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import com.ab.activity.AbActivity;

public class MyActivity extends AbActivity {
    /**
     * Called when the activity is first created.
     */

    private static  final String SPNAME="my_pref";
    private static  final String GUIDE_ACTIVITY="guide_activity";
    /**
    *�жϳ����Ƿ���μ��أ���ȡSharedPreferences�е�guide_activity�ֶ�
    */
    private boolean isFirst(Context context,String classname){
        if(context==null || classname.equalsIgnoreCase("") || classname==null) return false;
        String mResultStr = context.getSharedPreferences(SPNAME, Context.MODE_WORLD_READABLE)
                .getString(GUIDE_ACTIVITY, "");
        if(mResultStr.equalsIgnoreCase("false")) return false;
        else return true;
    }

    //*************************************************
    // Handler
    //*************************************************
    private final static int SWITCH_MAINACTIVITY = 1000;
    private final static int SWITCH_GUIDACTIVITY = 1001;
    public Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
                case SWITCH_MAINACTIVITY:
                    Intent mIntent=new Intent(MyActivity.this,MainActivity.class);
                    startActivity(mIntent);
                    finish();
                    break;
                case SWITCH_GUIDACTIVITY:
                    Intent mIntent2=new Intent(MyActivity.this,MainActivity.class);
                    startActivity(mIntent2);
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        boolean isFirstResult=isFirst(MyActivity.this,MyActivity.class.getName());
        if(isFirstResult)
            mHandler.sendEmptyMessageDelayed(SWITCH_GUIDACTIVITY, 1000);
        else {
            mHandler.sendEmptyMessageDelayed(SWITCH_MAINACTIVITY, 1000);
        }
    }
}
