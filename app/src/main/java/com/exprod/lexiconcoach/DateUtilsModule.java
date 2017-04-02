package com.exprod.lexiconcoach;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VLAD on 02.04.2017.
 */

@Module
public class DateUtilsModule {

    @Provides
    @Singleton
    public DateFormat provideShortDateFormat(){
        return new SimpleDateFormat("MM.dd.yyyy");
    }

    @Provides
    @Singleton
    public Calendar provideCalendar(){
        return Calendar.getInstance();
    }
}
