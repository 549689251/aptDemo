package com.pattern.wistronits.patterntest;

import com.pattern.wistronits.patterntest.bean.ObjBean;
import com.pattern.wistronits.patterntest.implement.PatternInterface;

/**
 * @author wistronits
 */
public class PatternImpl implements PatternInterface<ObjBean> {

    private ObjBean mObjBean;
    public PatternImpl() {
        mObjBean = new ObjBean("PatternInterface name ","PatternInterface index");
    }

    @Override
    public ObjBean loadOnlineData() {
        return mObjBean;
    }
}
