package alam.Oto1.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import alam.oto1.chronos.callback.DateRangeCallback;
import alam.oto1.chronos.codex.DateSelector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button datePicker = findViewById(R.id.main_activity_date_picker_btn_id);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateSelector.getInstance().renderDatePicker(MainActivity.this, true, new DateRangeCallback() {
                    @Override
                    public void onDateSelected(long startEpoch, long endEpoch) {

                    }
                });
            }
        });

    }
}