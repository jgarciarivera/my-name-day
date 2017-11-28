package com.example.garci.mynameday;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
<<<<<<< HEAD

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
=======
import java.util.Calendar;
>>>>>>> 6fe33422c3c4091b24072757c81ae445cc3adee4

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* Function that displays datepicker dialog window when button is clicked */
    public void showDatePicker (View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    /* Function that populates textview with text regarding day of week user was born on */
    public void onDateSet(DatePicker view, int year, int month, int day) {

        /* Implement Zeller Algorithm*/
        month = month + 1;
        String dayOfTheWeek;

        if (month == 1 || month == 2) {
            month = month + 12;
            year = year - 1;
        }
        int dayOfWeek = (day + ((13*(month + 1))/5) + (year%100) + ((year%100)/4) + ((year/100)/4) + 5*(year/100))%7;

        /* Based on Zeller algorithm output, decides which day of the week date fell on*/
        if (dayOfWeek == 0) {
            dayOfTheWeek = "Saturday";
        }

        else if (dayOfWeek == 1) {
            dayOfTheWeek = "Sunday";
        }

        else if (dayOfWeek == 2) {
            dayOfTheWeek = "Monday";
        }

        else if (dayOfWeek == 3) {
            dayOfTheWeek = "Tuesday";
        }

        else if (dayOfWeek == 4) {
            dayOfTheWeek = "Wednesday";
        }

        else if (dayOfWeek == 5) {
            dayOfTheWeek = "Thursday";
        }

        else if (dayOfWeek == 6) {
            dayOfTheWeek = "Friday";
        }

        else {
            dayOfTheWeek = "ERROR";
        }

        ((TextView) findViewById(R.id.textview_birthdate_description)).setText("You were born on a " + dayOfTheWeek + "!");
    }

    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }
    }

    public void displayRelatedCelebrities() {

    }

}
