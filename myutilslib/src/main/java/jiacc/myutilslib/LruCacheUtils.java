package jiacc.myutilslib;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by jiacc on 2018/1/29.
 */

public class LruCacheUtils {
    public static final String TAG = "LruCacheUtils";
    private static int MAXMEMORY = (int)Runtime.getRuntime().maxMemory()/1024;
    private static LruCache<String,Bitmap> mMemoryCache = new LruCache<String,Bitmap>(1024*1024){
        @Override
        protected int sizeOf(String key, Bitmap bmp) {
            if(bmp instanceof Bitmap){
                int size=bmp.getRowBytes()*bmp.getHeight()/1024;
                LogUtils.logE(TAG,"bitmap size is " + size);
                return size;
            }
            return 1;
        }
    };

    public static synchronized void addBitmapToMemoryCache(String key,Bitmap bitmap){
        if(mMemoryCache.get(key) == null){
            if(key != null &&bitmap!=null)
                mMemoryCache.put(key,bitmap);
            else
                LogUtils.logE(TAG,"bitmap is null");
        }else{
            LogUtils.logE(TAG,"the key is null");
        }
        LogUtils.logE(TAG,"size = "+mMemoryCache.hitCount()
                +", maxSize = " + MAXMEMORY);
    }

    public static synchronized Bitmap getBitmapFromMemoryCache(String key){
        Bitmap bm = mMemoryCache.get(key);
        if(key!=null){
            return bm;
        }
        return null;
    }

    public static synchronized void removeImageCache(String key) {
        if(key != null){
            Bitmap bmp = mMemoryCache.remove(key);
            if(bmp != null)
                bmp.recycle();
        }
    }

    public static synchronized void clearCache(){
        if(mMemoryCache != null){
            if(mMemoryCache.size() > 0){
                mMemoryCache.evictAll();
            }
        }
    }
}
