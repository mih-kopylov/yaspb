ALTER TABLE `reason_groups`
    DROP INDEX `ind_name_user`;

ALTER TABLE `reason_groups`
    ADD UNIQUE INDEX `ind_user_parent_name`(`user_id`, `parent_id`, `name`);
