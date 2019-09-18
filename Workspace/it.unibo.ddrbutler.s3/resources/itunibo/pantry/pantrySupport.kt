package itunibo.pantry

import it.unibo.kactor.ActorBasic

object pantrySupport{
	
	lateinit var pantry : Pantry
	
	fun create(initialValue : Int){
		pantry = Pantry(initialValue)
	}
	
	fun putDishes(actor : ActorBasic, quantity : Int){
		pantry.putDishes(quantity)
		println(pantry)
	}
	
	fun takeDishes(actor : ActorBasic, quantity : Int){
		pantry.takeDishes(quantity)
		println(pantry)
	}
}