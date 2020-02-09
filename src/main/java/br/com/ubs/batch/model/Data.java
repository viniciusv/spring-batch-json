package br.com.ubs.batch.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Data implements Serializable {

	private static final long serialVersionUID = -8308780015180744407L;

	private LinkedList<Produto> data;
	
	public Data() {}

	public Data(LinkedList<Produto> data) {
		super();
		this.data = data;
	}

	public LinkedList<Produto> getData() {
		return data;
	}

	public void setData(LinkedList<Produto> data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
}
