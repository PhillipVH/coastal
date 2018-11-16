package za.ac.sun.cs.coastal.reporting;

public interface Recorder {

	void record(String name, String property, String value);

	void record(String name, String property, long value);

	String getRecordedString(String name, String property);

	long getLong(String name, String property);
	
}
