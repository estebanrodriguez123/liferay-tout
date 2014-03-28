/**
 * Copyright (C) 2005-2014 Rivet Logic Corporation.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; version 3 of the License.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package com.rivetlogic.tout.config;

public class ToutConfig {
       
    private boolean enabled;
    private String showMoreURL;
    private int daysBeforeReminder;
    private String articleId;
    private long articleGroupId;
    
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getShowMoreURL() {
        return showMoreURL;
    }
    public void setShowMoreURL(String showMoreURL) {
        this.showMoreURL = showMoreURL;
    }
    public int getDaysBeforeReminder() {
        return daysBeforeReminder;
    }
    public void setDaysBeforeReminder(int daysBeforeReminder) {
        this.daysBeforeReminder = daysBeforeReminder;
    }
    public String getArticleId() {
        return articleId;
    }
    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
    public long getArticleGroupId() {
        return articleGroupId;
    }
    public void setArticleGroupId(long articleGroupId) {
        this.articleGroupId = articleGroupId;
    }    
}
