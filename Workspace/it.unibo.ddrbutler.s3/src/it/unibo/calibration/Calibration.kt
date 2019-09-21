/* Generated by AN DISI Unibo */ 
package it.unibo.calibration

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Calibration ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		
				var terminato = false
				var nextDir = ""
				var findTableInitDir = ""
				var startX = -1
				var startY = -1
				var endX = -1
				var endY = -1
				var lengthX = 0
				var lengthY = 0
				var angle = false
				var finito = false;
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[CALIBRATION]: Started...")
					}
					 transition( edgeName="goto",targetState="waitCmd", cond=doswitch() )
				}	 
				state("waitCmd") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("planningCompleted"), Term.createTerm("planningCompleted"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								forward("calibrationCompleted", "calibrationCompleted" ,"butlermind" ) 
						}
					}
					 transition(edgeName="t029",targetState="initAi",cond=whenDispatch("startCalibration"))
				}	 
				state("initAi") { //this:State
					action { //it:State
						itunibo.planner.plannerUtil.initAI(  )
						itunibo.planner.moveUtils.showCurrentRobotState(  )
					}
					 transition( edgeName="goto",targetState="forwardStep", cond=doswitch() )
				}	 
				state("forwardStep") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("moveCompleted"), Term.createTerm("moveCompleted"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								
											  	var positionX=
								itunibo.planner.moveUtils.getPosX(myself)
								var positionY=
								itunibo.planner.moveUtils.getPosY(myself)
								if(positionX==0 && positionY==0){
													terminato=true
											}
						}
						if(terminato){
						forward("endBoundary", "endBoundary" ,"calibration" ) 
						}else{
						forward("movementCmd", "movementCmd(w)" ,"movementhandler" ) 
						}
						itunibo.planner.moveUtils.showCurrentRobotState(  )
					}
					 transition(edgeName="t030",targetState="confirmForwardStep",cond=whenDispatch("moveCompleted"))
					transition(edgeName="t031",targetState="backtracking",cond=whenDispatch("moveFailed"))
					transition(edgeName="t032",targetState="terminazioneboundary",cond=whenDispatch("endBoundary"))
				}	 
				state("confirmForwardStep") { //this:State
					action { //it:State
						itunibo.planner.moveUtils.doPlannedMove(myself ,"w" )
					}
					 transition( edgeName="goto",targetState="forwardStep", cond=doswitch() )
				}	 
				state("backtracking") { //this:State
					action { //it:State
						itunibo.planner.moveUtils.backToCompensate(myself)
						itunibo.planner.plannerUtil.wallFound(  )
						itunibo.planner.moveUtils.showCurrentRobotState(  )
					}
					 transition(edgeName="t033",targetState="turnLeft",cond=whenDispatch("moveCompleted"))
				}	 
				state("turnLeft") { //this:State
					action { //it:State
						itunibo.planner.moveUtils.rotateLeft90tuning(myself)
					}
					 transition(edgeName="t034",targetState="forwardStep",cond=whenDispatch("moveCompleted"))
				}	 
				state("terminazioneboundary") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						itunibo.planner.moveUtils.showCurrentRobotState(  )
						itunibo.planner.moveUtils.saveMap(myself ,"mapRoom" )
					}
					 transition( edgeName="goto",targetState="startExploration", cond=doswitch() )
				}	 
				state("startExploration") { //this:State
					action { //it:State
						println("---START EXPLORATION ROOM---")
						println("---SNAKE STRATEGY---")
					}
					 transition( edgeName="goto",targetState="snakeForwardStep", cond=doswitch() )
				}	 
				state("snakeForwardStep") { //this:State
					action { //it:State
						itunibo.planner.moveUtils.moveAhead(myself)
						var positionX=
						itunibo.planner.moveUtils.getPosX(myself)
						var positionY=
						itunibo.planner.moveUtils.getPosY(myself)
						var dimX=
						itunibo.planner.moveUtils.getMapDimX(  )
						var dimY=
						itunibo.planner.moveUtils.getMapDimY(  )
						if(positionX == 0){
										nextDir="left"
								}else{
									if(positionX == dimX - 2)
										nextDir="right"
								}
						itunibo.planner.moveUtils.showCurrentRobotState(  )
						println("NEXT DIR: ${nextDir.toUpperCase()}")
					}
					 transition(edgeName="t035",targetState="snakeTurnLeft",cond=whenDispatchGuarded("moveCompleted",{nextDir.equals("left")}))
					transition(edgeName="t036",targetState="snakeTurnRight",cond=whenDispatchGuarded("moveCompleted",{nextDir.equals("right")}))
				}	 
				state("snakeTurnLeft") { //this:State
					action { //it:State
						itunibo.planner.moveUtils.rotateLeft90tuning(myself)
						var direction=
						itunibo.planner.moveUtils.getDirection(myself)
						if(direction.equals("downDir")){
									nextDir="forward"
								}else{
									nextDir="goAhead"
								}
						itunibo.planner.moveUtils.showCurrentRobotState(  )
						println("NEXT DIR: ${nextDir.toUpperCase()}")
					}
					 transition(edgeName="t037",targetState="snakeForwardStep",cond=whenDispatchGuarded("moveCompleted",{nextDir.equals("forward")}))
					transition(edgeName="t038",targetState="snakeGoAhead",cond=whenDispatchGuarded("moveCompleted",{nextDir.equals("goAhead")}))
				}	 
				state("snakeTurnRight") { //this:State
					action { //it:State
						itunibo.planner.moveUtils.rotateRight90tuning(myself)
						var direction=
						itunibo.planner.moveUtils.getDirection(myself)
						if(direction.equals("downDir")){
									nextDir="forward"
								}else{
									nextDir="goAhead"
								}
						itunibo.planner.moveUtils.showCurrentRobotState(  )
						println("NEXT DIR: ${nextDir.toUpperCase()}")
					}
					 transition(edgeName="t039",targetState="snakeForwardStep",cond=whenDispatchGuarded("moveCompleted",{nextDir.equals("forward")}))
					transition(edgeName="t040",targetState="snakeGoAhead",cond=whenDispatchGuarded("moveCompleted",{nextDir.equals("goAhead")}))
				}	 
				state("snakeGoAhead") { //this:State
					action { //it:State
						itunibo.planner.moveUtils.moveAhead(myself)
						var positionX=
						itunibo.planner.moveUtils.getPosX(myself)
						var positionY=
						itunibo.planner.moveUtils.getPosY(myself)
						var dimX=
						itunibo.planner.moveUtils.getMapDimX(  )
						var dimY=
						itunibo.planner.moveUtils.getMapDimY(  )
						nextDir="straight"
						if(positionX == 0){
										if(positionY == dimY - 3)
											nextDir="completed"
										else
											nextDir="left"
								}else{
									if(positionX == dimX - 2){
										if(positionY == dimY -3 )
											nextDir="completed"
										else
											nextDir="right"
									}
								}
						itunibo.planner.moveUtils.showCurrentRobotState(  )
						println("NEXT DIR: ${nextDir.toUpperCase()}")
					}
					 transition(edgeName="t041",targetState="endExploration",cond=whenDispatchGuarded("moveCompleted",{nextDir.equals("completed")}))
					transition(edgeName="t042",targetState="snakeTurnLeft",cond=whenDispatchGuarded("moveCompleted",{nextDir.equals("left")}))
					transition(edgeName="t043",targetState="snakeTurnRight",cond=whenDispatchGuarded("moveCompleted",{nextDir.equals("right")}))
					transition(edgeName="t044",targetState="snakeGoAhead",cond=whenDispatchGuarded("moveCompleted",{nextDir.equals("straight")}))
					transition(edgeName="t045",targetState="findTable",cond=whenDispatch("moveFailed"))
				}	 
				state("findTable") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						itunibo.planner.moveUtils.doPlannedMove(myself ,"s" )
						itunibo.planner.moveUtils.backToCompensate(myself)
						startX=
						itunibo.planner.moveUtils.getPosX(myself)
						startY=
						itunibo.planner.moveUtils.getPosY(myself)
						findTableInitDir=
						itunibo.planner.moveUtils.getDirection(myself)
						if(findTableInitDir.equals("leftDir")){
									startX--
								}
						if(findTableInitDir.equals("rightDir")){
									startX++
								}
						itunibo.planner.moveUtils.showCurrentRobotState(  )
					}
					 transition(edgeName="t046",targetState="goSouthFT",cond=whenDispatch("moveCompleted"))
				}	 
				state("goSouthFT") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						var temp=
						itunibo.planner.moveUtils.getDirection(myself)
						var positionX=
						itunibo.planner.moveUtils.getPosX(myself)
						var positionY=
						itunibo.planner.moveUtils.getPosY(myself)
						itunibo.planner.moveUtils.setObstacleOnCurrentDirection(myself)
						if(angle){
									lengthX++;
								}else{
									lengthY++
								}
								
								if(findTableInitDir.equals("leftDir")){
						itunibo.planner.moveUtils.rotateLeft90tuning(myself)
						}
									if(findTableInitDir.equals("rightDir")){
						itunibo.planner.moveUtils.rotateRight90tuning(myself)
						}
						itunibo.planner.moveUtils.showCurrentRobotState(  )
					}
					 transition(edgeName="t047",targetState="forwardStepFT",cond=whenDispatch("moveCompleted"))
				}	 
				state("forwardStepFT") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						itunibo.planner.moveUtils.moveAhead(myself)
						itunibo.planner.moveUtils.showCurrentRobotState(  )
					}
					 transition(edgeName="t048",targetState="goIntern",cond=whenDispatch("moveCompleted"))
				}	 
				state("goIntern") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if(findTableInitDir.equals("leftDir")){
						itunibo.planner.moveUtils.rotateRight90tuning(myself)
						}
									if(findTableInitDir.equals("rightDir")){
						itunibo.planner.moveUtils.rotateLeft90tuning(myself)
						}
						itunibo.planner.moveUtils.showCurrentRobotState(  )
					}
					 transition(edgeName="t049",targetState="checkTable",cond=whenDispatch("moveCompleted"))
				}	 
				state("checkTable") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						forward("movementCmd", "movementCmd(w)" ,"movementhandler" ) 
					}
					 transition(edgeName="t050",targetState="confirmForwardStepFT",cond=whenDispatch("moveCompleted"))
					transition(edgeName="t051",targetState="backtrackingFT",cond=whenDispatch("moveFailed"))
				}	 
				state("backtrackingFT") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						itunibo.planner.moveUtils.backToCompensate(myself)
					}
					 transition(edgeName="t052",targetState="goSouthFT",cond=whenDispatch("moveCompleted"))
				}	 
				state("confirmForwardStepFT") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						itunibo.planner.moveUtils.doPlannedMove(myself ,"w" )
					}
					 transition( edgeName="goto",targetState="angle", cond=doswitch() )
				}	 
				state("angle") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if(angle){
									finito=true
						endX=
						itunibo.planner.moveUtils.getPosX(myself)
						endY=
						itunibo.planner.moveUtils.getPosY(myself)
						if(findTableInitDir.equals("leftDir")){
										endX++
									}
						if(findTableInitDir.equals("rightDir")){
										endX--
									}
						
								}else{
									angle=true
								}
					}
					 transition( edgeName="goto",targetState="endExploration", cond=doswitchGuarded({finito}) )
					transition( edgeName="goto",targetState="goIntern", cond=doswitchGuarded({! finito}) )
				}	 
				state("endExploration") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						println("END EXPLORATION")
						println("START_X=$startX")
						println("START_Y=$startY")
						println("END_X=$endX")
						println("END_Y=$endY")
						println("LENGTH_X=$lengthX")
						println("LENGTH_Y=$lengthY")
						itunibo.planner.moveUtils.setTable( startX, startY, endX, endY, lengthX, lengthY  )
						var PosX=startX
								  var PosY=startY
								  if(findTableInitDir.equals("leftDir")){
									PosX++
								  }
								  if(findTableInitDir.equals("rightDir")){
									PosX--
								  }
						forward("setLocation", "setLocation(table,$PosX,$PosY)" ,"planner" ) 
						itunibo.planner.moveUtils.showCurrentRobotState(  )
						itunibo.planner.moveUtils.saveMap(myself ,"mapRoom" )
					}
					 transition( edgeName="goto",targetState="goHome", cond=doswitch() )
				}	 
				state("goHome") { //this:State
					action { //it:State
						forward("goto", "goto(rh)" ,"planner" ) 
					}
					 transition(edgeName="t053",targetState="waitCmd",cond=whenDispatch("planningCompleted"))
				}	 
			}
		}
}
