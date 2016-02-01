package com.example.jpeard.as1;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by jpeard on 1/27/16.
 */
public class LogListTest extends ActivityInstrumentationTestCase2 {
    public  LogListTest() {super(LogViewingActivity.class);}

    public void testAddLog(){
	LogList list = new LogList();
	DataLog log = new DataLog("2012-01-01", "Shell", 20.4, "Premium", 1.0, 1.0);
    
	list.addLog(log);
	assertEquals(list.getLogs().get(0), log);
    }

    public void testDeleteLog(){
	LogList list = new LogList();
	DataLog log = new DataLog("2012-01-01", "Shell", 20.4, "Premium", 1.0, 1.0);

	list.addLog(log);
	assertEquals(list.getLogs().get(0), log);
	list.deleteLog(log);
	assertEquals(list.getLogs().get(0), null);
    }

    public void testDeleteLog(){
	LogList list = new LogList();
	DataLog log1 = new DataLog("2012-01-01", "Shell", 20.4, "Premium", 1.0, 1.0);
	DataLog log2 = new DataLog("2013-01-01", "Shell", 10.4, "Premium", 1.0, 2.0);

	list.addLog(log1);
	assertEquals(list.getLogs().get(0), log1);
	list.replaceLog(log2, 0);
	assertEquals(list.getLogs().get(0), log2);
    }

    public void testDeleteLog(){
	LogList list = new LogList();
	DataLog log1 = new DataLog("2012-01-01", "Shell", 20.4, "Premium", 1.0, 1.0);
	DataLog log2 = new DataLog("2013-01-01", "Shell", 10.4, "Premium", 1.0, 2.0);
	ArrayList<String> test = new ArrayList<String>();
	test.add("Log 1");
	test.add("Log 2");

	list.addLog(log1);
	list.addLog(log2);
	assertEquals(list.getIndexes(), test);
}
