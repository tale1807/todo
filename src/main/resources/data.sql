INSERT INTO Users (id, name, email, password)
VALUES (UUID(), 'Иван Иванов', 'ivan@example.com', 'hashed_password');


INSERT INTO Tasks (id, user_id, title, description, status)
VALUES (UUID(), (SELECT id FROM Users WHERE name = 'Иван Иванов'), 'Новая задача', 'Описание задачи', true);
