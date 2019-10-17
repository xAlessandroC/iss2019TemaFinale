package itunibo.fridge

import it.unibo.kactor.ActorBasic
import org.eclipse.californium.core.CoapResource

object fridgeSupport {
	lateinit var fridge : Fridge
	lateinit var resourceContent : CoapResource;
	
	fun create(){
		fridge = Fridge()
	}
	
	fun getAll() : Map<String, Int>{
		return fridge.getAll()
	}
	
	fun setResource(contentRes: CoapResource){
		this.resourceContent=contentRes
	}
	
	fun isAvailable(actor : ActorBasic,foodcode : String, quantity : Int) : Boolean {
		return fridge.isAvailable(foodcode, quantity)
	}
	
	fun putFood(actor : ActorBasic, foodcode : String, quantity : Int){
		fridge.putFood(foodcode, quantity)
		println(fridge)
		
		//changed
		this.resourceContent.changed()
	}
	
	fun takeFood(actor : ActorBasic, foodcode : String, quantity : Int){
		fridge.takeFood(foodcode, quantity)
		println(fridge)
		
		//changed
		this.resourceContent.changed()
	}
}