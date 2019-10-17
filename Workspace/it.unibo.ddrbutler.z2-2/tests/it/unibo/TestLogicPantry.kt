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

class TestLogicPantry {
 	var resource : ActorBasic? = null
	var pantry : ActorBasic? = null
	
	@Before
	fun systemSetUp() {
  	 		GlobalScope.launch{
 			    println(" %%%%%%% TestLogicPantry starts robot mind ")
				it.unibo.ctxButler.main()
 			}
			delay(5000)		//give the time to start
			resource = sysUtil.getActor("resourcemodelbutler")
		    pantry = sysUtil.getActor("pantry")
		    println(" %%%%%%% TestLogicPantry getActors resource=${resource}")
 	}
 
	@After
	fun terminate() {
		println(" %%%%%%% TestLogicPantry terminate ")
	}
 
	@Test
	fun pantryTest() {
		println(" %%%%%%% TestLogicPantry  logic test ")
		delay(100)
		checkIdle(0)
		checkPut(1000)
		checkTake(1000)
 	}
	
	fun checkIdle(time:Long ){
		solveCheckGoal( resource!!,  "state( pantry, idle)" )
	}
	
	fun checkPut(time:Long ){
		GlobalScope.launch{
			pantry!!.forward("pantryPutDish","putDish(10)","pantry")
		}
		solveCheckGoal( resource!!,  "state( pantry, put )" )
		delay(time)
		checkIdle(0)
	}
	
	fun checkTake(time:Long ){
		GlobalScope.launch{
			pantry!!.forward("pantryTakeDish","takeDish(10)","pantry")
		}
		solveCheckGoal( resource!!,  "state( pantry, take )" )
		delay(time)
		checkIdle(0)
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

