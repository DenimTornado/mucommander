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

import com.mucommander.ui.action.AbstractActionDescriptor;
import com.mucommander.ui.action.ActionCategory;
import com.mucommander.ui.action.ActionDescriptor;
import com.mucommander.ui.action.MuAction;
import com.mucommander.ui.main.MainFrame;

import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * This action recalls the next folder in the current FolderPanel's history.
 *
 * @author Maxence Bernard
 */
public class GoForwardAction extends ActiveTabAction {

    public GoForwardAction(MainFrame mainFrame, Map<String,Object> properties) {
        super(mainFrame, properties);
    }


    @Override
    public void performAction() {
        mainFrame.getActivePanel().getFolderHistory().goForward();
    }

	@Override
	public ActionDescriptor getDescriptor() {
		return new Descriptor();
	}

    /**
     * Enables or disables this action based on the history of the currently active FolderPanel: if there is a next
     * folder in the history and the current tab is not locked, this action will be enabled, if not it will be disabled.
     */
    @Override
    protected void toggleEnabledState() {
        setEnabled(mainFrame.getActivePanel().getFolderHistory().hasForwardFolder() &&
        		  !mainFrame.getActivePanel().getTabs().getCurrentTab().isLocked());
    }


    public static final class Descriptor extends AbstractActionDescriptor {
    	public static final String ACTION_ID = "GoForward";
    	
		public String getId() { return ACTION_ID; }

		public ActionCategory getCategory() { return ActionCategory.NAVIGATION; }

		public KeyStroke getDefaultAltKeyStroke() { return null; }

		public KeyStroke getDefaultKeyStroke() { return KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.ALT_DOWN_MASK); }

        public MuAction createAction(MainFrame mainFrame, Map<String,Object> properties) {
            return new GoForwardAction(mainFrame, properties);
        }
    }
}
