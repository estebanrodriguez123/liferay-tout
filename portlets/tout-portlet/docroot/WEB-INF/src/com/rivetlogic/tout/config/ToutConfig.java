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

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ToutConfig {
       
    private boolean enabled;
    private String showMoreURL;
    private int daysBeforeReminder;
    private String articleId;
    private long articleGroupId;
    private List<Long>sites;
    private String pagesRegex;
    
    private String id;
    
    public ToutConfig(){
    	generateId();
    }
    
    public void generateId(){
    	UUID uuid = UUID.randomUUID();
    	id = uuid.toString();
    }
    
    public String getId() {
		return id;
	}

    public void setId(String id){
    	this.id = id;
    }
    
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
    public List<Long> getSites() {
		return sites;
	}
	public void setSites(List<Long> sites) {
		this.sites = sites;
	}

	public String getPagesRegex() {
		return pagesRegex;
	}

	public void setPagesRegex(String pagesRegex) {
		this.pagesRegex = pagesRegex;
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
    
    public boolean isEnabledOnSite(Long siteId, String pageName){
    	if(!isEnabled())
    		return false;
    	
    	if(null != sites && !sites.isEmpty()){
    		if(!sites.contains(siteId))
    			return false;
    	}
    	
    	if(null != pagesRegex && !pagesRegex.isEmpty()){
    		try{
    			Pattern pattern = Pattern.compile(pagesRegex.toLowerCase());
    			Matcher matcher = pattern.matcher(pageName.toLowerCase());
    			if (!matcher.find()){
    				return false;
    			}
    		}catch(PatternSyntaxException e){
    			return false;
    		}
    	}
    	return true;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToutConfig other = (ToutConfig) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
