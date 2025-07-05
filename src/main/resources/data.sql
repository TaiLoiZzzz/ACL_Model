-- 1. Bảng 'users' (Thêm nhiều người dùng hơn)
INSERT INTO users (username, email, full_name, is_active, password, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                                        ('adminuser', 'admin@example.com', 'Admin User', TRUE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system'),
                                                                                                                        ('editoruser', 'editor@example.com', 'Editor User', TRUE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system'),
                                                                                                                        ('authoruser', 'author@example.com', 'Author User', TRUE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system'),
                                                                                                                        ('subscriber', 'sub@example.com', 'Subscriber User', TRUE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system'),
                                                                                                                        ('moderator', 'mod@example.com', 'Moderator User', TRUE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system'),
                                                                                                                        ('author2', 'author2@example.com', 'Alice Wonderland', TRUE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system'),
                                                                                                                        ('subscriber2', 'sub2@example.com', 'Bob The Builder', TRUE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system'),
                                                                                                                        ('guestreader', 'guest@example.com', 'Charlie Chaplin', TRUE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system'),
                                                                                                                        ('inactiveuser', 'inactive@example.com', 'Inactive Account', FALSE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system'),
                                                                                                                        ('superauthor', 'superauthor@example.com', 'Super Author', TRUE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system'),
                                                                                                                        ('neweditor', 'neweditor@example.com', 'David Editor', TRUE, '$2a$10$abcdefghijklmnopqrstuvwxyza', NOW(), NOW(), 'system', 'system');

-- 2. Bảng 'roles' (Giữ nguyên)
INSERT INTO roles (name, description, is_active, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                     ('ROLE_ADMIN', 'Administrator with full access', TRUE, NOW(), NOW(), 'system', 'system'),
                                                                                                     ('ROLE_EDITOR', 'Editor can manage posts and categories', TRUE, NOW(), NOW(), 'system', 'system'),
                                                                                                     ('ROLE_AUTHOR', 'Author can create and manage their own posts', TRUE, NOW(), NOW(), 'system', 'system'),
                                                                                                     ('ROLE_SUBSCRIBER', 'Subscriber can read posts and comment', TRUE, NOW(), NOW(), 'system', 'system'),
                                                                                                     ('ROLE_MODERATOR', 'Moderator can approve/reject comments', TRUE, NOW(), NOW(), 'system', 'system');

-- 3. Bảng 'permissions' (Giữ nguyên)
INSERT INTO permissions (name, action, resource, description, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                                  ('user:read_all', 'read_all', 'user', 'View all user accounts', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('user:read', 'read', 'user', 'View a specific user account', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('user:update', 'update', 'user', 'Update any user account', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('user:delete', 'delete', 'user', 'Delete any user account', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('user:assign_role', 'assign_role', 'user', 'Assign roles to users', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('user:remove_role', 'remove_role', 'user', 'Remove roles from users', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('user:add_to_group', 'add_to_group', 'user', 'Add users to groups', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('user:remove_from_group', 'remove_from_group', 'user', 'Remove users from groups', NOW(), NOW(), 'system', 'system'),

                                                                                                                  ('role:read_all', 'read_all', 'role', 'View all roles', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('role:create', 'create', 'role', 'Create new roles', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('role:update', 'update', 'role', 'Update existing roles', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('role:delete', 'delete', 'role', 'Delete roles', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('role:assign_permission', 'assign_permission', 'role', 'Assign permissions to roles', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('role:remove_permission', 'remove_permission', 'role', 'Remove permissions from roles', NOW(), NOW(), 'system', 'system'),

                                                                                                                  ('permission:read_all', 'read_all', 'permission', 'View all permissions', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('permission:create', 'create', 'permission', 'Create new permissions', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('permission:update', 'update', 'permission', 'Update existing permissions', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('permission:delete', 'delete', 'permission', 'Delete permissions', NOW(), NOW(), 'system', 'system'),

                                                                                                                  ('group:read_all', 'read_all', 'group', 'View all groups', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('group:create', 'create', 'group', 'Create new groups', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('group:update', 'update', 'group', 'Update existing groups', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('group:delete', 'delete', 'group', 'Delete groups', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('group:assign_permission', 'assign_permission', 'group', 'Assign permissions to groups', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('group:remove_permission', 'remove_permission', 'group', 'Remove permissions from groups', NOW(), NOW(), 'system', 'system'),

                                                                                                                  ('post:read_all', 'read_all', 'post', 'View all posts', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('post:read', 'read', 'post', 'View a specific post', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('post:create', 'create', 'post', 'Create new posts', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('post:update_own', 'update_own', 'post', 'Update own posts', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('post:update_any', 'update_any', 'post', 'Update any post', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('post:delete_own', 'delete_own', 'post', 'Delete own posts', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('post:delete_any', 'delete_any', 'post', 'Delete any post', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('post:change_status', 'change_status', 'post', 'Change post status (e.g., publish)', NOW(), NOW(), 'system', 'system'),

                                                                                                                  ('category:read_all', 'read_all', 'category', 'View all categories', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('category:read', 'read', 'category', 'View a specific category', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('category:create', 'create', 'category', 'Create new categories', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('category:update', 'update', 'category', 'Update existing categories', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('category:delete', 'delete', 'category', 'Delete categories', NOW(), NOW(), 'system', 'system'),

                                                                                                                  ('comment:read_all', 'read_all', 'comment', 'View all comments', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('comment:read', 'read', 'comment', 'View a specific comment', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('comment:create', 'create', 'comment', 'Create new comments', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('comment:update_own', 'update_own', 'comment', 'Update own comments', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('comment:update_any', 'update_any', 'comment', 'Update any comment', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('comment:delete_own', 'delete_own', 'comment', 'Delete own comments', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('comment:delete_any', 'delete_any', 'comment', 'Delete any comment', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('comment:approve', 'approve', 'comment', 'Approve comments', NOW(), NOW(), 'system', 'system'),
                                                                                                                  ('comment:reject', 'reject', 'comment', 'Reject comments', NOW(), NOW(), 'system', 'system');

-- 4. Bảng 'role_has_permissions' (Mở rộng để phù hợp với người dùng mới nếu có)
INSERT INTO role_has_permissions (role_id, permission_id, created_at, updated_at, created_by, updated_by) VALUES
-- Admin Role Permissions
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'user:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'user:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'user:update'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'user:delete'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'user:assign_role'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'user:remove_role'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'user:add_to_group'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'user:remove_from_group'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'role:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'role:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'role:update'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'role:delete'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'role:assign_permission'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'role:remove_permission'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'permission:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'permission:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'permission:update'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'permission:delete'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'group:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'group:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'group:update'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'group:delete'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'group:assign_permission'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'group:remove_permission'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'post:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'post:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'post:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'post:update_own'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'post:update_any'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'post:delete_own'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'post:delete_any'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'post:change_status'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'category:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'category:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'category:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'category:update'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'category:delete'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'comment:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'comment:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'comment:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'comment:update_own'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'comment:update_any'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'comment:delete_own'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'comment:delete_any'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'comment:approve'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), (SELECT id FROM permissions WHERE name = 'comment:reject'), NOW(), NOW(), 'system', 'system'),

-- Editor Role Permissions
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'post:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'post:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'post:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'post:update_any'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'post:delete_any'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'post:change_status'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'category:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'category:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'category:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'category:update'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'category:delete'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'comment:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'comment:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'comment:approve'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), (SELECT id FROM permissions WHERE name = 'comment:reject'), NOW(), NOW(), 'system', 'system'),

-- Author Role Permissions
((SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), (SELECT id FROM permissions WHERE name = 'post:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), (SELECT id FROM permissions WHERE name = 'post:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), (SELECT id FROM permissions WHERE name = 'post:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), (SELECT id FROM permissions WHERE name = 'post:update_own'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), (SELECT id FROM permissions WHERE name = 'post:delete_own'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), (SELECT id FROM permissions WHERE name = 'comment:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), (SELECT id FROM permissions WHERE name = 'comment:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), (SELECT id FROM permissions WHERE name = 'comment:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), (SELECT id FROM permissions WHERE name = 'comment:update_own'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), (SELECT id FROM permissions WHERE name = 'comment:delete_own'), NOW(), NOW(), 'system', 'system'),

-- Subscriber Role Permissions
((SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), (SELECT id FROM permissions WHERE name = 'post:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), (SELECT id FROM permissions WHERE name = 'post:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), (SELECT id FROM permissions WHERE name = 'comment:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), (SELECT id FROM permissions WHERE name = 'comment:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), (SELECT id FROM permissions WHERE name = 'comment:create'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), (SELECT id FROM permissions WHERE name = 'comment:update_own'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), (SELECT id FROM permissions WHERE name = 'comment:delete_own'), NOW(), NOW(), 'system', 'system'),

-- Moderator Role Permissions
((SELECT id FROM roles WHERE name = 'ROLE_MODERATOR'), (SELECT id FROM permissions WHERE name = 'comment:read_all'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_MODERATOR'), (SELECT id FROM permissions WHERE name = 'comment:read'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_MODERATOR'), (SELECT id FROM permissions WHERE name = 'comment:approve'), NOW(), NOW(), 'system', 'system'),
((SELECT id FROM roles WHERE name = 'ROLE_MODERATOR'), (SELECT id FROM permissions WHERE name = 'comment:reject'), NOW(), NOW(), 'system', 'system');

-- 5. Bảng 'user_has_roles' (Mở rộng cho người dùng mới)
INSERT INTO user_has_roles (user_id, role_id, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                  ((SELECT id FROM users WHERE username = 'adminuser'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN'), NOW(), NOW(), 'system', 'system'),
                                                                                                  ((SELECT id FROM users WHERE username = 'editoruser'), (SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), NOW(), NOW(), 'system', 'system'),
                                                                                                  ((SELECT id FROM users WHERE username = 'authoruser'), (SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), NOW(), NOW(), 'system', 'system'),
                                                                                                  ((SELECT id FROM users WHERE username = 'subscriber'), (SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), NOW(), NOW(), 'system', 'system'),
                                                                                                  ((SELECT id FROM users WHERE username = 'moderator'), (SELECT id FROM roles WHERE name = 'ROLE_MODERATOR'), NOW(), NOW(), 'system', 'system'),
                                                                                                  ((SELECT id FROM users WHERE username = 'author2'), (SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), NOW(), NOW(), 'system', 'system'),
                                                                                                  ((SELECT id FROM users WHERE username = 'author2'), (SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), NOW(), NOW(), 'system', 'system'), -- Tác giả cũng có thể là người đăng ký
                                                                                                  ((SELECT id FROM users WHERE username = 'subscriber2'), (SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), NOW(), NOW(), 'system', 'system'),
                                                                                                  ((SELECT id FROM users WHERE username = 'guestreader'), (SELECT id FROM roles WHERE name = 'ROLE_SUBSCRIBER'), NOW(), NOW(), 'system', 'system'),
                                                                                                  ((SELECT id FROM users WHERE username = 'superauthor'), (SELECT id FROM roles WHERE name = 'ROLE_AUTHOR'), NOW(), NOW(), 'system', 'system'),
                                                                                                  ((SELECT id FROM users WHERE username = 'neweditor'), (SELECT id FROM roles WHERE name = 'ROLE_EDITOR'), NOW(), NOW(), 'system', 'system');

-- 6. Bảng 'groups' (Thêm nhóm mới)
INSERT INTO groups (name, description, is_active, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                      ('Content Creators', 'Group for all authors and editors', TRUE, NOW(), NOW(), 'system', 'system'),
                                                                                                      ('Moderation Team', 'Group for comment moderators', TRUE, NOW(), NOW(), 'system', 'system'),
                                                                                                      ('Marketing Team', 'Group for marketing related tasks and analytics', TRUE, NOW(), NOW(), 'system', 'system');

-- 7. Bảng 'group_has_users' (Mở rộng để gán người dùng vào nhóm mới)
INSERT INTO group_has_users (group_id, user_id, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                    ((SELECT id FROM groups WHERE name = 'Content Creators'), (SELECT id FROM users WHERE username = 'editoruser'), NOW(), NOW(), 'system', 'system'),
                                                                                                    ((SELECT id FROM groups WHERE name = 'Content Creators'), (SELECT id FROM users WHERE username = 'authoruser'), NOW(), NOW(), 'system', 'system'),
                                                                                                    ((SELECT id FROM groups WHERE name = 'Content Creators'), (SELECT id FROM users WHERE username = 'author2'), NOW(), NOW(), 'system', 'system'),
                                                                                                    ((SELECT id FROM groups WHERE name = 'Moderation Team'), (SELECT id FROM users WHERE username = 'moderator'), NOW(), NOW(), 'system', 'system'),
                                                                                                    ((SELECT id FROM groups WHERE name = 'Moderation Team'), (SELECT id FROM users WHERE username = 'adminuser'), NOW(), NOW(), 'system', 'system'),
                                                                                                    ((SELECT id FROM groups WHERE name = 'Marketing Team'), (SELECT id FROM users WHERE username = 'editoruser'), NOW(), NOW(), 'system', 'system'),
                                                                                                    ((SELECT id FROM groups WHERE name = 'Marketing Team'), (SELECT id FROM users WHERE username = 'subscriber'), NOW(), NOW(), 'system', 'system');

-- 8. Bảng 'group_has_permission' (Gán thêm quyền cho nhóm mới)
INSERT INTO group_has_permission (group_id, permission_id, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                               ((SELECT id FROM groups WHERE name = 'Moderation Team'), (SELECT id FROM permissions WHERE name = 'comment:approve'), NOW(), NOW(), 'system', 'system'),
                                                                                                               ((SELECT id FROM groups WHERE name = 'Moderation Team'), (SELECT id FROM permissions WHERE name = 'comment:reject'), NOW(), NOW(), 'system', 'system'),
                                                                                                               ((SELECT id FROM groups WHERE name = 'Marketing Team'), (SELECT id FROM permissions WHERE name = 'post:read_all'), NOW(), NOW(), 'system', 'system'),
                                                                                                               ((SELECT id FROM groups WHERE name = 'Marketing Team'), (SELECT id FROM permissions WHERE name = 'category:read_all'), NOW(), NOW(), 'system', 'system');

-- 9. Bảng 'categories' (Thêm nhiều danh mục hơn)
INSERT INTO categories (category_name, description, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                        ('Technology', 'Articles about software, hardware, and IT trends.', NOW(), NOW(), 'system', 'system'),
                                                                                                        ('Lifestyle', 'Posts on daily living, health, and personal development.', NOW(), NOW(), 'system', 'system'),
                                                                                                        ('Travel', 'Stories and guides about travel destinations.', NOW(), NOW(), 'system', 'system'),
                                                                                                        ('Food', 'Recipes, restaurant reviews, and culinary tips.', NOW(), NOW(), 'system', 'system'),
                                                                                                        ('Science', 'Explorations into various scientific fields and discoveries.', NOW(), NOW(), 'system', 'system'),
                                                                                                        ('Art & Culture', 'Discussions on art, literature, music, and cultural events.', NOW(), NOW(), 'system', 'system'),
                                                                                                        ('Health & Fitness', 'Tips and guides for a healthy lifestyle, workouts, and nutrition.', NOW(), NOW(), 'system', 'system');

-- 10. Bảng 'post' (Thêm nhiều bài viết hơn, với các tác giả khác nhau, trạng thái khác nhau)
INSERT INTO post (user_id, title, content, slug, status, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                             ((SELECT id FROM users WHERE username = 'authoruser'), 'Getting Started with Spring Boot', 'This is a comprehensive guide to setting up your first Spring Boot application. It covers initial setup, dependencies, and basic project structure. Ideal for beginners.', 'getting-started-with-spring-boot', 'published', NOW(), NOW(), 'authoruser', 'authoruser'),
                                                                                                             ((SELECT id FROM users WHERE username = 'authoruser'), 'My First Travel Blog Post', 'Exploring the beautiful landscapes of Vietnam, from Hanoi ancient streets to the breathtaking Ha Long Bay. A journey of discovery.', 'my-first-travel-blog-post', 'published', NOW(), NOW(), 'authoruser', 'authoruser'),
                                                                                                             ((SELECT id FROM users WHERE username = 'editoruser'), 'Advanced JPA Techniques', 'Deep dive into advanced JPA features like custom repositories, Criteria API, and performance optimization strategies for complex database interactions.', 'advanced-jpa-techniques', 'published', NOW(), NOW(), 'editoruser', 'editoruser'),
                                                                                                             ((SELECT id FROM users WHERE username = 'editoruser'), 'Draft Post for Review', 'This post is still under construction and needs review from editors. Focuses on the new features in Spring 6.', 'draft-post-for-review', 'draft', NOW(), NOW(), 'editoruser', 'editoruser'),
                                                                                                             ((SELECT id FROM users WHERE username = 'author2'), 'Understanding Quantum Physics', 'A beginner-friendly introduction to the fascinating world of quantum mechanics, explaining concepts like superposition and entanglement.', 'understanding-quantum-physics', 'published', NOW(), NOW(), 'author2', 'author2'),
                                                                                                             ((SELECT id FROM users WHERE username = 'author2'), 'Healthy Eating Habits for Busy Professionals', 'Practical tips and simple recipes for maintaining a balanced diet amidst a hectic work schedule.', 'healthy-eating-habits', 'published', NOW(), NOW(), 'author2', 'author2'),
                                                                                                             ((SELECT id FROM users WHERE username = 'superauthor'), 'The Future of AI in Software Development', 'An in-depth analysis of how Artificial Intelligence is transforming the software industry, from automated code generation to intelligent debugging tools.', 'future-of-ai-in-software-dev', 'published', NOW(), NOW(), 'superauthor', 'superauthor'),
                                                                                                             ((SELECT id FROM users WHERE username = 'superauthor'), 'Hidden Gems of Southeast Asian Cuisine', 'A culinary journey through lesser-known dishes and street food delights across Southeast Asia, perfect for food enthusiasts.', 'hidden-gems-southeast-asian-cuisine', 'published', NOW(), NOW(), 'superauthor', 'superauthor'),
                                                                                                             ((SELECT id FROM users WHERE username = 'neweditor'), 'Web Security Best Practices 2025', 'An updated guide to modern web security vulnerabilities and the best practices to mitigate them in web applications, including new threats.', 'web-security-best-practices-2025', 'draft', NOW(), NOW(), 'neweditor', 'neweditor'),
                                                                                                             ((SELECT id FROM users WHERE username = 'subscriber'), 'A Simple Guide to Gardening for Beginners', 'Tips and tricks for starting your first home garden, from choosing plants to pest control.', 'gardening-for-beginners', 'published', NOW(), NOW(), 'subscriber', 'subscriber');


-- 11. Bảng 'post_has_categories' (Liên kết nhiều bài viết với nhiều danh mục)
INSERT INTO post_has_categories (post_id, category_id, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                           ((SELECT id FROM post WHERE slug = 'getting-started-with-spring-boot'), (SELECT id FROM categories WHERE category_name = 'Technology'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'getting-started-with-spring-boot'), (SELECT id FROM categories WHERE category_name = 'Science'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'my-first-travel-blog-post'), (SELECT id FROM categories WHERE category_name = 'Travel'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'advanced-jpa-techniques'), (SELECT id FROM categories WHERE category_name = 'Technology'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'advanced-jpa-techniques'), (SELECT id FROM categories WHERE category_name = 'Science'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'draft-post-for-review'), (SELECT id FROM categories WHERE category_name = 'Technology'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'understanding-quantum-physics'), (SELECT id FROM categories WHERE category_name = 'Science'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'healthy-eating-habits'), (SELECT id FROM categories WHERE category_name = 'Health & Fitness'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'healthy-eating-habits'), (SELECT id FROM categories WHERE category_name = 'Lifestyle'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'the-future-of-ai-in-software-dev'), (SELECT id FROM categories WHERE category_name = 'Technology'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'hidden-gems-southeast-asian-cuisine'), (SELECT id FROM categories WHERE category_name = 'Food'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'hidden-gems-southeast-asian-cuisine'), (SELECT id FROM categories WHERE category_name = 'Travel'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'web-security-best-practices-2025'), (SELECT id FROM categories WHERE category_name = 'Technology'), NOW(), NOW(), 'system', 'system'),
                                                                                                           ((SELECT id FROM post WHERE slug = 'gardening-for-beginners'), (SELECT id FROM categories WHERE category_name = 'Lifestyle'), NOW(), NOW(), 'system', 'system');

-- 12. Bảng 'comments' (Thêm nhiều bình luận hơn, bao gồm lồng nhau và tất cả đều có user_id)
INSERT INTO comments (post_id, user_id, author_name, author_email, content, is_approved, parent_comment_id, created_at, updated_at, created_by, updated_by) VALUES
-- Comments for 'Getting Started with Spring Boot' (Post ID 1)
((SELECT id FROM post WHERE slug = 'getting-started-with-spring-boot'), (SELECT id FROM users WHERE username = 'subscriber'), 'Subscriber User', 'sub@example.com', 'Bài viết rất hay, giúp tôi hiểu rõ hơn về Spring Boot.', TRUE, NULL, NOW(), NOW(), 'subscriber', 'subscriber'),
((SELECT id FROM post WHERE slug = 'getting-started-with-spring-boot'), (SELECT id FROM users WHERE username = 'editoruser'), 'Editor User', 'editor@example.com', 'Cảm ơn bạn! Rất vui vì nó hữu ích.', TRUE, (SELECT id FROM comments WHERE content = 'Bài viết rất hay, giúp tôi hiểu rõ hơn về Spring Boot.' AND post_id = (SELECT id FROM post WHERE slug = 'getting-started-with-spring-boot')), NOW(), NOW(), 'editoruser', 'editoruser'),
((SELECT id FROM post WHERE slug = 'getting-started-with-spring-boot'), (SELECT id FROM users WHERE username = 'subscriber2'), 'Bob The Builder', 'sub2@example.com', 'Tôi có câu hỏi về cấu hình bảo mật trong Spring Boot, có thể hướng dẫn thêm không?', FALSE, NULL, NOW(), NOW(), 'subscriber2', 'subscriber2'),
((SELECT id FROM post WHERE slug = 'getting-started-with-spring-boot'), (SELECT id FROM users WHERE username = 'adminuser'), 'Admin User', 'admin@example.com', 'Bình luận này cần được duyệt kỹ hơn.', FALSE, (SELECT id FROM comments WHERE content = 'Tôi có câu hỏi về cấu hình bảo mật trong Spring Boot, có thể hướng dẫn thêm không?' AND post_id = (SELECT id FROM post WHERE slug = 'getting-started-with-spring-boot')), NOW(), NOW(), 'adminuser', 'adminuser'),
((SELECT id FROM post WHERE slug = 'getting-started-with-spring-boot'), (SELECT id FROM users WHERE username = 'authoruser'), 'Author User', 'author@example.com', 'Chào Bob, tôi sẽ sớm có bài viết chi tiết về security nhé!', TRUE, (SELECT id FROM comments WHERE content = 'Tôi có câu hỏi về cấu hình bảo mật trong Spring Boot, có thể hướng dẫn thêm không?' AND post_id = (SELECT id FROM post WHERE slug = 'getting-started-with-spring-boot')), NOW(), NOW(), 'authoruser', 'authoruser'),

-- Comments for 'My First Travel Blog Post' (Post ID 2)
((SELECT id FROM post WHERE slug = 'my-first-travel-blog-post'), (SELECT id FROM users WHERE username = 'subscriber'), 'Subscriber User', 'sub@example.com', 'Việt Nam đẹp quá! Bạn đã ghé thăm thành phố nào?', TRUE, NULL, NOW(), NOW(), 'subscriber', 'subscriber'),
((SELECT id FROM post WHERE slug = 'my-first-travel-blog-post'), (SELECT id FROM users WHERE username = 'authoruser'), 'Author User', 'author@example.com', 'Tôi chủ yếu khám phá Hà Nội và Vịnh Hạ Long.', TRUE, (SELECT id FROM comments WHERE content = 'Việt Nam đẹp quá! Bạn đã ghé thăm thành phố nào?' AND post_id = (SELECT id FROM post WHERE slug = 'my-first-travel-blog-post')), NOW(), NOW(), 'authoruser', 'authoruser'),
((SELECT id FROM post WHERE slug = 'my-first-travel-blog-post'), (SELECT id FROM users WHERE username = 'guestreader'), 'Charlie Chaplin', 'guest@example.com', 'Ảnh đẹp tuyệt vời! Có thể chia sẻ thêm về trải nghiệm ẩm thực không?', TRUE, NULL, NOW(), NOW(), 'guestreader', 'guestreader'),

-- Comments for 'Advanced JPA Techniques' (Post ID 3)
((SELECT id FROM post WHERE slug = 'advanced-jpa-techniques'), (SELECT id FROM users WHERE username = 'author2'), 'Alice Wonderland', 'author2@example.com', 'Bài viết rất sâu sắc, giúp tôi giải quyết nhiều vấn đề về hiệu suất.', TRUE, NULL, NOW(), NOW(), 'author2', 'author2'),
((SELECT id FROM post WHERE slug = 'advanced-jpa-techniques'), (SELECT id FROM users WHERE username = 'adminuser'), 'Admin User', 'admin@example.com', 'Tôi cũng thấy bài này rất chất lượng, cần chia sẻ rộng rãi.', TRUE, (SELECT id FROM comments WHERE content = 'Bài viết rất sâu sắc, giúp tôi giải quyết nhiều vấn đề về hiệu suất.' AND post_id = (SELECT id FROM post WHERE slug = 'advanced-jpa-techniques')), NOW(), NOW(), 'adminuser', 'adminuser'),

-- Comments for 'Understanding Quantum Physics' (Post ID 5)
((SELECT id FROM post WHERE slug = 'understanding-quantum-physics'), (SELECT id FROM users WHERE username = 'subscriber2'), 'Bob The Builder', 'sub2@example.com', 'Khoa học lượng tử luôn hấp dẫn! Rất mong chờ các bài viết tiếp theo.', TRUE, NULL, NOW(), NOW(), 'subscriber2', 'subscriber2');

-- 13. Bảng 'refresh_tokens' (Thêm thêm token cho các user mới)
INSERT INTO refresh_tokens (user_id, token, expires_at, is_revoked, created_at, updated_at, created_by, updated_by) VALUES
                                                                                                                        ((SELECT id FROM users WHERE username = 'adminuser'), 'admin_refresh_token_xyz123', NOW() + INTERVAL '7 day', FALSE, NOW(), NOW(), 'system', 'system'),
                                                                                                                        ((SELECT id FROM users WHERE username = 'authoruser'), 'author_refresh_token_abc456', NOW() + INTERVAL '7 day', FALSE, NOW(), NOW(), 'system', 'system'),
                                                                                                                        ((SELECT id FROM users WHERE username = 'editoruser'), 'editor_refresh_token_def789', NOW() + INTERVAL '7 day', FALSE, NOW(), NOW(), 'system', 'system'),
                                                                                                                        ((SELECT id FROM users WHERE username = 'subscriber'), 'subscriber_refresh_token_ghi012', NOW() + INTERVAL '7 day', FALSE, NOW(), NOW(), 'system', 'system'),
                                                                                                                        ((SELECT id FROM users WHERE username = 'author2'), 'author2_refresh_token_jkl345', NOW() + INTERVAL '7 day', FALSE, NOW(), NOW(), 'system', 'system');