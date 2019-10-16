/* Generated by AN DISI Unibo */ 
package it.unibo.addfoodhandler

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Addfoodhandler ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		var isPresent = false
			var FoodCode = ""
			var Qnt = ""
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[ADD_FOOD_HANDLER]: starts...")
					}
					 transition( edgeName="goto",targetState="waitingCmd", cond=doswitch() )
				}	 
				state("waitingCmd") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						isPresent = false
						println("[ADD_FOOD_HANDLER]: waiting for a command...")
					}
					 transition(edgeName="t082",targetState="askingFood",cond=whenDispatch("startAddFood"))
				}	 
				state("askingFood") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("startAddFood(FC,QNT)"), Term.createTerm("startAddFood(FC,QNT)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								FoodCode = payloadArg(0)
											Qnt = payloadArg(1)
						}
						var QntInt = Integer.parseInt(Qnt)
						itunibo.robot.fridgeInteraction.ask(myself ,FoodCode, QntInt )
					}
					 transition(edgeName="t083",targetState="analyzeResponse",cond=whenDispatch("responseFood"))
				}	 
				state("analyzeResponse") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("responseFood(RESPONSE)"), Term.createTerm("responseFood(yes)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								isPresent = true
						}
						if( checkMsgContent( Term.createTerm("responseFood(RESPONSE)"), Term.createTerm("responseFood(no)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								emit("alert", "alert" ) 
								isPresent = false
						}
					}
					 transition( edgeName="goto",targetState="endAddFood", cond=doswitchGuarded({!isPresent}) )
					transition( edgeName="goto",targetState="planF", cond=doswitchGuarded({! !isPresent}) )
				}	 
				state("planF") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						forward("goto", "goto(fridge)" ,"planner" ) 
					}
					 transition(edgeName="t084",targetState="takingFood",cond=whenDispatch("planningCompleted"))
				}	 
				state("takingFood") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						println("[ADD_FOOD_HANDLER]: I'm taking the new food from the fridge")
						var qntInteger=Integer.parseInt(Qnt)
						forward("modelChangeFridge","modelChangeFridge(fridge,take,$FoodCode,$Qnt)","resourcemodelfridge")
					}
					 transition( edgeName="goto",targetState="planT", cond=doswitch() )
				}	 
				state("planT") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						forward("goto", "goto(table)" ,"planner" ) 
					}
					 transition(edgeName="t085",targetState="puttingFood",cond=whenDispatch("planningCompleted"))
				}	 
				state("puttingFood") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						println("[ADD_FOOD_HANDLER]: I'm putting the new food to the table")
						forward("putElementTable","putElementTable(food, $FoodCode, $Qnt)","table")
						emit("updateContent", "updateContent(table,food,$FoodCode,$Qnt,put)" ) 
					}
					 transition( edgeName="goto",targetState="planRH", cond=doswitch() )
				}	 
				state("planRH") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						forward("goto", "goto(rh)" ,"planner" ) 
					}
					 transition(edgeName="t086",targetState="endAddFood",cond=whenDispatch("planningCompleted"))
				}	 
				state("endAddFood") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						println("[ADD_FOOD_HANDLER]: Ended")
						forward("addFoodCompleted", "addFoodCompleted" ,"butlermind" ) 
					}
					 transition( edgeName="goto",targetState="waitingCmd", cond=doswitch() )
				}	 
			}
		}
}
