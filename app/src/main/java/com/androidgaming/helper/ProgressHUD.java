package com.androidgaming.helper;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidgaming.R;


public class ProgressHUD extends Dialog {

    private Context context;


    public ProgressHUD(Context context) {
        super(context);
        this.context = context;
    }

    public ProgressHUD(Context context, int theme) {
        super(context, theme);
    }


    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            TextView txt = (TextView) findViewById(R.id.textOfLoader);
            txt.setText(message);
            txt.invalidate();
        }
    }

    public static ProgressHUD init(Context context) {

        ProgressHUD dialog = new ProgressHUD(context, R.style.ProgressHUD);
        dialog.setTitle("");
        dialog.setContentView(R.layout.progress_bar_loader);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        TextView textOfLoader = (TextView) dialog.findViewById(R.id.textOfLoader);
        textOfLoader.setText("");
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.4f;
        dialog.getWindow().setAttributes(lp);
        return dialog;
    }

//    public static ProgressHUD init(Context context, String message) {
//
//        ProgressHUD dialog = new ProgressHUD(context, R.style.ProgressHUD);
//        dialog.setTitle("");
//        dialog.setContentView(R.layout.progress_bar_loader);
//        dialog.setCancelable(false);
//        dialog.setCanceledOnTouchOutside(false);
//        TextView textOfLoader = (TextView) dialog.findViewById(R.id.textOfLoader);
//        textOfLoader.setText(message);
//        textOfLoader.setTextSize(11.0f);
//        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.dimAmount = 0.4f;
//        dialog.getWindow().setAttributes(lp);
//        return dialog;
//    }
}
