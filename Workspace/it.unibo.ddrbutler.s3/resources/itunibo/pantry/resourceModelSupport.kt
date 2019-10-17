package itunibo.pantry

import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.launch
import java.io.File

object resourceModelSupport{
	
	fun updatePantryModel( actor: ActorBasic, content: String, qnt: String ){
		actor.solve("action(pantry, $content, $qnt)")
		actor.solve("state(pantry , X, Y)")
		println("NEW STATE PANTRY: "+actor.getCurSol("X")+" "+actor.getCurSol("Y"))
	}
	
}

