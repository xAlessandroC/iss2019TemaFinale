package itunibo.table

import it.unibo.kactor.ActorBasic
import kotlinx.coroutines.launch
import java.io.File

object resourceModelSupport{
	
	fun updateTableModel( actor: ActorBasic,type: String, content: String, fc : String, qnt: String ){
		actor.solve("action(table, $type, $content, $fc, $qnt)")
		actor.solve("state(table , X, Y, Z, T)")
		println("NEW STATE TABLE: "+actor.getCurSol("X")+" "+actor.getCurSol("Y")+" "+actor.getCurSol("Z")+" "+actor.getCurSol("T"))
	}
	
}

