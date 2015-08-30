package com.jzsoft.platform2.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Job.class)
public class Job_ {
	public static volatile SingularAttribute<Job, String> id;
	public static volatile SingularAttribute<Job, String> workTicketNumber;
	public static volatile SingularAttribute<Job, String> jobName;
	// public static volatile SingularAttribute<Job, Integer> state;
	public static volatile SingularAttribute<Job, String> jobRecord;
	public static volatile SingularAttribute<Job, String> position;
	public static volatile SingularAttribute<Job, String> image;
	public static volatile SingularAttribute<Job, String> audio;
	public static volatile SingularAttribute<Job, Long> startTime;
}
