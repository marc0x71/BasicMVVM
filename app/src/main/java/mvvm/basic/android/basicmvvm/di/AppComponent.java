package mvvm.basic.android.basicmvvm.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import mvvm.basic.android.basicmvvm.MyApp;
import mvvm.basic.android.basicmvvm.di.activity.MainActivityModule;

/**
 * Created on 25/11/17.
 */

@Singleton
@Component(modules = {
		AndroidInjectionModule.class,
		AppModule.class,
		MainActivityModule.class
})
public interface AppComponent {
	@Component.Builder
	interface Builder {
		@BindsInstance
		Builder application(Application application);
		AppComponent build();
	}
	void inject(MyApp app);
}
