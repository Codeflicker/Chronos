package alam.oto1.chronos.codex;

import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import alam.oto1.chronos.R;
import alam.oto1.chronos.callback.DateRangeCallback;
import alam.oto1.chronos.utils.TimeUtils;


public class DateSelector {

    private static DateSelector Instance;

    public DateSelector() {

    }

    public static DateSelector getInstance() {
        if (Instance == null) {
            synchronized (DateSelector.class) {
                if (Instance == null) {
                    Instance = new DateSelector();
                }
            }
        }
        return Instance;
    }


    public void renderDatePicker(Context mCtx, boolean allowFutureDateSelection, DateRangeCallback callback ){
        final android.app.Dialog dialog = new android.app.Dialog(mCtx, R.style.simbaDateSelector);
        // setting dialog properties
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_date_selector);

        android.widget.TextView startDate = dialog.findViewById(R.id.fragment_date_selector_start_date_tv_id);
        android.widget.TextView endDate = dialog.findViewById(R.id.fragment_date_selector_end_date_tv_id);

        DatePicker startDatePicker = dialog.findViewById(R.id.fragment_date_selector_start_date_picker_id);
        DatePicker endDatePicker = dialog.findViewById(R.id.fragment_date_selector_end_date_picker_id);

        Button applyBtn = dialog.findViewById(R.id.fragment_date_selector_apply_btn_id);

        startDate.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startDatePicker.setVisibility(android.view.View.VISIBLE);
                endDatePicker.setVisibility(android.view.View.GONE);

            }
        });

        endDate.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startDatePicker.setVisibility(android.view.View.GONE);
                endDatePicker.setVisibility(android.view.View.VISIBLE);

            }
        });

        if(!allowFutureDateSelection){
            startDatePicker.setMaxDate(System.currentTimeMillis());
            endDatePicker.setMaxDate(System.currentTimeMillis());

        }


        applyBtn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if(callback != null){
                    callback.onDateSelected(TimeUtils.getEpoch(startDatePicker),TimeUtils.getEpoch(endDatePicker));
                }
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setCancelable(true);
    }
}
