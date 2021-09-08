package alam.oto1.chronos.codex;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.PorterDuff;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import androidx.core.content.ContextCompat;
import alam.oto1.chronos.R;
import alam.oto1.chronos.callback.DateRangeCallback;
import alam.oto1.chronos.utils.TimeUtils;


public class DateSelector extends ContextWrapper {

    private DateSelector Instance;
    private Button applyBtn;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    public DateSelector(Context base) {
        super(base);
    }

    public DateSelector Builder() {
        if (Instance == null) {
            synchronized (DateSelector.class) {
                if (Instance == null) {
                    Instance = new DateSelector(this);
                }
            }
        }
        return Instance;
    }


    public void Build(DateRangeCallback callback ){
        final android.app.Dialog dialog = new android.app.Dialog(this, R.style.simbaDateSelector);
        // setting dialog properties
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_date_selector);

        android.widget.TextView startDate = dialog.findViewById(R.id.fragment_date_selector_start_date_tv_id);
        android.widget.TextView endDate = dialog.findViewById(R.id.fragment_date_selector_end_date_tv_id);

        startDatePicker = dialog.findViewById(R.id.fragment_date_selector_start_date_picker_id);
        endDatePicker = dialog.findViewById(R.id.fragment_date_selector_end_date_picker_id);

        applyBtn = dialog.findViewById(R.id.fragment_date_selector_apply_btn_id);
        applyBtn.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.color_primary), PorterDuff.Mode.MULTIPLY);

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

    /**
     *
     * @param id
     * provide color reference from color resource
     * ex. R.color.color_primary
     */
    public void setButtonColor(int id){
        if(applyBtn !=null){
            applyBtn.getBackground().setColorFilter(ContextCompat.getColor(this,id), PorterDuff.Mode.MULTIPLY);
        }
    }

    /**
     *
     * @param allowFutureDateSelection
     * set it true if you want to allow future date selection else set to false
     */
    public void allowFutureDateSelection(boolean allowFutureDateSelection){
        if(!allowFutureDateSelection){
            startDatePicker.setMaxDate(System.currentTimeMillis());
            endDatePicker.setMaxDate(System.currentTimeMillis());
        }
    }


}
