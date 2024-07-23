CREATE TABLE Users
(
    id         UUID PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Tasks
(
    id          UUID PRIMARY KEY,
    user_id     UUID         NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status      VARCHAR(20)  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (id)
);
CREATE TABLE Tags
(
    id   UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE Reminders
(
    id            UUID PRIMARY KEY,
    task_id       UUID      NOT NULL,
    reminder_date TIMESTAMP NOT NULL,
    FOREIGN KEY (task_id) REFERENCES Tasks (id)
);
CREATE TABLE TaskTags
(
    task_id UUID NOT NULL,
    tag_id  UUID NOT NULL,
    PRIMARY KEY (task_id, tag_id),
    FOREIGN KEY (task_id) REFERENCES Tasks (id),
    FOREIGN KEY (tag_id) REFERENCES Tags (id)
);
