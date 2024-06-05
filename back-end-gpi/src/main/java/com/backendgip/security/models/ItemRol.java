package com.backendgip.security.models;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class ItemRol {


	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;


	    @ManyToOne(fetch = FetchType.EAGER)
	    private Item item;


	    @ManyToOne
	    private SubmenuRol submenuRol;

		@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "itemRol")
	  //  @JsonIgnore
	    private List<SubItemRol> subItemRol;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Item getItem() {
			return item;
		}


		public void setItem(Item item) {
			this.item = item;
		}


		public SubmenuRol getSubmenuRol() {
			return submenuRol;
		}


		public void setSubmenuRol(SubmenuRol submenuRol) {
			this.submenuRol = submenuRol;
		}


		public List<SubItemRol> getSubItemRol() {
			return subItemRol;
		}


		public void setSubItemRol(List<SubItemRol> subItemRol) {
			this.subItemRol = subItemRol;
		}

		


	/*	@Override
		public String toString() {
			return "ItemRol [id=" + id + ", item=" + item + "]";
		}*/
	       


	
}
