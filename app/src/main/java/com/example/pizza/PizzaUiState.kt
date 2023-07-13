package com.example.pizza

data class PizzaUiState (
    val BreadList: List<BreadTypeUiState> = listOf(),
)


data class BreadTypeUiState(
    val Bread: Int,
    val ingredients: List<Ingredient> = listOf(),
)

data class Ingredient(
    val basil: Int,
    val susage: Int,
    val Brocoili: Int,
    val mushroom: Int,
    val onion: Int,
    )



data class PizzaOrderingUiState(
    val id: Int = 0,
    val breads: List<Bread> = emptyList(),
)

data class Bread(
    val id: Int = 0,
    val image: Int = 0,
    val defaultPrice: Int = 0,
    val totalPrice: Int = 0,
    var size: Size = Size.SMALL,
    val toppings: List<Topping> = emptyList(),
)

data class Topping(
    val id: Int = 0,
    val name: String = "",
    val mainImage: Int = 0,
    val price: Int = 0,
    val image: Int = 0,
    var isSelected: Boolean = false,
)

enum class Size(val price: Int) {
    SMALL(5),
    MEDIUM(10),
    LARGE(15),
}





