package com.toparchy.molecule.bi.shipBuilding.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.toparchy.molecule.bi.shipBuilding.model.Wldzjh;

@Model
public class WldzjhRepository {
	@PersistenceUnit(name = "molecule")
	private EntityManagerFactory emf;
	private String sql;
	private Query query;
	private List<Wldzjh> objecArraytList;
	private List<WldzjhDataHandle> wldzjhDataHandles;

	public List<WldzjhDataHandle> findByGcbh(String gcbh) {
		EntityManager em = emf.createEntityManager();
		sql = "SELECT ppk_varcode,gcbh,xh,PostTask,FrontTask,CriticalNode FROM dbo.JH_D_Wldzjh_DJ WHERE gcbh = ?1 ORDER BY xh";
		query = em.createNativeQuery(sql, Wldzjh.class);
		query.setParameter(1, gcbh);
		objecArraytList = query.getResultList();
		wldzjhDataHandles = new ArrayList<WldzjhDataHandle>();
		WldzjhDataHandle wldzjhDataHandle;
		for (Wldzjh wldzjh : objecArraytList) {
			if (wldzjh.getPostTask() != null
					&& !wldzjh.getPostTask().equals("")) {
				if (wldzjh.getPostTask().split(",").length > 1) {
					for (String s : wldzjh.getPostTask().split(",")) {
						wldzjhDataHandle = new WldzjhDataHandle();
						wldzjhDataHandle.setSource("#DZ" + wldzjh.getXh());
						wldzjhDataHandle.setTarget("#DZ" + s);
						for (Wldzjh wldzjh2 : objecArraytList) {
							if (wldzjh2.getXh().equals(s))
								if (wldzjh2.getCriticalNode() != null) {
									if (wldzjh2.getCriticalNode().equals("1"))
										wldzjhDataHandle.setCriticalPath("1");
									else
										wldzjhDataHandle.setCriticalPath("");
								}
						}
						wldzjhDataHandles.add(wldzjhDataHandle);
					}
				} else {
					wldzjhDataHandle = new WldzjhDataHandle();
					wldzjhDataHandle.setSource("#DZ" + wldzjh.getXh());
					wldzjhDataHandle.setTarget("#DZ" + wldzjh.getPostTask());
					for (Wldzjh wldzjh2 : objecArraytList) {
						if (wldzjh2.getXh().equals(wldzjh.getPostTask()))
							if (wldzjh2.getCriticalNode() != null) {
								if (wldzjh2.getCriticalNode().equals("1"))
									wldzjhDataHandle.setCriticalPath("1");
								else
									wldzjhDataHandle.setCriticalPath("");
							}
					}
					wldzjhDataHandles.add(wldzjhDataHandle);
				}
			} else {
				wldzjhDataHandle = new WldzjhDataHandle();
				wldzjhDataHandle.setSource("#DZ" + wldzjh.getXh());
				wldzjhDataHandle.setTarget("");
				wldzjhDataHandle.setCriticalPath("");
				wldzjhDataHandles.add(wldzjhDataHandle);
			}
		}
		Collections.sort(wldzjhDataHandles);
		em.close();
		return wldzjhDataHandles;
	}

	class WldzjhDataHandle implements Comparable<WldzjhDataHandle> {
		private String source;
		private String target;
		private String criticalPath;

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}

		public String getCriticalPath() {
			return criticalPath;
		}

		public void setCriticalPath(String criticalPath) {
			this.criticalPath = criticalPath;
		}

		@Override
		public int compareTo(WldzjhDataHandle o) {
			if (!this.source.equals(o.getSource())) {
				if (Integer.valueOf(this.source.replace("#DZ", "")) <= Integer
						.valueOf(o.getSource().replace("#DZ", ""))) {
					return -1;
				} else {
					return 1;
				}
			} else {
				if (this.target.equals(""))
					return -1;
				else if (Integer.valueOf(this.target.replace("#DZ", "")) <= Integer
						.valueOf(o.getTarget().replace("#DZ", ""))) {
					return -1;
				} else {
					return 1;
				}
			}
		}
	}
}
