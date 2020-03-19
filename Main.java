/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import javaapplication1.Blockchain.Block;



/**
 *
 * @author Onur Can+"index : "+publicBook.get(i).index+"f"
 */
public class Main {
   
   public static void printTransaction(LinkedList<Block> dLedger){
            String transact = "";
            for(int i = 0;i<dLedger.size();i++){
                System.out.println("\nblock: "+i+" blocktime: "+dLedger.get(i).getTimeStamp());
                for(int j=0;j<dLedger.get(i).getTransactions().size();j++){
                    System.out.println("from: "+dLedger.get(i).getTransactions().get(j).getFromAddress()+" to: "+dLedger.get(i).getTransactions().get(j).getToAddress()+" amount: "+dLedger.get(i).getTransactions().get(j).getAmount());
                }
                
            }
               }
   public static void printBlocks(LinkedList<Block> blocks){
       String block ="";
        for(int i = 0;i<blocks.size();i++){
            System.out.println("\nblock: "+i+"\n Hash: "+blocks.get(i).getHash()+"\n previousHash: "+blocks.get(i).getPreviousHash()+"\n timeStamp: "+blocks.get(i).getTimeStamp()+"\n nonce: "+blocks.get(i).getNonce());
        } 
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {

                Blockchain chain = new Blockchain();
                chain.createTransaction("onur","ahmet",2);
                chain.createTransaction("onur","ali",5);
             
                chain.createGenesisBlock();
                  
                 chain.createTransaction("ahmet","onur",5);
                 chain.addBlock("ali");
                 // System.out.println(chain.list.get(1).transactionsBlock.getFirst());
                chain.createTransaction("ali","onur",5);
                
                
               
                chain.createTransaction("ali","onur",25);
                chain.createTransaction("onur","ruya",5);
                 chain.addBlock("ahmet");
                  chain.createTransaction("ahmet","onur",5);
                chain.createTransaction("ruya","onur",10);
                  chain.addBlock("ali3");
                  chain.createTransaction("ahmet","onur",5);
                chain.createTransaction("ruya","ali",5);
                chain.addBlock("blabla");
        
          printTransaction(chain.getList());
           //chain.isChainInvalid();
          System.out.println(chain.getBalanceOfAddress("ruya"));
         
          
        
          
             
           
    }
    
}
