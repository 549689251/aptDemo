package com.pattern.wistronits.patterntest;

/**
 * @author wistronits
 */
public class PatternBean<D> {


    public PatternBean(D key) {
        this.key = key;
    }

    private D key;

    public D getD() {
        return key;
    }

    public void setD(D d) {
        this.key = d;
    }
}
