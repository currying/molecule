package com.toparchy.molecule.scxt.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.toparchy.molecule.scxt.model.WorkingHoursVolumeFeedBack;

@ApplicationScoped
public class WorkingHoursVolumeFeedBackRepository {

	@PersistenceUnit(name = "paltform")
	private EntityManagerFactory emf;
	private String sql;
	private Query query;
	private List<WorkingHoursVolumeFeedBack> objecArraytList;

	public List<WorkingHoursVolumeFeedBack> findById(String id, int start, int max) {
		EntityManager em = emf.createEntityManager();
		sql = "select ppk_varcode,pgdbh, rybh, rymc, gzmc, gsfl, sdgs, fksj FROM dbo.RW_D_gsfk_DJ where pgd_id =?1";
		query = em.createNativeQuery(sql, WorkingHoursVolumeFeedBack.class);
		query.setParameter(1, id);
		query.setFirstResult(start);
		query.setMaxResults(max);
		objecArraytList = query.getResultList();
		em.close();
		return objecArraytList;
	}

}
