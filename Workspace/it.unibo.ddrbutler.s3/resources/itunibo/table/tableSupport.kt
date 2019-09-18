package itunibo.table

import it.unibo.kactor.ActorBasic

object tableSupport{
	
	lateinit var table : Table
	
	fun create(){
		table = Table()
	}
	
	fun putDishes(actor : ActorBasic, quantity : Int){
		table.putDishes(quantity)
		println(table)
	}
	
	fun takeDishes(actor : ActorBasic, quantity : Int){
		table.takeDishes(quantity)
		println(table)
	}
	
	fun putFood(actor : ActorBasic, foodcode : String, quantity : Int){
		table.putFood(foodcode, quantity)
		println(table)
	}
	
	fun takeFood(actor : ActorBasic, foodcode : String, quantity : Int){
		table.takeFood(foodcode, quantity)
		println(table)
	}
}