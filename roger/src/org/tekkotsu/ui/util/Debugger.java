/*
 * Created on Oct 24, 2004
 */
package org.tekkotsu.ui.util;

/**
 * @author asangpet
 *
 */
public class Debugger {
	public static int debugLevel = 1;
	
	public static final int DEBUG_ALL     = 7;
	public static final int DEBUG_TEMP    = 6;
	public static final int DEBUG_STORYBOARD_INFO  = 5;
	public static final int DEBUG_STORYBOARD_EVENT = 4;
	public static final int DEBUG_CURRENT = 3;
	public static final int DEBUG_ERROR   = 2;
	public static final int DEBUG_RELEASE = 1;
	public static final int DEBUG_NONE    = 0;
	
	public static int getDebugLevel() {
		return debugLevel;
	}
	
	public static void printError(String s)
	{
		printDebug(DEBUG_ERROR,s);
	}
	
	public static void printThrowable(Throwable e)
	{
		if(DEBUG_ERROR <= getDebugLevel())
			e.printStackTrace(System.err);
	}
	
	public static void printDebug(int level, String s) {
		if (level <= getDebugLevel()) System.err.println(s);
	}
	
	public static void printDebug(String s) {
		printDebug(DEBUG_ALL,s);
	}
	
	public static void Current(String s)
	{
		printDebug(DEBUG_CURRENT,s);
	}

	public static void printStoryboardEvent(String s) {
		printDebug(DEBUG_STORYBOARD_EVENT,s);
	}

	public static void printStoryboardInfo(String s) {
		printDebug(DEBUG_STORYBOARD_INFO,s);
	}

	public static void force() {
		debugLevel = DEBUG_ALL;
	}
}
