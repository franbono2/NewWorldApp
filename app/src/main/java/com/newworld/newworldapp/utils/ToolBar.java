package com.newworld.newworldapp.utils;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.newworld.newworldapp.R;

import java.util.Locale;

public class ToolBar {
    private void setAppLocale(String localeCode, Activity app) {
        Resources res = app.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(localeCode.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }

    public void cambiarIdioma(Activity app){
        //english
        app.finish();
        app.startActivity(app.getIntent());
        setAppLocale("en", app);
    }
}
