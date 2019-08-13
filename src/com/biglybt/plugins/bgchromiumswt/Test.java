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

import org.eclipse.swt.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class Test{

	public static void
	main(
			String[]		args )
	{
		String path = System.getProperty( "java.library.path" );
		
		path += (path.isEmpty()?"":File.pathSeparator) + "C:\\Users\\Paul\\git\\BiglyBT-plugin-bgchromiumswt";
		
		System.setProperty( "java.library.path", path );

		
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("Snippet 128");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		shell.setLayout(gridLayout);
		ToolBar toolbar = new ToolBar(shell, SWT.NONE);
		ToolItem itemBack = new ToolItem(toolbar, SWT.PUSH);
		itemBack.setText("Back");
		ToolItem itemForward = new ToolItem(toolbar, SWT.PUSH);
		itemForward.setText("Forward");
		ToolItem itemStop = new ToolItem(toolbar, SWT.PUSH);
		itemStop.setText("Stop");
		ToolItem itemRefresh = new ToolItem(toolbar, SWT.PUSH);
		itemRefresh.setText("Refresh");
		ToolItem itemGo = new ToolItem(toolbar, SWT.PUSH);
		itemGo.setText("Go");
		ToolItem itemTest = new ToolItem(toolbar, SWT.PUSH);
		itemTest.setText("Test");

		GridData data = new GridData();
		data.horizontalSpan = 3;
		toolbar.setLayoutData(data);

		Label labelAddress = new Label(shell, SWT.NONE);
		labelAddress.setText("Address");

		final Text location = new Text(shell, SWT.BORDER);
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		data.grabExcessHorizontalSpace = true;
		location.setLayoutData(data);

		final org.eclipse.swt.chromium.Browser browser;
		try {
			browser = new org.eclipse.swt.chromium.Browser(shell, SWT.NONE);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			display.dispose();
			return;
		}
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.verticalAlignment = GridData.FILL;
		data.horizontalSpan = 3;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		browser.setLayoutData(data);

		final Label status = new Label(shell, SWT.NONE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		status.setLayoutData(data);

		final ProgressBar progressBar = new ProgressBar(shell, SWT.NONE);
		data = new GridData();
		data.horizontalAlignment = GridData.END;
		progressBar.setLayoutData(data);

		/* event handling */
		Listener listener = event -> {
			ToolItem item = (ToolItem) event.widget;
			String string = item.getText();
			if (string.equals("Back"))
				browser.back();
			else if (string.equals("Forward"))
				browser.forward();
			else if (string.equals("Stop"))
				browser.stop();
			else if (string.equals("Refresh"))
				browser.refresh();
			else if (string.equals("Go"))
				browser.setUrl(location.getText());
			else if (string.equals("Test"))
				System.out.println( "Url=" + browser.getUrl());
		};
		browser.addProgressListener(new ProgressListener() {
			@Override
			public void changed(ProgressEvent event) {
				if (event.total == 0) return;
				int ratio = event.current * 100 / event.total;
				progressBar.setSelection(ratio);
			}
			@Override
			public void completed(ProgressEvent event) {
				progressBar.setSelection(0);
			}
		});
		browser.addStatusTextListener(event -> status.setText(event.text));
		browser.addLocationListener(LocationListener.changedAdapter(event -> {
			if (event.top) location.setText(event.location);
		}
				));
		itemBack.addListener(SWT.Selection, listener);
		itemForward.addListener(SWT.Selection, listener);
		itemStop.addListener(SWT.Selection, listener);
		itemRefresh.addListener(SWT.Selection, listener);
		itemGo.addListener(SWT.Selection, listener);
		itemTest.addListener(SWT.Selection, listener);
		location.addListener(SWT.DefaultSelection, e -> browser.setUrl(location.getText()));

		shell.open();
		browser.setUrl("http://eclipse.org");

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
