/* Generated by AN DISI Unibo */ 
package it.unibo.planner

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Planner ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		
				var NextMove = ""
				var finito = false
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[PLANNER]: Started...")
						solve("consult('roomDescription.pl')","") //set resVar	
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("waitCmd") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("schedulingCompleted"), Term.createTerm("schedulingCompleted"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
						}
					}
					 transition(edgeName="t040",targetState="calculatePath",cond=whenDispatch("goto"))
				}	 
				state("calculatePath") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("goto(X)"), Term.createTerm("goto(X)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								solve("location(${payloadArg(0)},X,Y)","") //set resVar	
								
												var cX=getCurSol("X").toString()
												var cY=getCurSol("Y").toString()
								itunibo.planner.moveUtils.setGoal(myself ,cX, cY )
								itunibo.planner.moveUtils.doPlan(myself)
								println("STORED!!")
						}
					}
					 transition( edgeName="goto",targetState="schedulingNextMove", cond=doswitch() )
				}	 
				state("schedulingNextMove") { //this:State
					action { //it:State
						
									finito=false
									NextMove=""
						solve("move(X)","") //set resVar	
						if(currentSolution.isSuccess()) NextMove = getCurSol("X").toString()
						 		else{
						 			 finito=true
						 		}
						if(!finito){
						solve("retract(move(_))","") //set resVar	
						if(currentSolution.isSuccess()) println("move cancelled")
						 		else{
						 			 println("move not cancelled")
						 		}
						}
						forward("schedulingCompleted", "schedulingCompleted" ,"planner" ) 
					}
					 transition(edgeName="t041",targetState="waitCmd",cond=whenDispatchGuarded("schedulingCompleted",{finito}))
					transition(edgeName="t042",targetState="execMove",cond=whenDispatchGuarded("schedulingCompleted",{!finito}))
				}	 
				state("execMove") { //this:State
					action { //it:State
						delay(1000) 
						forward("mindCmd", "mindCmd($NextMove)" ,"robotmind" ) 
						itunibo.planner.moveUtils.doPlannedMove(myself ,NextMove )
						itunibo.planner.moveUtils.showCurrentRobotState(  )
					}
					 transition(edgeName="t043",targetState="schedulingNextMove",cond=whenDispatch("moveCompleted"))
				}	 
			}
		}
}
