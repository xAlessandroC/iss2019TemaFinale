/* Generated by AN DISI Unibo */ 
package it.unibo.maitre

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Maitre ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[MAITRE]: starts...")
						itunibo.maitre.maitreGUI.init(myself)
					}
					 transition(edgeName="t083",targetState="initStateRoom",cond=whenDispatch("updateMaitre"))
				}	 
				state("initStateRoom") { //this:State
					action { //it:State
						itunibo.maitre.maitreGUI.readFromFile(  )
					}
					 transition( edgeName="goto",targetState="sendingPrepare", cond=doswitch() )
				}	 
				state("sendingPrepare") { //this:State
					action { //it:State
						println("[MAITRE]: I'm in sendingPrepare")
						itunibo.maitre.maitreGUI.enableOnlyPrepare(  )
					}
					 transition(edgeName="t084",targetState="notifyPrepare",cond=whenDispatch("prepareSended"))
					transition(edgeName="t085",targetState="updateSP",cond=whenDispatch("updateMaitre"))
				}	 
				state("notifyPrepare") { //this:State
					action { //it:State
						forward("taskChange", "taskChange(butler,prepare,null,null)" ,"resourcemodelbutler" ) 
					}
					 transition( edgeName="goto",targetState="waitingPrepCompleted", cond=doswitch() )
				}	 
				state("waitingPrepCompleted") { //this:State
					action { //it:State
						itunibo.maitre.maitreGUI.enableOnlySR(  )
						forward("modelUpdateMaitre", "modelUpdate(maitre,prepare)" ,"maitremodel" ) 
						println("[MAITRE]: waiting for a prepareCompleted...")
					}
					 transition(edgeName="t086",targetState="sendingAC",cond=whenDispatch("modelChangedMaitre"))
					transition(edgeName="t087",targetState="updateP",cond=whenDispatch("updateMaitre"))
				}	 
				state("sendingAC") { //this:State
					action { //it:State
						println("[MAITRE]: I'm in sendingAC")
						itunibo.maitre.maitreGUI.enableOnlyAC(  )
					}
					 transition(edgeName="t088",targetState="notifyAddFood",cond=whenDispatch("addFoodSended"))
					transition(edgeName="t089",targetState="notifyClear",cond=whenDispatch("clearSended"))
					transition(edgeName="t090",targetState="updateP",cond=whenDispatch("updateMaitre"))
				}	 
				state("notifyAddFood") { //this:State
					action { //it:State
						forward("taskChange", "taskChange(butler,add,taralli,5)" ,"resourcemodelbutler" ) 
					}
					 transition( edgeName="goto",targetState="waitingAddFoodCompleted", cond=doswitch() )
				}	 
				state("waitingAddFoodCompleted") { //this:State
					action { //it:State
						itunibo.maitre.maitreGUI.enableOnlySR(  )
						forward("modelUpdateMaitre", "modelUpdate(maitre,addFood)" ,"maitremodel" ) 
						println("[MAITRE]: waiting for an addFoodCompleted...")
					}
					 transition(edgeName="t091",targetState="sendingAC",cond=whenDispatch("modelChangedMaitre"))
					transition(edgeName="t092",targetState="updateAC",cond=whenDispatch("updateMaitre"))
					transition(edgeName="t093",targetState="handleWarning",cond=whenEvent("alert"))
				}	 
				state("notifyClear") { //this:State
					action { //it:State
						forward("taskChange", "taskChange(butler,clear,null,null)" ,"resourcemodelbutler" ) 
					}
					 transition( edgeName="goto",targetState="waitingClearCompleted", cond=doswitch() )
				}	 
				state("waitingClearCompleted") { //this:State
					action { //it:State
						itunibo.maitre.maitreGUI.enableOnlySR(  )
						forward("modelUpdateMaitre", "modelUpdate(maitre,clear)" ,"maitremodel" ) 
						println("[MAITRE]: waiting for a clearCompleted...")
					}
					 transition(edgeName="t094",targetState="sendingPrepare",cond=whenDispatch("modelChangedMaitre"))
					transition(edgeName="t095",targetState="updateC",cond=whenDispatch("updateMaitre"))
				}	 
				state("updateSP") { //this:State
					action { //it:State
						println("[MAITRE]: I'm in updateSP")
						itunibo.maitre.maitreGUI.readFromFile(  )
					}
					 transition( edgeName="goto",targetState="sendingPrepare", cond=doswitch() )
				}	 
				state("updateAC") { //this:State
					action { //it:State
						println("[MAITRE]: I'm in updateAC")
						itunibo.maitre.maitreGUI.readFromFile(  )
					}
					 transition( edgeName="goto",targetState="sendingAC", cond=doswitch() )
				}	 
				state("updateP") { //this:State
					action { //it:State
						println("[MAITRE]: I'm in updateP")
						itunibo.maitre.maitreGUI.readFromFile(  )
					}
					 transition( edgeName="goto",targetState="waitingPrepCompleted", cond=doswitch() )
				}	 
				state("updateA") { //this:State
					action { //it:State
						println("[MAITRE]: I'm in updateA")
					}
					 transition( edgeName="goto",targetState="waitingAddFoodCompleted", cond=doswitch() )
				}	 
				state("updateC") { //this:State
					action { //it:State
						println("[MAITRE]: I'm in updateC")
					}
					 transition( edgeName="goto",targetState="waitingClearCompleted", cond=doswitch() )
				}	 
				state("handleWarning") { //this:State
					action { //it:State
						println("[MAITRE]: received an alert")
					}
					 transition( edgeName="goto",targetState="sendingAC", cond=doswitch() )
				}	 
			}
		}
}
