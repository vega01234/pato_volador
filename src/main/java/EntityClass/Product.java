package EntityClass;

public class Product {
    
    private int id_product;
    private String name_product;
    private float price_product;
    private String img_path_product;
    
    // Constructors 
    public Product() {
    }

    public Product(int id_product, String name_product, float price_product, String img_path_product) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.price_product = price_product;
        this.img_path_product = img_path_product;
    }
    
    // Getters and Setters
    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public float getPrice_product() {
        return price_product;
    }

    public void setPrice_product(float price_product) {
        this.price_product = price_product;
    }

    public String getImg_path_product() {
        return img_path_product;
    }

    public void setImg_path_product(String img_path_product) {
        this.img_path_product = img_path_product;
    }
    
}
