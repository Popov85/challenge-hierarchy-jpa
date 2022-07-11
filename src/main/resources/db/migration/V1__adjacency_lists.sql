CREATE TABLE department (
    id IDENTITY PRIMARY KEY,
    parent_id BIGINT,
    name VARCHAR(500),
    members INT,
    archived BOOLEAN DEFAULT false);

ALTER TABLE department ADD CONSTRAINT DEPARTMENT_UNIQUE UNIQUE(name);

ALTER TABLE department
    ADD FOREIGN KEY (parent_id)
        REFERENCES department(id);

