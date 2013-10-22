package com.chy.dao.impl;

import com.chy.dao.ImageDao;
import com.chy.model.Image;
import org.springframework.stereotype.Repository;

@Repository
public class ImageDaoImpl extends AbstractDaoImpl<Image, String> implements
		ImageDao {
	protected ImageDaoImpl() {
		super(Image.class);
	}

	public Image saveOrUpdateIamge(Image image) {
		return ((Image) saveOrUpdate(image));
	}
}