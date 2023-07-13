package com.example.pizza

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PizzaViewModel : ViewModel() {

    private val _state = MutableStateFlow(PizzaUiState())
    val state = _state.asStateFlow()

    init {
        getbreadType()
    }

    fun getbreadType() {
        _state.update {
            it.copy(
                BreadList = listOf(
                    BreadTypeUiState(
                        Bread = R.drawable.bread1
                    ),
                    BreadTypeUiState(
                        Bread = R.drawable.bread2
                    ),
                    BreadTypeUiState(
                        Bread = R.drawable.bread4
                    ),
                    BreadTypeUiState(
                        Bread = R.drawable.bread3
                    ),
                    BreadTypeUiState(
                        Bread = R.drawable.bread5
                    )
                )
            )
        }
    }




    private val _states = MutableStateFlow(PizzaOrderingUiState())
    val states = _states.asStateFlow()

    init {
        _states.update {
            it.copy(
                breads = getBread(),
            )
        }
    }

    fun updateBreadSize(size: Size, breadIndex: Int) {
        _states.update {
            val breads = it.breads.toMutableList()
            val bread = breads[breadIndex]

            breads[breadIndex] = bread.copy(
                size = size,
                totalPrice = bread.defaultPrice + size.price,
            )

            it.copy(
                breads = breads,
            )
        }
    }

    fun updateToppingSelection(toppingIndex: Int, breadIndex: Int) {
        _states.update {
            val breads = it.breads.toMutableList()
            val bread = breads[breadIndex]
            val toppings = bread.toppings.toMutableList()
            val topping = toppings[toppingIndex]

            toppings[toppingIndex] = topping.copy(
                isSelected = !topping.isSelected,
            )

            breads[breadIndex] = bread.copy(
                toppings = toppings,
            )

            it.copy(
                breads = breads,
            )
        }
    }


    private fun getBread(): List<Bread> {
        return listOf(
            Bread(1, R.drawable.bread1, 50, 50, Size.SMALL, getToppings()),
            Bread(2, R.drawable.bread2, 55, 55, Size.SMALL, getToppings()),
            Bread(3, R.drawable.bread3, 60, 60, Size.SMALL, getToppings()),
            Bread(4, R.drawable.bread4, 65, 65, Size.SMALL, getToppings()),
            Bread(5, R.drawable.bread5, 70, 70, Size.SMALL, getToppings()),
        )
    }

    private fun getToppings(): List<Topping> {
        return listOf(
            Topping(
                1, "Basil", R.drawable.basil3, 2, R.drawable.basil_group,
            ),
            Topping(
                2, "Onion", R.drawable.onion3, 4, R.drawable.onion_group,
            ),
            Topping(
                3, "Broccoli", R.drawable.brocli2, 6, R.drawable.brocoli_group,
            ),
            Topping(
                4, "Mushroom", R.drawable.mushroom3, 8, R.drawable.mushroom_group,
            ),
            Topping(
                5, "Sausage", R.drawable.susage9, 10,
                R.drawable.susage_group,
            ),
        )
    }
}