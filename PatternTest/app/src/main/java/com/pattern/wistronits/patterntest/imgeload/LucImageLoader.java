package com.pattern.wistronits.patterntest.imgeload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wistronits
 */
public class LucImageLoader {

    private static BuildCacheImage mCacheImageBuilder = new BuildCacheImage(getCacheType()).build();


    private static ExecutorService mExecutor = Executors.newSingleThreadExecutor();

    public static void displayImage(String url, ImageView imageView) {
        Bitmap bitmap;
        bitmap = mCacheImageBuilder.cacheImage.getCacheData(url);

        if (bitmap != null) {
            Log.d("LucImageLoader", "走内存数据。。。。。");
            imageView.setImageBitmap(bitmap);
            return;
        }

        loadFromOnline(url, imageView);

    }

    private static void loadFromOnline(final String url, final ImageView imageView) {

        mExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = submitLoadRequest(url);

                if (bitmap == null) {
                    return;
                }
                mCacheImageBuilder.cacheImage.putCacheData(url, bitmap);
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    private static Bitmap submitLoadRequest(String url) {

        Bitmap bitmap;
        try {
            URL netUri = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) netUri.openConnection();
            bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
            urlConnection.disconnect();
            Log.d("LucImageLoader", "走网络数据。。。。。");

            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static class BuildCacheImage {
        private ZbkCache cacheImage;

        public BuildCacheImage(ZbkCache cacheImage) {
            this.cacheImage = cacheImage;
        }

        private BuildCacheImage build() {

            return this;
        }
    }


    private static ZbkCache getCacheType() {
        return new DisCacheImage(new LruCache(1000));
    }

}
