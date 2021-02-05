package domain;

import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

public class Customer {
	private Long cust_id;
	private String cust_name;
	private String cust_source;
	private String cust_level;
	private String cust_phone;
	private String cust_mobile;
	private String cust_image;

	//客户和联系人一对多
	@OneToMany
	private Set<LinkMan> linkman = new HashSet<LinkMan>();

	public String getCust_image() {
		return cust_image;
	}

	public void setCust_image(String cust_image) {
		this.cust_image = cust_image;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_source() {
		return cust_source;
	}

	public void setCust_source(String cust_source) {
		this.cust_source = cust_source;
	}

	public String getCust_level() {
		return cust_level;
	}

	public void setCust_level(String cust_level) {
		this.cust_level = cust_level;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getCust_mobile() {
		return cust_mobile;
	}

	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}

	public Set<LinkMan> getLinkman() {
		return linkman;
	}

	public void setLinkman(Set<LinkMan> linkman) {
		this.linkman = linkman;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"cust_id=" + cust_id +
				", cust_name='" + cust_name + '\'' +
				", cust_source='" + cust_source + '\'' +
				", cust_level='" + cust_level + '\'' +
				", cust_phone='" + cust_phone + '\'' +
				", cust_mobile='" + cust_mobile + '\'' +
				'}';
	}
}
