# Tiny Library
Library management system with MySQL, interface built by Swing in Java. This is the mid-term project for the course Database System in Zhejiang University.

![Tiny Library](https://i.imgur.com/fIEyUmF.png)



### Features

- Two kinds of user login mode: Administrator and Reader
- Insert and Update book when logged as Administrator
- Add reader, Drop reader when logged as Administrator
- View the borrow list, borrow history, book list, user list when logged as Administrator
- Search book with name, author or year when logged as Redaer
- Borrow or Return book when logged as Reader
- View one's borrow list and borrow history when logged as Reader



### Usage

There is a archived version of the program, you can run it directly with the following command.

``` bash
java -jar target/tiny_library-1.1-jar-with-dependencies.jar
```

To compile and archive, use the following command.

``` bash
mvn clean dependency:copy-dependencies compile assembly:single
```



### Local Support
##### Data Base Construction
Tiny Library requires database system support, since it's a database based application. The database could be constructed with `schema.sql`.
##### Configuration

Some configuration, including the user name and password of the database, is required. The configuration can be modified in `/src/main/java/Configuration/configuration.java`.



### Usage

The administrator mode and reader mode is supported. Under the administrator mode, one is allowed to import books, manager readers, and get access to the borrow and return data. Under the reader mode, one can search for book, borrow or return book, and view his / her borrow and return history.

The default user name and password of the administrator is `Administrator` and `admin`, you can modify this in `/src/main/java/Configuration/configuration.java`. You can login the system with this directly.

Before adding any reader to the database, you cannot login with the reader mode. This is only possible after registering reader under the administrator mode with the new reader's user name and password.

After adding the reader, the system can be logged in with either administrator mode or reader mode. Just enter the user name and password in the login page, the system would automatically enter the administrator mode or user mode according to your user name.