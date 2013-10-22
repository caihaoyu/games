package com.chy.dao.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chy.dao.OptionDao;
import com.chy.model.Option;

@Repository
public class OptionDaoImpl extends AbstractDaoImpl<Option, String> implements
		OptionDao {
	protected OptionDaoImpl() {
		super(Option.class);
	}

	public List<Option> searchOptions(String query, Collection<String> ids) {
		Criteria criteria = getCurrentSession().createCriteria(Option.class);
		criteria.add(Restrictions.or(
				Restrictions.like("name", "%" + query + "%"),
				Restrictions.like("pinyinName", "%" + query + "%")));

		if ((null != ids) && (ids.size() > 0)) {
			criteria.add(Restrictions.and(Restrictions.or(
					Restrictions.like("name", "%" + query + "%"),
					Restrictions.like("pinyinName", "%" + query + "%")),
					Restrictions.not(Restrictions.in("id", ids))));
		}

		List list = criteria.list();
		return list;
	}

	@Transactional(readOnly = true)
	public Option getOptionByName(String name) {
		Criteria criteria = getCurrentSession().createCriteria(Option.class);
		criteria.add(Restrictions.eq("name", name));

		List options = criteria.list();
		Option option = null;
		if ((null != options) && (options.size() > 0)) {
			option = (Option) options.get(0);
		}
		return option;
	}
}