/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 *
 * @author Onur Can
 */
public class Blockchain {
    private DateFormat dateFormat;
    private LinkedList<Block> list;
    private LinkedList<Transaction> pendingTransactions;
    private float miningReward;
    
    public Blockchain(){
       list=new LinkedList<Block>();
       dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
       miningReward=100;
       pendingTransactions= new LinkedList<Transaction>();
    }
    public LinkedList<Block> getList(){
        return this.list;
    }
    public Block createGenesisBlock()throws NoSuchAlgorithmException{
        
        if(list.isEmpty()){
           
        
        Block genesis = new Block(pendingTransactions,"17/2/2020","previousHash") ;
        list.add(genesis);
        pendingTransactions.clear();
        return genesis;
        }else{
             LOGGER.warning("You are already have a genesis block");
             return null;
        }
    }
    public void addBlock(String rewardAddress)throws NoSuchAlgorithmException{
        Date date1 = new Date();
        Block newBlock =new Block(pendingTransactions,this.dateFormat.format(date1),list.getLast().hash);
        this.list.add(newBlock);
        pendingTransactions.clear();
        Transaction transaction = new Transaction("mining reward",rewardAddress,miningReward);
        pendingTransactions.add(transaction);   
        
    }
    public void createTransaction(String fromAddress ,String toAddress,float amount){
       Transaction transaction=new Transaction(fromAddress,toAddress,amount);
       pendingTransactions.add(transaction);
       
    }
/*    public void deneme(){
        LOGGER.warning(this.list.get(0).transactionsBlock.get(1).getFromAddress());
          LOGGER.warning(this.list.get(5).transactionsBlock.size()+"");
    }*/
 
    public float getBalanceOfAddress(String address){
        float balance=0;
      
        for(int i =0;i<this.list.size();i++){
          
            for(int j = 0;j<this.list.get(i).transactionsBlock.size();j++){
          
                
                if(this.list.get(i).transactionsBlock.get(j).getFromAddress().equals(address)){
                    
                       balance-=this.list.get(i).transactionsBlock.get(j).getAmount();
                      
                }else if(this.list.get(i).transactionsBlock.get(j).getToAddress().equals(address)){
                      balance+=this.list.get(i).transactionsBlock.get(j).getAmount();
                }
            
            }
        
        }
        return balance;
    }
    public boolean isChainInvalid() throws NoSuchAlgorithmException{

        for(int i=1;i<list.size();i++){
                  if(this.list.get(i-1).hash != this.list.get(i).previousHash){
                     LOGGER.warning("invalid chain "+i+"or"+(i-1));
                
                     return false;
                   
                  }
        }for(int i=0;i<list.size();i++){
              if(!(this.list.get(i).hash.equals(this.list.get(i).calculateHash()))){
                     // System.out.println(this.list.get(i).hash+"     "+this.list.get(i).calculateHash()+"   "+i+"            "+this.list.get(i).calculateHash());
                      LOGGER.warning("chain has broken"+i);
                      
                  return false;
                  }
        }
                
            LOGGER.warning("chain valid");

                      return true;
    }
        
    public class Block {
    private String timeStamp;
    private String previousHash;
    private LinkedList<Transaction> transactionsBlock;
    private String hash;
    private int nonce=0;
    private int difficulty=3;

    public Block(LinkedList<Transaction> transactions,String timeStamp,String previousHash) throws NoSuchAlgorithmException {
        this.transactionsBlock = (LinkedList<Transaction>) transactions.clone();
        this.timeStamp=timeStamp;
        this.previousHash=previousHash;
        this.hash = mineBlock(this.difficulty);
      
    }
    public String getTimeStamp(){
        return this.timeStamp;
    }
    public String getPreviousHash(){
        return this.previousHash;
    }
    public LinkedList<Transaction> getTransactions(){
        return this.transactionsBlock;
    }
    public String getHash(){
        return this.hash;
    }
    public int getNonce(){
        return this.nonce;
    }
    
    
    public String calculateHash() throws NoSuchAlgorithmException {
       String blockProperty =this.timeStamp+this.previousHash+this.transactionsBlock+String.valueOf(this.nonce);
        

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(blockProperty.getBytes(StandardCharsets.UTF_8));
        // bytes to hex
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
       
     
        return sb.toString();

    }
    public String mineBlock(int difficulty) throws NoSuchAlgorithmException{
      String currentHash= calculateHash();
      String zero="";
      for(int i=0;i<difficulty;i++)
         zero = zero.concat("0");
    
       while(!(currentHash.substring(0,difficulty).equals(zero))){
            this.nonce++;
          currentHash= calculateHash();
          
       }
      return currentHash;  
    }


}
}
