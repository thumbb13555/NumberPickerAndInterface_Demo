package com.jetec.numberpickerandinterface_demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import java.text.SimpleDateFormat;
import java.util.Date;

class TimePickerDialog {
    private Activity activity;
    /**使InterFace可以被類別使用*/
    OnDialogRespond onDialogRespond;

    public TimePickerDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {
        /**關於AlertDialog相關的設置請參考這篇文章：
         * https://thumbb13555.pixnet.net/blog/post/310777160*/
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.number_picker_dialog, null);
        mBuilder.setView(view);
        mBuilder.setPositiveButton("確定",null);
        mBuilder.setNegativeButton("取消",null);
        AlertDialog dialog = mBuilder.create();
        /**這裡是設置NumberPicker相關*/
        NumberPicker npHour, npMin, npSec;
        npHour = view.findViewById(R.id.numberPicker_H);
        npMin = view.findViewById(R.id.numberPicker_M);
        npSec = view.findViewById(R.id.numberPicker_S);
        /**取得現在時間*/
        Date date = new Date();
        /**設置NumberPicker的最大、最小以及NumberPicker現在要顯示的內容*/
        npHour.setMaxValue(23);
        npHour.setMinValue(0);
        npHour.setValue(Integer.parseInt(new SimpleDateFormat("HH").format(date)));

        npMin.setMaxValue(59);
        npMin.setMinValue(0);
        npMin.setValue(Integer.parseInt(new SimpleDateFormat("mm").format(date)));

        npSec.setMaxValue(59);
        npSec.setMinValue(0);
        npSec.setValue(Integer.parseInt(new SimpleDateFormat("ss").format(date)));
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener((v -> {
            /**格式化字串*/
            String s = String.format("%02d:%02d:%02d"
                    , npHour.getValue(),
                    npMin.getValue()
                    ,npSec.getValue());
            /**這邊將值放進OnDialogRespond中*/
            onDialogRespond.onRespond(s);
            dialog.dismiss();
        }));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener((v -> {
            dialog.dismiss();
        }));
    }
    /**設置Interface，使取到的直可以被回傳*/
    interface OnDialogRespond{
        void onRespond(String selected);
    }
}
