package com.json_xml.parse.constants;

public class Paths {


    public static final String REPORT_EX_CHOOSE = "\nEnter 'END'  or EX number between 1 and 10: \n";

    //Exercises
    public static final String EX_1 = "\t1.\tProducts in Range";
    public static final String EX_2 = "\t2.\tSuccessfully Sold Products";
    public static final String EX_3 = "\t3.\tCategories by Products Count";
    public static final String EX_4 = "\t4.\tUsers and Products";
    public static final String EX_5 = "\t5.\tOrdered Customers";
    public static final String EX_6 = "\t6.\tCars from Make Toyota";
    public static final String EX_7 = "\t7.\tLocal Suppliers";
    public static final String EX_8 = "\t8.\tCars with Their List of Parts";
    public static final String EX_9 = "\t9.\tTotal Sales by Customer";
    public static final String EX_10 = "\t10.\tSales with Applied Discount";
    public static final String END = "\n\tEND\tto terminate program\n";

    //input files path
    public static final String USER_JSON_FILEPATH = "src/main/resources/files/json/users.json";
    public static final String CATEGORY_JSON_FILEPATH = "src/main/resources/files/json/categories.json";
    public static final String PRODUCT_JSON_FILEPATH = "src/main/resources/files/json/products.json";
    public static final String CAR_JSON_FILEPATH = "src/main/resources/files/json/cars.json";
    public static final String CUSTOMER_JSON_FILEPATH = "src/main/resources/files/json/customers.json";
    public static final String PART_JSON_FILEPATH = "src/main/resources/files/json/parts.json";
    public static final String SUPPLIER_JSON_FILEPATH = "src/main/resources/files/json/suppliers.json";


    //out path
    public static final String OUT_DIR_JSON_FILEPATH = "src/main/resources/files/out/json/";
    //out files

    //part1
    public static final String OUT_PRODUCT_IN_RANGE = "product-in-range.json";
    public static final String OUT_SELLER_SOLD_PRODUCT = "seller-success-sold-product.json";
    public static final String OUT_CATEGORY_PRODUCT_COUNT_TOTAL_PRICE = "category-product-count-total-price.json";
    public static final String OUT_USER_PRODUCT = "users-and_products.json";

    //part2
    public static final String OUT_ORDERED_CUSTOMER_FILE = "ordered-customers.json";
    public static final String OUT_CAR_TOYOTA_FILE = "toyota-cars.json";
    public static final String OUT_SUPPLIER_LOCAL_FILE = "local-suppliers.json";
    public static final String OUT_CAR_PARTS_FILE = "cars-and-parts.json";
    public static final String OUT_CUSTOMER_SALES_FILE = "customers-total-sales.json";
    public static final String OUT_SALES_DISCOUNT_FILE = "sales-discounts.json";


}
