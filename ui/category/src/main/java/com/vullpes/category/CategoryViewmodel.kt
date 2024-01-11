package com.vullpes.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vullpes.category.usecases.ButtonSaveCategoryEnabledUsecase
import com.vullpes.category.usecases.CreateCategoryUseCase
import com.vullpes.category.usecases.ListCategoryUseCase
import com.vullpes.category.usecases.UpdateCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewmodel @Inject constructor(
    private val listCategoryUseCase: ListCategoryUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
    private val buttonSaveCategoryEnabledUsecase: ButtonSaveCategoryEnabledUsecase
) : ViewModel() {
    var uiState by mutableStateOf(UiStateCategory())
        private set
    init {
        viewModelScope.launch {
            listCategoryUseCase.invoke().collect{
                uiState = uiState.copy(categories = it)
            }
        }
    }



    fun openModalCategory(category: Category? = null) {
        uiState = if(category != null){
            uiState.copy(categorySelected = category, openModalCategory = true)
        }else{
            uiState.copy(categorySelected = Category(), openModalCategory = true)
        }
    }

    fun closeModalCategory() {
        uiState = uiState.copy(openModalCategory = false)
    }

    fun nameCategory(nameCategory: String) {
        uiState = uiState.copy(categorySelected = uiState.categorySelected?.copy(nameCategory = nameCategory))
        checkCategorySaveButtonEnabled()
    }

    fun statusCategory(statusCategory: Boolean) {
        uiState = uiState.copy(categorySelected = uiState.categorySelected?.copy(active = statusCategory))
        checkCategorySaveButtonEnabled()
    }

    private fun checkCategorySaveButtonEnabled(){
        uiState = uiState.copy(buttonCategoryEnabled = buttonSaveCategoryEnabledUsecase.execute(uiState.categorySelected!!))
    }

    fun onSaveCategory() = viewModelScope.launch(Dispatchers.IO){
        withContext(Dispatchers.Main){
            uiState = uiState.copy(loading = true)
        }
        if(uiState.categorySelected?.categoryID == 0){
            createCategoryUseCase.invoke(uiState.categorySelected!!)
        }else{
            updateCategoryUseCase.invoke(uiState.categorySelected!!)
        }
        withContext(Dispatchers.Main){
            uiState = uiState.copy(loading = false,openModalCategory = false)
        }
    }


}