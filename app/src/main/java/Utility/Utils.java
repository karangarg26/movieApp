package Utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by karangarg on 26/09/15.
 */
public class Utils {

    public static final DisplayImageOptions UIL_DEFAULT_DISPLAY_OPTIONS = new DisplayImageOptions.Builder()
            .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).bitmapConfig(Bitmap.Config.RGB_565)
            .cacheOnDisc(true).cacheInMemory(true).build();

    public static ImageLoaderConfiguration getImageLoaderConfiguration(Context context) {
        final int MEMORY_CACHE_LIMIT = 2 * 1024 * 1024;
        final int THREAD_POOL_SIZE = 5;

        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(THREAD_POOL_SIZE)
                .threadPriority(Thread.NORM_PRIORITY).denyCacheImageMultipleSizesInMemory().memoryCache(
                        new UsingFreqLimitedMemoryCache(MEMORY_CACHE_LIMIT)).writeDebugLogs()
                .defaultDisplayImageOptions(UIL_DEFAULT_DISPLAY_OPTIONS).imageDecoder(
                        new SmartUriDecoder(context, new BaseImageDecoder(false))).build();
        return imageLoaderConfiguration;
    }

    public static class SmartUriDecoder implements ImageDecoder {

        private final BaseImageDecoder imageUriDecoder;

        private final Reference<Context> context;

        public SmartUriDecoder(Context context, BaseImageDecoder imageUriDecoder) {
            if (imageUriDecoder == null) {
                throw new NullPointerException("Image decoder can't be null");
            }

            this.context = new WeakReference(context);
            this.imageUriDecoder = imageUriDecoder;
        }

        @Override
        public Bitmap decode(ImageDecodingInfo info) throws IOException {
            if (TextUtils.isEmpty(info.getImageKey())) {
                return null;
            }

            return imageUriDecoder.decode(info);
        }
    }

}
