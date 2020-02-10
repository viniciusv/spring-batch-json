package br.com.ubs.batch.process;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;

import br.com.ubs.batch.model.Produto;

public class CustomItemWriter implements ItemWriter<Produto>{
	
	private DataSource dataSource;
	
    public CustomItemWriter(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void write(List<? extends Produto> items) throws Exception {
    	System.out.println("Processing..." + items.toString());
    }

}
