package com.toparchy.molecule.bi.shipBuilding.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import com.toparchy.molecule.bi.shipBuilding.model.Gcxbjh;

@Model
public class GcxbjhRepository {
	@PersistenceUnit(name = "molecule")
	private EntityManagerFactory emf;
	private String sql;
	private Query query;
	private List<Gcxbjh> objecArraytList;
	private List<GcxbjhDataHandle> gcxbjhDataHandles;
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	public List<GcxbjhDataHandle> findByGcbh(String gcbh) {
		EntityManager em = emf.createEntityManager();
		sql = "select ppk_varcode, gcbh,jhkgsj1,sjkgsj1,jhkgsj2,sjkgsj2,jhkgsj3,sjkgsj3,jhkgsj4,sjkgsj4,jhkgsj5,sjkgsj5 from dbo.JH_D_Gcxbjh_DJ where gcbh=?1";
		query = em.createNativeQuery(sql, Gcxbjh.class);
		query.setParameter(1, gcbh);
		objecArraytList = query.getResultList();
		gcxbjhDataHandles = new ArrayList<GcxbjhDataHandle>();
		int i = 1;
		for (Gcxbjh gcxbjh : objecArraytList) {
			GcxbjhDataHandle gcxbjhDataHandle = new GcxbjhDataHandle();
			gcxbjhDataHandle.setId(i);
			gcxbjhDataHandle.setContent("开工-进坞");
			gcxbjhDataHandle.setStart(formatter.format(gcxbjh.getJhkg()));
			gcxbjhDataHandle.setEnd(formatter.format(gcxbjh.getJhjw()));
			gcxbjhDataHandle.setGroup(1);
			gcxbjhDataHandles.add(gcxbjhDataHandle);
			i++;
			gcxbjhDataHandle = new GcxbjhDataHandle();
			gcxbjhDataHandle.setId(i);
			gcxbjhDataHandle.setContent("进坞-出坞");
			gcxbjhDataHandle.setStart(formatter.format(gcxbjh.getJhjw()));
			gcxbjhDataHandle.setEnd(formatter.format(gcxbjh.getJhcw()));
			gcxbjhDataHandle.setGroup(1);
			gcxbjhDataHandles.add(gcxbjhDataHandle);
			i++;
			gcxbjhDataHandle = new GcxbjhDataHandle();
			gcxbjhDataHandle.setId(i);
			gcxbjhDataHandle.setContent("出坞-试航");
			gcxbjhDataHandle.setStart(formatter.format(gcxbjh.getJhcw()));
			gcxbjhDataHandle.setEnd(formatter.format(gcxbjh.getJhsh()));
			gcxbjhDataHandle.setGroup(1);
			gcxbjhDataHandles.add(gcxbjhDataHandle);
			i++;
			gcxbjhDataHandle = new GcxbjhDataHandle();
			gcxbjhDataHandle.setId(i);
			gcxbjhDataHandle.setContent("试航交船");
			gcxbjhDataHandle.setStart(formatter.format(gcxbjh.getJhsh()));
			gcxbjhDataHandle.setEnd(formatter.format(gcxbjh.getJhjc()));
			gcxbjhDataHandle.setGroup(1);
			gcxbjhDataHandles.add(gcxbjhDataHandle);
			i++;
		}
		em.close();
		return gcxbjhDataHandles;
	}

	class GcxbjhDataHandle {
		private int id;
		private String content;
		private String start;
		private String end;
		private int group;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getStart() {
			return start;
		}

		public void setStart(String start) {
			this.start = start;
		}

		public String getEnd() {
			return end;
		}

		public void setEnd(String end) {
			this.end = end;
		}

		public int getGroup() {
			return group;
		}

		public void setGroup(int group) {
			this.group = group;
		}

	}
}
