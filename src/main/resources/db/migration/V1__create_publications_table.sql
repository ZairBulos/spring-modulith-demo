CREATE TABLE IF NOT EXISTS publications (
    id              BIGSERIAL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    content         TEXT NOT NULL,
    published_at    TIMESTAMP WITH TIME ZONE NOT NULL
);
