package org.zsq.job.core.jobs;

import lombok.extern.slf4j.Slf4j;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.AbstractSimpleElasticJob;

/**
 * 示例：elasticJob simple 类型
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: SimpleJob.java, V1.0.0 2015年11月19日 下午8:48:34 $
 */
@Slf4j
public class SimpleJob extends AbstractSimpleElasticJob {

	@Override
	public void process(JobExecutionMultipleShardingContext context) {
		log.info("JOB NAME {}",context.getJobName());
		log.info("任务参数 {}",context.getJobParameter());
	}

}
