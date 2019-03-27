package za.ac.sun.cs.coastal.symbolic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The inputs to a run of the system-under-test in the form of a mapping from
 * variable indices (as {@link Integer}s) to variable values (as {@link Object}s).
 */
public class InputMap {

	/**
	 * Mapping from variable indices to variable values.
	 */
	private final Map<String, Object> inputVector;

	/**
	 * Construct a new, empty set of inputs.
	 */
	public InputMap() {
		inputVector = new HashMap<>();
	}

	/**
	 * Construct a new set of inputs based on an existing set.
	 * 
	 * @param inputVector the older set of inputs
	 */
	public InputMap(InputMap inputVector) {
		this.inputVector = new HashMap<>(inputVector.inputVector);
	}

	/**
	 * Return the variable value associated with a name.
	 * 
	 * @param name the name of the variable
	 * @return the value of the variable (or {@code null})
	 */
	public Object get(String name) {
		return inputVector.get(name);
	}

	/**
	 * Associates a new value with a variable name.
	 * 
	 * @param name  the new of the variable
	 * @param value the new value of the variable
	 * @return the old value of the variable (or {@code null})
	 */
	public Object put(String name, Object value) {
		return inputVector.put(name, value);
	}

	/**
	 * Returns the set of all names that are mapped.
	 * 
	 * @return all names with values
	 */
	public Set<String> getNames() {
		return inputVector.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return inputVector.toString();
	}

}
