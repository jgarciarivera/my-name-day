package com.example.garci.mynameday;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;


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
        TextView weekdayView = MainActivity.this.findViewById(R.id.textview_birthdate_description);
        weekdayView.setVisibility(View.VISIBLE);

        new ConnectToSite().execute(month, day);
        TextView celebView = MainActivity.this.findViewById(R.id.celebrity_birthdate_matches);
        celebView.setVisibility(View.VISIBLE);


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
    private class ConnectToSite extends AsyncTask<Integer, Integer, String[]> {
        @Override
        protected String[] doInBackground(Integer... ints) {


            int month = ints[0];
            int day = ints[1];

            String monthName = "january";
            if (month == 1) {
                monthName = "january";
            }
            else if (month == 2) {
                monthName = "february";
            }
            else if (month == 3) {
                monthName = "march";
            }
            else if (month == 4) {
                monthName = "april";
            }
            else if (month == 5) {
                monthName = "may";
            }
            else if (month == 6) {
                monthName = "june";
            }
            else if (month == 7) {
                monthName = "july";
            }
            else if (month == 8) {
                monthName = "august";
            }
            else if (month == 9) {
                monthName = "september";
            }
            else if (month == 10) {
                monthName = "october";
            }
            else if (month == 11) {
                monthName = "november";
            }
            else if (month == 12) {
                monthName = "december";
            }

            String html = "http://www.famousbirthdays.com/" + monthName + day + ".html";
            Document doc = null;
            try {
                doc = Jsoup.connect(html).get();

            } catch (Exception e) {
                Log.d("TestOutput", "Did not connect to website");
                Log.d("TestOutput", e.toString());
            }

            Elements celebrities = doc.getElementsByClass("face person-item");
            String celebrityNames[] = new String[400];
            int i = 0;
            celebrities = doc.select("div[class=name]");
            for(Element elem : celebrities) {

                celebrityNames[i] = elem.html();
                i++;
            }

            return celebrityNames;
        }

        @Override
        protected void onPostExecute(String[] result) {
            String listOfCelebrities = "";
            for (int i = 0; i < 4; i++) {
                listOfCelebrities = listOfCelebrities + result[i];
                listOfCelebrities = listOfCelebrities + "\n";
            }
            ((TextView) findViewById(R.id.celebrity_birthdate_matches)).setText("Celebrities Born On Your Birthday\n" + listOfCelebrities);
        }

    }




}
