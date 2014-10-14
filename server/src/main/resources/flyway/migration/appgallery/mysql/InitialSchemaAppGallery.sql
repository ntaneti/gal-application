-- ---------------------------------------------------------------
-- Drop and re-create the "fsg_app_gallery" schema
-- ---------------------------------------------------------------
Drop database if exists fsg_app_gallery;

create database fsg_app_gallery;

grant all privileges on fsg_app_gallery.*
   to 'appgallery'@'localhost'
   identified by 'appgallery'
   with grant option;

grant all privileges on fsg_app_gallery.*
   to 'appgallery'@'%'
   identified by 'appgallery'
   with grant option;