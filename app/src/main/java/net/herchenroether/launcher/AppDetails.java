package net.herchenroether.launcher;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * Created by Adam on 11/1/2017.
 */

public class AppDetails {
    private final CharSequence mLabel;
    private final CharSequence mPackageName;
    private final Drawable mAppIcon;

    public AppDetails(@NonNull CharSequence label,
                      @NonNull CharSequence packageName,
                      @NonNull Drawable appIcon) {
        mLabel = label;
        mPackageName = packageName;
        mAppIcon = appIcon;
    }

    public CharSequence getLabel() {
        return mLabel;
    }

    public CharSequence getPackageName() {
        return mPackageName;
    }

    public Drawable getAppIcon() {
        return mAppIcon;
    }
}
