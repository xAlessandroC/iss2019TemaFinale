package itunibo.maitre

import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.launch
import java.io.File

object resourceModelSupport{
	val fileName : String = "E:/Didattica/temaFinale/iss2019TemaFinale/Workspace/it.unibo.ddrbutler.s2/fridgeContent.txt"
	val file = File(fileName)
			
	fun updateMaitreModel( actor: ActorBasic, content: String ){
		actor.solve("action(maitre, $content)")
	}
	
	fun writeUpdateFile(device : String, foodCode : String, qnt : String) {
		file.printWriter().use {
			out -> out.println("${device}, ${foodCode}, ${qnt}")
		}
		
		println("[DEBUG]: Line printed")
	}
	
}

