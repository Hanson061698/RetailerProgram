package com.springboot.retailer123;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;




import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControllerFirst {

	public Map<Integer, SaveCustomer> map = new HashMap<>();   // this is the data set, saved all the details and points earned in each month, points earned in total 3 month, and other customer info 
	
    @PostMapping("/processData")
    public int processData(@RequestBody CustomerData customerData) {

          
        for(int i = 0; i < customerData.getCustomers().size(); i++) {

        	SaveCustomer sc = new SaveCustomer();
        	sc.setEmail(customerData.getCustomers().get(i).getEmail());
        	sc.setName(customerData.getCustomers().get(i).getName());
        	sc.setId(customerData.getCustomers().get(i).getId());
        	
        	List<Points> pointsFor3month = new ArrayList<>();
        	int total = 0;
        	for(int j = 0; j < customerData.getCustomers().get(i).getTransactions().size(); j++) { 
        		Transaction t = customerData.getCustomers().get(i).getTransactions().get(j);
        		int currentMonthPoints = calculatePoints(t.getAmount());
        		total += currentMonthPoints;
        		Points p = new Points();
        		p.setMonth(t.getMonth());
        		p.setAmount(t.getAmount());
        		p.setPoints(currentMonthPoints);
        		pointsFor3month.add(p);
        	}
        	sc.setPointsFor3month(pointsFor3month);
        	sc.setTotalPoints(total);
        	
        	map.put(customerData.getCustomers().get(i).getId(), sc);  //save the current customerInfo
        }
        
        
        return map.size();
        
    }
    
    @GetMapping("/customerData")
    public SaveCustomer getById(@RequestParam String id) {
    	
    	return map.get(Integer.valueOf(id));
    }
    
    @GetMapping("/firstMonthEarned")
    public int firstMonthEarned(@RequestParam String id) {
    	
    
    	return map.get(Integer.valueOf(id)).getPointsFor3month().get(0).getPoints();
    }
    
    @GetMapping("/secondMonthEarned")
    public int secondMonthEarned(@RequestParam String id) {
    	
    	return map.get(Integer.valueOf(id)).getPointsFor3month().get(1).getPoints();
    }
    
    @GetMapping("/thirdMonthEarned")
    public int thirdMonthEarned(@RequestParam String id) {
    	
    	return map.get(Integer.valueOf(id)).getPointsFor3month().get(2).getPoints();
    }
    
    
    @GetMapping("/totalEarned3month")
    public int totalEarned3month(@RequestParam String id) {
    	
    	return map.get(Integer.valueOf(id)).getTotalPoints();
    }
    
    
    public static class CustomerData {
        private List<Customer> customers;

        public List<Customer> getCustomers() {
            return customers;
        }

        public void setCustomers(List<Customer> customers) {
            this.customers = customers;
        }
        
    }

    public static class Customer {

        private String name;
        private String email;
        private List<Transaction> transactions;
        private int id;
        
        
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        
  
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<Transaction> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
            
        }
        
        
    }

    public static class Transaction {
        private int month;
        private int amount;

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
    
    public static class Points extends Transaction{
    	

    	private int points;
    	
    	public void setPoints(int points) {
    		this.points = points;
    	}
    	
    	public int getPoints() {
    		return points;
    	}	
    	
    }
    
    
    public int calculatePoints(int amount) {
    	
    	int res = 0;
    	
    	if(50 < amount && amount <= 100) {
    		res = amount - 50;
    	}
    		
    	if(amount > 100) {
    		res = (amount - 100) * 2 + 50;	
    	}
    		
    	
    	return res;
    }
    
    
    
    
    public static class SaveCustomer {

      private String name;
      private String email;
      private List<Points> pointsFor3month;
      private int totalPoints;
      private int id;
      
      
      public int getId() {
          return id;
      }

      public void setId(int id) {
          this.id = id;
      }
      

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public String getEmail() {
          return email;
      }

      public void setEmail(String email) {
          this.email = email;
      }

      public List<Points> getPointsFor3month() {
          return pointsFor3month;
      }

      public void setPointsFor3month(List<Points> pointsFor3month) {
          this.pointsFor3month = pointsFor3month;
      }
      
      public void setTotalPoints(int totalPoints) {
    	  this.totalPoints = totalPoints;
      }
      
      public int getTotalPoints() {
    	  return totalPoints;
      }
      
      
  }
    
    
}

