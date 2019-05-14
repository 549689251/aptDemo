package com.pattern.wistronits.patterntest.imgeload;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import okhttp3.internal.cache.DiskLruCache;

/**
 * @author wistronits
 */
public class DisCacheImage implements ZbkCache<String, Bitmap> {

    private LruCache<String,Bitmap> mDisCacheImage;

    public DisCacheImage(LruCache mDisCacheImage) {
        this.mDisCacheImage = mDisCacheImage;
    }

    @Override
    public void putCacheData(String key, Bitmap bitmap) {
        mDisCacheImage.put(key, bitmap);
    }

    @Override
    public Bitmap getCacheData(String key) {
        return mDisCacheImage.get(key);
    }
}
