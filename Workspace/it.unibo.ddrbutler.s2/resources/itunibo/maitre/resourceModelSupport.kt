package itunibo.maitre

import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.launch
import java.io.File

object resourceModelSupport{
	
	fun updateMaitreModel( actor: ActorBasic, content: String ){
		actor.solve("action(maitre, $content)")
	}
	
}

