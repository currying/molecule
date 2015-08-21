package com.toparchy.platform.ui.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.toparchy.platform.annotation.PlatformData;
import com.toparchy.platform.ui.model.Layout;
import com.toparchy.platform.ui.model.PositionLayout;

@Stateless
public class LayoutEJB {
	@Inject
	@PlatformData
	private EntityManager platformEm;

	public List<PositionLayout> getPositionLayout(String layoutId) {
		CriteriaBuilder cb = platformEm.getCriteriaBuilder();
		CriteriaQuery<PositionLayout> criteria = cb
				.createQuery(PositionLayout.class);
		Root<PositionLayout> positionLayout = criteria
				.from(PositionLayout.class);
		criteria.select(positionLayout).where(
				cb.equal(positionLayout.get("id"), layoutId));
		return platformEm.createQuery(criteria).getResultList();
	}

	public Layout getLayout(String layoutId) {
		CriteriaBuilder cb = platformEm.getCriteriaBuilder();
		CriteriaQuery<Layout> criteria = cb.createQuery(Layout.class);
		Root<Layout> layout = criteria.from(Layout.class);
		criteria.select(layout).where(cb.equal(layout.get("id"), layoutId));
		return platformEm.createQuery(criteria).getSingleResult();
	}

	public Layout getLayoutTest() {
		CriteriaBuilder cb = platformEm.getCriteriaBuilder();
		CriteriaQuery<Layout> criteria = cb.createQuery(Layout.class);
		Root<Layout> layout = criteria.from(Layout.class);
		criteria.select(layout);
		return platformEm.createQuery(criteria).getSingleResult();
	}
}
