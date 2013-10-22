package com.chy.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.chy.dao.UserDao;
import com.chy.model.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, String>
  implements UserDao
{
  protected UserDaoImpl()
  {
    super(User.class);
  }

  public User login(String account, String passWord)
  {
    Criteria criteria = getCurrentSession().createCriteria(User.class);
    criteria.add(Restrictions.and(Restrictions.eq("account", account), Restrictions.eq("passWord", passWord)));

    List users = criteria.list();
    if ((null != users) && (users.size() > 0)) {
      return ((User)users.get(0));
    }
    return null;
  }
}