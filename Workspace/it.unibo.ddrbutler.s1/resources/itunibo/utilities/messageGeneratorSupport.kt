package itunibo.utilities

import it.unibo.bls.interfaces.IObserver
import it.unibo.kactor.ActorBasic
import java.util.Observable
import javax.swing.JFrame
import java.awt.event.ActionListener

object messageGeneratorSupport {
	
	fun create( actor: ActorBasic, msgIdent: String ){
			
		var frame = JFrame(msgIdent)
		var container = frame.getContentPane()
		container.add(guiSupport(actor))
		frame.pack()
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		frame.isVisible();
		frame.setVisible(true)
			
	}
}

