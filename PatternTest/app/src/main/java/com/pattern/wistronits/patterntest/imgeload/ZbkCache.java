package com.pattern.wistronits.patterntest.imgeload;

import android.graphics.Bitmap;

public interface ZbkCache<T, D> {
    void putCacheData(String key, Bitmap bitmap);

    Bitmap getCacheData(String key);
}
