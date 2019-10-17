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

class TestObstacleDetection {
 	var resource : ActorBasic? = null
	var obstacledetector : ActorBasic? = null
	
	@Before
	fun systemSetUp() {
  	 		GlobalScope.launch{
 			    println(" %%%%%%% TestObstacleDetection starts robot mind ")
				it.unibo.ctxButler.main()
 			}
			delay(5000)		//give the time to start
			resource = sysUtil.getActor("resourcemodel")
		    obstacledetector = sysUtil.getActor("obstacledetector")	
		    println(" %%%%%%% TestObstacleDetection getActors resource=${resource}")
 	}
 
	@After
	fun terminate() {
		println(" %%%%%%% TestObstacleDetection terminate ")
	}
 
	@Test
	fun moveTest() {
		println(" %%%%%%% TestObstacleDetection  obstacleTest ")
		generateSonarDataOverTarget(300)
		generateSonarDataBelowTarget(300)
 	}

	fun generateSonarDataBelowTarget( time:Long ) {
		println(" %%%%%%% TestObstacleDetection  belowTarget %%%")
		moveRobot( obstacledetector!!, "5", time)			
		solveCheckGoal( resource!!,  "state( sonar, 5, obstacle )" )
	}
	
	fun generateSonarDataOverTarget( time:Long ) {
		println(" %%%%%%% TestObstacleDetection  overTarget %%%")
		moveRobot( obstacledetector!!, "200", time)			
		solveCheckGoal( resource!!,  "state( sonar, 200, clear )" )
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

