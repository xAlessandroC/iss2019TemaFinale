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

class TestHandleObstacle {
 	var resource : ActorBasic? = null
	var obstacledetector : ActorBasic? = null
	
	@Before
	fun systemSetUp() {
  	 		GlobalScope.launch{
 			    println(" %%%%%%% TestHandleObstacle starts robot mind ")
				it.unibo.ctxButler.main()
 			}
			delay(5000)		//give the time to start
			resource = sysUtil.getActor("resourcemodel")
		    obstacledetector = sysUtil.getActor("obstacledetector")	
		    println(" %%%%%%% TestHandleObstacle getActors resource=${resource}")
 	}
 
	@After
	fun terminate() {
		println(" %%%%%%% TestHandleObstacle terminate ")
	}
 
	@Test
	fun moveTest() {
		println(" %%%%%%% TestHandleObstacle  obstacleTest ")
		generateSonarDataBelowTarget(300)
		checkHandling(150)
 	}

	fun generateSonarDataBelowTarget( time:Long ) {
		println(" %%%%%%% TestHandleObstacle  belowTarget %%%")
		moveRobot( obstacledetector!!, "5", time)			
		solveCheckGoal( resource!!,  "state( sonar, 5, obstacle )" )
	}

	fun checkHandling( time:Long ) {
		solveCheckGoal( resource!!,  "state( robot, state(rotateRight) )" )
		delay(time)
		solveCheckGoal( resource!!,  "state( robot, state(stopped) )" )
	}
//----------------------------------------
	
	fun moveRobot( actor : ActorBasic, value : String, time : Long ){
		actor.scope.launch{
			println("--- emit sonarData($value)")
  			MsgUtil.sendMsg("sonarRobot","sonar($value)",actor)
 		}
		delay(time)
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

