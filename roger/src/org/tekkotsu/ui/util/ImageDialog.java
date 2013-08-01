/*
 * Created on Apr 22, 2005
 */
package org.tekkotsu.ui.util;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author asangpet
 */
public class ImageDialog extends Window {
    Image image;
    String title=null;
    
    public ImageDialog(Shell parent, Image image) {
        super(parent);
        this.image = image;
        this.setShellStyle( SWT.SHELL_TRIM );
    }

    @Override
	protected void configureShell(Shell shell)
    {
        super.configureShell(shell);        
        if (title != null)
            shell.setText(title);
    }	

    public void setTitle(String title)
    {
        this.title = title;
    }

    
    @Override
	protected Control createContents(Composite parent)
    {
        ScrolledComposite composite = new ScrolledComposite(parent,SWT.H_SCROLL | SWT.V_SCROLL);
        //composite.setSize(400,300);
        //ScrolledComposite composite = (Composite) super.createDialogArea(parent);        
        Canvas canvas = new Canvas(composite,SWT.FILL);
        ImageData data = image.getImageData();
        canvas.setSize(data.width,data.height);
        canvas.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                e.gc.drawImage(image,0,0);
            }
        });        
        
        composite.setContent(canvas);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        parent.setLayout(layout);
        GridData gd = new GridData();
        gd.horizontalAlignment = SWT.FILL;
        gd.verticalAlignment = SWT.FILL;
        gd.grabExcessVerticalSpace = true;
        gd.grabExcessHorizontalSpace = true;
        composite.setLayoutData(gd);
        parent.setSize(600,600);
        return composite;
    }
  }