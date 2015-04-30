package com.microerp.scm.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ab.fragment.AbFragment;
import com.example.MicroERP_1_0_0.R;

/**
 * Created by zhongcy on 2015/4/28.
 */
public class UserFragment extends AbFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.user_info,container,false);
        ViewHolder holder=new ViewHolder();
        holder.left= (TextView) view.findViewById(R.id.itemsLeft);
        holder.right= (TextView) view.findViewById(R.id.itemsRight);
        //将数据信息适配到界面中

        return  view;
    }
    static  class ViewHolder{
        TextView left;
        TextView right;
    }
}
