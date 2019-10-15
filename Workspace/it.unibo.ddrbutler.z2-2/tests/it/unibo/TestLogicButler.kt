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

class TestLogicButler {
 	var resource : ActorBasic? = null
	var mind : ActorBasic? = null
	
	@Before
	fun systemSetUp() {
  	 		GlobalScope.launch{
 			    println(" %%%%%%% TestLogicButler starts ")
				it.unibo.ctxButler.main()
 			}
			delay(5000)		//give the time to start
			resource = sysUtil.getActor("resourcemodelbutler")
		    mind = sysUtil.getActor("butler")
		    println(" %%%%%%% TestLogicButler getActors resource=${resource}")
 	}
 
	@After
	fun terminate() {
		println(" %%%%%%% TestLogicButler terminate ")
	}
 
	@Test
	fun butlerTest() {
		println(" %%%%%%% TestLogicButler  logic test ")
		delay(100)
		checkPrepare(1000)
		checkAddFood(1000)
		checkClear(1000)
 	}
	
	fun checkPrepare(time:Long ){
		GlobalScope.launch{
			mind!!.forward("prepare","prepare","butler")
		}
		solveCheckGoal( resource!!,  "state( butler, prepare )" )
	}
	
	fun checkAddFood(time:Long ){
		GlobalScope.launch{
			mind!!.forward("addFood","addFood(cibo1,10)","butler")
		}
		solveCheckGoal( resource!!,  "state( butler, addFood )" )
	}
	
	fun checkClear(time:Long ){
		GlobalScope.launch{
			mind!!.forward("clear","clear","butler")
		}
		solveCheckGoal( resource!!,  "state( butler, clear )" )
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

