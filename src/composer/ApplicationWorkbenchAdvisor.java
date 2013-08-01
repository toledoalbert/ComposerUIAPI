package composer;

import javax.swing.JOptionPane;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.tekkotsu.gef.StateMachineEditor;
import org.tekkotsu.gef.StateMachineEditorInput;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	//ID for the perspective
	private static final String PERSPECTIVE_ID = "Composer.perspective"; //$NON-NLS-1$

	//create the workbench window advisor object
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

    //method to allow access to the id of the perspective
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
	
	//pre startup method
	public void preStartup(){
		
	}
	
	//post startup method.
	@Override
	public void postStartup(){
			
		//Get the page object
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		//Try opening the editpr with the editor input.
		try {
			page.openEditor(new StateMachineEditorInput("StateMachine"), "editor.statemachineeditor", false);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//pre shut down method.
	public boolean preShutdown(){
		
		//Just print shut shut shut. and return true.
		System.out.println("Shut shut shut!");
		return true;
		
	}
	
	//initialize the workbench
	public void initialize(IWorkbenchConfigurer configurer){
		
		//call the super method to initialize.
		super.initialize(configurer);
		configurer.setSaveAndRestore(false);
		
	}
	
	
}
