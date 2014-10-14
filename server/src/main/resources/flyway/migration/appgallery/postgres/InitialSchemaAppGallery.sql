-- ---------------------------------------------------------------
-- Drop and re-create the "fsg_app_gallery" schema
-- ---------------------------------------------------------------
DROP SCHEMA IF EXISTS public CASCADE;

DROP DATABASE IF EXISTS fsg_app_gallery;

CREATE DATABASE fsg_app_gallery;
CREATE SCHEMA public;

GRANT ALL PRIVILEGES ON DATABASE fsg_app_gallery
   TO "appgallery" WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON SCHEMA public
   TO "appgallery" WITH GRANT OPTION;