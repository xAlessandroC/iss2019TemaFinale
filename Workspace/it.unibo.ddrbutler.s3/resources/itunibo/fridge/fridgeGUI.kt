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
	
	private lateinit var buttonPanel : JPanel
	private lateinit var textFieldPanel : JPanel
			
	private lateinit var foodCodeField : JTextField
	private lateinit var qntField : JTextField
	private lateinit var updateButton : JButton
	
	private lateinit var actor : ActorBasic
	 
	public fun init(a : ActorBasic) {
		actor = a
		
		frame = JFrame("Fridge GUI")
		frame.setSize(300, 150)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		frame.getContentPane().setBackground(Color.WHITE)
		frame.setLayout(BorderLayout())
		
		buttonPanel = JPanel()
		textFieldPanel = JPanel()
		
		updateButton = JButton("Update").apply {
			addActionListener(ButtonClickListener())
		}
		
		foodCodeField = JTextField(15)
		qntField = JTextField(4)
		
		buttonPanel.add(updateButton)
		textFieldPanel.add(foodCodeField)
		textFieldPanel.add(qntField)
		
		frame.add(buttonPanel, BorderLayout.NORTH)
		frame.add(textFieldPanel, BorderLayout.CENTER)
		frame.setVisible(true)
	}
	
	private class ButtonClickListener : ActionListener {
		override fun actionPerformed(e : ActionEvent) {
			when(e.actionCommand) {
				"Update" -> GlobalScope.launch {
					actor.emit("updateContent", "updateContent(fridge, cibo, ${foodCodeField.getText()}, ${qntField.getText()})")
				}
			else -> GlobalScope.launch {
					println("[MAITRE]: unexpected command received")
				}	
			}
		}	
	}
	
}


