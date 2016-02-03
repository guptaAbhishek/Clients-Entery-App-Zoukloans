package com.example.abhishek.zoukloanscliententry;

/**
 * Created by abhishek on 29/01/16.
 */
public class Clients {
    private String client_name;
    private String client_mobile;
    private String client_email;
    private String pan;
    private String company_name;
    private String business_type;
    private String loan_amount;
    private String description;

    public String getClient_name(){
        return client_name;
    }

    public String getClient_mobile(){
        return client_mobile;
    }

    public String getClient_email(){
        return client_email;
    }

    public String getPan(){
        return pan;
    }

    public String getCompany_name(){
        return company_name;
    }

    public String getBusiness_type(){
        return business_type;
    }

    public String getLoan_amount(){
        return loan_amount;
    }

    public String getDescription(){
        return description;
    }

    public void setClient_name(String client_name){
        this.client_name = client_name;
    }

    public void setClient_mobile(String client_mobile){
        this.client_mobile = client_mobile;
    }

    public void setClient_email(String client_email){
        this.client_email = client_email;
    }

    public void setPan(String pan){
        this.pan = pan;
    }

    public void setCompany_name(String company_name){
        this.company_name = company_name;
    }

    public void setBusiness_type(String business_type){
        this.business_type = business_type;
    }

    public void setLoan_amount(String loan_amount){
        this.loan_amount = loan_amount;
    }

    public void setDescription(String description){
        this.description = description;
    }


}
