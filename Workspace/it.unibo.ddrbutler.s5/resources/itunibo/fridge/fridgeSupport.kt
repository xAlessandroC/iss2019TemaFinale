package itunibo.fridge

import it.unibo.kactor.ActorBasic

object fridgeSupport {
	lateinit var fridge : Fridge
	
	fun create(){
		fridge = Fridge()
	}
	
	fun getAll() : Map<String, Int>{
		return fridge.getAll()
	}
	
	fun isAvailable(actor : ActorBasic,foodcode : String, quantity : Int) : Boolean {
		return fridge.isAvailable(foodcode, quantity)
	}
	
	fun putFood(actor : ActorBasic, foodcode : String, quantity : Int){
		fridge.putFood(foodcode, quantity)
		println(fridge)
	}
	
	fun takeFood(actor : ActorBasic, foodcode : String, quantity : Int){
		fridge.takeFood(foodcode, quantity)
		println(fridge)
	}
}