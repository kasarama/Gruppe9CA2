
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package entities;
//
//import java.io.Serializable;
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
///**
// *
// * @author Selina A.S.
// */
//@Entity
//
//public class CityInfo implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    private String zipcode;
//    private String city;
//
//    
//    //Fixed, added cascade.
//    //@OneToMany(mappedBy = "cityInfo")
//    //List<Address> addressList;
//    //private Address address; 
//
//    public CityInfo() {
//    }
//    
//    public CityInfo(String city, String zipcode) {
//        this.zipcode = zipcode;
//        this.city = city;
//    }
//    
//    public Integer getId() {
//        return id;
//    }
//   
//    public String getZipcode() {
//        return zipcode;
//    }
//
//    public void setZipcode(String zipcode) {
//        this.zipcode = zipcode;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
////    public Address getAddress() {
////        return address;
////    }
////
////    public void setAddress(Address address) {
////        this.address = address;
////    }
//    
//    
//    /**
//    Nedenstående metode giver os lov til at tilføje en adresse.
//    * Og lige nu gemmer vi vel ikke adressen nogen steder, så vi skal vel have listen.
//    */
//    
////    public void addAddress(Address address)
////        this.addressList.add(address)
////        if(address != null){
////           address.setCityInfo(this); 
////        }
//    
////    public List<Address> getAddress(){
////        return addressList;
////    }
//}

