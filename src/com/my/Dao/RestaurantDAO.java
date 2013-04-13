package com.my.Dao;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.my.Entity.Restaurant;

/**
 * A data access object (DAO) providing persistence and search support for
 * Restaurant entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.my.Entity.Restaurant
 * @author MyEclipse Persistence Tools
 */
public class RestaurantDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(RestaurantDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String RESTNAME = "restname";
	public static final String ADDRESS = "address";
	public static final String LOCATION = "location";
	public static final String EMAIL = "email";
	public static final String TEL = "tel";
	public static final String DELIVERY = "delivery";
	public static final String VERSION = "version";

	public void save(Restaurant transientInstance) {
		log.debug("saving Restaurant instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Restaurant persistentInstance) {
		log.debug("deleting Restaurant instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Restaurant findById(java.lang.Integer id) {
		log.debug("getting Restaurant instance with id: " + id);
		try {
			Restaurant instance = (Restaurant) getSession().get(
					"com.my.Entity.Restaurant", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Restaurant instance) {
		log.debug("finding Restaurant instance by example");
		try {
			List results = getSession().createCriteria("com.my.Entity.Restaurant")
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
		log.debug("finding Restaurant instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Restaurant as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByRestname(Object restname) {
		return findByProperty(RESTNAME, restname);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByTel(Object tel) {
		return findByProperty(TEL, tel);
	}

	public List findByDelivery(Object delivery) {
		return findByProperty(DELIVERY, delivery);
	}

	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	public List findAll() {
		log.debug("finding all Restaurant instances");
		try {
			String queryString = "from Restaurant";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Restaurant merge(Restaurant detachedInstance) {
		log.debug("merging Restaurant instance");
		try {
			Restaurant result = (Restaurant) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Restaurant instance) {
		log.debug("attaching dirty Restaurant instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Restaurant instance) {
		log.debug("attaching clean Restaurant instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}