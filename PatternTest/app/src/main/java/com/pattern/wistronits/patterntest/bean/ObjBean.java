package com.pattern.wistronits.patterntest.bean;

/**
 * @author wistronits
 */
public class ObjBean {

    private String objName;
    private String objIndex;

    public ObjBean(String objName, String objIndex) {
        this.objName = objName;
        this.objIndex = objIndex;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjIndex() {
        return objIndex;
    }

    public void setObjIndex(String objIndex) {
        this.objIndex = objIndex;
    }

    @Override
    public String toString() {
        return "ObjBean{" +
                "objName='" + objName + '\'' +
                ", objIndex='" + objIndex + '\'' +
                '}';
    }
}
