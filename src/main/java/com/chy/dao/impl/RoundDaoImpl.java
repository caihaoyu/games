package com.chy.dao.impl;

import com.chy.dao.RoundDao;
import com.chy.model.Round;
import org.springframework.stereotype.Repository;

@Repository
public class RoundDaoImpl extends AbstractDaoImpl<Round, String> implements
		RoundDao {
	protected RoundDaoImpl() {
		super(Round.class);
	}
}