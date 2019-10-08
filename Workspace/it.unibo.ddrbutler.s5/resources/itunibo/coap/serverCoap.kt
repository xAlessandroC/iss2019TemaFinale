package itunibo.coap

import it.unibo.kactor.ActorBasic
import org.eclipse.californium.core.CoapServer

object serverCoap{
		lateinit var actor : ActorBasic
		
		fun create( a: ActorBasic ){
			actor = a
			var port = 5863
			val server   = CoapServer(port);		//COAP SERVER
			var contentResourceCoap = fridgeContentResource( "content" )
			var availableResourceCoap = fridgeAvailableResource("available")
			//var putResourceCoap = fridgePutResource("put")
			//var takeResourceCoap = fridgeTakeResource("take")
			server.add( contentResourceCoap );
			server.add( availableResourceCoap );
			println("[COAP]: Server Started at coap://localhost:$port");	
			server.start();
 		}
	
		fun getServerActor() : ActorBasic{
			return actor;
		}
	}
