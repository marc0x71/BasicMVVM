package mvvm.basic.android.basicmvvm.di.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import mvvm.basic.android.basicmvvm.viewmodel.MainViewModel;

/**
 * Created on 25/11/17.
 */

@Module
public abstract class ViewModelModule {
	@Binds
	@IntoMap
	@ViewModelKey(MainViewModel.class)
	abstract ViewModel bindMainViewModel(MainViewModel viewModel);

	@Binds
	abstract ViewModelProvider.Factory bindViewModelFactory(MyViewModelFactory factory);
}
