package com.toparchy.molecule.scxt.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.toparchy.molecule.scxt.model.WorkingHoursVolume;

@ApplicationScoped
public class WorkingHoursVolumeRepository {

	@PersistenceUnit(unitName = "gswl")
	private EntityManagerFactory emf;
	private String sql;
	private Query query;
	private List<WorkingHoursVolume> objecArraytList;

	public List<WorkingHoursVolume> findAll(int start, int max) {
		EntityManager em = emf.createEntityManager();
		sql = "select ppk_varcode,pgdbh,gcbh,kdrq,sgxm,sglwd,pgnr,sgqy,jhkgsj,jhwgsj,pgzt,kdry,cxmc,pgdms,zymc,jdmc,xmhf,zylx,sgbm,sgbz,degs,sgdw,lwlx from dbo.RW_D_Hcpgd_DJ";
		query = em.createNativeQuery(sql, WorkingHoursVolume.class);
		query.setFirstResult(start);
		query.setMaxResults(max);
		objecArraytList = query.getResultList();
		em.close();
		return objecArraytList;
	}

	public List<WorkingHoursVolume> findByCondition(String name, String value, int start, int max) {
		EntityManager em = emf.createEntityManager();
		sql = "select ppk_varcode,pgdbh,gcbh,kdrq,sgxm,sglwd,pgnr,sgqy,jhkgsj,jhwgsj,pgzt,kdry,cxmc,pgdms,zymc,jdmc,xmhf,zylx,sgbm,sgbz,degs,sgdw,lwlx from dbo.RW_D_Hcpgd_DJ where "
				+ name + "=?1";
		query = em.createNativeQuery(sql, WorkingHoursVolume.class);
		query.setParameter(1, value);
		query.setFirstResult(start);
		query.setMaxResults(max);
		objecArraytList = query.getResultList();
		em.close();
		return objecArraytList;
	}

}
