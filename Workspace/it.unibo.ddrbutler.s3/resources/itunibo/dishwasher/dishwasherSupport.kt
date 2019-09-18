package itunibo.dishwasher

import it.unibo.kactor.ActorBasic

object dishwasherSupport{
	
	lateinit var dishwasher : Dishwasher
	
	fun create(){
		dishwasher = Dishwasher()
	}
	
	fun putDishes(actor : ActorBasic, quantity : Int){
		dishwasher.putDishes(quantity)
		println(dishwasher)
	}
	
	fun takeDishes(actor : ActorBasic, quantity : Int){
		dishwasher.takeDishes(quantity)
		println(dishwasher)
	}
}