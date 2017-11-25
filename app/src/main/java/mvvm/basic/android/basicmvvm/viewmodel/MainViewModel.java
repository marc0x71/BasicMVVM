package mvvm.basic.android.basicmvvm.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

/**
 * Created on 25/11/17.
 */

public class MainViewModel extends ViewModel {

	private MutableLiveData<Integer> value = new MutableLiveData<>();

	@Inject
	public MainViewModel(int i) {
		value.setValue(i);
	}

	public MutableLiveData<Integer> getValue() {
		return value;
	}
}
