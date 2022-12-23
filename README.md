TITLE: Scheduling Application
WGU C 195 – Software II
Advanced Java Application Development

PURPOSE: To provide GUI application that allows the customer to schedule, modify, and cancel appointments, add, modify, and delete customers and view various reports from data in a SQL database. 

AUTHOR NAME: Keighley Manke  

AUTHOR CONTACT INFO: kmanke@wgu.edu

DATE: December 2022 

APPLICATION VERSION: 1.0

IDE: IntelliJ IDEA Community Edition 2022.1.1

JDK: Java SDK 11.0.2

JavaFX VERSION:  11.0.2

MySQL DRIVER VERSION: Java 8.0.25

INSTRUCTIONS:

When the application is launched a log-in screen opens where the user’s Zone ID and local date and time are displayed. If determined necessary by the Zone ID, the login text and error dialogue are translated into French. The user enters a username and password that is checked against those in the database. After a successful log-in, an appointment alert is displayed, informing the user if there are any appointments scheduled within the next 15 minutes. 

The main screen consists of three main tabs: the Customer tab, the Appointments tab, and the Reports tab. The first tab visualized is the Customer tab. From here the user can see all the customers and corresponding information from the Customer table in the database. The user can add, modify, or delete a customer record from the database. The customer ID is auto incremented from the database and cannot be modified. A customer record cannot be deleted until any appointments they may have are also deleted from the database. 

The Appointments tab consists of three tabs: All Appointments, Monthly Appointments, and Weekly Appointments. Each tab will display appointment information from the database. The All Appointments tab displays the entirety of the appointment table in the database, the Monthly Appointments tab filters the appointments to the current month, and the Weekly Appointments tab filters the appointments to the current week. At any point on the Appointments tab the user may add, modify, and delete an appointment from the database. The appointment ID is auto incremented in the database and cannot be modified. 

The Reports tab allows the user to see a count of the total number of appointments by month and type, as well as by each customer. The user may also select a contact and see all the appointments that contact has scheduled.

At any time the user may log out of the application from the main screen. 

ADDITIONAL REPORT: From the reports tab the user can select a customer and view a total of appointments for that customer. 




