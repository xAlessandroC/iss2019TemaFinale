/* Generated by AN DISI Unibo */ 
package it.unibo.fridge

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Fridge ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[FRIDGE]: Started...")
					}
					 transition( edgeName="goto",targetState="initialize", cond=doswitch() )
				}	 
				state("initialize") { //this:State
					action { //it:State
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("waitCmd") { //this:State
					action { //it:State
						println("[FRIDGE]: Sono in waitCmd")
					}
					 transition(edgeName="t055",targetState="answering",cond=whenDispatch("query"))
					transition(edgeName="t056",targetState="puttingFood",cond=whenDispatch("putFood"))
					transition(edgeName="t057",targetState="takingFood",cond=whenDispatch("takeFood"))
				}	 
				state("answering") { //this:State
					action { //it:State
						println("[FRIDGE]: Sono in answering")
						if( checkMsgContent( Term.createTerm("query(FOODCODE,QNT)"), Term.createTerm("query(FOODCODE,QNT)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								forward("answer", "answer(${payloadArg(0)},yes)" ,"butler" ) 
						}
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("puttingFood") { //this:State
					action { //it:State
						println("[FRIDGE]: Sono in puttingFood")
						if( checkMsgContent( Term.createTerm("putFood(FOODCODE,QNT)"), Term.createTerm("putFood(FOODCODE,QNT)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
						}
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("takingFood") { //this:State
					action { //it:State
						println("[FRIDGE]: Sono in takingFood")
						if( checkMsgContent( Term.createTerm("takeFood(FOODCODE,QNT)"), Term.createTerm("takeFood(FOODCODE,QNT)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
						}
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
			}
		}
}
