package com.pattern.wistronits.patterntest.imgeload;

import android.graphics.Bitmap;
import android.util.LruCache;

import okhttp3.internal.cache.DiskLruCache;

/**
 * @author wistronits
 */
public class LucCacheImage implements ZbkCache<String, Bitmap> {


    private LruCache<String, Bitmap> mLruCache;

    public LucCacheImage(LruCache<String, Bitmap> lruCache) {
        this.mLruCache = lruCache;
    }

    @Override
    public void putCacheData(String key, Bitmap cacheBitmap) {
        mLruCache.put(key, cacheBitmap);
    }

    @Override
    public Bitmap getCacheData(String key) {
        return mLruCache.get(key);
    }
}
