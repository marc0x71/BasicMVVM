package mvvm.basic.android.basicmvvm.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mvvm.basic.android.basicmvvm.di.viewmodel.ViewModelModule;

/**
 * Created on 25/11/17.
 */

@Module(includes = ViewModelModule.class)
class AppModule {

	@Singleton
	@Provides
	int provideInt() { return 123; }
}
