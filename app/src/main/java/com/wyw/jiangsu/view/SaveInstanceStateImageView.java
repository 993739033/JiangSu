package com.wyw.jiangsu.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;

/**
 * Created by wyw on 2017/9/5.
 * 自动保存状态的imageview  根据path
 */

public class SaveInstanceStateImageView extends android.support.v7.widget.AppCompatImageView {
    private String foreground; // foreground  或者 resourceId
    private String background;

    public SaveInstanceStateImageView(Context context) {
        super(context);
    }

    public SaveInstanceStateImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SaveInstanceStateImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImageBitmap(Bitmap bm,String path) {
        super.setImageBitmap(bm);
        this.foreground = path;
    }

    public void setImageDrawable(@Nullable Drawable drawable,String path) {
        super.setImageDrawable(drawable);
        this.foreground = path;
    }

    public void setBackgroundDrawable(Drawable background,String path) {
        super.setBackgroundDrawable(background);
        this.background = path;
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        foreground = String.valueOf(resId);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        super.setBackgroundResource(resId);
        background = String.valueOf(resId);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable = super.onSaveInstanceState();
        if (!TextUtils.isEmpty(foreground)) {
            SavedState ss = new SavedState(parcelable);
            ss.foreground = foreground;
            ss.background = background;
            return ss;
        }
        return parcelable;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        foreground = ss.foreground;
        background = ss.background;
        if (!TextUtils.isEmpty(foreground)) {
            if (isNumber(foreground)) {
                setImageResource(Integer.parseInt(foreground));
            } else {
                Glide.with(getContext()).load(foreground).into(this);
            }
        }
        if (!TextUtils.isEmpty(background)) {
            if (isNumber(background)) {
                setImageResource(Integer.parseInt(background));
            } else {
                Glide.with(getContext()).load(background).into(this);
            }
        }

    }

    private boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                continue;
            }
            return false;
        }
        return true;
    }

    public static class SavedState extends BaseSavedState {
        String foreground;
        String background;

        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(foreground);
            out.writeString(background);
        }

        @SuppressWarnings("hiding")
        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

        private SavedState(Parcel in) {
            super(in);
            foreground = in.readString();
            background = in.readString();
        }
    }
}
