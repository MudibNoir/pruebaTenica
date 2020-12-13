package com.pruebatecnica.spring.product.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.pruebatecnica.spring.product.batch.dao.ProductDao;
import com.pruebatecnica.spring.product.batch.listener.JobListener;
import com.pruebatecnica.spring.commons.repository.models.entity.Product;
import com.pruebatecnica.spring.product.batch.processor.ProductItemProcessor;
import com.pruebatecnica.spring.product.batch.writer.ProcessedProductWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private ProductDao productDao;

	@Bean
	public Job readCSVFileJob(JobListener listener) {
		return jobBuilderFactory.get("readCSVFileJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(step1())
				.next(step2())
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Product, Product>chunk(5)
				.reader(reader("productos.csv"))
				.processor(processor1())
				.writer(CsvWriter())
				.build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<Product,Product>chunk(5)
				.reader(reader("output/productos-procesados.csv"))
				.writer(writer2())
				.build();
	}


	@Bean
	public FlatFileItemReader<Product> reader(String resource) {

		
		return new FlatFileItemReaderBuilder<Product>().name("ItemReader")
				.resource(new ClassPathResource(resource))
				.linesToSkip(1)
				.delimited().delimiter(";")
				.names(new String[] { "productName", "productDescription", "productPrice", "productUnits" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {
					{
						setTargetType(Product.class);
					}
				})
				.build();
	}

	@Bean
	public FlatFileItemWriter<Product> CsvWriter() {
		FlatFileItemWriter<Product> writer = new FlatFileItemWriter<>();
		writer.setResource(new FileSystemResource("output/productos-procesados.csv"));
		writer.setAppendAllowed(false);

		BeanWrapperFieldExtractor<Product> fieldExtractor = new BeanWrapperFieldExtractor<>();
		DelimitedLineAggregator<Product> lineAggregator = new DelimitedLineAggregator<>();

		fieldExtractor.setNames(new String[] { "productName", "productDescription", "productPrice", "productUnits" });

		lineAggregator.setDelimiter(";");
		lineAggregator.setFieldExtractor(fieldExtractor);

		writer.setLineAggregator(lineAggregator);

		return writer;
	}

	@Bean
	public ProductItemProcessor processor1() {
		return new ProductItemProcessor();
	}

	@Bean
	public ProcessedProductWriter writer2() {
		return new ProcessedProductWriter(productDao);
	}

}
