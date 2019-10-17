package itunibo.dishwasher

import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.launch
import java.io.File

object resourceModelSupport{
	
	fun updateDishwasherModel( actor: ActorBasic, content: String, qnt: String ){
		actor.solve("action(dishwasher, $content, $qnt)")
		actor.solve("state(dishwasher , X, Y)")
		println("NEW STATE DISHWASHER: "+actor.getCurSol("X")+" "+actor.getCurSol("Y"))
	}
	
}

