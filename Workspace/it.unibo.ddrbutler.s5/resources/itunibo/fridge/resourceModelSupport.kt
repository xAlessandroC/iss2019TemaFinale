package itunibo.fridge

import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.launch
import java.io.File

object resourceModelSupport{
	
	fun updateFridgeModel( actor: ActorBasic, task: String, content: String, qnt: String ){
		actor.solve("action(fridge, $task, $content, $qnt)")
		actor.solve("state(fridge , X, Y, Z)")
		println("NEW STATE FRIDGE: "+actor.getCurSol("X")+" "+actor.getCurSol("Y")+" "+actor.getCurSol("Z"))
	}
	
}

