package itunibo.robot

import org.eclipse.californium.core.CoapClient
import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object fridgeInteraction{
	
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
		println("RISPOSTA : $coapResp")
	}
	
}
