package com.indra.posbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Indrap on 9/08/2017.
 */

@Document(collection = "package")
public class Package {

    @Id
    private String packageId;
    private String code;
    private String name;
    private String description;
    private List<Item> items;
    @Transient
    private List<Item> itemService;
    @Transient
    private List<Item> itemAdmin;
    @Transient
    private List<Item> itemStock;
    @Transient
    private List<Item> itemFacility;
    @Transient
    private List<Item> itemOther;

    public Package(){

    }

    public Package(String packageId){
        this.packageId = packageId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItemService() {
        return itemService;
    }

    public void setItemService(List<Item> itemService) {
        this.itemService = itemService;
    }

    public List<Item> getItemAdmin() {
        return itemAdmin;
    }

    public void setItemAdmin(List<Item> itemAdmin) {
        this.itemAdmin = itemAdmin;
    }

    public List<Item> getItemStock() {
        return itemStock;
    }

    public void setItemStock(List<Item> itemStock) {
        this.itemStock = itemStock;
    }

    public List<Item> getItemFacility() {
        return itemFacility;
    }

    public void setItemFacility(List<Item> itemFacility) {
        this.itemFacility = itemFacility;
    }

    public List<Item> getItemOther() {
        return itemOther;
    }

    public void setItemOther(List<Item> itemOther) {
        this.itemOther = itemOther;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    /*
    items hold the data on database
    for easier ui, the display will be split to specific array based on item type
     */
    public void breakItems(){
        if(!this.getItems().isEmpty()){
            //clean all
            this.setItemAdmin(new ArrayList<Item>());
            this.setItemFacility(new ArrayList<Item>());
            this.setItemService(new ArrayList<Item>());
            this.setItemStock(new ArrayList<Item>());
            this.setItemOther(new ArrayList<Item>());

            for (Item item: this.getItems()){
                switch (item.getType()){
                    case "ADMIN":
                        this.getItemAdmin().add(item);
                        break;
                    case "SERVICE":
                        this.getItemService().add(item);
                        break;
                    case "STOCK":
                        this.getItemStock().add(item);
                        break;
                    case "FACILITY":
                        this.getItemFacility().add(item);
                        break;
                    default:
                        this.getItemOther().add(item);
                        break;
                }
            }
            //clean up
            this.setItems(new ArrayList<Item>());
        }

    }

    /*
    items hold the data on database
    all specific item placeholder merge to generic items before saved to database
     */
    public void mergeItems(){
        this.setItems(new ArrayList<Item>());
        if(! this.getItemAdmin().isEmpty()){
            this.getItems().addAll(this.getItemAdmin());
            this.setItemAdmin(new ArrayList<Item>());
        }
        if( !this.getItemService().isEmpty()){
            this.getItems().addAll(this.getItemService());
            this.setItemService(new ArrayList<Item>());
        }
        if( !this.getItemStock().isEmpty()){
            this.getItems().addAll(this.getItemStock());
            this.setItemStock(new ArrayList<Item>());
        }
        if( !this.getItemFacility().isEmpty()){
            this.getItems().addAll(this.getItemFacility());
            this.setItemFacility(new ArrayList<Item>());
        }
        if( !this.getItemOther().isEmpty()){
            this.getItems().addAll(this.getItemOther());
            this.setItemOther(new ArrayList<Item>());
        }

    }
}
