package doug.scripting;

import java.io.IOException;
import java.io.PrintStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.console.IOConsole;
import org.mozilla.javascript.tools.shell.Main;

public class RhinoConsole extends IOConsole {

	Thread thread;
	
	public RhinoConsole() {
		super("Rhino", null);
		
		thread = new Thread() {
			public void run() {
				try {
					RhinoConsole.this.run();
				} catch (IOException e) {
					Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e));
				}
			}
		};
		thread.start();
	}
	
	public void run() throws IOException {
		ClassLoader classLoader = new BundleProxyClassLoader(Activator.getDefault().getBundle());
		Main.shellContextFactory.initApplicationClassLoader(classLoader);
		Main.setIn(getInputStream());
		Main.setOut(new PrintStream(newOutputStream()));
		Main.setErr(new PrintStream(newOutputStream()));
		Main.main(new String[0]);
	}
	
	
	public static int test() {
		return 5;
	}
	
}
