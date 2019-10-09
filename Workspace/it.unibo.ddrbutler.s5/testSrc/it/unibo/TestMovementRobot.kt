package it.unibo

import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.After
import org.junit.Before
import org.junit.Test
import alice.tuprolog.SolveInfo
import it.unibo.kactor.sysUtil
import it.unibo.kactor.ActorBasic
import it.unibo.kactor.MsgUtil
import kotlinx.coroutines.launch
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay

class TestMovementRobot {
 	var resource : ActorBasic? = null
	var mind : ActorBasic? = null
	
	@Before
	fun systemSetUp() {
  	 		GlobalScope.launch{
 			    println(" %%%%%%% TestMovementRobot starts robot mind ")
				it.unibo.ctxButler.main()
 			}
			delay(10000)		//give the time to start
			resource = sysUtil.getActor("resourcemodelbutler")
		    mind = sysUtil.getActor("movementhandler")	
		    println(" %%%%%%% TestMovementRobot getActors resource=${resource}")
 	}
 
	@After
	fun terminate() {
		println(" %%%%%%% TestMovementRobot terminate ")
	}
 
	@Test
	fun moveTest() {
		println(" %%%%%%% TestMovementRobot  moveTest ")
 		rotateRight(500)
 		rotateLeft(500)
 		moveForward(700)	
 		moveBackward(700)
 	}

	fun moveForward( time:Long ) {
		println(" %%%%%%% TestMovementRobot  moveForward using resource%%%")
		moveRobot( mind!!, "w", 200)			
		solveCheckGoal( resource!!,  "state( robot, state(movingForward) )" )
		delay(time)
		solveCheckGoal( resource!!,  "state( robot, state(stopped) )" )
 	}
	
	fun moveBackward( time:Long ) {
		println(" %%%%%%% TestMovementRobot  moveBackward %%%")
		simulateObstacle(mind!!,"",10)
		moveRobot( mind!!, "s", 50)			
		solveCheckGoal( resource!!,  "state( robot, state(movingBackward) )" )
		delay(700)
		solveCheckGoal( resource!!,  "state( robot, state(stopped) )" )
 	}
	 
	fun rotateLeft( time:Long ) {
		println(" %%%%%%% TestMovementRobot  rotateLeft %%%")
		moveRobot( mind!!, "a", 100)			
		solveCheckGoal( resource!!,  "state( robot, state(rotateLeft) )" )
		delay(time)
		solveCheckGoal( resource!!,  "state( robot, state(stopped) )" )
	}
	 
	fun rotateRight( time:Long ) {
		println(" %%%%%%% TestMovementRobot  rotateRight %%%")
		moveRobot( mind!!, "d", 100)			
		solveCheckGoal( resource!!,  "state( robot, state(rotateRight) )" )
		delay(time)
		solveCheckGoal( resource!!,  "state( robot, state(stopped) )" )
	}	

//----------------------------------------
	
	fun moveRobot( actor : ActorBasic, move : String, time : Long ){
		actor.scope.launch{
			println("--- moveRobot movementCmd($move)")
  			MsgUtil.sendMsg("movementCmd","movementCmd($move)",actor)
 		}
		delay(time) //give time to do the move
  	}
	
	fun simulateObstacle( actor : ActorBasic, move : String, time : Long ){
		actor.scope.launch{
  			MsgUtil.sendMsg("movementCmd","movementCmd(w)",actor)
 		}
		delay(300)
		actor.scope.launch{
			MsgUtil.sendMsg("obstacle","obstacle",actor)
 		}
		delay(time) //give time to do the move
  	}

 	
	fun solveCheckGoal( actor : ActorBasic, goal : String ){
		actor.solve( goal  )
		var result =  actor.resVar
		println(" %%%%%%%  actor={$actor.name} goal= $goal  result = $result")
		assertTrue("", result == "success" )
	}

	fun delay( time : Long ){
		Thread.sleep( time )
	}


}

