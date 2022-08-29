package com.dbs.demo.service;



import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dbs.demo.dto.TransactionDto;
import com.dbs.demo.model.Customer;
import com.dbs.demo.model.Employee;
import com.dbs.demo.model.MyUserDetails;
import com.dbs.demo.model.Transaction;
import com.dbs.demo.model.Transaction.Status;
import com.dbs.demo.repo.CustomerRepo;
import com.dbs.demo.repo.EmployeeRepo;
import com.dbs.demo.repo.TransactionRepo;
import com.dbs.demo.response.ResponseHandler;

@Service
public class EmployeeService {
	
	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private EmployeeRepo er;
	
	@Autowired
	private TransactionRepo tr;
	
	public ResponseEntity<Object> transferCtc(TransactionDto transaction) {
		try {
			MyUserDetails userDetails= (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			System.out.println(userDetails.toString());
			transaction.setEmployeeId(null);
			transaction.setEmployeeId(userDetails.getId());
			// TODO Auto-generated method stub
			if(
					transaction.getCurrencyAmount() == 0 
					|| transaction.getCustomerId() == null
					|| transaction.getEmployeeId() == null
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
			if(transaction.getReceiverBic().equals(receiver.getBank().getBic()) == false) {
				System.out.println(transaction.getReceiverBic() + " " + receiver.getBank().getBic());
				return ResponseHandler.generateResponse(400, "Reciver BIC does not match with given BIC.");
			}
			
			//if any 1 of above true, check receiver terror list entry
			//Check if employee exists
			Optional<Employee> optionalEmployee = er.findById(transaction.getEmployeeId());
			if(optionalEmployee.isEmpty()) {
				return ResponseHandler.generateResponse(400, "Employee ID is invalid");
			}
			Employee emp = optionalEmployee.get();
			
			//calc transfer fee
			double transferFee = transaction.getCurrencyAmount() * 0.0025;
			
			//TODO
			int rowAff;
			rowAff = cr.updateBalance((double)(sender.getClearBalance()- transaction.getCurrencyAmount() - transferFee), sender.getCustomerId());
			if(rowAff != 1) {
				return ResponseHandler.generateResponse(500, "Something went wrong!");
			}
			rowAff=0;
			rowAff = cr.updateBalance((double)(receiver.getClearBalance() + transaction.getCurrencyAmount()), receiver.getCustomerId());
			if(rowAff != 1) {
				return ResponseHandler.generateResponse(500, "Something went wrong!");
			}
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
			transactionObj.setTransferFee(transferFee);
			transactionObj.setTransferDate(new Date());
			transactionObj.setEmployee(emp);
			transactionObj.setStatus(Status.ACCEPTED);
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
			MyUserDetails userDetails= (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			System.out.println(userDetails.toString());
			transaction.setEmployeeId(null);
			transaction.setEmployeeId(userDetails.getId());
			
			if(
					transaction.getCurrencyAmount() == 0 
					|| transaction.getCustomerId() == null
					|| transaction.getEmployeeId() == null
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
			//Check if employee exists
			Optional<Employee> optionalEmployee = er.findById(transaction.getEmployeeId());
			if(optionalEmployee.isEmpty()) {
				return ResponseHandler.generateResponse(400, "Employee ID is invalid");
			}
			Employee emp = optionalEmployee.get();
			
			//calc transfer fee
			double transferFee = transaction.getCurrencyAmount() * 0.0025;
			
			//TODO
			int rowAff;
			rowAff = cr.updateBalance((double)(sender.getClearBalance()- transaction.getCurrencyAmount() - transferFee), sender.getCustomerId());
			if(rowAff != 1) {
				return ResponseHandler.generateResponse(500, "Something went wrong!");
			}
			rowAff=0;
			rowAff = cr.updateBalance((double)(receiver.getClearBalance() + transaction.getCurrencyAmount()), receiver.getCustomerId());
			if(rowAff != 1) {
				return ResponseHandler.generateResponse(500, "Something went wrong!");
			}
			
			//create & initiate transaction
			Transaction transactionObj = new Transaction();
			transactionObj.setCustomer(sender);
			transactionObj.setSenderBank(sender.getBank());
			transactionObj.setReceiverBank(receiver.getBank());
			transactionObj.setReceiverAccountHolderNumber(receiver.getCustomerId());
			transactionObj.setReceiverAccountHolderName(receiver.getAccountHolderName());
			transactionObj.setCurrencyAmount(transaction.getCurrencyAmount());
			transactionObj.setTransferFee(transferFee);
			transactionObj.setTransferDate(new Date());
			transactionObj.setEmployee(emp);
			transactionObj.setStatus(Status.ACCEPTED);
			Transaction saved = tr.save(transactionObj);
			
			//send result
			return ResponseHandler.generateResponse(200, saved);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseHandler.generateResponse(400, e.getMessage());
		}
	}

	public Customer getCustomer(String customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> optionalCustomer = cr.findById(customerId);
		if(optionalCustomer.isEmpty())
			return null;
		
		return optionalCustomer.get();
	}
	
	public ResponseEntity<Object> transactionHistory(){
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Collection<Transaction> transactions = tr.getApprovedTransactions(userDetails.getId());
		return ResponseHandler.generateResponse(200, transactions);
	}

	public ResponseEntity<Object> getPendingTransactions() {
		// TODO Auto-generated method stub
		Collection<Transaction> transactions = tr.findEmployeePendingTransactionsById();
		return ResponseHandler.generateResponse(200, transactions);
	}

	public ResponseEntity<Object> finalizeTransaction(TransactionDto transaction) {
		// TODO Auto-generated method stub
		if(transaction.getTransactionId() == 0) {
			return ResponseHandler.generateResponse(400, "Please enter transactionId");
		}
		
		Optional<Transaction> transactionOriginalOptional = tr.findById(transaction.getTransactionId());
		
		if(transactionOriginalOptional.isEmpty()) {
			return ResponseHandler.generateResponse(400, "No Transaction exists with that ID");
		}
		Transaction transactionOriginal = transactionOriginalOptional.get();
		
		if(transactionOriginal.getStatus() != Status.PENDING || transactionOriginal.getStatus() == Status.REJECTED) {
			return ResponseHandler.generateResponse(400, "This transaction is not pending anymore.");
		}
		
		//Check if employee exists
		MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Employee> optionalEmployee = er.findById(userDetails.getId());
		if(optionalEmployee.isEmpty()) {
			return ResponseHandler.generateResponse(400, "Inavlid Employee");
		}
		Employee emp = optionalEmployee.get();
		
		String remarks = transaction.getEmployeeRemarks();
		if(remarks != null) {
			transactionOriginal.setEmployeeRemarks(remarks);
		}
		
		if(transaction.getStatus() == Status.ACCEPTED) {
			
			//if sender balance > amount + fees
			Optional<Customer> senderOptional = cr.findById(transactionOriginal.getCustomer().getCustomerId());

			if(senderOptional.isEmpty()) {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Invalid sender account number.");
			}
			
			Customer sender = senderOptional.get();
//		System.out.println(sender);
			if(transactionOriginal.getCurrencyAmount() <= 0) {
				return ResponseHandler.generateResponse(400, "Amount must be greater than 0.");
			}
			
			if(sender.getClearBalance() < transactionOriginal.getCurrencyAmount()) {
				//if balance less than amount check overdraft option
				if(sender.isOverdraftFlag() == false) {
					return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST
							, "Sender has low Account Balance, And overdraft option not available.");
				}
			};
			
			//if receiver exists : getReceiverAccountHolderNumber is customer_id in customer table
			Optional<Customer> receiverOptional = cr.findById(transactionOriginal.getReceiverAccountHolderNumber());
			if(receiverOptional.isEmpty()) {
				return ResponseHandler.generateResponse(400, "Receiver doesn't exist.");
			}
			Customer receiver = receiverOptional.get();
			
			//if any 1 of above true, check receiver terror list entry
			
			
			//calc transfer fee
			double transferFee = transactionOriginal.getCurrencyAmount() * 0.0025;
			
			//TODO
			int rowAff;
			rowAff = cr.updateBalance((double)(sender.getClearBalance()- transactionOriginal.getCurrencyAmount() - transferFee), sender.getCustomerId());
			if(rowAff != 1) {
				return ResponseHandler.generateResponse(500, "Something went wrong!");
			}
			rowAff=0;
			rowAff = cr.updateBalance((double)(receiver.getClearBalance() + transactionOriginal.getCurrencyAmount()), receiver.getCustomerId());
			if(rowAff != 1) {
				return ResponseHandler.generateResponse(500, "Something went wrong!");
			}
			
			transactionOriginal.setTransferFee(transferFee);
			transactionOriginal.setTransferDate(new Date());
			transactionOriginal.setEmployee(emp);
			transactionOriginal.setStatus(Status.ACCEPTED);
		}else if(transaction.getStatus() == Status.REJECTED) {
			transactionOriginal.setTransferDate(new Date());
			transactionOriginal.setEmployee(emp);
			transactionOriginal.setStatus(Status.REJECTED);
		}
		
		transactionOriginal = tr.save(transactionOriginal);
		
		ResponseEntity<Object> response = ResponseHandler.generateResponse(200, transactionOriginal);
		
		return response;
	}


	public ResponseEntity<Object> getEmployeeData() {
	// TODO Auto-generated method stub
			MyUserDetails userDetails= (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//				System.out.println(userDetails.toString());
			HashMap<String, Object> empData = new HashMap<>();
			Optional<Employee> empOptional = er.findById(userDetails.getId());
			if(empOptional.isEmpty()) {
				return ResponseHandler.generateResponse(400, "Please Log In First.");
			}
			Employee emp = empOptional.get();
			empData.put("user", emp);
			List<Transaction> Pendingtransactions = tr.findEmployeePendingTransactionsById();
			empData.put("pendingTransactions", Pendingtransactions);
			
			List<Transaction> transactions = tr.getFinalizedTransactions(emp.getEmployeeId());
			empData.put("finalizedTransactions", transactions);
			
			return ResponseHandler.generateResponse(200, empData);
	}
}
