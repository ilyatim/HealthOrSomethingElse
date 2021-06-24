package com.example.healtsorsomethingelse.utils.database

import com.example.healtsorsomethingelse.utils.BaseViewModel

/*@HiltViewModel*/
class FoodViewModel /*@Inject constructor*/(/*private val repo: FoodRepository*/) : BaseViewModel() {

    /*private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)
    val state: StateFlow<UiState>
        get() = _state

    private val action: Channel<UiAction> = Channel(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    fun sendAction(action: UiAction) {
        launch { this@FoodViewModel.action.send(action) }
    }

    private fun handleIntent() {
        launch {
            action.consumeAsFlow().collect {
                when (it) {
                    is UiAction.Loading -> loadContent(it.type)
                }
            }
        }
    }

    private fun loadContent(type: RecipesType) {
        _state.value = UiState.Loading
        launch {
            _state.value = UiState.Content(repo.getRecipes(type))
        }
    }*/
}