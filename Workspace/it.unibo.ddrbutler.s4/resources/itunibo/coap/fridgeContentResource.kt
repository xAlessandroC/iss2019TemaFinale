package itunibo.coap

import org.eclipse.californium.core.CoapResource
import it.unibo.kactor.ActorBasic
import org.eclipse.californium.core.CoapServer
import org.eclipse.californium.core.server.resources.CoapExchange
import org.eclipse.californium.core.coap.CoAP.Type

class fridgeContentResource (name : String ) : CoapResource(name) {
	
	init{ 
		println("--------------------------------------------------")
		println("fridgeContentResource init")
		println("--------------------------------------------------")
		setObservable(true) 				// enable observing	!!!!!!!!!!!!!!
		//setObserveType(Type.CON)			// configure the notification type to CONs
		//getAttributes().setObservable();	// mark observable in the Link-Format			
	}
	
	override fun handleGET(exchange: CoapExchange?) {
		var res=""
		var mapFood : Map<String, Int> = itunibo.fridge.fridgeSupport.getAll()
		
		for(s : String in mapFood.keys){
			res+="$s,${mapFood.get(s)};"
		}
		exchange!!.respond(""+res);
	}
	
	override fun handlePOST(exchange: CoapExchange?) {
		exchange!!.respond("Usage: GET /content")
	}
	
	override fun handlePUT(exchange: CoapExchange?) {
		exchange!!.respond("Usage: GET /content")
	}

	override fun handleDELETE(exchange: CoapExchange?) {
		exchange!!.respond("Usage: GET /content")
	}
}


