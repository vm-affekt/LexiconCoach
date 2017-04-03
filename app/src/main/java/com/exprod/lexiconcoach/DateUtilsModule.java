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
    public static final String ITEM_DATE_FORMAT_NAME = "itemDateFormat";
    public static final String SQLITE_DATE_TIME_FORMAT_NAME = "SQLiteDateTimeFormat";

    @Provides
    @Singleton
    @Named(ITEM_DATE_FORMAT_NAME)
    public DateFormat provideItemDateFormat(){
        return new SimpleDateFormat("MM.dd.yyyy");
    }

    @Provides
    @Singleton
    @Named(SQLITE_DATE_TIME_FORMAT_NAME)
    public DateFormat provideSQLiteDateTimeFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
