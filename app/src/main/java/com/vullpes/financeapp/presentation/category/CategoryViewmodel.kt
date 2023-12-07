package com.vullpes.financeapp.presentation.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vullpes.financeapp.domain.model.Category
import com.vullpes.financeapp.domain.usecases.category.CreateCategoryUseCase
import com.vullpes.financeapp.domain.usecases.category.ListCategoryUseCase
import com.vullpes.financeapp.domain.usecases.category.UpdateCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewmodel @Inject constructor(
    private val listCategoryUseCase: ListCategoryUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            listCategoryUseCase.invoke().collect{
                uiState = uiState.copy(categories = it)
            }
        }
    }

    var uiState by mutableStateOf(UiStateCategory())
        private set

    fun openModalCategory(category: Category? = null) {
        uiState = if (category != null) {
            uiState.copy(categorySelected = category, openModalCategory = true)
        } else {
            uiState.copy(categorySelected = Category(), openModalCategory = false)
        }
    }

    fun closeModalCategory() {
        uiState = uiState.copy(openModalCategory = false)
    }

    fun nameCategory(nameCategory: String) {
        uiState = uiState.copy(categorySelected = uiState.categorySelected?.copy(nameCategory = nameCategory))
    }

    fun statusCategory(statusCategory: Boolean) {
        uiState = uiState.copy(categorySelected = uiState.categorySelected?.copy(active = statusCategory))
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