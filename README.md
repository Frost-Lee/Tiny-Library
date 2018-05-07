# Tiny-Library
Library management system with MySQL, interface built by Swing in Java

![Alt text](https://cl.ly/0D2k2n1t473Z/Tiny%20Library.png)

# Features
- Two kinds of user login mode: Administrator and Reader
- Insert and Update book when logged as Administrator
- Add reader, Drop reader when logged as Administrator
- View the borrow list, borrow history, book list, user list when logged as Administrator
- Search book with name, author or year when logged as Redaer
- Borrow or Return book when logged as Reader
- View one's borrow list and borrow history when logged as Reader

# How to Config
## 1. Construct the database use MySQL in your computer
Tiny Library requires MySQL, since it's a database based application, you can find the SQL statements to create the database in databaseConstruct.sql.
## 2. Edit the code so that it JDBC can connect to the database
The application requires the  user name (useually 'root') and the root user's password, as well as the host of the database. Give the variable DB_URL in TLDataManager.java a value so that the JDBC could connect.
The value should have the following form:
__"jdbc:mysql://localhost/TinyLibrary?user=\<USER NAME\>&password=\<USER PASSWORD\>&useUnicode=true&characterEncoding=UTF8&useSSL=true"__

# How to Use
The default user name and password of the administrator is 'Administrator' and 'admin', you can customize the name and password by assigning variable administratorName and administratorPassword in TLDatManager.java.
Once you have logged in as Administrator, you can create reader, then when you can login by the user name and password of a Reader.
