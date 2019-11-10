package co.edu.unal.reto8_androidsqlite.model;

public class Company {

    private long id;
    private String name;
    private String url;
    private String telephone;
    private String email;
    private String productsAndServices;
    private String companyClassification;

    public Company() {
    }

    public Company(long id, String name, String url, String telephone, String email, String productsAndServices, String companyClassification) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.telephone = telephone;
        this.email = email;
        this.productsAndServices = productsAndServices;
        this.companyClassification = companyClassification;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductsAndServices() {
        return productsAndServices;
    }

    public void setProductsAndServices(String productsAndServices) {
        this.productsAndServices = productsAndServices;
    }

    public String getCompanyClassification() {
        return companyClassification;
    }

    public void setCompanyClassification(String companyClassification) {
        this.companyClassification = companyClassification;
    }

    @Override
    public String toString() {
        return "Company id: " + id + "\n" +
                "Name: " + name + "\n" +
                "Url: " + url + "\n" +
                "Telephone: " + telephone + "\n" +
                "Email: " + email + "\n" +
                "Products and services: " + productsAndServices + "\n" +
                "Classification: " + companyClassification;
    }
}
