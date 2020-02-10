package br.com.ubs.batch.process;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

import br.com.ubs.batch.dto.ProdutoDto;
import br.com.ubs.batch.model.Produto;

public class CustomItemProcessor implements ItemProcessor<ProdutoDto, Produto> {

	private String file_name;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		JobExecution jobExecution = stepExecution.getJobExecution();
		for (StepExecution step : jobExecution.getStepExecutions()) {
			if(step.getExecutionContext().containsKey("fileName")) {
				this.file_name = step.getExecutionContext().getString("fileName");
				break;
			}
		}
	}

	@Override
	public Produto process(ProdutoDto ProdutoDto) throws Exception {
		Produto produto = new Produto(ProdutoDto, this.file_name);
		System.out.println("Processing..."+ this.file_name + " " + produto.toString());
		return produto;
	}

}
