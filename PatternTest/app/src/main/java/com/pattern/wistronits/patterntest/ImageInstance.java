package com.pattern.wistronits.patterntest;

/**
 * @author wistronits
 */
public class ImageInstance {


    private static ImageInstance mRealInstance;
    private static final ImageInstance mInstance = new ImageInstance();
    //懒汉

    public ImageInstance getInstance() {
        return mInstance;
    }

    //饿汉
    public ImageInstance getRealInstance() {

        if (mRealInstance == null) {
            synchronized (ImageInstance.class) {
                if (mRealInstance == null) {
                    mRealInstance = new ImageInstance();
                }
            }
        }
        return mRealInstance;
    }


}
