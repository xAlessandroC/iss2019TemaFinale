package itunibo.pantry

import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.launch
import java.io.File

object resourceModelSupport{
	
	fun updatePantryModel( actor: ActorBasic, content: String ){
		actor.solve("action(pantry, $content)")
	}
	
}

