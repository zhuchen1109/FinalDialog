package cn.zc.finaldialog.base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author zc
 */
@SuppressLint("NewApi")
public class DialogBase extends Dialog implements DialogInterface.OnShowListener {

    protected final static String POSITIVE = "POSITIVE";
    protected final static String NEGATIVE = "NEGATIVE";
    protected final static String NEUTRAL = "NEUTRAL";
    private OnShowListener mShowListener;

    protected DialogBase(Context context) {
        super(context);
    }

    protected DialogBase(Context context, int theme) {
        super(context, theme);
    }

    protected void setVerticalMargins(View view, int topMargin, int bottomMargin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        boolean changed = false;
        if (topMargin > -1 && params.topMargin != topMargin) {
            params.topMargin = topMargin;
            changed = true;
        }
        if (bottomMargin > -1 && params.bottomMargin != bottomMargin) {
            params.bottomMargin = bottomMargin;
            changed = true;
        }
        if (changed)
            view.setLayoutParams(params);
    }

    protected void setViewInternal(View view) {
        super.setContentView(view);
    }

    @Override
    public final void setOnShowListener(OnShowListener listener) {
        mShowListener = listener;
    }

    protected final void setOnShowListenerInternal() {
        super.setOnShowListener(this);
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if (mShowListener != null)
            mShowListener.onShow(dialog);
    }

    protected void setBackgroundCompat(View view, Drawable d) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            //noinspection deprecation
            view.setBackgroundDrawable(d);
        } else {
            view.setBackground(d);
        }
    }

    protected void setTypeface(TextView text, Typeface t) {
        if (t == null) return;
        int flags = text.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG;
        text.setPaintFlags(flags);
        text.setTypeface(t);
    }
}
