/* Generated by AN DISI Unibo */ 
package it.unibo.obstacledetector

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Obstacledetector ( name: String, scope: CoroutineScope ) : ActorBasicFsm( name, scope){
 	
	override fun getInitialState() : String{
		return "s0"
	}
		
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("[OBSTACLE DETECTOR]: Started...")
					}
					 transition( edgeName="goto",targetState="waitSonarData", cond=doswitch() )
				}	 
				state("waitSonarData") { //this:State
					action { //it:State
						println("[OBSTACLE DETECTOR]: Sono in waitSonarData")
					}
					 transition(edgeName="t01",targetState="handleSonarData",cond=whenEvent("sonarRobot"))
				}	 
				state("handleSonarData") { //this:State
					action { //it:State
						println("[OBSTACLE DETECTOR]: Sono in handleSonarData")
					}
					 transition( edgeName="goto",targetState="waitSonarData", cond=doswitch() )
				}	 
			}
		}
}
