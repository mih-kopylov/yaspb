ALTER TABLE `reason_groups`
    ADD FOREIGN KEY `fk__reason_groups__user`(`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE ON UPDATE CASCADE ,
    ADD FOREIGN KEY `fk__reason_groups__parent`(`parent_id`) REFERENCES `reason_groups`(`id`) ON DELETE CASCADE ON UPDATE CASCADE