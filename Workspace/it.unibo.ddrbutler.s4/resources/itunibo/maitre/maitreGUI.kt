package itunibo.maitre

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener
import java.awt.event.ActionEvent
import java.io.File
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import it.unibo.kactor.ActorBasicFsm
import it.unibo.kactor.ActorBasic
import it.unibo.kactor.sysUtil

object maitreGUI {
	
	private lateinit var frame : JFrame
	private lateinit var panelButton : JPanel
	private lateinit var panelTextArea : JPanel
	
	private lateinit var prepareButton : JButton
	private lateinit var addFoodButton : JButton
	private lateinit var clearButton : JButton
	
	private lateinit var stopButton : JButton
	private lateinit var reactivateButton : JButton
	
	private lateinit var textArea : JTextArea
	
	private lateinit var maitreActor : ActorBasic
	
	public fun init(a : ActorBasic) {
		maitreActor = a
		
		frame = JFrame("Maitre GUI")
		frame.setSize(600, 400)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		
		frame.setLayout(BorderLayout())
		frame.getContentPane().setBackground(Color.WHITE)
		
		panelButton = JPanel()
		panelTextArea = JPanel()
		
		prepareButton = JButton("Prepare").apply {
			addActionListener(ButtonClickListener())
		}
				
		addFoodButton = JButton("Add food").apply {
			addActionListener(ButtonClickListener())
		}
		
		clearButton = JButton("Clear").apply {
			addActionListener(ButtonClickListener())
		}
		
		stopButton = JButton("Stop").apply {
			addActionListener(ButtonClickListener())
		}
		
		reactivateButton = JButton("Reactivate").apply {
			addActionListener(ButtonClickListener())
		}
		
		textArea = JTextArea(20, 50)
		textArea.setEditable(false)
		
		panelButton.add(prepareButton)
		panelButton.add(addFoodButton)
		panelButton.add(clearButton)
		panelButton.add(stopButton)
		panelButton.add(reactivateButton)
		panelTextArea.add(textArea)
		
		frame.add(panelButton, BorderLayout.NORTH)
		frame.add(panelTextArea, BorderLayout.SOUTH)
		
		frame.setVisible(true)
	}
	
	public fun enableOnlyPrepare() {
		prepareButton.setEnabled(true);
		addFoodButton.setEnabled(false);
		clearButton.setEnabled(false);
		stopButton.setEnabled(false);
		reactivateButton.setEnabled(false);
	}
	
	public fun enableOnlySR() {
		prepareButton.setEnabled(false);
		addFoodButton.setEnabled(false);
		clearButton.setEnabled(false);
		stopButton.setEnabled(true);
		reactivateButton.setEnabled(true);
	}
	
	public fun enableOnlyAC() {
		prepareButton.setEnabled(false);
		addFoodButton.setEnabled(true);
		clearButton.setEnabled(true);
		stopButton.setEnabled(false);
		reactivateButton.setEnabled(false);
	}
	
	public fun readFromFile(){
		var actor = sysUtil.getActor("maitremodel")
		actor!!.solve("findall(content(X, Y, Z, W), content(X, Y, Z, W), L)")
		var lineRead : String = actor.getCurSol("L").toString()
		var lines : List<String> = lineRead.replace("[", "").replace("]", "").split("),")
				
		val stringBuilder = StringBuilder()
		lines.forEach { line -> stringBuilder.append(line + ')' + '\n') }

		var new = stringBuilder.toString()
		textArea.setText(new)
	}
	
	
	private class ButtonClickListener : ActionListener {
		override fun actionPerformed(e : ActionEvent) {
			when(e.actionCommand) {
				"Prepare" -> GlobalScope.launch {
					maitreActor.forward("prepareSended", "prepareSended", maitreActor)
				}
				"Add food" -> GlobalScope.launch {
					maitreActor.forward("addFoodSended", "addFoodSended", maitreActor)
				}
				"Clear" -> GlobalScope.launch {
					maitreActor.forward("clearSended", "clearSended", maitreActor)
				}
				"Stop" -> GlobalScope.launch {
					maitreActor.forward("stop", "stop", "resourcemodelbutler")
				}
				"Reactivate" -> GlobalScope.launch {
					maitreActor.forward("reactivate", "reactivate", "resourcemodelbutler")
				}
			else -> GlobalScope.launch {
					println("[MAITRE]: unexpected command received")
				}	
			}
		}	
	}
	
}


