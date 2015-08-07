package com.montazze.groupchatimagecombiner;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Choirul on 8/7/2015.
 */
public class ImageCombiner {

    /**
     * http://androidattop.blogspot.com/2012/06/merge-multiple-images-into-one-image-in.html
     * @param c
     * @param s
     * @param p
     * @return
     */
    public static Bitmap combine(Bitmap c, Bitmap s, Bitmap p) { // can add a 3rd parameter 'String loc' if you want to save the new image - left some code to do that at the bottom
        Bitmap cs = null;
        int width = (c.getWidth() + s.getWidth() + p.getWidth())/3;
        int height = (c.getHeight() + s.getHeight() + p.getHeight())/3;

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(cs);

        c = centerCrop(c);
        s = centerCrop(s);
        p = centerCrop(p);

        c = Bitmap.createBitmap(c,
                c.getWidth() / 4,
                0,
                c.getWidth() / 2,
                c.getHeight());
        c = Bitmap.createScaledBitmap(c, width/2, height, false);
        s = Bitmap.createScaledBitmap(s, width/2, height/2, false);
        p = Bitmap.createScaledBitmap(p, width/2, height/2, false);

        comboImage.drawBitmap(c, 0f, 0f, null);
        comboImage.drawBitmap(s, c.getWidth()+6, 0, null);
        comboImage.drawBitmap(p,c.getWidth()+6, s.getHeight()+6, null);

        return cs;
    }

    /**
     * http://stackoverflow.com/questions/6908604/android-crop-center-of-bitmap
     * @param srcBmp
     * @return
     */
    private static Bitmap centerCrop(Bitmap srcBmp){
        Bitmap dstBmp;
        if (srcBmp.getWidth() >= srcBmp.getHeight()){
            dstBmp = Bitmap.createBitmap(
                    srcBmp,
                    srcBmp.getWidth()/2 - srcBmp.getHeight()/2,
                    0,
                    srcBmp.getHeight(),
                    srcBmp.getHeight()
            );

        }else{
            dstBmp = Bitmap.createBitmap(
                    srcBmp,
                    0,
                    srcBmp.getHeight()/2 - srcBmp.getWidth()/2,
                    srcBmp.getWidth(),
                    srcBmp.getWidth()
            );
        }
        return dstBmp;
    }
}
