CREATE TABLE department (
                            id IDENTITY PRIMARY KEY,
                            name VARCHAR(500),
                            members INT,
                            archived BOOLEAN DEFAULT false);

ALTER TABLE department ADD CONSTRAINT DEPARTMENT_UNIQUE UNIQUE(name);

CREATE TABLE department_tree (
                                 department_id BIGINT NOT NULL,
                                 child_id BIGINT NOT NULL,
                                 PRIMARY KEY(department_id, child_id));

ALTER TABLE department_tree
    ADD FOREIGN KEY (department_id)
        REFERENCES department(id);

ALTER TABLE department_tree
    ADD FOREIGN KEY (child_id)
        REFERENCES department(id);

