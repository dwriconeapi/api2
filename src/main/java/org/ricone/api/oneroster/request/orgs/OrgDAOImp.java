package org.ricone.api.oneroster.request.orgs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.core.model.oneroster.QOrg;
import org.ricone.api.core.model.oneroster.QOrgChild;
import org.ricone.api.oneroster.component.BaseDAO;
import org.ricone.api.oneroster.component.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

@Repository("OneRoster:Orgs:OrgDAO")
@SuppressWarnings({"unchecked", "unused"})
class OrgDAOImp extends BaseDAO implements OrgDAO {
	@PersistenceContext private EntityManager em;
	@Autowired private OrgFilterer filterer;
	private Logger logger = LogManager.getLogger(OrgDAOImp.class);

	@Override
	public QOrg getOrg(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QOrg> select = cb.createQuery(QOrg.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_ID), refId),
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (QOrg) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QOrg> getAllOrgs(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QOrg> select = cb.createQuery(QOrg.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		//Paging
		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllOrgs(metadata));
		}
		return (List<QOrg>) q.getResultList();
	}

	@Override
	public QOrg getSchool(RequestData metadata, String refId) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QOrg> select = cb.createQuery(QOrg.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_ID), refId),
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(TYPE), SCHOOL),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));

		Query q = em.createQuery(select);
		try {
			return (QOrg) q.getSingleResult();
		}
		catch(NoResultException ignored) { }
		return null;
	}

	@Override
	public List<QOrg> getAllSchools(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<QOrg> select = cb.createQuery(QOrg.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(TYPE), SCHOOL),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.distinct(true);
		select.select(from);
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));

		Query q = em.createQuery(select);
		if(metadata.getPaging().isPaged()) {
			q.setFirstResult(metadata.getPaging().getOffset());
			q.setMaxResults(metadata.getPaging().getLimit());
			metadata.getPaging().setPagingHeaders(countAllSchools(metadata));
		}
		return (List<QOrg>) q.getResultList();
	}

	/* Counts */

	@Override
	public int countAllOrgs(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}

	@Override
	public int countAllSchools(RequestData metadata) throws Exception {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> select = cb.createQuery(Long.class);
		final Root<QOrg> from = select.from(QOrg.class);
		final SetJoin<QOrg, QOrgChild> children = (SetJoin<QOrg, QOrgChild>) from.<QOrg, QOrgChild>join(CHILDREN, JoinType.LEFT).alias(CHILDREN);

		//Add Root Object & Joins to Filterer
		filterer.addJoins(from, children);

		//Define Method Specific Predicates
		final Predicate methodSpecificPredicate = cb.and(
			cb.equal(from.get(SOURCED_SCHOOL_YEAR), 2019),
			cb.equal(from.get(TYPE), SCHOOL),
			from.get(DISTRICT_ID).in(metadata.getApplication().getDistrictLocalIds())
		);

		select.select(cb.countDistinct(from));
		select.where(getWhereClause(metadata, cb, filterer, methodSpecificPredicate));
		select.orderBy(getSortOrder(metadata, cb, from));
		return em.createQuery(select).getSingleResult().intValue();
	}
}