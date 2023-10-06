# HW-JDBC-Pharmacy-Management-System
## Pharmacy Management Console Application
### Overview
The Pharmacy Management Console Application is a versatile solution designed to efficiently manage pharmacy operations. It caters to two distinct roles: Patient and Admin, each with specific functionalities. Here's an overview of the application's key features:

### Features
### Patient Role
- Add Prescription: Patients can create prescriptions by entering the required data.

- View Confirmed Prescriptions: Patients can view confirmed prescriptions, including the price for each item and the total price.

- Edit Prescription: Patients have the option to edit prescriptions they have created.

- Delete Prescription: Patients can remove prescriptions if they are no longer needed.

### Admin Role
- View All Prescriptions: Admins have access to all prescriptions registered by all patients.

- Confirm Prescriptions: Admins can confirm each prescription individually, marking them as processed.

- Manage Items: After confirming a prescription, the admin must specify which items exist and which do not (items have a boolean field as 'doesExist').

- Specify Item Prices: Admins must set prices for each existing item in the prescription. Once this is done, the prescription is prepared and can be viewed by the patient.

### Prescription Details
- Each prescription must contain at least one item.

- Patients can enter up to a maximum of 10 items for each prescription.

### Database Connectivity with JDBC
- The application utilizes JDBC (Java Database Connectivity) to establish connections to the PostgreSQL database, ensuring seamless data management.
