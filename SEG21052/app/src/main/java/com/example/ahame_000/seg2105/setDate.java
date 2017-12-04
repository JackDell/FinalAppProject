package com.example.ahame_000.seg2105;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class setDate implements View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {

    private EditText et;
    private Calendar calendar;
    private Context context;


    public setDate(EditText et, Context context) {
        this.et = et;
        this.et.setOnFocusChangeListener(this);
        this.calendar = Calendar.getInstance();
        this.context = context;
    }

    @Override
    public void onDateSet(DatePicker picker, int year, int month, int day) {
        String format = "dd/MM/yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CANADA);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        et.setText(DateHelper.getDateString(calendar.getTime()));
    }

    @Override
    public void onFocusChange(View view, boolean isFocused) {
        if(isFocused) {
            new DatePickerDialog(this.context,
                    this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

}
