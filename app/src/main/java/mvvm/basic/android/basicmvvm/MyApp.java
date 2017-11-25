package mvvm.basic.android.basicmvvm;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import mvvm.basic.android.basicmvvm.di.AppInjector;
import timber.log.Timber;

/**
 * Created on 25/11/17.
 */

public class MyApp extends Application implements HasActivityInjector {

	@Inject
	DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

	@Override
	public void onCreate() {
		super.onCreate();

		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree() {
				@Override
				protected String createStackElementTag(StackTraceElement element) {
					return super.createStackElementTag(element) + ":" + element.getLineNumber();
				}
			});
		} else {
			Timber.plant(new ReleaseTree());
		}

		AppInjector.init(this);
	}

	@Override
	public AndroidInjector<Activity> activityInjector() {
		return dispatchingAndroidInjector;
	}

	private static class ReleaseTree extends Timber.Tree {

		@Override
		protected boolean isLoggable(int priority) {
			return !(priority == Log.VERBOSE || priority == Log.DEBUG);
		}

		@Override
		protected void log(int priority, String tag, String message, Throwable t) {
			if (!isLoggable(priority)) return;

			if (priority == Log.ASSERT) {
				Log.wtf(tag, message);
			} else {
				Log.println(priority, tag, message);

			}
			return;

		}
	}
}
