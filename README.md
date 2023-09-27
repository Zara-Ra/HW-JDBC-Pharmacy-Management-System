# HW-JDBC-Pharmacy-Management-System

In this project I have used JDBC to connect to the Postgresql database.
This console application manages a pharmacy, there are two roles Patient and Admin.

Patient :

  •Add prescription by entering the required data

  •See confirmed prescriptions with the price for each item and total price

  •Edit prescription

  •Delete prescription  
  
    Each prescription consists of at least one item 
    Patient can enter at most 10 items for each prescription

Admin:
  
  •See all the prescriptions registered by all patients

  •Confirm each of them one by one.

  •After confirming the prescription, the admin must specify which items exists, which not(so items must have a boolean field as 'doesExist')

  •Admin must specify the price of each existing item. after that, the prescription is prepared and can be seen by the patient

