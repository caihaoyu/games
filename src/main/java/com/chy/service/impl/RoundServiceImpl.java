package com.chy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chy.dao.RoundDao;
import com.chy.model.Round;
import com.chy.service.RoundService;

@Service("roundService")
@Transactional(readOnly = true)
public class RoundServiceImpl extends AbstractServiceImpl<Round, String>
		implements RoundService {

	@Autowired
	public RoundServiceImpl(RoundDao dao) {
		super(dao);
		this.dao=dao;
		// TODO Auto-generated constructor stub
	}

	RoundDao dao;

	@Transactional(readOnly = true)
	public Round getRound(String id) {
		return ((Round) this.dao.findById(id));
	}

	@Transactional(readOnly = false)
	public Round saveOrUpdate(Round round) {
		return this.dao.saveOrUpdate(round);
	}

	@Transactional(readOnly = false)
	public void deleteRound(Round round) {
		this.dao.delete(round);
	}

	@Transactional(readOnly = true)
	public List<Round> findAllRound() {
		return this.dao.findAll();
	}
}