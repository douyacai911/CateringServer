package com.my.Dao;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.my.Entity.Food;

/**
 * A data access object (DAO) providing persistence and search support for Food
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.my.Entity.Food
 * @author MyEclipse Persistence Tools
 */
public class FoodDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(FoodDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PRICE = "price";
	public static final String STARS = "stars";
	public static final String CATEGORYID = "categoryid";
	public static final String PICTUREURL = "pictureurl";

	public void save(Food transientInstance) {
		log.debug("saving Food instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Food persistentInstance) {
		log.debug("deleting Food instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Food findById(java.lang.Integer id) {
		log.debug("getting Food instance with id: " + id);
		try {
			Food instance = (Food) getSession().get("com.my.Entity.Food", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Food instance) {
		log.debug("finding Food instance by example");
		try {
			List results = getSession().createCriteria("com.my.Entity.Food")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Food instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Food as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByStars(Object stars) {
		return findByProperty(STARS, stars);
	}

	public List findByCategoryid(Object categoryid) {
		return findByProperty(CATEGORYID, categoryid);
	}

	public List findByPictureurl(Object pictureurl) {
		return findByProperty(PICTUREURL, pictureurl);
	}

	public List findAll() {
		log.debug("finding all Food instances");
		try {
			String queryString = "from Food";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Food merge(Food detachedInstance) {
		log.debug("merging Food instance");
		try {
			Food result = (Food) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Food instance) {
		log.debug("attaching dirty Food instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Food instance) {
		log.debug("attaching clean Food instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}