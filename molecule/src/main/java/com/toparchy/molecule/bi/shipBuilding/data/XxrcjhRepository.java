package com.toparchy.molecule.bi.shipBuilding.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.toparchy.molecule.bi.shipBuilding.model.Xxrcjh;

@Model
public class XxrcjhRepository {
	@PersistenceUnit(name = "molecule")
	private EntityManagerFactory emf;
	private String sql;
	private Query query;
	private List<Xxrcjh> objecArraytList;
	private List<XxrcjhKanban> sxrcjhKanbans;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<XxrcjhKanban> findFdByGdbh(String gcbh) {
		EntityManager em = emf.createEntityManager();
		sql = "SELECT t1.ppk_varcode,t1.gcbh,t1.gcmc,t1.sgxm,t1.xmhf,t1.jkxm,t1.glpg,t1.gllx,t1.sjsj,t2.kbys "
				+ "FROM   (SELECT * "
				+ "FROM   (SELECT b.*, "
				+ "ROW_NUMBER() "
				+ "OVER ( "
				+ "partition BY b.sgxm "
				+ "ORDER BY b.sjsj DESC) num "
				+ "FROM   (SELECT ppk_varcode,gcmc,gcbh,cxmc,sgxm,xmhf,xmsx,jkxm,glpg,gllx,sjsj, "
				+ "(SELECT ISNULL(kbys, '0') "
				+ "FROM   dbo.RW_B_Jkxm_Dm "
				+ "WHERE  dbo.RW_B_Jkxm_Dm.xmhf = RW_D_Scjkfk_DJ.xmhf "
				+ "AND dbo.RW_B_Jkxm_Dm.jkxm = RW_D_Scjkfk_DJ.jkxm)kbys "
				+ "FROM  dbo.RW_D_Scjkfk_DJ "
				+ "WHERE  xmhf = '分段') b) a "
				+ "WHERE  sjsj IS NOT NULL "
				+ "AND num = 1) t1, "
				+ "RW_B_Jkxm_Dm t2 "
				+ "WHERE  t1.jkxm = t2.jkxm "
				+ "AND t1.gcbh = ?1 " + "ORDER  BY t1.sgxm,t1.xmsx;";

		query = em.createNativeQuery(sql, Xxrcjh.class);
		query.setParameter(1, gcbh);
		// query.setFirstResult(start);
		// query.setMaxResults(max);
		objecArraytList = query.getResultList();
		sxrcjhKanbans = new ArrayList<XxrcjhKanban>();
		for (Xxrcjh xxrcjh : objecArraytList) {
			XxrcjhKanban xxrcjhFdKanban = new XxrcjhKanban();
			xxrcjhFdKanban.setSgxm(xxrcjh.getSgxm());
			xxrcjhFdKanban.setKbys(xxrcjh.getKbys());
			sxrcjhKanbans.add(xxrcjhFdKanban);
		}
		em.close();
		return sxrcjhKanbans;
	}

	public List<XxrcjhKanban> findZzByGdbh(String gcbh) {
		EntityManager em = emf.createEntityManager();
		sql = "SELECT t1.ppk_varcode,t1.gcbh,t1.gcmc,t1.sgxm,t1.xmhf,t1.jkxm,t1.glpg,t1.gllx,t1.sjsj,t2.kbys "
				+ "FROM   (SELECT * "
				+ "FROM   (SELECT b.*, "
				+ "ROW_NUMBER() "
				+ "OVER ( "
				+ "partition BY b.sgxm "
				+ "ORDER BY b.sjsj DESC) num "
				+ "FROM   (SELECT ppk_varcode,gcmc,gcbh,cxmc,sgxm,xmhf,xmsx,jkxm,glpg,gllx,sjsj, "
				+ "(SELECT ISNULL(kbys, '0') "
				+ "FROM   dbo.RW_B_Jkxm_Dm "
				+ "WHERE  dbo.RW_B_Jkxm_Dm.xmhf = RW_D_Scjkfk_DJ.xmhf "
				+ "AND dbo.RW_B_Jkxm_Dm.jkxm = RW_D_Scjkfk_DJ.jkxm)kbys "
				+ "FROM  dbo.RW_D_Scjkfk_DJ "
				+ ") b) a "
				+ "WHERE  sjsj IS NOT NULL "
				+ "AND num = 1) t1, "
				+ "RW_B_Jkxm_Dm t2 "
				+ "WHERE  t1.jkxm = t2.jkxm "
				+ "AND t1.gcbh = ?1 " + "ORDER  BY t1.sgxm,t1.xmsx;";

		query = em.createNativeQuery(sql, Xxrcjh.class);
		query.setParameter(1, gcbh);
		// query.setFirstResult(start);
		// query.setMaxResults(max);
		objecArraytList = query.getResultList();
		sxrcjhKanbans = new ArrayList<XxrcjhKanban>();
		for (Xxrcjh xxrcjh : objecArraytList) {
			XxrcjhKanban xxrcjhFdKanban = new XxrcjhKanban();
			xxrcjhFdKanban.setSgxm(xxrcjh.getSgxm().replace("+", "_x2B_")
					.replace("-", "_x2B_"));
			xxrcjhFdKanban.setKbys(xxrcjh.getKbys());
			sxrcjhKanbans.add(xxrcjhFdKanban);
		}
		em.close();
		return sxrcjhKanbans;
	}

	class XxrcjhKanban {
		private String sgxm;
		private String kbys;

		public String getSgxm() {
			return sgxm;
		}

		public void setSgxm(String sgxm) {
			this.sgxm = sgxm;
		}

		public String getKbys() {
			return kbys;
		}

		public void setKbys(String kbys) {
			this.kbys = kbys;
		}

	}
}
