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
package org.floggy.synchronization.jme.maven;

import java.io.File;
import java.util.List;

import net.sourceforge.floggy.maven.MavenLogWrapper;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.floggy.synchronization.jme.weaver.Configuration;
import org.floggy.synchronization.jme.weaver.Weaver;

/**
* Goal which runs the Floggy compiler.
*
* @goal synchronization-weaver
* @phase process-classes
* @requiresDependencyResolution compile
 */
public class SynchronizationMojo extends AbstractMojo {
	/**
	* DOCUMENT ME!
	*
	* @parameter expression="${configurationFile}"
	* @optional
	 */
	private File configurationFile;

	/**
	* Location of the file.
	*
	* @parameter expression="${project.build.outputDirectory}"
	* @required
	 */
	private File input;

	/**
	* Location of the file.
	*
	* @parameter expression="${project.build.outputDirectory}"
	* @required
	 */
	private File output;

	/**
	* Location of the file.
	*
	* @parameter expression="${project}"
	* @required
	 */
	private MavenProject project;

	/**
	* .
	*
	* @parameter expression="false"
	* @optional
	 */
	private boolean generateSource = false;

	/**
	* DOCUMENT ME!
	*
	* @throws MojoExecutionException DOCUMENT ME!
	*/
	public void execute() throws MojoExecutionException {
		MavenLogWrapper.setLog(getLog());

		LogFactory.getFactory().setAttribute(
				"org.apache.commons.logging.Log",
				"net.sourceforge.floggy.maven.MavenLogWrapper");

		Weaver weaver = new Weaver();

		try {
			List list = project.getCompileClasspathElements();
			File temp =
				new File(project.getBuild().getDirectory(),
					String.valueOf(System.currentTimeMillis()));
			FileUtils.forceMkdir(temp);
			weaver.setOutputFile(temp);
			weaver.setInputFile(input);
			weaver.setClasspath((String[]) list.toArray(new String[list.size()]));

			if (configurationFile == null) {
				Configuration configuration = new Configuration();
				configuration.setGenerateSource(generateSource);
				weaver.setConfiguration(configuration);
			} else {
				weaver.setConfigurationFile(configurationFile);
			}

			weaver.execute();
			FileUtils.copyDirectory(temp, output);
			FileUtils.forceDelete(temp);
		} catch (Exception e) {
			throw new MojoExecutionException(e.getMessage(), e);
		}
	}
}
