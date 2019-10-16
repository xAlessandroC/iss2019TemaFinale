package itunibo.maitre

import org.eclipse.californium.core.CoapClient
import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapResponse

object fridgeInteraction{
	
	fun enableObserving(a: ActorBasic){
		val hostAddr = "localhost" // "192.168.43.67 3"
		val port = 5863
		val resourceName = "content"
		var coapClient = CoapClient("coap://$hostAddr:" + port + "/" + resourceName)
		
		var handler = maitreHandler(a)
		var relation =  coapClient.observe(handler);
		
		println("Observing enabled")
		
	}
	
	fun ask(a: ActorBasic, fc : String, qnt : Int ){
		
		val hostAddr = "localhost" // "192.168.43.67 3"
		val port = 5863
		val resourceName = "available"
		var coapClient = CoapClient("coap://$hostAddr:" + port + "/" + resourceName)
		println("Client started")
		
		val coapResp = coapClient.post("fc=$fc&qnt=$qnt",0).getResponseText()
		
		GlobalScope.launch{
			a.autoMsg("responseFood","responseFood($coapResp)")
		}
	}
	
	fun takeFood(a: ActorBasic,fc : String, qnt: Int){
		val hostAddr = "localhost" // "192.168.43.67 3"
		val port = 5863
		val resourceName = "take"
		var coapClient = CoapClient("coap://$hostAddr:" + port + "/" + resourceName)
		println("Client started")
		
		val coapResp = coapClient.post("fc=$fc&qnt=$qnt",0).getResponseText()
		
	}
	
	fun putFood(a: ActorBasic,fc : String, qnt: Int){
		val hostAddr = "localhost" // "192.168.43.67 3"
		val port = 5863
		val resourceName = "put"
		var coapClient = CoapClient("coap://$hostAddr:" + port + "/" + resourceName)
		println("Client started")
		
		val coapResp = coapClient.post("fc=$fc&qnt=$qnt",0).getResponseText()
	}
	
	fun getContent(a: ActorBasic){
		val hostAddr = "localhost" // "192.168.43.67 3"
		val port = 5863
		val resourceName = "content"
		var coapClient = CoapClient("coap://$hostAddr:" + port + "/" + resourceName)
		println("Client started")
		
		val coapResp = coapClient.get().getResponseText()
		
		//parsing response
		println("COAP RESP: $coapResp")
		var array=coapResp.split(";");
		for(s:String in array){
			var fc="";
			var qnt=""
			if(!s.equals("")){
				fc = s.split(",")[0]
				qnt =s.split(",")[1]
				a.solve("assert( content(fridge, food, $fc, $qnt) )")
			}
			
			
		}
	}
	
}
