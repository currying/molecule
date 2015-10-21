package com.toparchy.molecule.bi.shipBuilding.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.toparchy.molecule.bi.shipBuilding.model.Hxjdjh;
import com.toparchy.molecule.bi.shipBuilding.model.HxjdjhDataHandle;

@ApplicationScoped
public class HxjdjhRepository {
	@PersistenceUnit(unitName = "molecule")
	private EntityManagerFactory emf;
	private String sql;
	private Query query;
	private List<Hxjdjh> objecArraytList;
	private List<HxjdjhDataHandle> hxjdjhDataHandles;
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	public List<HxjdjhDataHandle> findByGcbh(String gcbh, int start, int max) {
		EntityManager em = emf.createEntityManager();
		sql = "select ppk_varcode,cxmc,gcbh,gcmc,xmmc,xmbh,xmsx,jhkg1,jhwg1 from JH_D_Hxjdjh_DJ where gcbh =?1 order by xmsx";
		query = em.createNativeQuery(sql, Hxjdjh.class);
		query.setParameter(1, gcbh);
		query.setFirstResult(start);
		query.setMaxResults(max);
		objecArraytList = query.getResultList();
		int i = 1;
		hxjdjhDataHandles = new ArrayList<HxjdjhDataHandle>();
		for (Hxjdjh hxjdjh : objecArraytList) {
			HxjdjhDataHandle hxjdjhDataHandle = new HxjdjhDataHandle();
			hxjdjhDataHandle.setId(i);
			hxjdjhDataHandle.setStart_date(formatter.format(hxjdjh.getJhkg()));
			hxjdjhDataHandle.setEnd_date(formatter.format(hxjdjh.getJhwg()));
			hxjdjhDataHandle.setText("项目名称：" + hxjdjh.getXmmc() + "\n计划开工："
					+ hxjdjh.getJhkg() + "\n计划完工：" + hxjdjh.getJhwg());
			hxjdjhDataHandles.add(hxjdjhDataHandle);
			i++;
		}
		em.close();
		return hxjdjhDataHandles;
	}
}
