/*
 * This file is part of the COASTAL tool, https://deepseaplatform.github.io/coastal/
 *
 * Copyright (c) 2019, Computer Science, Stellenbosch University.  All rights reserved.
 *
 * Licensed under GNU Lesser General Public License, version 3.
 * See LICENSE.md file in the project root for full license information.
 */
package za.ac.sun.cs.coastal.symbolic.exceptions;

/**
 * Exception to signal that {@link System#exit(int)} was invoked by the system
 * under test.
 */
public class SystemExitException extends ControlException {

	/**
	 * Required for serialization.
	 */
	private static final long serialVersionUID = 7592301818476180000L;

}
