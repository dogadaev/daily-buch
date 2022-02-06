package org.dogadaev.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.dogadaev.entity.Diary
import org.dogadaev.entity.ViewModelPayload
import org.dogadaev.interactor.usecase.DiaryUseCase
import org.dogadaev.interactor.usecase.factory.UseCaseFactory
import javax.inject.Inject

class DiaryViewModel @AssistedInject constructor(
    @Assisted extras: CreationExtras,
    useCaseFactory: UseCaseFactory,
) : ViewModel() {

    private val useCase = useCaseFactory.createDiaryUseCase(
        diaryId = extras[DIARY_ID_EXTRA_KEY]!!
    )

    val data = MutableLiveData<Diary>()
    val title = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            useCase.diary.collect {
                data.postValue(it)

                title.postValue(it.title)
            }
        }
    }

    fun insertTestEntry() {
        viewModelScope.launch {
            useCase.insertEntry()
        }
    }

    companion object {
        val DIARY_ID_EXTRA_KEY = object : CreationExtras.Key<String> {}
    }
}