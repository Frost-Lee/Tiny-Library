create table Book
(
  book_name           varchar(100) not null
    primary key,
  publisher           varchar(100) null,
  author              varchar(100) null,
  year_of_publication int          null,
  call_number         int          not null,
  collection_amount   int          not null
)
  charset = utf8;

create index Book_book_name_index
  on Book (book_name);

create table Reader
(
  reader_id    int auto_increment
    primary key,
  name         varchar(100) not null,
  contact_info varchar(100) not null
)
  charset = utf8;

create index Reader_reader_id_index
  on Reader (reader_id);

create table Reader_pass
(
  reader_id int          not null
    primary key,
  password  varchar(100) not null,
  constraint Reader_pass_Reader_reader_id_fk
  foreign key (reader_id) references tinylibrary.Reader (reader_id)
    on delete cascade
)
  charset = utf8;

create index Reader_pass_reader_id_index
  on Reader_pass (reader_id);

create table borrow_book
(
  reader_id   int          not null,
  book_name   varchar(100) not null,
  borrow_time datetime     not null,
  primary key (reader_id, book_name, borrow_time),
  constraint borrow_book_Reader_reader_id_fk
  foreign key (reader_id) references tinylibrary.Reader (reader_id)
    on delete cascade,
  constraint borrow_book_Book_book_name_fk
  foreign key (book_name) references tinylibrary.Book (book_name)
    on delete cascade
)
  charset = utf8;

create index borrow_book_Book_book_name_fk
  on borrow_book (book_name);

create index borrow_book_borrow_time_index
  on borrow_book (borrow_time);

create table return_book
(
  reader_id   int          not null,
  book_name   varchar(100) not null,
  return_time datetime     not null,
  borrow_time datetime     null,
  primary key (reader_id, book_name, return_time),
  constraint return_book_Reader_reader_id_fk
  foreign key (reader_id) references tinylibrary.Reader (reader_id)
    on delete cascade,
  constraint return_book_Book_book_name_fk
  foreign key (book_name) references tinylibrary.Book (book_name)
    on delete cascade
)
  charset = utf8;

create index return_book_Book_book_name_fk
  on return_book (book_name);

create index return_book_return_time_index
  on return_book (return_time);

create trigger borrow_from
  after INSERT
  on borrow_book
  for each row
  UPDATE Book
  SET collection_amount = collection_amount - 1
  WHERE book_name = NEW.book_name;

create trigger return_to
  after DELETE
  on borrow_book
  for each row
  BEGIN 
    UPDATE Book
      SET collection_amount = collection_amount + 1
    WHERE book_name = OLD.book_name;
    INSERT INTO return_book
      VALUES (OLD.reader_id, OLD.book_name, NOW(), OLD.borrow_time);
  end；