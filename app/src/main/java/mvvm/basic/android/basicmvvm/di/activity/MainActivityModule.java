package mvvm.basic.android.basicmvvm.di.activity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import mvvm.basic.android.basicmvvm.ui.MainActivity;

/**
 * Created on 25/11/17.
 */

@Module
public abstract class MainActivityModule {
	@ContributesAndroidInjector
	abstract MainActivity contributeMainActivity();
}
