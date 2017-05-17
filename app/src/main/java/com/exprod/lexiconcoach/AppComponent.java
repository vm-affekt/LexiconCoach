package com.exprod.lexiconcoach;

import com.exprod.lexiconcoach.mvpmodels.VocabularyMvpModelImpl;
import com.exprod.lexiconcoach.mvpmodels.WordMvpModelImpl;
import com.exprod.lexiconcoach.repositories.VocabularyRepositoryImpl;
import com.exprod.lexiconcoach.repositories.WordRepository;
import com.exprod.lexiconcoach.repositories.WordRepositoryImpl;
import com.exprod.lexiconcoach.ui.activities.MainActivity;
import com.exprod.lexiconcoach.ui.dialogs.PutWordDialog;
import com.exprod.lexiconcoach.ui.fragments.PutVocabularyFragment;
import com.exprod.lexiconcoach.ui.fragments.StatisticFragment;
import com.exprod.lexiconcoach.storage.DbModule;
import com.exprod.lexiconcoach.ui.fragments.VocabularyListFragment;
import com.exprod.lexiconcoach.ui.fragments.WordsEditFragment;
import com.exprod.lexiconcoach.ui.presenters.PutVocabularyPresenter;
import com.exprod.lexiconcoach.ui.presenters.PutWordPresenter;
import com.exprod.lexiconcoach.ui.presenters.VocabularyListPresenter;
import com.exprod.lexiconcoach.ui.presenters.WordsEditPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by VLAD on 26.03.2017.
 */

@Singleton
@Component(
        modules = {
                DbModule.class,
                AppModule.class,
                PresenterModule.class,
                ModelModule.class,
                RepositoriesModule.class,
                DateUtilsModule.class,
        }
)
public interface AppComponent {
        // Activity
        void injectInto(MainActivity activity);

        // Fragments
        void injectInto(VocabularyListFragment fragment);
        void injectInto(StatisticFragment fragment);
        void injectInto(PutVocabularyFragment fragment);
        void injectInto(WordsEditFragment fragment);

        // Dialogs
        void injectInto(PutWordDialog dialog);

        // Presenters
        void injectInto(VocabularyListPresenter presenter);
        void injectInto(PutVocabularyPresenter presenter);
        void injectInto(WordsEditPresenter presenter);
        void injectInto(PutWordPresenter presenter);

        // Models
        void injectInto(VocabularyMvpModelImpl model);
        void injectInto(WordMvpModelImpl model);

        // Repositories
        void injectInto(VocabularyRepositoryImpl repository);
        void injectInto(WordRepositoryImpl repository);

}
