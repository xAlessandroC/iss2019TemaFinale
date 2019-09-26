package itunibo.coap

import org.eclipse.californium.core.CoapResource
import it.unibo.kactor.ActorBasic
import org.eclipse.californium.core.CoapServer
import org.eclipse.californium.core.server.resources.CoapExchange

class fridgeAvailableResource (name : String ) : CoapResource(name) {
	
	init{ 
		println("--------------------------------------------------")
		println("fridgeAvailableResource init")
		println("--------------------------------------------------")
		setObservable(true) 				// enable observing	!!!!!!!!!!!!!!
		//setObserveType(Type.CON)			// configure the notification type to CONs
		//getAttributes().setObservable();	// mark observable in the Link-Format			
	}
	
	override fun handleGET(exchange: CoapExchange?) {
		exchange!!.respond("Usage: POST /available fc=<FOODCODE>&qnt=<QUANTITY>");
	}
	
	override fun handlePOST(exchange: CoapExchange?) {
		val arg = exchange!!.getRequestText()
		
		//mi aspetto contenuto del tipo fc=**&qnt=**
		//estraggo campi
		
		var args=arg.split("&");
		var fc=args[0].split("=")[1]
		var qnt=Integer.parseInt(args[1].split("=")[1])
		
		var available=itunibo.fridge.fridgeSupport.isAvailable(serverCoap.getServerActor(),fc,qnt)
		
		var res=""
		if(available){
			res="yes"
		}else{
			res="no"
		}
		println("RISPOSTA SERVER : $res")
		exchange.respond(res);
	}
	
	override fun handlePUT(exchange: CoapExchange?) {
		exchange!!.respond("Usage: POST /available fc=<FOODCODE>&qnt=<QUANTITY>");
	}

	override fun handleDELETE(exchange: CoapExchange?) {
		exchange!!.respond("Usage: POST /available fc=<FOODCODE>&qnt=<QUANTITY>");
	}
}


