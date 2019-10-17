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
			delay(5000)		//give the time to start
			resource = sysUtil.getActor("resourcemodel")
		    mind = sysUtil.getActor("robotmind")	
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
 		stoprobot(100)
 	}

	fun stoprobot(time:Long) {
		println(" %%%%%%% TestMovementRobot  stoprobot usung robot %%%")
		moveRobot( mind!!, "h", time)			
		solveCheckGoal( resource!!,  "state( robot, state(stopped) )" )
	}
	fun moveForward( time:Long ) {
		println(" %%%%%%% TestMovementRobot  moveForward using resource%%%")
		moveRobot( mind!!, "w", time)			
		solveCheckGoal( resource!!,  "state( robot, state(movingForward) )" )
 	}
	
	fun moveBackward( time:Long ) {
		println(" %%%%%%% TestMovementRobot  moveBackward %%%")
		moveRobot( mind!!, "s", time)			
		solveCheckGoal( resource!!,  "state( robot, state(movingBackward) )" )
 	}
	 
	fun rotateLeft( time:Long ) {
		println(" %%%%%%% TestMovementRobot  rotateLeft %%%")
		moveRobot( mind!!, "a", time)			
		solveCheckGoal( resource!!,  "state( robot, state(rotateLeft) )" )
	}
	 
	fun rotateRight( time:Long ) {
		println(" %%%%%%% TestMovementRobot  rotateRight %%%")
		moveRobot( mind!!, "d", time)			
		solveCheckGoal( resource!!,  "state( robot, state(rotateRight) )" )
	}	

//----------------------------------------
	
	fun moveRobot( actor : ActorBasic, move : String, time : Long ){
		actor.scope.launch{
			println("--- moveRobot mindCmd($move)")
  			MsgUtil.sendMsg("mindCmd","mindCmd($move)",actor)
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

