package mvvm.basic.android.basicmvvm.ui;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import mvvm.basic.android.basicmvvm.R;
import mvvm.basic.android.basicmvvm.databinding.ActivityMainBinding;
import mvvm.basic.android.basicmvvm.di.Injectable;
import mvvm.basic.android.basicmvvm.viewmodel.MainViewModel;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements Injectable, HasActivityInjector {

	ActivityMainBinding binding;

	@Inject
	ViewModelProvider.Factory viewModelFactory;

	@Inject
	DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

	MainViewModel viewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Timber.d("onCreate!");

		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		binding.tvMessage.setText("Hello!");

		viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
		viewModel.getValue().observe(this, new Observer<Integer>() {
			@Override
			public void onChanged(@Nullable Integer integer) {
				binding.tvMessage.setText("" + integer);
			}
		});
	}

	@Override
	public void onInjectionCompleted() {
		Timber.d("Injection done!");
	}

	@Override
	public AndroidInjector<Activity> activityInjector() {
		return dispatchingActivityInjector;
	}
}
