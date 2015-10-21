package com.toparchy.molecule.wzxt.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.toparchy.molecule.wzxt.model.MaterialStorage;

@ApplicationScoped
public class MaterialStorageRepository {

	@PersistenceUnit(unitName = "wzxt")
	private EntityManagerFactory emf;
	private String sql;
	private Query query;
	private List<MaterialStorage> objecArraytList;

	public List<MaterialStorage> findByLdbh(String ldbh, int start, int max, int mark) {
		EntityManager em = emf.createEntityManager();
		if (mark == 2) {
			sql = "select khmc, khbh, kdrq, ldbh, cgy, htbh, bmbh, bmmc, ckbh, ckmc, rklx, gcbh, gcmc, rkly, shry, shrq, wzbh,wzmc, wzms, rksl, sssl, jldw, wdysm, shbz, bz, wzrkd_id from dbo.wzrkdwj where ldbh =?1 order by wzrkd_id";
			query = em.createNativeQuery(sql, MaterialStorage.class);
			query.setParameter(1, ldbh);
			query.setFirstResult(start);
			query.setMaxResults(max);
			objecArraytList = query.getResultList();
		} else {
			sql = "select khmc, khbh, kdrq, ldbh, cgy, htbh, bmbh, bmmc, ckbh, ckmc, rklx, gcbh, gcmc, rkly, shry, shrq, wzbh,wzmc, wzms, rksl, sssl, jldw, wdysm, shbz, bz, wzrkd_id from dbo.wzrkdwj where ldbh =?1 and shbz=?2 order by wzrkd_id";
			query = em.createNativeQuery(sql, MaterialStorage.class);
			query.setParameter(1, ldbh);
			query.setParameter(2, mark);
			query.setFirstResult(start);
			query.setMaxResults(max);
			objecArraytList = query.getResultList();
		}
		em.close();
		return objecArraytList;
	}

	// public MaterialStorage createMaterialStorage(MaterialStorageFilter mf) {
	// EntityManager em = emf.createEntityManager();
	// sql = "INSERT INTO dbo.wzrkdwj (,,,,,,) VALUES(?1,?2)";
	// }
	@Transactional
	public void updateMaterialStorage(List<MaterialStorage> materialStorages) {
		EntityManager em = emf.createEntityManager();
		for (MaterialStorage materialStorage : materialStorages) {
			sql = "UPDATE dbo.wzrkdwj SET khmc=?1,khbh=?2,kdrq=?3,ldbh=?4,cgy=?5,htbh=?6,bmbh=?7,"
					+ "bmmc=?8,ckbh=?9,ckmc=?10,rklx=?11,gcbh=?12,gcmc=?13,rkly=?14,shry=?15,"
					+ "shrq=?16,wzbh=?17,wzmc=?18,wzms=?19,rksl=?20,sssl=?21,jldw=?22,wdysm=?23,shbz=?24,"
					+ "bz=?25 WHERE wzrkd_id=?26";
			query = em.createNativeQuery(sql);
			query.setParameter(1, materialStorage.getKhmc());
			query.setParameter(2, materialStorage.getKhbh());
			query.setParameter(3, materialStorage.getKdrq());
			query.setParameter(4, materialStorage.getLdbh());
			query.setParameter(5, materialStorage.getCgy());
			query.setParameter(6, materialStorage.getHtbh());
			query.setParameter(7, materialStorage.getBmbh());
			query.setParameter(8, materialStorage.getBmmc());
			query.setParameter(9, materialStorage.getCkbh());
			query.setParameter(10, materialStorage.getCkmc());
			query.setParameter(11, materialStorage.getRklx());
			query.setParameter(12, materialStorage.getGcbh());
			query.setParameter(13, materialStorage.getGcmc());
			query.setParameter(14, materialStorage.getRkly());
			query.setParameter(15, materialStorage.getShry());
			query.setParameter(16, materialStorage.getShrq());
			query.setParameter(17, materialStorage.getWzbh());
			query.setParameter(18, materialStorage.getWzmc());
			query.setParameter(19, materialStorage.getWzms());
			query.setParameter(20, materialStorage.getRksl());
			query.setParameter(21, materialStorage.getSssl());
			query.setParameter(22, materialStorage.getJldw());
			query.setParameter(23, materialStorage.getWdysm());
			query.setParameter(24, materialStorage.getShbz());
			query.setParameter(25, materialStorage.getBz());
			query.setParameter(26, materialStorage.getWzrkd_id());
			query.executeUpdate();
		}
		em.close();
	}

	@Transactional
	public void delteteMaterialStorage(long wzrkdId) {
		EntityManager em = emf.createEntityManager();
		sql = "DELETE FROM dbo.wzrkdwj WHERE wzrkd_id=?1";
		em.createNativeQuery(sql).setParameter(1, 31558).executeUpdate();
		em.close();
	}
}
