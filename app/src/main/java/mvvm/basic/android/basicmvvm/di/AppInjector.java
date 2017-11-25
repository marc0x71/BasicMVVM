package mvvm.basic.android.basicmvvm.di;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.HasActivityInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;
import mvvm.basic.android.basicmvvm.MyApp;
import timber.log.Timber;

/**
 * Created on 25/11/17.
 */

public class AppInjector {

	public static void init(MyApp app) {
		DaggerAppComponent.builder().application(app)
				.build().inject(app);

		app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
			@Override
			public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
				handleActivity(activity);
			}

			@Override
			public void onActivityStarted(Activity activity) {

			}

			@Override
			public void onActivityResumed(Activity activity) {

			}

			@Override
			public void onActivityPaused(Activity activity) {

			}

			@Override
			public void onActivityStopped(Activity activity) {

			}

			@Override
			public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

			}

			@Override
			public void onActivityDestroyed(Activity activity) {

			}
		});
	}

	private static void handleActivity(Activity activity) {

		Timber.d("handleActivity(" + activity.getLocalClassName() + ")");

		if (activity instanceof HasSupportFragmentInjector || activity instanceof HasActivityInjector) {
			AndroidInjection.inject(activity);
			if (activity instanceof Injectable) {
				((Injectable)activity).onInjectionCompleted();
			}
		}
		if (activity instanceof FragmentActivity) {
			((FragmentActivity) activity).getSupportFragmentManager()
					.registerFragmentLifecycleCallbacks(
							new FragmentManager.FragmentLifecycleCallbacks() {
								@Override
								public void onFragmentCreated(FragmentManager fm, Fragment f,
															  Bundle savedInstanceState) {
									if (f instanceof Injectable) {
										AndroidSupportInjection.inject(f);
										((Injectable)f).onInjectionCompleted();
									}
								}
							}, true);
		}
	}
}
