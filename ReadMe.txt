Instructions to Run the Project

Open the project in IDE (e.g. Eclipse,Intellij).
I have used MongoDB cloud cluster and it is already configured in the project.
So, just run the project it will start working.



API Documentation

<Domain> - http://localhost:8080 (while running on local server)
<Domain> - https://employwise-production.up.railway.app (while using hosted server)




Add Employee 

End point - <Domain>/api/employees/add
Method - POST
Request body - 
{
    "employeeName": <name>,
    "email": <email>,
    "phoneNumber": <phone>,
    "reportTo": <id-of-other-employee>,  //Optional
    "profileImage": <url-of-any-google-image>
}





GetAll Employee (With pagination) 

End point - <Domain>/api/employees/getAll
Method - GET
Request Param - 
    pageNumber - <page-number> //Default value = 0
    pageSize - <page-size> //Default value = 5
    sortBy - <sort-by>   //Default value = employeeName
    sortDir - <sort-direction> //Default value = true
    (Here, true for ascending and false for descending)




Delete Employee 

End point - <Domain>/api/employees/{id}
Method - DELETE





Update Employee 

End point - <Domain>/api/employees/{id}
Method - PUT
Request body - 
{
    "employeeName": <name>,	//Optional
    "email": <email>,		//Optional
    "phoneNumber": <phone>,	//Optional
    "reportTo": <id-of-other-employee>,  //Optional
    "profileImage": <url-of-any-google-image> //Optional
}





Get nth level manager 

End point - <Domain>/api/employees/manager/{id}
Method - GET
Request Param - 
    level - <level_of_manager> //Default value = 1