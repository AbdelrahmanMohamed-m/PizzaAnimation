package com.example.pizza.pizzaScreen

import androidx.lifecycle.ViewModel
import com.example.pizza.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PizzaViewModel : ViewModel() {

    private val _state = MutableStateFlow(PizzaOrderingUiState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                breads = getBread(),
            )
        }
    }

    fun updateBreadSize(size: Size, breadIndex: Int) {
        _state.update {
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
        _state.update {
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
            Bread(1, R.drawable.bread1, 30, 30, Size.SMALL, getToppings()),
            Bread(2, R.drawable.bread2, 27, 27, Size.SMALL, getToppings()),
            Bread(3, R.drawable.bread3, 34, 34, Size.SMALL, getToppings()),
            Bread(4, R.drawable.bread4, 53, 53, Size.SMALL, getToppings()),
            Bread(5, R.drawable.bread5, 32, 32, Size.SMALL, getToppings()),
        )
    }

    private fun getToppings(): List<Topping> {
        return listOf(
            Topping(
                1, "Basil", R.drawable.basil3, R.drawable.basil_group,
            ),
            Topping(
                2, "Onion", R.drawable.onion3, R.drawable.onion_group,
            ),
            Topping(
                3, "Broccoli", R.drawable.brocli2, R.drawable.brocoli_group,
            ),
            Topping(
                4, "Mushroom", R.drawable.mushroom3, R.drawable.mushroom_group,
            ),
            Topping(
                5, "Sausage", R.drawable.susage9, R.drawable.susage_group,
            ),
        )
    }
}