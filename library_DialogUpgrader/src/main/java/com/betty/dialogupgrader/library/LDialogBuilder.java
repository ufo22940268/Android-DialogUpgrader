package com.betty.dialogupgrader.library;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.View;

import java.util.Arrays;

/**
* Created by ccheng on 8/29/14.
*/
public class LDialogBuilder {

    public final Context mContext;
    public final Resources mResources;
    public DialogInterface.OnClickListener mPositiveListener;
    public DialogInterface.OnClickListener mNegativeListener;
    private View mCustomView;
    private CharSequence[] mItems;
    private DialogInterface.OnClickListener mOnItemClickListener;

    public LDialogBuilder(Context _context) {
        this.mContext = _context;
        mResources = _context.getResources();
    }

    public String mTitle = "", mContent = "", mPositiveText = "", mNegativeText = "", mPositiveColour = "";
    public boolean mDarkTheme = false;

    public LDialogBuilder setTitle(String _title) {
        this.mTitle = _title;
        return this;
    }

    public LDialogBuilder setTitle(int _title) {
        this.mTitle = mResources.getString(_title);
        return this;
    }


    public LDialogBuilder setMessage(String _content) {
        this.mContent = _content;
        return this;
    }

    public LDialogBuilder setMessage(int res) {
        this.mContent = mResources.getString(res);
        return this;
    }

    public LDialogBuilder positiveText(String _positiveText) {
        this.mPositiveText = _positiveText;
        return this;
    }

    public LDialogBuilder negativeText(String _negativeText) {
        this.mNegativeText = _negativeText;
        return this;
    }

    public LDialogBuilder positiveColor(String _positiveColour) {
        this.mPositiveColour = _positiveColour;
        return this;
    }

    public LDialogBuilder darkTheme(boolean _isDark) {
        this.mDarkTheme = _isDark;
        return this;
    }

    public AlertDialog build() {
        if (mItems != null) {
            return buildListCustomDialog();
        } else {
            return buildCustomDialog();
        }
    }

    private CustomDialog buildCustomDialog() {
        final CustomDialog customDialog = new CustomDialog(this);

        CustomDialog.ClickListener clickListener = new CustomDialog.ClickListener() {
            @Override
            public void onConfirmClick() {
                if (mPositiveListener != null) {
                    mPositiveListener.onClick(customDialog, 0);
                }
            }

            @Override
            public void onCancelClick() {
                if (mNegativeListener != null) {
                    mNegativeListener.onClick(customDialog, 1);
                }
            }
        };
        customDialog.setClickListener(clickListener);

        if (mCustomView != null) {
            customDialog.setCustomView(mCustomView);
        }

        return customDialog;
    }

    private AlertDialog buildListCustomDialog() {
        String[] strs = Arrays.copyOf(mItems, mItems.length, String[].class);
        return new CustomListDialog(mContext, mTitle, strs);
    }

    public LDialogBuilder setPositiveButton(int res, DialogInterface.OnClickListener onClickListener) {
        String string = mResources.getString(res);
        return setPositiveButton(string, onClickListener);
    }

    public LDialogBuilder setPositiveButton(String string, DialogInterface.OnClickListener onClickListener) {
        LDialogBuilder LDialogBuilder = positiveText(string);
        LDialogBuilder.mPositiveListener = onClickListener;
        return LDialogBuilder;
    }

    public LDialogBuilder setNegativeButton(int res, DialogInterface.OnClickListener onClickListener) {
        String string = mResources.getString(res);
        return setNegativeButton(string, onClickListener);
    }

    public LDialogBuilder setNegativeButton(String string, DialogInterface.OnClickListener onClickListener) {
        LDialogBuilder LDialogBuilder = negativeText(string);
        LDialogBuilder.mNegativeListener = onClickListener;
        return LDialogBuilder;
    }

    public LDialogBuilder setView(View v) {
        mCustomView = v;
        return this;
    }

    public LDialogBuilder setItems(CharSequence[] items, DialogInterface.OnClickListener onClickListener) {
        mItems = items;
        mOnItemClickListener = onClickListener;
        return this;
    }

    public AlertDialog create() {
        return build();
    }

    public void setSingleChoiceItems(int reader_mode, int index, DialogInterface.OnClickListener onClickListener) {
    }

    public AlertDialog show() {
        AlertDialog alertDialog = create();
        alertDialog.show();
        return alertDialog;
    }

    public LDialogBuilder setCancelable(boolean b) {
        return this;
    }
}
