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
 			    println(" %%%%%%% TestLogicButler starts robot mind ")
				it.unibo.ctxPantry.main()
 			}
			delay(5000)		//give the time to start
			resource = sysUtil.getActor("resourcemodelpantry")
		    pantry = sysUtil.getActor("pantry")
		    println("%%%%%%% TestLogicPantry getActors resource=${resource}")
 	}

	@After
	fun terminate() {
		println("%%%%%%% TestLogicPantry terminate ")
	}
	
	/*
 	 *NOTA: Bisogna rallentare l'esecuzione della pantry per testare correttamente
 	*/
	@Test
	fun pantryTest() {
		println("%%%%%%% TestLogicPantry logic test")
		delay(100)
		checkIdle(0)
		checkPut(500)
		checkTake(500)
 	}
	
	fun checkIdle(time:Long ){
		solveCheckGoal( resource!!,  "state( pantry, idle, null)" )
	}
	
	fun checkPut(time:Long ){
		GlobalScope.launch{
			pantry!!.forward("putDishPantry","putDishPantry(10)","pantry")
			
		}
		delay(200)
		solveCheckGoal( resource!!,  "state( pantry, put, 10 )" )
		delay(time)
		checkIdle(0)
		
	}
	
	fun checkTake(time:Long ){
		GlobalScope.launch{
			pantry!!.forward("takeDishPantry","takeDishPantry(10)","pantry")
		}
		delay(500)
		solveCheckGoal( resource!!,  "state( pantry, take, 10 )" )
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

