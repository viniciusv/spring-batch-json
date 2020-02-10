package br.com.ubs.batch.config;

import java.net.MalformedURLException;
import java.text.ParseException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.json.GsonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.gson.Gson;

import br.com.ubs.batch.dto.ProdutoDto;
import br.com.ubs.batch.model.Produto;
import br.com.ubs.batch.partitioner.CustomMultiResourcePartitioner;
import br.com.ubs.batch.process.CustomItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchJsonConfig {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Autowired
	DataSource dataSource;

	@Value("${diretorio.json}")
	private Resource[] diretorioJson;

	@Bean(name = "partitionerJob")
	public Job partitionerJob() throws UnexpectedInputException, MalformedURLException, ParseException {
		return jobs.get("partitionerJob")
				.start(partitionStep())
				.build();
	}

	@Bean
	public Step partitionStep() throws UnexpectedInputException, MalformedURLException, ParseException {
		return steps.get("partitionStep")
				.partitioner("slaveStep", partitioner())
				.step(slaveStep())
				.taskExecutor(taskExecutor())
				.build();
	}

	@Bean
	public Step slaveStep() throws UnexpectedInputException, MalformedURLException, ParseException {
		return steps.get("slaveStep")
				.<ProdutoDto, Produto>chunk(10)
				.reader(itemReader(null))
				.processor(itemProcessor())
				.writer(itemWriter())
				.build();
	}

	@Bean
	public CustomMultiResourcePartitioner partitioner() {
		CustomMultiResourcePartitioner partitioner = new CustomMultiResourcePartitioner();
		partitioner.setResources(this.diretorioJson);
		return partitioner;
	}

	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(5);
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setQueueCapacity(5);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}

	@Bean
	@StepScope
	public JsonItemReader<ProdutoDto> itemReader(@Value("#{stepExecutionContext[fileName]}") String filename) throws UnexpectedInputException, ParseException {
		Gson objectMapper = new Gson();

		GsonJsonObjectReader<ProdutoDto> jsonObjectReader = new GsonJsonObjectReader<>(ProdutoDto.class);
		jsonObjectReader.setMapper(objectMapper);

		return new JsonItemReaderBuilder<ProdutoDto>()
				.name("itemReaderJson")
				.jsonObjectReader(jsonObjectReader)
				.resource(new ClassPathResource("json/"+filename))
				.build();
	}

	@Bean
	@StepScope
	public CustomItemProcessor itemProcessor() {
		return new CustomItemProcessor();
	}

	@Bean
	@StepScope
	public JdbcBatchItemWriter<Produto> itemWriter() {
		JdbcBatchItemWriter<Produto> itemWriter = new JdbcBatchItemWriter<Produto>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO produto (file_import,industry,origin,price,product,quantity,type,volume) VALUES (:file_import_name, :industry, :origin, :price, :product, :quantity, :type, :volume)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Produto>());
		return itemWriter;
	}

}
