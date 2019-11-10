/* Generated by AN DISI Unibo */ 
package it.unibo.contentontable

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Contentontable ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[contentontable]: starts...")
						solve("consult('sysRules.pl')","") //set resVar	
					}
					 transition( edgeName="goto",targetState="waitingCmd", cond=doswitch() )
				}	 
				state("waitingCmd") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						println("[contentontable]: I'm waiting for a command...")
					}
					 transition(edgeName="t095",targetState="gettingContent",cond=whenDispatch("getContent"))
					transition(edgeName="t096",targetState="settingContent",cond=whenEvent("updateContent"))
				}	 
				state("gettingContent") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						storeCurrentMessageForReply()
						solve("findall(ontable(X,Y,Z),ontable(X,Y,Z),L)","") //set resVar	
						if(currentSolution.isSuccess()) { var response = getCurSol("L").toString()
						replyToCaller("responseContent","responseContent(${response})")
						 }
						else
						{ println("[contentontable]: error in solving file")
						 }
					}
					 transition( edgeName="goto",targetState="waitingCmd", cond=doswitch() )
				}	 
				state("settingContent") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("updateContent(DEVICE,TYPE,FOODCODE,QNT,OP_T)"), Term.createTerm("updateContent(table,TYPE,FC,QNT,OP_T)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								solve("ontable(${payloadArg(1)},${payloadArg(2)},X)","") //set resVar	
								if(currentSolution.isSuccess()) { 
													var old = Integer.parseInt(getCurSol("X").toString())
													var difference = Integer.parseInt(payloadArg(3))
													var New = 0					
								
													if(payloadArg(4).equals("put")){
														New = old + difference
													}
													if(payloadArg(4).equals("take")){
														New = old - difference
													}
								if(New == 0 ){
								solve("retract(ontable(${payloadArg(1)},${payloadArg(2)},_))","") //set resVar	
								}else{
								solve("replaceRule(ontable(${payloadArg(1)},${payloadArg(2)},_),ontable(${payloadArg(1)},${payloadArg(2)},$New))","") //set resVar	
								}
								if(currentSolution.isSuccess()) { println("[contentontable]: I've replaced a line")
								 }
								else
								{ println("[contentontable]: error in replacing the new line")
								 }
								 }
								else
								{ solve("assert(ontable(${payloadArg(1)},${payloadArg(2)},${payloadArg(3)}))","") //set resVar	
								if(currentSolution.isSuccess()) { println("[contentontable]: I've added a new line")
								 }
								else
								{ println("[contentontable]: error in adding a new line")
								 }
								 }
						}
					}
					 transition( edgeName="goto",targetState="waitingCmd", cond=doswitch() )
				}	 
			}
		}
}
