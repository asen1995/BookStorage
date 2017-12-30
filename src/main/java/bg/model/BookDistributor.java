package bg.model;

public class BookDistributor {

	private String nameOfDistributor;
	private String telephone;
	private String address;

	public BookDistributor() {
		nameOfDistributor = telephone = address = null;
	}

	public BookDistributor(String nameOfDistributor, String telephone, String address) {
		super();
		this.nameOfDistributor = nameOfDistributor;
		this.telephone = telephone;
		this.address = address;
	}

	@Override
	public String toString() {
		return " Книгоразпространител \n Име - " + nameOfDistributor + " \n телефон -" + telephone + "\n адрес -"
				+ address;
	}

	public final String getNameOfDistributor() {
		return nameOfDistributor;
	}

	public final void setNameOfDistributor(String nameOfDistributor) {
		this.nameOfDistributor = nameOfDistributor;
	}

	public final String getTelephone() {
		return telephone;
	}

	public final void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		this.address = address;
	}

}
