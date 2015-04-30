package com.microerp.db;

import android.content.Context;
import com.ab.db.orm.AbSDDBHelper;
import com.ab.util.AbFileUtil;
import com.microerp.model.SCMPraybill;

public class DBSDHelper extends AbSDDBHelper {
	// 数据库名
	private static final String DBNAME = "microerp.db";
    
    // 当前数据库的版本
	private static final int DBVERSION = 1;
	// 要初始化的表
	private static final Class<?>[] clazz = {SCMPraybill.class};

	public DBSDHelper(Context context) {
		super(context,AbFileUtil.getDbDownloadDir(context), DBNAME, null, DBVERSION, clazz);
	}

}



