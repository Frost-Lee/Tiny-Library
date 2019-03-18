# Tiny-Library
Library management system with MySQL, interface built by Swing in Java

![Tiny Library](https://i.imgur.com/z9AdEU6.png)

### Features
- Two kinds of user login mode: Administrator and Reader
- Insert and Update book when logged as Administrator
- Add reader, Drop reader when logged as Administrator
- View the borrow list, borrow history, book list, user list when logged as Administrator
- Search book with name, author or year when logged as Redaer
- Borrow or Return book when logged as Reader
- View one's borrow list and borrow history when logged as Reader

### Local Support
##### Data Base Construction
Tiny Library requires database system support, since it's a database based application. The database could be constructed with `definition.sql` and `trigger.sql`.
##### Configuration

Some configuration, including the user name and password of the database, is required. The configuration can be modified in `/src/Configuration.java`.


### Usage
The default user name and password of the administrator is `Administrator` and `admin`, you can customize the name and password by modifying the configuration file.
Once you have logged in as Administrator, you are able to create reader, then when you can login with the user name and password of a Reader.