/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.p_uts_23090032_b_2025;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.Arrays;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Administrator
 */
public class CRUD_23090032_B_2025 {
         

    public static void main(String[] args) {
        String URL = "mongodb://localhost:27017/";
        try (MongoClient mongo = MongoClients.create(URL)) {
            MongoDatabase DB = mongo.getDatabase("uts_23090032_B_2025");
            MongoCollection<Document> collection = DB.getCollection("coll_23090032_B_2025");
            //menambahkan 3 data (document)
            Document product1 = new Document("nama", "alifiashasa") //string
                    .append("warna", Arrays.asList("white", "black", "pink")) //list
                    .append("umur", 21); //ineteger
            
            Document product2 = new Document("merk", "laptop") //string
                    .append("warna", Arrays.asList("white", "black")) //list
                    .append("quantity", 50); //ineteger
   
            Document product3 = new Document("nama", "sepatu") //string
                    .append("warna", Arrays.asList("white", "black", "ungu")) //list
                    .append("quantity", 33); //ineteger
            collection.insertMany(Arrays.asList(product1, product2, product3));
            //update data
            Bson filter = Filters.eq("nama", "alifiashasa"); // Membuat filter untuk mencari dokumen dengan nama = "alifiashasa"
            Bson update = Updates.set("kelas", "4B"); // Membuat update untuk menambahkan/menetapkan field "kelas" dengan nilai "4B"
            collection.updateOne(filter, update); //menjalankan update
            //delete
            Bson fil = Filters.eq("nama", "alifiashasa"); // Membuat filter ulang untuk dokumen dengan nama = "alifiashasa"
            Bson del = Updates.unset("kelas"); // Membuat perintah untuk menghapus (unset) field "kelas"
            collection.updateOne(fil, del); // Menjalankan perintah
            //searching
            Bson f = Filters.eq("nama", "alifiashasa");// Membuat filter untuk mencari dokumen dengan nama = "alifiashasa"
            FindIterable<Document> SEARCH = collection.find(f); // Menjalankan pencarian menggunakan filter tersebut
            for (Document d : SEARCH) { // Menampilkan hasil pencarian ke console dalam format JSON
                System.out.println(d.toJson());
            }
        }
    }
}
