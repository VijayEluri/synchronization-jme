/**
 * Copyright (c) 2006-2010 Floggy Open Source Group. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.floggy.synchronization.jme.core;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class SynchronizationException extends Exception {
	private static final long serialVersionUID = 7139165976718330303L;

	/** The root cause */
	protected Throwable rootCause;

/**
   * Construct a new SynchronizationException with a specified detail message.
   * 
   * @param message
   *            The detail message.
   */
	public SynchronizationException(final String message) {
		super(message);
	}

/**
   * Construct a new SynchronizationException with a specified detail message
   * and root cause.
   * 
   * @param message
   *            The detail message.
   */
	public SynchronizationException(final String message,
		final Throwable rootCause) {
		super(message);
		this.rootCause = rootCause;
	}

	/**
	* Gets the root cause if any of the current throwable
	*
	* @return the root cause or null.
	*/
	public Throwable getRootCause() {
		return rootCause;
	}

	/**
	* DOCUMENT ME!
	*/
	public void printStackTrace() {
		super.printStackTrace();

		if (rootCause != null) {
			rootCause.printStackTrace();
		}
	}
}
