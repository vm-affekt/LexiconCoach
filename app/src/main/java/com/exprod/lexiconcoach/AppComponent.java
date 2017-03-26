package com.exprod.lexiconcoach;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by VLAD on 26.03.2017.
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
        }
)
public interface AppComponent {
}
