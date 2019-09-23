package prova

import org.eclipse.californium.core.CoapClient
import kotlinx.coroutines.runBlocking
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapResponse

private lateinit var coapClient: CoapClient

fun main() = runBlocking{
	val hostAddr = "localhost" // "192.168.43.67 3"

	val resourceName = "content"
	createClient(hostAddr, 5863, resourceName)
	
	//val coapResp = coapClient.post("fc=taralli&qnt=10",0).getResponseText()
	val coapResp = coapClient.get().getResponseText()
	println(coapResp)
	//println(coapResp.getResponseText())
}

fun createClient(serverAddr: String, port: Int, resourceName: String?) {
		coapClient = CoapClient("coap://$serverAddr:" + port + "/" + resourceName)
		println("Client started")
}