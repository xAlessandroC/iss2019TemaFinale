/* Generated by AN DISI Unibo */ 
package it.unibo.robotmind

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Robotmind ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[ROBOT MIND]: Started...")
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("waitCmd") { //this:State
					action { //it:State
					}
					 transition(edgeName="t03",targetState="handleCmd",cond=whenDispatch("robotCmd"))
					transition(edgeName="t04",targetState="turn",cond=whenEvent("obstacle"))
				}	 
				state("handleCmd") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("robotCmd(CMD)"), Term.createTerm("robotCmd(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								forward("robotCmd", "robotCmd(${payloadArg(0)})" ,"basicrobot" ) 
						}
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("turn") { //this:State
					action { //it:State
						forward("robotCmd", "robotCmd(s)" ,"basicrobot" ) 
						stateTimer = TimerActor("timer_turn", 
							scope, context!!, "local_tout_robotmind_turn", 300.toLong() )
					}
					 transition(edgeName="t05",targetState="stop",cond=whenTimeout("local_tout_robotmind_turn"))   
				}	 
				state("stop") { //this:State
					action { //it:State
						forward("robotCmd", "robotCmd(h)" ,"basicrobot" ) 
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
			}
		}
}
