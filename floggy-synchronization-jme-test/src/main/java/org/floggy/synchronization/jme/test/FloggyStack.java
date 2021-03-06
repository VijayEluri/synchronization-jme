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
package org.floggy.synchronization.jme.test;

import java.util.Enumeration;
import java.util.Stack;

import org.floggy.synchronization.jme.core.Synchronizable;

import net.sourceforge.floggy.persistence.Deletable;
import net.sourceforge.floggy.persistence.FloggyException;
import net.sourceforge.floggy.persistence.Persistable;
import net.sourceforge.floggy.persistence.PersistableManager;

/**
* DOCUMENT ME!
*
* @author <a href="mailto:thiago.moreira@floggy.org">Thiago Moreira</a>
* @version $Revision$
 */
public class FloggyStack implements Persistable, Synchronizable, Deletable {
	/** DOCUMENT ME! */
	protected Stack x;

	/**
	* DOCUMENT ME!
	*
	* @throws FloggyException DOCUMENT ME!
	*/
	public void delete() throws FloggyException {
		if (x != null) {
			Enumeration enumeration = x.elements();

			while (enumeration.hasMoreElements()) {
				Object object = (Object) enumeration.nextElement();

				if (object instanceof Persistable) {
					PersistableManager.getInstance().delete((Persistable) object);
				}
			}
		}
	}

	/**
	* DOCUMENT ME!
	*
	* @param obj DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		final FloggyStack other = (FloggyStack) obj;

		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;

		return true;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public Stack getX() {
		return x;
	}

	/**
	* DOCUMENT ME!
	*
	* @return DOCUMENT ME!
	*/
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((x == null) ? 0 : x.hashCode());

		return result;
	}

	/**
	* DOCUMENT ME!
	*
	* @param x DOCUMENT ME!
	*/
	public void setX(Stack x) {
		this.x = x;
	}
}
