package composer;

import javax.swing.JOptionPane;

import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "Composer.perspective"; //$NON-NLS-1$

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
	
	
	public void preStartup(){
		
	}
	
	public boolean preShutdown(){
		System.out.println("Shut shut shut!");
		return true;
		
	}
	
	public void initialize(IWorkbenchConfigurer configurer){
		super.initialize(configurer);
		configurer.setSaveAndRestore(true);
	}
	
}
