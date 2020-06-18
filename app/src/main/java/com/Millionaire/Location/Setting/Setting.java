package com.Millionaire.Location.Setting;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.TransitionInflater;

import com.Millionaire.Location.R;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Setting {

    public static void shareText(Context context, String subject, String body) {
        Intent txtIntent = new Intent(Intent.ACTION_SEND);
        txtIntent.setType("text/plain");
        txtIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        txtIntent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(txtIntent, "Share"));
    }

    public static String getTime() {
        long time;
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+3:30"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm");
        date.setTimeZone(TimeZone.getTimeZone("GMT+3:30"));

        String localTime = date.format(currentLocalTime);
        if (cal.get(Calendar.HOUR) == 0)
            time = ((12 * 60) + cal.get(Calendar.MINUTE));
        else
            time = ((cal.get(Calendar.HOUR)) * 60) + cal.get(Calendar.MINUTE);
        Log.i("time2", "time9: " + cal.get(Calendar.HOUR) + " m " + cal.get(Calendar.MINUTE) + " localTime " + localTime + " time " + time);
        return String.valueOf(time);
    }

    public static void setFragment(Fragment newFragment, Context context) {
        FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        newFragment.setArguments(bundle);
        ft.setCustomAnimations(R.anim.fade_show, R.anim.fade_hide);
        ft.replace(R.id.ConstMaster, newFragment);
        ft.commitAllowingStateLoss();
    }

    public static void FinishFragStartFrag_ShareElement_NoBack(Fragment newFragment, List<View> views, int transId, Context context) {
        FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            newFragment.setSharedElementEnterTransition(TransitionInflater.from(context).inflateTransition(transId));

            for (int i = 0; i < views.size(); i++)
                ft.addSharedElement(views.get(i), views.get(i).getTransitionName());
            ft.replace(R.id.ConstMaster, newFragment);
            ft.commitAllowingStateLoss();
        } else {
            ft.replace(R.id.ConstMaster, newFragment);
            ft.commitAllowingStateLoss();
        }
    }

    public static void ShowAlertDialog(String msg, Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(msg).setCancelable(true);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    public static void setDatePicker(final TextView textView, Context context) {
        DatePickerDialog dialog = new DatePickerDialog();
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                NumberFormat formatter = new DecimalFormat("00");
                String date = String.valueOf(year) + "/" +
                        formatter.format(monthOfYear + 1) + "/" +
                        formatter.format(dayOfMonth);
                String[] date1 = date.split("/");
                //  String finalDate = String.format(Locale.US, "%s/%s/%s", farsi(date1[0]), farsi(date1[1]), farsi(date1[2]));
                String finalDate = String.format(Locale.US, "%s/%s/%s", date1[0], date1[1], date1[2]);
                textView.setText(finalDate);
            }
        });
        FragmentManager fm = ((AppCompatActivity) context).getFragmentManager();
        dialog.setThemeDark(false);
        dialog.show(fm, "timePicker");
    }

    public static String faToEn(String num) {
        return num
                .replace("۰", "0")
                .replace("۱", "1")
                .replace("۲", "2")
                .replace("۳", "3")
                .replace("۴", "4")
                .replace("۵", "5")
                .replace("۶", "6")
                .replace("۷", "7")
                .replace("۸", "8")
                .replace("۹", "9");
    }

    public static String getDateToday() {
        PersianCalendar now = new PersianCalendar();
        NumberFormat formatter = new DecimalFormat("00");
        String date = String.valueOf(now.getPersianYear()) + "/" +
                formatter.format(now.getPersianMonth() + 1) + "/" +
                formatter.format(now.getPersianDay());
        String[] date1 = date.split("/");
        String TodayDate = String.format(Locale.US, "%s/%s/%s", date1[0], date1[1], date1[2]);

        return TodayDate;
    }

}
