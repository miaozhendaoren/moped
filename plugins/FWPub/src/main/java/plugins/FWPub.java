package tests;

import java.io.IOException;
import com.sun.squawk.VM;
import com.sun.squawk.io.mailboxes.Channel;
import com.sun.squawk.io.mailboxes.Envelope;
import com.sun.squawk.io.mailboxes.ByteArrayEnvelope;
import sics.port.PluginPPort;
import sics.port.PluginRPort;
import sics.plugin.PlugInComponent;

public class FWPub extends PlugInComponent {
	private PluginPPort fs;
	private PluginRPort ff;
	
	public FWPub() {}
	
	public FWPub(String[] args) {
		super(args);
	}
	
	public static void main(String[] args) {
		VM.println("FWPub.main()\r\n");
		FWPub publish = new FWPub(args);
		publish.init();
		publish.doFunction();
		VM.println("FWPub-main done\r\n");
	}

	@Override
	public void init() {
		// Initiate PluginPPort
		fs = new PluginPPort(this, "fs");
		ff = new PluginRPort(this, "ff");
	}
	
	public void run() {
		VM.println("FWPub.main()\r\n");
		init();
		doFunction();
		VM.println("FWPub-main done\r\n");
	}

	public void doFunction() {
		String data;
		for (int i = 0; i < 1000; i++) {
//			VM.println("[FWPub is running]");
			VM.println("[FWPub is running]");
			
			int frontWheelSpeedData = ff.readInt();
			data = "fw|" + String.valueOf(frontWheelSpeedData);
			fs.write(data);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
//				VM.println("Interrupted.\r\n");
			}

		}
	}
}
