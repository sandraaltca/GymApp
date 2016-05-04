package com.example.sandra.gymapp.Dialog;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Fragment dialeg picker date
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DateDialog extends DialogFragment {

    private DatePickerDialog.OnDateSetListener escuchador;
    public void setOnDateSetListener(DatePickerDialog.OnDateSetListener escuchador) {
        this.escuchador = escuchador;
    }

    /**
     * Metode que s'executa al crear el Dialeg.
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // EL calendari es possarà per defecta a la data que se l'hi envii des de on sigui cridat.
        Calendar calendario = Calendar.getInstance();
        Bundle args = this.getArguments();
        if (args != null) {
            long fecha = args.getLong("fecha");
            calendario.setTimeInMillis(fecha);
        }
        //Agafa les dades
        int any = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
        //Retorna les dades agafades del calendar.
        return new DatePickerDialog(getActivity(), escuchador, any, mes, dia);
    }
}


