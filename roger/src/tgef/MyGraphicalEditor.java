package tgef;

import java.io.ByteArrayInputStream;
import org.eclipse.gef.dnd.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.tekkotsu.api.DefaultClassReader;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.SetupMachine;
import org.tekkotsu.api.TransitionClass;
import org.tekkotsu.api.TransitionInstance;

import creationFactory.*;

import part.tree.AppTreeEditPartFactory;
import tekkotsu.edit.parts.AppEditPartFactory;

public class MyGraphicalEditor extends GraphicalEditorWithFlyoutPalette {
	// String I.D used for plugin
	public static final String ID = "tutogef.mygraphicaleditor";

	// Hashmaps to store the nodeclasses and names.
	static final HashMap<String, NodeClass> nodesMap = new HashMap<String, NodeClass>();
	static final HashMap<String, TransitionClass> transitionsMap = new HashMap<String, TransitionClass>();

	// Graphical Editor container
	private Entreprise model;
	private KeyHandler keyHandler;
	private String nodeType;
	public String tester;

	private boolean editorSaving = false;

	// Nested class for outline and miniature view
	protected class OutlinePage extends ContentOutlinePage {
		private SashForm sash;

		// Needed variables for miniature view
		private ScrollableThumbnail thumbnail;
		private DisposeListener disposeListener;

		public OutlinePage() {
			super(new TreeViewer());
		}

		public void createControl(Composite parent) {
			sash = new SashForm(parent, SWT.VERTICAL);

			getViewer().createControl(sash);

			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new AppTreeEditPartFactory());
			getViewer().setContents(model);

			getSelectionSynchronizer().addViewer(getViewer());

			// Code for adding minature view to canvas
			Canvas canvas = new Canvas(sash, SWT.BORDER);
			LightweightSystem lws = new LightweightSystem(canvas);
			thumbnail = new ScrollableThumbnail(
					(Viewport) ((ScalableRootEditPart) getGraphicalViewer()
							.getRootEditPart()).getFigure());
			thumbnail.setSource(((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart())
					.getLayer(LayerConstants.PRINTABLE_LAYERS));
			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}

			};
			getGraphicalViewer().getControl().addDisposeListener(
					disposeListener);
		}

		public void init(IPageSite pageSite) {
			super.init(pageSite);

			IActionBars bars = getSite().getActionBars();
			bars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
					getActionRegistry().getAction(ActionFactory.UNDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.REDO.getId(),
					getActionRegistry().getAction(ActionFactory.REDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
					getActionRegistry().getAction(ActionFactory.DELETE.getId()));
			bars.updateActionBars();

			getViewer().setKeyHandler(keyHandler);

			// Necessary code to add context menu
			ContextMenuProvider provider = new AppContextMenuProvider(
					getViewer(), getActionRegistry());

		}

		public Control getControl() {
			return sash;
		}

		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());
			if (getGraphicalViewer().getControl() != null
					&& !getGraphicalViewer().getControl().isDisposed())
				getGraphicalViewer().getControl().removeDisposeListener(
						disposeListener);
			super.dispose();
		}

	}

	public String getNodeType() {
		return nodeType;
	}

	// returns container
	Entreprise getModel() {
		return model;
	}

	public MyGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	public Entreprise CreateEntreprise() throws IOException {

		Entreprise psyEntreprise = new Entreprise();
		psyEntreprise.setName("My First Behavior");
		return psyEntreprise;

	}

	protected void configureGraphicalViewer() {

		// Adds Zoom support
		double[] zoomLevels;
		ArrayList<String> zoomContributions;

		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new AppEditPartFactory());

		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));
		// list of possible zooms
		zoomLevels = new double[] { 0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0,
				4.0, 5.0, 10.0, 20.0 };
		manager.setZoomLevels(zoomLevels);
		// zoom predefinitions
		zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);

		// Keyboard handler that allows for view manipulation with '+' and '-'
		KeyHandler keyHandler = new KeyHandler();

		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));

		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE),
				MouseWheelZoomHandler.SINGLETON);

		viewer.setKeyHandler(keyHandler);

		// Keyhander as it pertains to the outline
		keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE),
				MouseWheelZoomHandler.SINGLETON);

		viewer.setKeyHandler(keyHandler);

		// adds context menu to right click
		ContextMenuProvider provider = new AppContextMenuProvider(viewer,
				getActionRegistry());
		viewer.setContextMenu(provider);

	}

	// Nested class called using this adapter method
	public Object getAdapter(Class type) {
		if (type == ZoomManager.class)
			return ((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart()).getZoomManager();
		if (type == IContentOutlinePage.class) {
			return new OutlinePage();
		}
		return super.getAdapter(type);
	}

	@Override
	protected void initializeGraphicalViewer() {

		GraphicalViewer viewer = getGraphicalViewer();

		try {
			model = CreateEntreprise();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		viewer.setContents(model);

		TemplateTransferDropTargetListener dropListener = new TemplateTransferDropTargetListener(
				viewer);
		viewer.addDropTargetListener(dropListener);
		// dropListener.getTemplate();

	}

	// For drag and drop
	@Override
	protected PaletteViewerProvider createPaletteViewerProvider() {
		return new PaletteViewerProvider(getEditDomain()) {
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer.addDragSourceListener(new TemplateTransferDragSourceListener(
						viewer));
			}
		};
	}

	private void createOutputStream(OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(getModel());
		oos.close();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		editorSaving = true;
		final File file = ((MyEditorInput) getEditorInput()).getFile();
		if (file != null) {
			WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
				public void execute(final IProgressMonitor monitor) {
					try {
						ByteArrayOutputStream outStream = new ByteArrayOutputStream();
						ObjectOutputStream out = new ObjectOutputStream(
								outStream);
						out.writeObject(getModel());
						FileOutputStream fos = new FileOutputStream(file);
						fos.write(outStream.toByteArray());
						fos.close();
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			try {
				new ProgressMonitorDialog(getSite().getWorkbenchWindow()
						.getShell()).run(false, true, op);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		editorSaving = false;
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);

		File file = ((MyEditorInput) input).getFile();
		if (file != null && file.exists()) {
			try {
				FileInputStream is = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(is);
				setModel((Entreprise) ois.readObject());
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (!editorSaving) {
				if (getGraphicalViewer() != null) {
					getGraphicalViewer().setContents(getModel());
				}
			}
		}
	}

	private void setModel(Entreprise readObject) {
		this.model = readObject;
	}

	public void doSaveAs() {
		// Show a SaveAs dialog
		Shell shell = getSite().getWorkbenchWindow().getShell();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		dialog.setOriginalFile(((IFileEditorInput) getEditorInput()).getFile());
		dialog.open();

		IPath path = dialog.getResult();
		if (path != null) {
			// try to save the editor's contents under a different file name
			final IFile file = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(path);
			try {
				new ProgressMonitorDialog(shell).run(false, // don't fork
						false, // not cancelable
						new WorkspaceModifyOperation() { // run this operation
					public void execute(final IProgressMonitor monitor) {
						try {
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							createOutputStream(out);
							file.create(
									new ByteArrayInputStream(out
											.toByteArray()), // contents
											true, // keep saving, even if IFile
											// is out of sync with the
											// Workspace
											monitor); // progress monitor
						} catch (CoreException ce) {
							ce.printStackTrace();
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					}
				});
				// set input to the new file
				setInput(new FileEditorInput(file));
				getCommandStack().markSaveLocation();
			} catch (InterruptedException ie) {
				// should not happen, since the monitor dialog is not cancelable
				ie.printStackTrace();
			} catch (InvocationTargetException ite) {
				ite.printStackTrace();
			}
		}
	}

	/*
	 * public void createActions() { super.createActions();
	 * 
	 * ActionRegistry registry = getActionRegistry(); IAction action = new
	 * RenameAction(this); registry.registerAction(action);
	 * getSelectionActions().add(action.getId()); }
	 */

	public static HashMap<String, NodeClass> getHashMap() {

		return nodesMap;

	}

	public static HashMap<String, TransitionClass> getTransitionsMap() {

		return transitionsMap;

	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		// TODO Auto-generated method stub
		PaletteRoot root = new PaletteRoot();

		PaletteGroup manipGroup = new PaletteGroup("Manipulation objets");
		root.add(manipGroup);

		SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
		manipGroup.add(selectionToolEntry);
		manipGroup.add(new MarqueeToolEntry());

		root.setDefaultEntry(selectionToolEntry);

		// Ads separator

		PaletteSeparator sep2 = new PaletteSeparator();
		root.add(sep2);
		PaletteGroup instGroup = new PaletteGroup("Creation elements");
		root.add(instGroup);

		// Drag and drop Node

		{

			try {

				// Read nodes in
				final ArrayList<NodeClass> nodesList = new DefaultClassReader()
				.getNodes();

				for (int i = 0; i < nodesList.size(); i++) {

					// Current node to operate with.
					NodeClass currentNode = nodesList.get(i);

					// Add name and the nodeclass object to the map
					nodesMap.put(currentNode.getName(), currentNode);

					// Create instance object
					NodeInstance instance = new NodeInstance(currentNode);

					CombinedTemplateCreationEntry entry = new CombinedTemplateCreationEntry(
							currentNode.getName(), "Creation of a "
									+ currentNode.getName(), instance,
									new NodeCreationFactory(NodeInstance.class,
											currentNode.getName()), null, null);
					// entry.addTemplateTransferDragSourceListener();

					instGroup.add(entry);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		PaletteSeparator sep3 = new PaletteSeparator();
		root.add(sep3);

		PaletteDrawer connectionElements = new PaletteDrawer(
				"Connecting Elements");
		root.add(connectionElements);

		{

			try {

				// Read nodes in
				final ArrayList<TransitionClass> transitionsList = new DefaultClassReader()
				.getTransitions();

				for (int i = 0; i < transitionsList.size(); i++) {

					// Current node to operate with.
					TransitionClass currentTransition = transitionsList.get(i);

					// Add name and the nodeclass object to the map
					transitionsMap.put(currentTransition.getName(),
							currentTransition);

					// Create instance object
					TransitionInstance instance = new TransitionInstance(
							currentTransition);

					connectionElements.add(new ConnectionCreationToolEntry(
							currentTransition.getName(), "Create "
									+ currentTransition.getName()
									+ " Connections",
									new TransitionInstanceCreationFactory(
											TransitionInstance.class, currentTransition
											.getName()), null, null));

				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return root;

	}

}
