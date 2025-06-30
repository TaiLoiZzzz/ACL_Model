-- Sample data for Access Control List System
-- Note: This file will be executed by Spring Boot automatically on startup

-- Insert sample permissions
INSERT INTO permissions (id, name, description, resource, action, created_at, updated_at) VALUES 
(1, 'READ_USERS', 'Permission to read user data', 'USERS', 'READ', NOW(), NOW()),
(2, 'CREATE_USERS', 'Permission to create new users', 'USERS', 'CREATE', NOW(), NOW()),
(3, 'UPDATE_USERS', 'Permission to update user data', 'USERS', 'UPDATE', NOW(), NOW()),
(4, 'DELETE_USERS', 'Permission to delete users', 'USERS', 'DELETE', NOW(), NOW()),

(5, 'READ_ROLES', 'Permission to read role data', 'ROLES', 'READ', NOW(), NOW()),
(6, 'CREATE_ROLES', 'Permission to create new roles', 'ROLES', 'CREATE', NOW(), NOW()),
(7, 'UPDATE_ROLES', 'Permission to update role data', 'ROLES', 'UPDATE', NOW(), NOW()),
(8, 'DELETE_ROLES', 'Permission to delete roles', 'ROLES', 'DELETE', NOW(), NOW()),

(9, 'READ_PERMISSIONS', 'Permission to read permission data', 'PERMISSIONS', 'READ', NOW(), NOW()),
(10, 'CREATE_PERMISSIONS', 'Permission to create new permissions', 'PERMISSIONS', 'CREATE', NOW(), NOW()),
(11, 'UPDATE_PERMISSIONS', 'Permission to update permission data', 'PERMISSIONS', 'UPDATE', NOW(), NOW()),
(12, 'DELETE_PERMISSIONS', 'Permission to delete permissions', 'PERMISSIONS', 'DELETE', NOW(), NOW()),

(13, 'READ_GROUPS', 'Permission to read group data', 'GROUPS', 'READ', NOW(), NOW()),
(14, 'CREATE_GROUPS', 'Permission to create new groups', 'GROUPS', 'CREATE', NOW(), NOW()),
(15, 'UPDATE_GROUPS', 'Permission to update group data', 'GROUPS', 'UPDATE', NOW(), NOW()),
(16, 'DELETE_GROUPS', 'Permission to delete groups', 'GROUPS', 'DELETE', NOW(), NOW()),

(17, 'VIEW_REPORTS', 'Permission to view system reports', 'REPORTS', 'READ', NOW(), NOW()),
(18, 'MANAGE_SYSTEM', 'Permission to manage system settings', 'SYSTEM', 'MANAGE', NOW(), NOW());

-- Insert sample roles
INSERT INTO roles (id, name, description, is_active, created_at, updated_at) VALUES 
(1, 'ADMIN', 'System Administrator with full access', true, NOW(), NOW()),
(2, 'USER_MANAGER', 'User Manager with user management permissions', true, NOW(), NOW()),
(3, 'VIEWER', 'Read-only access to most resources', true, NOW(), NOW()),
(4, 'MODERATOR', 'Content moderator with limited admin rights', true, NOW(), NOW()),
(5, 'GUEST', 'Guest user with minimal permissions', true, NOW(), NOW());

-- Insert sample groups
INSERT INTO groups (id, name, description, is_active, created_at, updated_at) VALUES 
(1, 'IT_DEPARTMENT', 'Information Technology Department', true, NOW(), NOW()),
(2, 'HR_DEPARTMENT', 'Human Resources Department', true, NOW(), NOW()),
(3, 'FINANCE_DEPARTMENT', 'Finance Department', true, NOW(), NOW()),
(4, 'MARKETING_DEPARTMENT', 'Marketing Department', true, NOW(), NOW()),
(5, 'EXECUTIVES', 'Executive Management', true, NOW(), NOW());

-- Assign permissions to roles
INSERT INTO role_has_permissions (id, role_id, permission_id, created_at, updated_at) VALUES 
-- ADMIN role gets all permissions
(1, 1, 1, NOW(), NOW()), (2, 1, 2, NOW(), NOW()), (3, 1, 3, NOW(), NOW()), (4, 1, 4, NOW(), NOW()),
(5, 1, 5, NOW(), NOW()), (6, 1, 6, NOW(), NOW()), (7, 1, 7, NOW(), NOW()), (8, 1, 8, NOW(), NOW()),
(9, 1, 9, NOW(), NOW()), (10, 1, 10, NOW(), NOW()), (11, 1, 11, NOW(), NOW()), (12, 1, 12, NOW(), NOW()),
(13, 1, 13, NOW(), NOW()), (14, 1, 14, NOW(), NOW()), (15, 1, 15, NOW(), NOW()), (16, 1, 16, NOW(), NOW()),
(17, 1, 17, NOW(), NOW()), (18, 1, 18, NOW(), NOW()),

-- USER_MANAGER role gets user management permissions
(19, 2, 1, NOW(), NOW()), (20, 2, 2, NOW(), NOW()), (21, 2, 3, NOW(), NOW()), (22, 2, 4, NOW(), NOW()),
(23, 2, 5, NOW(), NOW()), (24, 2, 13, NOW(), NOW()), (25, 2, 17, NOW(), NOW()),

-- VIEWER role gets read permissions only
(26, 3, 1, NOW(), NOW()), (27, 3, 5, NOW(), NOW()), (28, 3, 9, NOW(), NOW()), 
(29, 3, 13, NOW(), NOW()), (30, 3, 17, NOW(), NOW()),

-- MODERATOR role gets moderate permissions
(31, 4, 1, NOW(), NOW()), (32, 4, 3, NOW(), NOW()), (33, 4, 5, NOW(), NOW()), 
(34, 4, 7, NOW(), NOW()), (35, 4, 13, NOW(), NOW()), (36, 4, 15, NOW(), NOW()), (37, 4, 17, NOW(), NOW()),

-- GUEST role gets minimal permissions
(38, 5, 1, NOW(), NOW()), (39, 5, 17, NOW(), NOW());

-- Insert sample users (password is "password123" encoded with BCrypt)
INSERT INTO users (id, username, email, password, full_name, is_active, created_at, updated_at) VALUES 
(1, 'admin', 'admin@example.com', '$2a$10$Xl0yhvzLIxNlUd.xvuTZ9eR8mHaBaM/6bHjyF6w9K5wYHrW8v9/we', 'System Administrator', true, NOW(), NOW()),
(2, 'manager', 'manager@example.com', '$2a$10$Xl0yhvzLIxNlUd.xvuTZ9eR8mHaBaM/6bHjyF6w9K5wYHrW8v9/we', 'User Manager', true, NOW(), NOW()),
(3, 'viewer', 'viewer@example.com', '$2a$10$Xl0yhvzLIxNlUd.xvuTZ9eR8mHaBaM/6bHjyF6w9K5wYHrW8v9/we', 'System Viewer', true, NOW(), NOW()),
(4, 'moderator', 'moderator@example.com', '$2a$10$Xl0yhvzLIxNlUd.xvuTZ9eR8mHaBaM/6bHjyF6w9K5wYHrW8v9/we', 'Content Moderator', true, NOW(), NOW()),
(5, 'guest', 'guest@example.com', '$2a$10$Xl0yhvzLIxNlUd.xvuTZ9eR8mHaBaM/6bHjyF6w9K5wYHrW8v9/we', 'Guest User', true, NOW(), NOW()),
(6, 'john.doe', 'john.doe@example.com', '$2a$10$Xl0yhvzLIxNlUd.xvuTZ9eR8mHaBaM/6bHjyF6w9K5wYHrW8v9/we', 'John Doe', true, NOW(), NOW()),
(7, 'jane.smith', 'jane.smith@example.com', '$2a$10$Xl0yhvzLIxNlUd.xvuTZ9eR8mHaBaM/6bHjyF6w9K5wYHrW8v9/we', 'Jane Smith', true, NOW(), NOW()),
(8, 'mike.wilson', 'mike.wilson@example.com', '$2a$10$Xl0yhvzLIxNlUd.xvuTZ9eR8mHaBaM/6bHjyF6w9K5wYHrW8v9/we', 'Mike Wilson', true, NOW(), NOW()),
(9, 'sarah.johnson', 'sarah.johnson@example.com', '$2a$10$Xl0yhvzLIxNlUd.xvuTZ9eR8mHaBaM/6bHjyF6w9K5wYHrW8v9/we', 'Sarah Johnson', false, NOW(), NOW()),
(10, 'david.brown', 'david.brown@example.com', '$2a$10$Xl0yhvzLIxNlUd.xvuTZ9eR8mHaBaM/6bHjyF6w9K5wYHrW8v9/we', 'David Brown', true, NOW(), NOW());

-- Assign roles to users
INSERT INTO user_has_roles (id, user_id, role_id, created_at, updated_at) VALUES 
(1, 1, 1, NOW(), NOW()),  -- admin has ADMIN role
(2, 2, 2, NOW(), NOW()),  -- manager has USER_MANAGER role
(3, 3, 3, NOW(), NOW()),  -- viewer has VIEWER role
(4, 4, 4, NOW(), NOW()),  -- moderator has MODERATOR role
(5, 5, 5, NOW(), NOW()),  -- guest has GUEST role
(6, 6, 3, NOW(), NOW()),  -- john.doe has VIEWER role
(7, 7, 2, NOW(), NOW()),  -- jane.smith has USER_MANAGER role
(8, 8, 4, NOW(), NOW()),  -- mike.wilson has MODERATOR role
(9, 9, 5, NOW(), NOW()),  -- sarah.johnson has GUEST role (inactive user)
(10, 10, 3, NOW(), NOW()), -- david.brown has VIEWER role
(11, 1, 2, NOW(), NOW()),  -- admin also has USER_MANAGER role (multiple roles)
(12, 2, 3, NOW(), NOW());  -- manager also has VIEWER role (multiple roles)

-- Assign users to groups
INSERT INTO group_has_users (id, group_id, user_id, created_at, updated_at) VALUES 
(1, 1, 1, NOW(), NOW()),   -- admin in IT_DEPARTMENT
(2, 1, 6, NOW(), NOW()),   -- john.doe in IT_DEPARTMENT
(3, 1, 8, NOW(), NOW()),   -- mike.wilson in IT_DEPARTMENT
(4, 2, 2, NOW(), NOW()),   -- manager in HR_DEPARTMENT
(5, 2, 7, NOW(), NOW()),   -- jane.smith in HR_DEPARTMENT
(6, 2, 9, NOW(), NOW()),   -- sarah.johnson in HR_DEPARTMENT
(7, 3, 3, NOW(), NOW()),   -- viewer in FINANCE_DEPARTMENT
(8, 3, 10, NOW(), NOW()),  -- david.brown in FINANCE_DEPARTMENT
(9, 4, 4, NOW(), NOW()),   -- moderator in MARKETING_DEPARTMENT
(10, 4, 5, NOW(), NOW()),  -- guest in MARKETING_DEPARTMENT
(11, 5, 1, NOW(), NOW()),  -- admin in EXECUTIVES
(12, 5, 2, NOW(), NOW());  -- manager in EXECUTIVES

-- Update sequences for auto-increment (PostgreSQL specific)
SELECT setval('permissions_id_seq', (SELECT MAX(id) FROM permissions));
SELECT setval('roles_id_seq', (SELECT MAX(id) FROM roles));
SELECT setval('groups_id_seq', (SELECT MAX(id) FROM groups));
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('role_has_permissions_id_seq', (SELECT MAX(id) FROM role_has_permissions));
SELECT setval('user_has_roles_id_seq', (SELECT MAX(id) FROM user_has_roles));
SELECT setval('group_has_users_id_seq', (SELECT MAX(id) FROM group_has_users)); 