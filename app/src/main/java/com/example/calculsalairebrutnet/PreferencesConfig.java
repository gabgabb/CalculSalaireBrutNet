package com.example.calculsalairebrutnet;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class PreferencesConfig {

    // Préférences des devises
    public static void saveDevisePref(Context context, Devise devise, int nbSpinner) {
        Gson gson = new Gson();
        String json = gson.toJson(devise);

        SharedPreferences parametres = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = parametres.edit();
        editor.putString("devise" + nbSpinner, json);
        editor.apply();
    }

    public static Devise loadDevisePref(Context context, int nbSpinner) {
        SharedPreferences parametres = PreferenceManager.getDefaultSharedPreferences(context);
        String json = parametres.getString("devise" + nbSpinner, "{\"id\":0,\"insigne\":\"€\",\"nomDevise\":\"Euro\",\"taux\":1}");

        Gson gson = new Gson();
        Type type = new TypeToken<Devise>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    // Préférences des items des Spinners
    public static void saveSpinnerItem(Context context, Devise devise, int nbSpinner) {
        SharedPreferences parametres = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = parametres.edit();

        editor.putInt("SpinnerItem" + nbSpinner, devise.getId());
        editor.apply();

    }

    public static int loadSpinnerItem(Context context, int nbSpinner) {
        SharedPreferences parametres = PreferenceManager.getDefaultSharedPreferences(context);
        return parametres.getInt("SpinnerItem" + nbSpinner, 0);

    }

    // Préférences des checkboxs
    public static void saveCheckBox(Context context, CheckBox cb, int nbCheckbox) {
        SharedPreferences parametres = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = parametres.edit();
        if (cb.isChecked()) {
            editor.putBoolean("CheckBox" + nbCheckbox, true);
        } else if (!cb.isChecked()) {
            editor.putBoolean("CheckBox" + nbCheckbox, false);
        }
        editor.apply();
    }

    public static boolean loadCheckBox(Context context, int nbCheckBox) {
        SharedPreferences parametres = PreferenceManager.getDefaultSharedPreferences(context);
        return parametres.getBoolean("CheckBox" + nbCheckBox, false);
    }

    // Préférences des radiosButtons
    public static void saveRadioButton(Context context, RadioButton rb, int nbRadioButton) {
        SharedPreferences parametres = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = parametres.edit();
        if (rb.isChecked() && nbRadioButton==1) {
            editor.putBoolean("RadioButton" + nbRadioButton, true);
            editor.putBoolean("RadioButton" + 2, false);
        } else if(rb.isChecked() && nbRadioButton==2){
            editor.putBoolean("RadioButton" + 1, false);
            editor.putBoolean("RadioButton" + nbRadioButton, true);
        }
        editor.apply();

    }

    public static boolean loadRadioButton(Context context, int nbRadioButton) {
        SharedPreferences parametres = PreferenceManager.getDefaultSharedPreferences(context);

        if (nbRadioButton==1) {
            return parametres.getBoolean("RadioButton"+nbRadioButton, true);
        } else  {
            return parametres.getBoolean("RadioButton"+nbRadioButton, false);
        }
    }

    public static void clearEditor(Context context){
        SharedPreferences parametres = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = parametres.edit();

        editor.clear();
        editor.apply();
    }
}
