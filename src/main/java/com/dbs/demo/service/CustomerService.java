package com.dbs.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dbs.demo.dto.TransactionDto;
import com.dbs.demo.model.Customer;
import com.dbs.demo.model.Employee;
import com.dbs.demo.model.MyUserDetails;
import com.dbs.demo.model.Transaction;
import com.dbs.demo.model.Transaction.Status;
import com.dbs.demo.repo.CustomerRepo;
import com.dbs.demo.repo.TransactionRepo;
import com.dbs.demo.response.ResponseHandler;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepo cr;
	
	@Autowired
	TransactionRepo tr;
	
	public ResponseEntity<Object> transferCtc(TransactionDto transaction){
		try {
			MyUserDetails userDetails= (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			System.out.println(userDetails.toString());
			transaction.setCustomerId(null);
			transaction.setCustomerId(userDetails.getId());
			// TODO Auto-generated method stub
			if(
					transaction.getCurrencyAmount() == 0 
					|| transaction.getCustomerId() == null
					|| transaction.getReceiverAccountHolderNumber() == null
					|| transaction.getReceiverBic() == null
					) {
				return ResponseHandler.generateResponse(400
						, "Please make sure you entered "
								+ "Receiver Account holder number & ReceiverBIC");
				
			}
			
			if(transaction.getCustomerId() == transaction.getReceiverAccountHolderNumber()) {
				return ResponseHandler.generateResponse(400,
						"Tryna be smart? We are smarter. You can't transfer to self");
			}
			//if sender balance > amount + fees

			Optional<Customer> senderOptional = cr.findById(transaction.getCustomerId());

			if(senderOptional.isEmpty()) {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Invalid sender account number.");
			}
			
			Customer sender = senderOptional.get();
//		System.out.println(sender);
			if(transaction.getCurrencyAmount() <= 0) {
				return ResponseHandler.generateResponse(400, "Amount must be greater than 0.");
			}
			
			if(sender.getClearBalance() < transaction.getCurrencyAmount()) {
				//if balance less than amount check overdraft option
				if(sender.isOverdraftFlag() == false) {
					return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST
							, "Sender has low Account Balance, And overdraft option not available.");
				}
			};
			
			//if receiver exists : getReceiverAccountHolderNumber is customer_id in customer table
			Optional<Customer> receiverOptional = cr.findById(transaction.getReceiverAccountHolderNumber());
			if(receiverOptional.isEmpty()) {
				return ResponseHandler.generateResponse(400, "Receiver doesn't exist.");
			}
			Customer receiver = receiverOptional.get();
			//check if receiverBic matches with given bic
			if(transaction.getReceiverBic().equals(receiver.getBank().getBic()) == false) {
				System.out.println(transaction.getReceiverBic() + " " + receiver.getBank().getBic());
				return ResponseHandler.generateResponse(400, "Reciver BIC does not match with given BIC.");
			}
			
			//if any 1 of above true, check receiver terror list entry
			
			//calc transfer fee
//			double transferFee = transaction.getCurrencyAmount() * 0.0025;
			
			//TODO
//			int rowAff;
//			rowAff = cr.updateBalance((double)(sender.getClearBalance()- transaction.getCurrencyAmount() - transferFee), sender.getCustomerId());
//			if(rowAff != 1) {
//				return ResponseHandler.generateResponse(500, "Something went wrong!");
//			}
//			rowAff=0;
//			rowAff = cr.updateBalance((double)(receiver.getClearBalance() + transaction.getCurrencyAmount()), receiver.getCustomerId());
//			if(rowAff != 1) {
//				return ResponseHandler.generateResponse(500, "Something went wrong!");
//			}
//			Add back after adding all banks to DB
//			rowAff = 0;
//			rowAff = cr.addToBank(transferFee, transaction.getSenderBic());
			
			//create & initiate transaction
			Transaction transactionObj = new Transaction();
			transactionObj.setCustomer(sender);
			transactionObj.setSenderBank(sender.getBank());
			transactionObj.setReceiverBank(receiver.getBank());
			transactionObj.setReceiverAccountHolderNumber(receiver.getCustomerId());
			transactionObj.setReceiverAccountHolderName(receiver.getAccountHolderName());
			transactionObj.setCurrencyAmount(transaction.getCurrencyAmount());
			transactionObj.setTransferDate(new Date());
			transactionObj.setStatus(Status.PENDING);
			Transaction saved = tr.save(transactionObj);
			
			//send result
			return ResponseHandler.generateResponse(200, saved);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(400, e.getMessage());
		}
	}

	public ResponseEntity<Object> transferCtb(TransactionDto transaction) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			if(
					transaction.getCurrencyAmount() == 0 
					|| transaction.getCustomerId() == null
					|| transaction.getReceiverAccountHolderNumber() == null
					|| transaction.getSenderBic() == null
					|| transaction.getReceiverBic() == null
					) {
				return ResponseHandler.generateResponse(400
						, "Please make sure you entered customerID, EmployeeID, "
								+ "Receiver Account holder number SenderBIC & ReceiverBIC");
				
			}
			//if sender balance > amount + fees

			Optional<Customer> senderOptional = cr.findById(transaction.getCustomerId());

			if(senderOptional.isEmpty()) {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Invalid sender account number.");
			}
			
			Customer sender = senderOptional.get();
//		System.out.println(sender);
			if(transaction.getCurrencyAmount() <= 0) {
				return ResponseHandler.generateResponse(400, "Amount must be greater than 0.");
			}
			
			if(sender.getClearBalance() < transaction.getCurrencyAmount()) {
				//if balance less than amount check overdraft option
				if(sender.isOverdraftFlag() == false) {
					return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST
							, "Sender has low Account Balance, And overdraft option not available.");
				}
			};
			
			//if receiver exists : getReceiverAccountHolderNumber is customer_id in customer table
			Optional<Customer> receiverOptional = cr.findById(transaction.getReceiverAccountHolderNumber());
			if(receiverOptional.isEmpty()) {
				return ResponseHandler.generateResponse(400, "Receiver doesn't exist.");
			}
			Customer receiver = receiverOptional.get();
			//check if receiverBic matches with given bic
			if(receiver.getCustomerType().equals("bank") == false) {
				return ResponseHandler.generateResponse(400, "Receiver Should be of type Bank.");
			}
			
			if(transaction.getReceiverBic().equals(receiver.getBank().getBic()) == false) {
				return ResponseHandler.generateResponse(400, "Reciver BIC does not match with given BIC.");
			}
			
			//if any 1 of above true, check receiver terror list entry
			
			//TODO
//			int rowAff;
//			rowAff = cr.updateBalance((double)(sender.getClearBalance()- transaction.getCurrencyAmount()), sender.getCustomerId());
//			if(rowAff != 1) {
//				return ResponseHandler.generateResponse(500, "Something went wrong!");
//			}
//			rowAff=0;
//			rowAff = cr.updateBalance((double)(receiver.getClearBalance() + transaction.getCurrencyAmount()), receiver.getCustomerId());
//			if(rowAff != 1) {
//				return ResponseHandler.generateResponse(500, "Something went wrong!");
//			}
			
			//create & initiate transaction
			Transaction transactionObj = new Transaction();
			transactionObj.setCustomer(sender);
			transactionObj.setSenderBank(sender.getBank());
			transactionObj.setReceiverBank(receiver.getBank());
			transactionObj.setReceiverAccountHolderNumber(receiver.getCustomerId());
			transactionObj.setReceiverAccountHolderName(receiver.getAccountHolderName());
			transactionObj.setCurrencyAmount(transaction.getCurrencyAmount());
			transactionObj.setStatus(Status.PENDING);
			transactionObj.setTransferDate(new Date());
			Transaction saved = tr.save(transactionObj);
			
			//send result
			return ResponseHandler.generateResponse(200, saved);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(400, e.getMessage());
		}
	}

	public ResponseEntity<Object> getCustomerData() {
		// TODO Auto-generated method stub
		MyUserDetails userDetails= (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println(userDetails.toString());
		HashMap<String, Object> custData = new HashMap<>();
		Optional<Customer> custOptional = cr.findById(userDetails.getId());
		if(custOptional.isEmpty()) {
			return ResponseHandler.generateResponse(400, "Please Log In First.");
		}
		Customer cust = custOptional.get();
		custData.put("user", cust);
		List<Transaction> transactions = tr.getCustomerTransactions(cust.getCustomerId());
		custData.put("transactions", transactions);
		
		return ResponseHandler.generateResponse(200, custData);
	}
}
