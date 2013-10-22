package com.chy.scope;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ViewScope implements Scope {
	public Object get(String name, ObjectFactory<?> objectFactory) {
		if (FacesContext.getCurrentInstance().getViewRoot() != null) {
			 Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot()
					.getViewMap();
			if (viewMap.containsKey(name)) {
				return viewMap.get(name);
			}
			Object object = objectFactory.getObject();
			viewMap.put(name, object);
			return object;
		}

		return null;
	}

	public Object remove(String name) {
		if (FacesContext.getCurrentInstance().getViewRoot() != null) {
			return FacesContext.getCurrentInstance().getViewRoot().getViewMap()
					.remove(name);
		}
		return null;
	}

	public void registerDestructionCallback(String name, Runnable callback) {
	}

	public Object resolveContextualObject(String key) {
		return null;
	}

	public String getConversationId() {
		return null;
	}
}