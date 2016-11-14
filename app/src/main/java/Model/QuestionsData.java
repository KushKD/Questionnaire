package Model;

import java.io.Serializable;

/**
 * Created by kuush on 11/14/2016.
 */

public class QuestionsData implements Serializable {

    public String ComtactPersonName;
    public String Company_Name;
    public String State;
    public String City;
    public String Address;
    public String WebsiteAddress;
    public String ContactPersonName;
    public String ContactPersonemail;
    public String ContactPersonMobile;
    public String ContactPersonLandlineOffice;
    public String ContactPersonFax;


    private String Question_One;
    private String Question_Two;
    private String Question_Three;

    public String getContactPersonMobile() {
        return ContactPersonMobile;
    }

    public void setContactPersonMobile(String contactPersonMobile) {
        ContactPersonMobile = contactPersonMobile;
    }

    public String getContactPersonFax() {
        return ContactPersonFax;
    }

    public void setContactPersonFax(String contactPersonFax) {
        ContactPersonFax = contactPersonFax;
    }

    public String getContactPersonLandlineOffice() {
        return ContactPersonLandlineOffice;
    }

    public void setContactPersonLandlineOffice(String contactPersonLandlineOffice) {
        ContactPersonLandlineOffice = contactPersonLandlineOffice;
    }

    public String getContactPersonemail() {
        return ContactPersonemail;
    }

    public void setContactPersonemail(String contactPersonemail) {
        ContactPersonemail = contactPersonemail;
    }

    public String getContactPersonName() {
        return ContactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        ContactPersonName = contactPersonName;
    }

    public String getWebsiteAddress() {
        return WebsiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        WebsiteAddress = websiteAddress;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public String getComtactPersonName() {
        return ComtactPersonName;
    }

    public void setComtactPersonName(String comtactPersonName) {
        ComtactPersonName = comtactPersonName;
    }



    public String getQuestion_Three() {
        return Question_Three;
    }

    public void setQuestion_Three(String question_Three) {
        Question_Three = question_Three;
    }

    public String getQuestion_Two() {
        return Question_Two;
    }

    public void setQuestion_Two(String question_Two) {
        Question_Two = question_Two;
    }

    public String getQuestion_One() {
        return Question_One;
    }

    public void setQuestion_One(String question_One) {
        Question_One = question_One;
    }


}
