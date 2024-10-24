CREATE TABLE token_store (
    id SERIAL NOT NULL,
    token VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
    expiry_date TIMESTAMP NOT NULL,

    CONSTRAINT pk__token_store PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);