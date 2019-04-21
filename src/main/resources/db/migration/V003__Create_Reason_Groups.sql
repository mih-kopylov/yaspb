CREATE TABLE `reason_groups` (
    `id`        BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`      VARCHAR(255) NOT NULL,
    `user_id`   BIGINT       NOT NULL REFERENCES `user`(`id`),
    `parent_id` BIGINT       NULL REFERENCES `reason_groups`(`id`),
    UNIQUE INDEX `ind_name_user`(`user_id`, `name`)
);
