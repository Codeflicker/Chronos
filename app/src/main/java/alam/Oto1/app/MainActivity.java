package alam.Oto1.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import alam.oto1.chronos.callback.DateRangeCallback;
import alam.oto1.chronos.codex.DateSelector;

public class MainActivity extends AppCompatActivity implements DateRangeCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button datePicker = findViewById(R.id.main_activity_date_picker_btn_id);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DateSelector selector = new DateSelector(MainActivity.this).Builder();
               selector.Build(MainActivity.this);
               selector.allowFutureDateSelection(false);
               selector.setButtonColor(R.color.color_primary);

            }
        });

    }

    @Override
    public void onDateSelected(long startEpoch, long endEpoch) {

    }
}