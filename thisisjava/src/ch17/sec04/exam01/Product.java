package ch17.sec04.exam01;

import java.util.Objects;

public class Product {
	private int pno;
	private String name;
	private String company;
	private int price;
	
	public Product(int pno, String name, String company, int price) {
		this.pno = pno;
		this.name = name;
		this.company = company;
		this.price = price;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(pno);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return pno == other.pno;
	}


	public int getPno() { return pno; }
	public String getName() { return name; }
	public String getCompany() { return company; }
	public int getPrice() { return price; }

//	@Override
//	public String toString() {
//		return "Product [pno=" + pno + ", name=" + name + ", company=" + company + ", price=" + price + "]";
//	}
//	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("{")
				.append("pno:").append(pno).append(", ")
				.append("name:").append(name).append(", ")
				.append("company:" + company + ", ")
				.append("price:" + price)
				.append("}")
				.toString();
	}
	
}