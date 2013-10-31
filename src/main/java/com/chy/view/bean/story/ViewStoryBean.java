package com.chy.view.bean.story;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chy.model.Story.Story;
import com.chy.service.ItemService;
import com.chy.service.StoryService;
import com.chy.view.bean.BaseBean;

@Component
@Scope("view")
public class ViewStoryBean extends BaseBean {

	private static final long serialVersionUID = -7128781497227863630L;

	@Autowired
	private StoryService storyService;

	@Autowired
	private ItemService itemService;

	private Story story;

	@PostConstruct
	public void setup() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();

		if ((externalContext.getRequestParameterMap().get("storyId") != null)) {
			story = storyService.get(externalContext.getRequestParameterMap()
					.get("storyId"));
		}
	}

}
