package itunibo.fridge

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import it.unibo.kactor.ActorBasicFsm
import it.unibo.kactor.ActorBasic

object fridgeGUI {
	private lateinit var frame : JFrame
	private lateinit var updateButton : JButton
	
	private lateinit var actor : ActorBasic
	 
	public fun init(a : ActorBasic) {
		actor = a
		
		frame = JFrame("Fridge GUI")
		frame.setSize(100, 150)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		frame.getContentPane().setBackground(Color.WHITE)
		
		updateButton = JButton("Update").apply {
			addActionListener(ButtonClickListener())
		}
		
		frame.add(updateButton)
		frame.setVisible(true)
	}
	
	private class ButtonClickListener : ActionListener {
		override fun actionPerformed(e : ActionEvent) {
			when(e.actionCommand) {
				"Update" -> GlobalScope.launch {
					actor.emit("updateContent", "updateContent(fridge, brasciole, 10)")
				}
			else -> GlobalScope.launch {
					println("[MAITRE]: unexpected command received")
				}	
			}
		}	
	}
	
}


