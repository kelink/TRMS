//package com.dummy.domain;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//@Entity  
//@Table(name = "Authority")   
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)  
//public class Authority { 
//	/**
//	 * ô‡œﬁ±Ì
//	 */
//	@Id  
//	@GeneratedValue(strategy = GenerationType.IDENTITY)  
//	private Long id; 
//	
//	@Column(name="name") 
//	private String name;  
//	
//	@Column(name="display_name") 
//    private String displayName;  
//  
//    public Long getId() {  
//		 return id;  
//	 }  	  
//	public void setId(Long id) {  
//	    this.id = id;  
//	}
//    public String getName() {  
//        return name;  
//    }  
//  
//    public void setName(String name) {  
//        this.name = name;  
//    }  
//  
//     
//    public String getDisplayName() {  
//        return displayName;  
//    }  
//  
//    public void setDisplayName(String displayName) {  
//        this.displayName = displayName;  
//    }  
//  
//    @Override  
//    public String toString() {  
//        return displayName; 
//    }  
// }
