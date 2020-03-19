/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.security.KeyPair;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Onur Can
 */
public class Transaction {
    private String fromAddress;
    private String toAddress;
    private float amount;
    
    public Transaction(String fromAddress,String toAddress,float amount){
       this.fromAddress=fromAddress;
       this.toAddress=toAddress;
       this.amount=amount;
       
    }
    public String getFromAddress(){
        return this.fromAddress;
    }
    public String getToAddress(){
        return this.toAddress;
    }
    public float getAmount(){
        return this.amount;
    }
   
    }
    

