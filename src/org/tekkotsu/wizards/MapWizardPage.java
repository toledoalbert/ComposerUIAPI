package org.tekkotsu.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.tekkotsu.api.MapBuilderWizard;
import org.tekkotsu.api.MapBuilderWriter;

public class MapWizardPage extends WizardPage {
  private Text text1;
  private String code;
  private MapBuilderWizard wiz;
  private MapBuilderWriter writer;
  private Composite container;

  public MapWizardPage() {
    super("MapBuilder Requests Wizard");
    setTitle("MapBuilder Requests");
    setDescription("This wizard helps you generate the right code for MapBuilder Requests");
  }

  @Override
  public void createControl(Composite parent) {
    container = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    Label label1 = new Label(container, SWT.NONE);
    label1.setText("Put here a value");

    text1 = new Text(container, SWT.BORDER | SWT.SINGLE);
    text1.setText("");
    text1.addKeyListener(new KeyListener() {

      @Override
      public void keyPressed(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (!text1.getText().isEmpty()) {
          setPageComplete(true);

        }
      }

    });
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    text1.setLayoutData(gd);
    // Required to avoid an error in the system
    setControl(container);
    setPageComplete(false);

  }

  public String getText1() {
    return text1.getText();
  }
}

