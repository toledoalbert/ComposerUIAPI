package org.tekkotsu.ui.util;

import java.util.ArrayList;
import java.util.Random;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.FileDialog;



public class FilesystemUtil
{
	public static final String USER = System.getProperty("user.name");
	public static final String HOME = System.getProperty("user.home");
	public static final String PWD  = System.getProperty("user.dir");
	public static final String TMP  = System.getProperty("java.io.tmpdir");
	public static final String PSEP = System.getProperty("file.separator");
	
	public static final IPath  TEMP_PATH = new Path(TMP);
	
	private static String tStickyPath  = HOME;
	private static Random random       = new Random();
	
	/** List of all temp files created this session */
	private static final ArrayList<IPath> tempFiles = new ArrayList<IPath>();
	
	public static void setFileDialogStartPath(FileDialog d)
	{
		String stickpath = FilesystemUtil.stickyPathGet();
		Debugger.printDebug(Debugger.DEBUG_ALL, "Open path: " + FilesystemUtil.stickyPathGet());
		
		if(stickpath != null)
		{
			IPath openPath = new Path(FilesystemUtil.stickyPathGet());
			String openDir  = openPath.removeLastSegments(1).toString();
			
			d.setFilterPath(openDir);
		}
	}
	
	public static void stickyPathSet(String path) {
		if(path == null)
		{
			return;
		}
		
		tStickyPath = path;
	}
	
	public static String stickyPathGet()
	{
		return tStickyPath;
	}
	
	public static IPath createTempPath(String ext)
	{
		assert(ext != null);
		
		StringBuffer buf = new StringBuffer();
		buf.append("storyboard-temp-");
		buf.append(random.nextLong());
		buf.append(".");
		buf.append(ext);
		
		IPath path = TEMP_PATH.append(buf.toString());
		tempFiles.add(path);
		
		return path;
	}
	
	/**
	 * Removes all of the temp files created so far.
	 */
	public static void removeTempFiles()
	{
		for( IPath path : tempFiles )
		{
			path.toFile().delete();
		}
		tempFiles.clear();
	}

	public static boolean isTempFile(IPath path) {
		return TEMP_PATH.isPrefixOf(path);
	}

	public static void deleteIfTempFile(IPath path) {
		if( isTempFile(path) )
		{
			tempFiles.remove(path);
			path.toFile().delete();
		}
	}
}
