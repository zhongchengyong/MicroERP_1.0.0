package com.microerp.dao;

import android.content.Context;
import com.ab.db.orm.dao.AbDBDaoImpl;
import com.microerp.db.DBSDHelper;
import com.microerp.model.SCMPraybill;

/**
 * Created by zhongcy on 2015/4/25.
 * local database in SDCards
 * include a constructor method
 *
 */
public class Praybill extends AbDBDaoImpl<SCMPraybill> {
    public Praybill(Context context){
        super(new DBSDHelper(context),SCMPraybill.class);
    }
}
