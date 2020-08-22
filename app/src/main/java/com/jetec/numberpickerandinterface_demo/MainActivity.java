package com.jetec.numberpickerandinterface_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**開啟模組*/
        TimePickerDialog dialog = new TimePickerDialog(this);

        Button btShow = findViewById(R.id.button);
        TextView tvRes = findViewById(R.id.textView_Respond);
        btShow.setOnClickListener(v -> {
            dialog.showDialog();
        });
        /**這邊取得自己所設置之模組回調*/
        dialog.onDialogRespond = new TimePickerDialog.OnDialogRespond() {
            @Override
            public void onRespond(String selected) {
                tvRes.setText(selected);
            }
        };
    }
}