package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.MenuEvent
import com.example.cafebrown.presentation.states.MenuState
import com.example.cafebrown.ui.screens.MenuItemData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

//@HiltViewModel
//class SignInViewModel @Inject constructor (private val signInUseCase: SignInUseCase) : ViewModel() {
class MenuListViewModel : ViewModel() {
    private val _menuState = mutableStateOf(
        MenuState(
            menuListState = listOf(
                MenuItemData(
                    1, "قهوه", R.drawable.coffee_cup
                ),
                MenuItemData(
                    2, "چای و دمنوش", R.drawable.tea
                ),
                MenuItemData(
                    3, "نوشیدنی های گرم", R.drawable.coffee_break
                ),
                MenuItemData(
                    4, "نوشیدنی های سرد", R.drawable.cool
                ),
                MenuItemData(
                    5, "بستنی", R.drawable.ice_cream
                ),
                MenuItemData(
                    6, "کیک و دسر", R.drawable.muffin
                ),
                MenuItemData(
                    7, "میان وعده و غذا", R.drawable.fast_food
                ),
                MenuItemData(
                    8, "صبحانه", R.drawable.breakfast
                )
            )
//            response = Resource.Error("")
        )
    )
    val menuState: State<MenuState> = _menuState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event: MenuEvent) {
        when(event){
            is MenuEvent.UpdateLoading -> TODO()
        }
    }
}