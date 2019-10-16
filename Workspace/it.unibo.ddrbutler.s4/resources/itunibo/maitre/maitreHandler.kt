package itunibo.maitre

import org.eclipse.californium.core.CoapResponse

import org.eclipse.californium.core.CoapHandler
import it.unibo.kactor.ActorBasic

class maitreHandler(a : ActorBasic) : CoapHandler{
	
	val maitreActor=a
	
	override fun onLoad(response:CoapResponse){
		println("[HANDLER]:"+response.getResponseText())
		var fields=response.getResponseText().split(";")
		for(s:String in fields){
			if(s!=""){
				var nome=s.split(",")[0]
				var qnt=s.split(",")[1]
				
				maitreActor.solve("content(fridge,food,${nome},_)")
				if(maitreActor.solveOk()){
					maitreActor.solve("replaceRule(content(fridge,food,${nome},_),content(fridge,food,${nome},${qnt}))")
					println("[HANDLER]: Sostituisco")
				}else{
					maitreActor.solve("assert(content(fridge,food,${nome},${qnt}))")
					println("[HANDLER]: Aggiungo")
				}
			}
		}
		
		itunibo.maitre.maitreGUI.readFromFile()
	}
	
	override fun onError(){
		println("observer error")
	}
}

