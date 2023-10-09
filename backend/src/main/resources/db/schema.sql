CREATE TABLE `members`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL,
    `mail`        VARCHAR(255) NOT NULL,
    `profile_url` VARCHAR(255) NULL,
    `created_at`  DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at`  DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (id)
);

CREATE TABLE `studies`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `profile_url` VARCHAR(255) NULL,
    `created_at`  DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at`  DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (id)
);

CREATE TABLE `posts`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `content`       LONGTEXT     NOT NULL,
    `thumbnail_url` VARCHAR(255) NOT NULL,
    `title`         VARCHAR(255) NOT NULL,
    `subtitle`      VARCHAR(255) NOT NULL,
    `study_id`      BIGINT       NOT NULL,
    `created_at`    DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at`    DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (id)
);

CREATE TABLE `tags`
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255) NOT NULL,
    `created_at` DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at` DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (id)
);

CREATE TABLE `study_members`
(
    `member_id`  BIGINT       NOT NULL,
    `study_id`   BIGINT       NOT NULL,
    `role`       VARCHAR(255) NOT NULL,
    `created_at` DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at` DATETIME(6)  NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`member_id`, `study_id`)
);

CREATE TABLE `post_authors`
(
    `member_id`  BIGINT      NOT NULL,
    `post_id`    BIGINT      NOT NULL,
    `created_at` DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at` DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`member_id`, `post_id`)
);

CREATE TABLE `post_tags`
(
    `post_id`    BIGINT      NOT NULL,
    `tag_id`     BIGINT      NOT NULL,
    `created_at` DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    `updated_at` DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`post_id`, `tag_id`)
);

ALTER TABLE `study_members`
    ADD FOREIGN KEY (`member_id`) REFERENCES members (id)
        ON DELETE CASCADE;
ALTER TABLE `study_members`
    ADD FOREIGN KEY (`study_id`) REFERENCES studies (id)
        ON DELETE CASCADE;

ALTER TABLE `post_authors`
    ADD FOREIGN KEY (`member_id`) REFERENCES members (id)
        ON DELETE CASCADE;

ALTER TABLE `post_authors`
    ADD FOREIGN KEY (`post_id`) REFERENCES posts (id)
        ON DELETE CASCADE;

ALTER TABLE `post_tags`
    ADD FOREIGN KEY (`post_id`) REFERENCES posts (id)
        ON DELETE CASCADE;

ALTER TABLE `post_tags`
    ADD FOREIGN KEY (`tag_id`) REFERENCES tags (id)
        ON DELETE CASCADE;
