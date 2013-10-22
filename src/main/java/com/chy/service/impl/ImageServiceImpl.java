package com.chy.service.impl;

import com.chy.dao.ImageDao;
import com.chy.model.Image;
import com.chy.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("imageService")
@Transactional(readOnly=true)
public class ImageServiceImpl extends AbstractServiceImpl<Image, String>
  implements ImageService
{
  @Autowired
  public ImageServiceImpl(ImageDao imageDao)
  {
    super(imageDao);
  }
}