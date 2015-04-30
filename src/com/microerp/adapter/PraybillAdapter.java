package com.microerp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import com.example.MicroERP_1_0_0.R;
import com.microerp.model.SCMPraybill;

import java.util.List;

/**
 * Created by zhongcy on 2015/4/27.
 */
public class PraybillAdapter extends BaseAdapter {
    private Context mContext;
    // xml转View对象
    private LayoutInflater mInflater;
    // 列表展现的数据
    private List mlist;

    public PraybillAdapter(Context context, List praybilllist) {
        mContext = context;
        mlist = praybilllist;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view=mInflater.inflate(R.layout.data_list,viewGroup,false);
            viewHolder=new ViewHolder();
            viewHolder.itemsTitle= (TextView) view.findViewById(R.id.itemsTitle);
            viewHolder.itemsText= (EditText) view.findViewById(R.id.itemsText);
            view.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) view.getTag();
        }
        //获取数据显示到界面中
        SCMPraybill praybillvo= (SCMPraybill) mlist.get(i);
        viewHolder.itemsTitle.setText(String.valueOf(praybillvo.getId()));
        viewHolder.itemsText.setText(praybillvo.getProduct());
        return view;
    }
    /**
     * View元素
     */
    static class ViewHolder {
        TextView itemsTitle;
        EditText itemsText;
    }

}
