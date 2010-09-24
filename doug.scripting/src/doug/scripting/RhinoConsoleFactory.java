package doug.scripting;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;

public class RhinoConsoleFactory implements IConsoleFactory {

	@Override
	public void openConsole() {
        IConsoleManager manager = ConsolePlugin.getDefault().getConsoleManager();
        IConsole[] consoles = manager.getConsoles();
        for (IConsole console : consoles) {
        	if (console.getName().equals("Rhino")) {
        		IWorkbenchWindow window = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow();
        		if (window != null) {
        			IWorkbenchPage page = window.getActivePage();
        			if (page != null) {
        				IViewPart consoleView = page.findView(IConsoleConstants.ID_CONSOLE_VIEW);
        				if (consoleView instanceof IConsoleView) {
        					((IConsoleView)consoleView).display(console);
        				}
        			}
        		}

        	}
       		return;
        }
        
        manager.addConsoles(new IConsole[] { new RhinoConsole() });
	}
	
}
