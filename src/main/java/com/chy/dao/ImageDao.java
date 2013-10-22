package com.chy.dao;

import com.chy.model.Image;

public abstract interface ImageDao extends AbstractDao<Image, String>
{
  public abstract Image saveOrUpdateIamge(Image paramImage);
}