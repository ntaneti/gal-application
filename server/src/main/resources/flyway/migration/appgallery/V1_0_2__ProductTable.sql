use fsg_app_gallery;

-- ---------------------------------------------------------------
-- Create 'fsg_app_gallery' tables
-- ---------------------------------------------------------------

create table products (
    product_id bigint not null,
    creationTime datetime not null,
    lastUpdateTime datetime not null,
    uuid binary(16) not null unique,
    name varchar(255) not null,
    description varchar(255) not null,
    primary key (product_id),
    unique (name)
) ROW_FORMAT=DYNAMIC;

create table if not exists hibernate_sequences (
  sequence_name varchar(255) not null ,
  next_val bigint,
  primary key ( sequence_name )
) ROW_FORMAT=DYNAMIC;

-- ---------------------------------------------------------------
-- Initial Sequence values
-- ---------------------------------------------------------------

INSERT INTO `hibernate_sequences` (`sequence_name`, `next_val`) VALUES ('product_seq',1);