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
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

create table if not exists partner(
 partner_id bigint not null,
 name varchar(128) NULL,
 email varchar(128) NULL,
 uuid binary(16) not null unique,
 website varchar(45) NULL,
 partner_type varchar(35) NULL,
 creationTime datetime not null,
 lastUpdateTime datetime not null,
 primary key (partner_id),
 unique (email)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

create table if not exists application(
 application_id bigint not null,
 author_id bigint not null,
 uuid binary(16) not null unique,
 status varchar(45) NULL,
 name varchar(45) NULL,
 version bigint,
 download_link varchar(512) NULL,
 platform varchar(45) NULL,
 creationTime datetime not null,
 lastUpdateTime datetime not null,
 primary key (application_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

create table if not exists category(
 category_id bigint not null,
 name varchar(128) NULL, 
 creationTime datetime not null,
 lastUpdateTime datetime not null,
 primary key (category_id),
 unique(name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

create table if not exists application_category(
 application_id bigint not null,
 category_id bigint not null,
 primary key (application_id, category_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

create table if not exists hibernate_sequences (
 sequence_name varchar(255) not null ,
 next_val bigint,
 primary key ( sequence_name )
)ROW_FORMAT=DYNAMIC;

-- ---------------------------------------------------------------
-- Create 'fsg_app_gallery' constraints
-- ---------------------------------------------------------------
alter table application
 add index FK_APP_A_AUTHOR_ID (author_id),
 add constraint FK_APP_A_AUTHOR_ID
 foreign key (author_id)
 references partner (partner_id)  
 ON DELETE NO ACTION
 ON UPDATE NO ACTION;

alter table application_category
 add index FK_APP_A_APPLICATION_ID (application_id),
 add index FK_APP_A_CATEGORY_ID (category_id),
 add constraint FK_APP_A_APPLICATION_ID
 foreign key (application_id)
 references application (application_id)  
 ON DELETE NO ACTION
 ON UPDATE NO ACTION,
 add constraint FK_APP_A_CATEGORY_ID
 foreign key (category_id)
 references category (category_id)  
 ON DELETE NO ACTION
 ON UPDATE NO ACTION;

-- ---------------------------------------------------------------
-- Create 'fsg_app_gallery' indexes
-- ---------------------------------------------------------------

create index GAL_APPLICATION_AUTHOR_ID on application (author_id);

create index GAL_CATEGORY_NAME on category (name);

create index GAL_PARTNER_EMAIL on partner (email);

-- ---------------------------------------------------------------
-- Initial Sequence values
-- ---------------------------------------------------------------

INSERT INTO `hibernate_sequences` (`sequence_name`, `next_val`) VALUES ('product_seq',1), ('application_seq',1), ('partner_seq',1), ('category_seq', 1);




