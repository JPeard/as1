package com.example.jpeard.as1;

import java.util.ArrayList;

/**
 * Created by jpeard on 1/27/16.
 */
public class LogList {
    ArrayList<DataLog> logs;

    public LogList() {
        this.logs = new ArrayList<DataLog>();
    }

    public void clear(){
	// Clears the ArrayList
        logs.clear();
    }

    public ArrayList<DataLog> getLogs() {
	// Returns the ArrayList
        return logs;
    }

    public void addLog(DataLog log){
	// Appends to the ArrayList
        logs.add(log);
    }

    public void deleteLog(DataLog log){
	// Deletes a log from the ArrayList
        logs.remove(log);
    }

    public void deleteIndex(int i){
	// Deletes a log from a particular index of the ArrayList
        logs.remove(i);
    }

    public int indexOf(DataLog log){
	// Returns the index of a particular log
        return logs.indexOf(log);
    }

    public DataLog getIndex(int i){
	// Returns the log at a particular index
        return logs.get(i);
    }

    public void replaceLog(DataLog newLog, int i){
	// Replace the log at index i with the new log
        logs.set(i,newLog);
    }

    public ArrayList<String> getIndexes(){
	// Returns an ArrayList of Log titles as Strings
	// ie. "Log 1", "Log 2", etc.
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < logs.size(); i++){
            temp.add(logs.get(i).getDate());
        }
        return temp;
    }

}
