CREATE TABLE "Partner" (
  "id" INT NOT NULL,
  "name" VARCHAR(128) NOT NULL,
  "email" VARCHAR(128) NULL,
  "uuid" uuid NOT NULL,
  "websiteURL" VARCHAR(512) NULL,
  "partnerType" VARCHAR(35) NOT NULL,
  "creationTime" timestamp NOT NULL,
  "lastUpdateTime" timestamp NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "Application" (
  "id" INT NOT NULL,
  "authorId" INT NOT NULL,
  "uuid" uuid NOT NULL,
  "appStatus" VARCHAR(45) NULL,
  "appName" VARCHAR(255) NOT NULL,
  "appVersion" VARCHAR(45) NULL,
  "downloadLink" VARCHAR(512) NULL,
  "platform" VARCHAR(45) NULL,
  "averageRating" INT DEFAULT 0,
  "ratingCount" INT DEFAULT 0,
  "populatiry" INT DEFAULT 0,
  "creationTime" timestamp NOT NULL,
  "lastUpdateTime" timestamp NOT NULL,
  "version" serial,
  PRIMARY KEY ("id")
);

CREATE TABLE "Image" (
  "id" INT NOT NULL,
  "creationTime" timestamp NULL,
  "lastUpdateTime" timestamp NULL,
  "url" VARCHAR(512) NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "Category" (
  "id" INT NOT NULL,
  "name" VARCHAR(128) NULL,
  "creationTime" timestamp NOT NULL,
  "lastUpdateTime" timestamp NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "Application_Category" (
  "applicationId" INT NOT NULL,
  "categoryId" INT NOT NULL,
  PRIMARY KEY ("applicationId", "categoryId")
);

CREATE TABLE "Tags" (
  "id" INT NOT NULL,
  "name" VARCHAR(45) NULL,
  "creationTime" timestamp NOT NULL,
  "lastUpdateTime" timestamp NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "Application_Tags" (
  "applicationId" INT NOT NULL,
  "tagsId" INT NOT NULL,
  PRIMARY KEY ("applicationId", "tagsId")
);

CREATE TABLE "Awards" (
  "id" INT NOT NULL,
  "applicationId" INT NOT NULL,
  "title" VARCHAR(128) NULL,
  "link" VARCHAR(256) NULL,
  "awardImgId" INT NOT NULL,
  "creationTime" timestamp NOT NULL,
  "lastUpdateTime" timestamp NOT NULL,
  PRIMARY KEY ("id")
);

CREATE TABLE "Application_Locale" (
  "applicationId" INT NOT NULL,
  "locale" VARCHAR(45) NOT NULL,
  "title" VARCHAR(256) NULL,
  "description" BYTEA NULL,
  "appSummary" VARCHAR(512) NULL,
  PRIMARY KEY ("applicationId", "locale")
);

CREATE TABLE "Application_Screenshot" (
  "applicationId" INT NOT NULL,
  "locale" VARCHAR(45) NOT NULL,
  "screenshotId" INT NOT NULL,
  "imageId" BIGINT NOT NULL,
  "status" VARCHAR(45) NULL,
  "creationTime" timestamp NULL,
  "lastUpdateTime" timestamp NULL,
  PRIMARY KEY ("applicationId", "locale", "screenshotId")
);

CREATE TABLE "Tags_Locale" (
  "tagId" INT NOT NULL,
  "locale" VARCHAR(45) NOT NULL,
  "label" VARCHAR(64) NULL,
  PRIMARY KEY ("tagId", "locale")
);

CREATE TABLE "Category_Locale" (
  "categoryId" INT NOT NULL,
  "locale" VARCHAR(45) NOT NULL,
  "label" VARCHAR(45) NULL,
  PRIMARY KEY ("categoryId", "locale")
);


ALTER TABLE "Application" ADD CONSTRAINT "fk_Application_Author" FOREIGN KEY ("authorId") REFERENCES "Partner" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Application" ("authorId");
ALTER TABLE "Application_Category" ADD CONSTRAINT "fk_Application_has_Category_Application1"  FOREIGN KEY ("applicationId") REFERENCES "Application" ("id")  ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Application_Category" ("applicationId");
ALTER TABLE "Application_Category" ADD CONSTRAINT "fk_Application_has_Category_Category1" FOREIGN KEY ("categoryId") REFERENCES "Category" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Application_Category" ("categoryId");
ALTER TABLE "Application_Tags" ADD CONSTRAINT "fk_Application_has_Tags_Application1"  FOREIGN KEY ("applicationId") REFERENCES "Application" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Application_Tags" ("applicationId");
ALTER TABLE "Application_Tags" ADD CONSTRAINT "fk_Application_has_Tags_Tags1" FOREIGN KEY ("tagsId") REFERENCES "Tags" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Application_Tags" ("tagsId");
ALTER TABLE "Awards" ADD CONSTRAINT "fk_Awards_Application1" FOREIGN KEY ("applicationId") REFERENCES "Application" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Awards" ("applicationId");
ALTER TABLE "Awards" ADD CONSTRAINT "fk_Awards_Resource1" FOREIGN KEY ("awardImgId") REFERENCES "Image" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Awards" ("awardImgId");
ALTER TABLE "Application_Locale" ADD CONSTRAINT "fk_Application_Locale_Application1" FOREIGN KEY ("applicationId") REFERENCES "Application" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Application_Locale" ("applicationId");
ALTER TABLE "Application_Screenshot" ADD CONSTRAINT "fk_Application_Screenshot_Application_Locale1" FOREIGN KEY ("applicationId" , "locale") REFERENCES "Application_Locale" ("applicationId" , "locale") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Application_Screenshot" ("applicationId" , "locale");
ALTER TABLE "Application_Screenshot" ADD CONSTRAINT "fk_Application_Screenshot_Image1"  FOREIGN KEY ("imageId") REFERENCES "Image" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Application_Screenshot" ("imageId");
ALTER TABLE "Tags_Locale" ADD CONSTRAINT "fk_Tags_Locale_Tags1" FOREIGN KEY ("tagId") REFERENCES "Tags" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Tags_Locale" ("tagId");
ALTER TABLE "Category_Locale" ADD CONSTRAINT "fk_Category_Locale_Category1"  FOREIGN KEY ("categoryId") REFERENCES "Category" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "Category_Locale" ("categoryId");
