

Calculate the points customer earned and save it with customer details to the map, each time when you want to check the points, use id as a key for the map. 

I designed JSON based on the requirement:

{
  "customers": [
    {
      "id": 1,
      "name": "hanson",
      "email": "hansonchen8686@gmail.com",
      "transactions": [
        {
          "month": 1,
          "amount": 100
        },
        {
          "month": 2,
          "amount": 140
        },
        {
          "month": 3,
          "amount": 120
        }
      ]
    }
    
  ]
}


 1. you can send the request alone with the JSON to save the customer data, including id, name, email, and purchase amount for each month. 
 http://localhost:8080/api/processData
 
 2. send the get request along with the id you just created, http://localhost:8080/api/customerData?id=1  
 then you will get:
 
 {
    "name": "hanson",
    "email": "hansonchen8686@gmail.com",
    "pointsFor3month": [
        {
            "month": 1,
            "amount": 100,
            "points": 50
        },
        {
            "month": 2,
            "amount": 140,
            "points": 130
        },
        {
            "month": 3,
            "amount": 120,
            "points": 90
        }
    ],
    "totalPoints": 270,
    "id": 1
}


totalPoints means all the points you earn for 3 month. 


