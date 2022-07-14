delete from department_tree;
delete from department;

ALTER TABLE department ALTER COLUMN id RESTART WITH 1;