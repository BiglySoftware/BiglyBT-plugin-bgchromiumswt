/*
 * Copyright (C) Bigly Software, Inc, All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA
 */

package com.biglybt.plugins.bgchromiumswt;

import java.io.File;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import com.biglybt.pif.Plugin;
import com.biglybt.pif.PluginException;
import com.biglybt.pif.PluginInterface;
import com.biglybt.ui.swt.BrowserWrapper;
import com.biglybt.ui.swt.BrowserWrapperSWTFactory;

public class 
ChromiumSWTPlugin
	implements Plugin
{
	public static void 
	load(
		PluginInterface pluginInterface )
	{
			// this works because the browser explicitly uses this path rather than relying on the Java runtime
		
		String path = System.getProperty( "java.library.path" );
		
		path += (path.isEmpty()?"":File.pathSeparator) + pluginInterface.getPluginDirectoryName();
		
		System.setProperty( "java.library.path", path );
		
		BrowserWrapperSWTFactory.registerProvider(
			new BrowserWrapperSWTFactory.BrowserProvider(){
				
				@Override
				public BrowserWrapper 
				create(
					Composite 			composite, 
					int 				style, 
					Map<String, Object> properties)
				{
					return( new ChromiumSWTBrowser(composite, style));
				}
			});
	}
	
	@Override
	public void 
	initialize(
		PluginInterface pluginInterface ) 
				
		throws PluginException
	{
	}
}
