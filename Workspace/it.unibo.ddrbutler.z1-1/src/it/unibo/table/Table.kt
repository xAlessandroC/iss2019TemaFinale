/* Generated by AN DISI Unibo */ 
package it.unibo.table

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Table ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[TABLE]: Started...")
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("waitCmd") { //this:State
					action { //it:State
						println("[TABLE]: Sono in waitCmd")
					}
					 transition(edgeName="t053",targetState="puttingElement",cond=whenDispatch("putElement"))
					transition(edgeName="t054",targetState="takingElement",cond=whenDispatch("takeElement"))
				}	 
				state("puttingElement") { //this:State
					action { //it:State
						println("[TABLE]: Sono in puttingElement")
						if( checkMsgContent( Term.createTerm("putElement(TYPE,CODE,QNT)"), Term.createTerm("putElement(TYPE,CODE,QNT)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								var stringa= "[TABLE]: Sto depositando "+payloadArg(2)+" "+payloadArg(0)
								println(stringa)
						}
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("takingElement") { //this:State
					action { //it:State
						println("[TABLE]: Sono in takingElement")
						if( checkMsgContent( Term.createTerm("takeElement(TYPE,CODE,QNT)"), Term.createTerm("takeElement(TYPE,CODE,QNT)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
						}
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
			}
		}
}
