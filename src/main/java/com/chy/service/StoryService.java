package com.chy.service;

import com.chy.model.User;
import com.chy.model.Story.Story;


public abstract interface StoryService extends AbstractService<Story, String>
{
	public String addStory(String title,String header,String rule,User user);
}