/*
 * This file is part of muCommander, http://www.mucommander.com
 * Copyright (C) 2002-2012 Maxence Bernard
 *
 * muCommander is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * muCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mucommander.ui.action.impl;

import com.mucommander.ui.action.*;
import com.mucommander.ui.main.MainFrame;
import com.mucommander.ui.tabs.ActiveTabListener;

import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * Pops up the DrivePopupButton (the drop down button that allows to quickly select a volume or bookmark)
 * of the left FolderPanel.
 *
 * @author Maxence Bernard
 */
public class PopupRightDriveButtonAction extends MuAction implements ActiveTabListener {

    PopupRightDriveButtonAction(MainFrame mainFrame, Map<String, Object> properties) {
        super(mainFrame, properties);
        
        mainFrame.getRightPanel().getTabs().addActiveTabListener(this);
        
        activeTabChanged();
    }

    /**
     * Enables or disables this action based on the current tab is not locked, 
     * this action will be enabled, if not it will be disabled.
     */
    public void activeTabChanged() {
    	setEnabled(!mainFrame.getRightPanel().getTabs().getCurrentTab().isLocked());
	}

    @Override
    public void performAction() {
        mainFrame.getRightPanel().getDriveButton().popupMenu();
    }

	@Override
	public ActionDescriptor getDescriptor() {
		return new Descriptor();
	}


    public static final class Descriptor extends AbstractActionDescriptor {
    	public static final String ACTION_ID = "PopupRightDriveButton";
    	
		public String getId() { return ACTION_ID; }

		public ActionCategory getCategory() { return ActionCategory.NAVIGATION; }

		public KeyStroke getDefaultAltKeyStroke() { return null; }

		public KeyStroke getDefaultKeyStroke() { return KeyStroke.getKeyStroke(KeyEvent.VK_F2, KeyEvent.ALT_DOWN_MASK); }

        public MuAction createAction(MainFrame mainFrame, Map<String,Object> properties) {
            return new PopupRightDriveButtonAction(mainFrame, properties);
        }
    }
}
