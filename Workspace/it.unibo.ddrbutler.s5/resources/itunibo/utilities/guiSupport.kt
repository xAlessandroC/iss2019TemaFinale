package itunibo.utilities

import javax.swing.JPanel
import java.awt.event.ActionListener
import it.unibo.kactor.ActorBasic
import java.awt.event.ActionEvent
import javax.swing.JTextField
import javax.swing.JButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class guiSupport(actor: ActorBasic) : JPanel(),ActionListener{
	
	lateinit var messageField : JTextField
	lateinit var receiverField : JTextField
	lateinit var sendButton : JButton
	
	var actor=actor
	
	init{
		messageField = JTextField(30)
		receiverField = JTextField(30)
		sendButton = JButton("SEND")
		
		sendButton.addActionListener(this)
		
		this.add(messageField)
		this.add(receiverField)
		this.add(sendButton)
		
		this.setSize(300,300)
	}
	
	override fun actionPerformed(e:ActionEvent){
		var msgId=messageField.getText().split(":")[0]
		var msgContent=messageField.getText().split(":")[1]
		var receiver=receiverField.getText()
		
		GlobalScope.launch{
			actor.forward(msgId,msgContent,receiver)
		}
		
	}
}

