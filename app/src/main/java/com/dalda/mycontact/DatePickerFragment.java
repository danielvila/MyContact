package com.dalda.mycontact;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {
    private DatePickerDialog.OnDateSetListener listener;

    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Utilice la fecha actual como fecha predeterminada en el selector
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Cree una nueva instancia de DatePickerDialog y devuélvala
        return new DatePickerDialog(getActivity(), listener, year - 18, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Hacer algo con la fecha elegida por el usuario
    }
}
