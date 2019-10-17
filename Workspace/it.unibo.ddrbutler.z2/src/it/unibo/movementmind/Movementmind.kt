/* Generated by AN DISI Unibo */ 
package it.unibo.movementmind

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Movementmind ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		var mittente=""
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[MOVEMENT-MIND]: Started...")
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("waitCmd") { //this:State
					action { //it:State
						println("[MOVEMENT-MIND]: Sono in waitCmd")
					}
					 transition(edgeName="t033",targetState="handleCommand",cond=whenDispatch("exec"))
				}	 
				state("handleCommand") { //this:State
					action { //it:State
						println("[MOVEMENT-MIND]: Sono in handleCommand")
						if( checkMsgContent( Term.createTerm("exec(MITTENTE,X)"), Term.createTerm("exec(MITTENTE,X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								mittente=payloadArg(0)
								forward("robotCmd", "robotCmd(${payloadArg(1)})" ,"basicrobot" ) 
						}
						stateTimer = TimerActor("timer_handleCommand", 
							scope, context!!, "local_tout_movementmind_handleCommand", 350.toLong() )
					}
					 transition(edgeName="t034",targetState="answer",cond=whenTimeout("local_tout_movementmind_handleCommand"))   
					transition(edgeName="t035",targetState="waitCmd",cond=whenEvent("obstacle"))
				}	 
				state("answer") { //this:State
					action { //it:State
						println("[MOVEMENT-MIND]: Sono in answer")
						if(mittente.equals("execroute")){
						forward("movementCompleted", "movementCompleted" ,"execroute" ) 
						}else{
						forward("movementCompleted", "movementCompleted" ,"butler" ) 
						}
					}
					 transition(edgeName="t036",targetState="handleCommand",cond=whenDispatch("exec"))
				}	 
			}
		}
}
