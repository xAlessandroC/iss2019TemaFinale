/* Generated by AN DISI Unibo */ 
package it.unibo.maitremodel

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Maitremodel ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[MAITRE_MODEL]: starts...")
						solve("consult('sysRules.pl')","") //set resVar	
					}
					 transition( edgeName="goto",targetState="waitingCmd", cond=doswitch() )
				}	 
				state("waitingCmd") { //this:State
					action { //it:State
						println("[MAITRE_MODEL]: waiting for a command...")
					}
					 transition(edgeName="t012",targetState="modelChanging",cond=whenDispatch("modelChangeMaitre"))
					transition(edgeName="t013",targetState="modelUpdating",cond=whenDispatch("modelUpdateMaitre"))
					transition(edgeName="t014",targetState="updatingRoom",cond=whenEvent("updateContent"))
				}	 
				state("modelUpdating") { //this:State
					action { //it:State
						println("[MAITRE_MODEL]: updating model...")
						if( checkMsgContent( Term.createTerm("modelUpdateMaitre(NAME,STATE)"), Term.createTerm("modelUpdateMaitre(maitre,STATE)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								itunibo.maitre.resourceModelSupport.updateMaitreModel(myself ,payloadArg(1) )
						}
					}
					 transition( edgeName="goto",targetState="waitingCmd", cond=doswitch() )
				}	 
				state("modelChanging") { //this:State
					action { //it:State
						println("[MAITRE_MODEL]: received a modelChange command")
						if( checkMsgContent( Term.createTerm("modelChangeMaitre(NAME,STATE)"), Term.createTerm("modelChangeMaitre(maitre,STATE)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								forward("modelChangedMaitre", "modelChanged" ,"maitre" ) 
						}
					}
					 transition( edgeName="goto",targetState="waitingCmd", cond=doswitch() )
				}	 
				state("updatingRoom") { //this:State
					action { //it:State
						println("[MAITRE_MODEL]: received an updatingRoom command")
						if( checkMsgContent( Term.createTerm("updateContent(DEVICE,TYPE,FOODCODE,QNT)"), Term.createTerm("updateContent(DEVICE,TYPE,FOODCODE,QNT)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								println("[MAITRE_MODEL]: msg from ${payloadArg(0)}, type ${payloadArg(1)}, foodCode ${payloadArg(2)}, qnt ${payloadArg(3)}")
								solve("content(${payloadArg(0)},${payloadArg(1)},${payloadArg(2)},_)","") //set resVar	
								if(currentSolution.isSuccess()) { solve("replaceRule(content(${payloadArg(0)},${payloadArg(1)},${payloadArg(2)},_),content(${payloadArg(0)},${payloadArg(1)},${payloadArg(2)},${payloadArg(3)}))","") //set resVar	
								if(currentSolution.isSuccess()) { println("[MAITRE_MODEL]: I've replaced a line")
								forward("updateMaitre", "updateMaitre" ,"maitre" ) 
								 }
								else
								{ println("[MAITRE_MODEL]: error in replacing the new line")
								 }
								 }
								else
								{ solve("assert(content(${payloadArg(0)},${payloadArg(1)},${payloadArg(2)},${payloadArg(3)}))","") //set resVar	
								if(currentSolution.isSuccess()) { println("[MAITRE_MODEL]: I've added a new line")
								forward("updateMaitre", "updateMaitre" ,"maitre" ) 
								 }
								else
								{ println("[MAITRE_MODEL]: error in adding a new line")
								 }
								 }
						}
					}
					 transition( edgeName="goto",targetState="waitingCmd", cond=doswitch() )
				}	 
			}
		}
}
