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

class TestPositionRobot {
 	var resource : ActorBasic? = null
	var actor : ActorBasic? = null
	
	@Before
	fun systemSetUp() {
  	 		GlobalScope.launch{
 			    println(" %%%%%%% TestPositionRobot starts")
				it.unibo.ctxButler.main()
 			}
			delay(8000)		//give the time to start
			resource = sysUtil.getActor("resourcemodelbutler")
		    actor = sysUtil.getActor("calibration")
		    println(" %%%%%%% TestLogicButler getActors resource=${resource}")
 	}
 
	@After
	fun terminate() {
		println(" %%%%%%% TestPositionRobot terminate ")
	}
 
	/*Per avere un test più veloce testiamo solo se il cambiamento di posizione
 	 *avviene in maniera coerente col modelo
 	*/
	@Test
	fun positionTest() {
		println(" %%%%%%% TestPositionRobot  logic test ")
		delay(100)
		checkInit(0)
		checkRH(1000)
 	}
	
	fun checkInit(time:Long ){
		solveCheckGoal( resource!!,  "position( robot,0,0 )" )
	}
	
	fun checkRH(time:Long ){
		GlobalScope.launch{
			itunibo.planner.plannerUtil.initAI()
			itunibo.planner.moveUtils.doPlannedMove(actor!!,"w")
		}
		delay(time)
		solveCheckGoal( resource!!,  "position( robot, 0,1 )" )
	}

//----------------------------------------

 	
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

