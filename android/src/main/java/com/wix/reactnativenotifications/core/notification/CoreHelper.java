package com.wix.reactnativenotifications.core.notification;

import android.util.DisplayMetrics;
import android.webkit.URLUtil;
import android.util.Patterns;

class CoreHelper {

  static boolean isValidUrl(String url) {
    return URLUtil.isValidUrl(url) && Patterns.WEB_URL.matcher(url).matches();
  }

  static int getDeivceDPI(DisplayMetrics metrics) {
  
    int dpi = metrics.densityDpi;

    if (dpi >= DisplayMetrics.DENSITY_XXXHIGH) {
      return DisplayMetrics.DENSITY_XXXHIGH;
    } else if (dpi >= DisplayMetrics.DENSITY_XXHIGH) {
      return DisplayMetrics.DENSITY_XXHIGH;
    } else if (dpi >= DisplayMetrics.DENSITY_XHIGH){
      return DisplayMetrics.DENSITY_XHIGH;
    } else if (dpi >= DisplayMetrics.DENSITY_HIGH) {
      return DisplayMetrics.DENSITY_HIGH;
    } else if (dpi >= DisplayMetrics.DENSITY_MEDIUM) {
      return DisplayMetrics.DENSITY_MEDIUM;
    } else {
      return DisplayMetrics.DENSITY_LOW;
    } 
  }

  static float getScaleMultiple(int dpi, float size) {
    switch (dpi) {
      case DisplayMetrics.DENSITY_LOW:
        return (float) 48 / size;

      case DisplayMetrics.DENSITY_MEDIUM:
        return (float) 64 / size;

      case DisplayMetrics.DENSITY_HIGH:
        return (float) 96 / size;

      case DisplayMetrics.DENSITY_XHIGH:
        return (float) 128 / size;
        
      case DisplayMetrics.DENSITY_XXHIGH:
        return (float) 192 / size;

      case DisplayMetrics.DENSITY_XXHIGH:
        return (float) 256 / size;

      default:
        return (float) 192 / size;
    }
  }

  static Bitmap makeLargeIcon(Bitmap image, int dpi){
    Bitmap icon = image;
    int imageHeight = icon.getHeight(); //get original image height
    int imageWidth = icon.getWidth();
    int shortenSize = imageWidth < imageHeight ? imageWidth : imageHeight;
    boolean portrait = imageWidth < imageHeight ? true : false;
    int lengthToCrop = (longerSide - shorterSide) / 2;
    int startX = portrait ? 0 : lengthToCrop;
    int startY = portrait ? lengthToCrop : 0;
    icon = Bitmap.createBitmap(icon, startX, startY, shortenSize, shortenSize);

    float multiple = getScaleMultiple(dpi, shortenSize);
    int newSize = Math.round(shortenSize * multiple);
    return Bitmap.createScaledBitmap(
      icon,
      newSize,
      newSize,
      true
     );
  }
}