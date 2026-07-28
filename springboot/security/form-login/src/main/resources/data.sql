CREATE DATABASE java_basic
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id VARCHAR(50) NOT NULL,
                        password VARCHAR(50) NOT NULL,
                        user_name VARCHAR(20) NOT NULL
);

INSERT INTO member (user_id, password, user_name) VALUES
                                                      ('hong', '1234', '홍길동'),
                                                      ('kim', '1234', '김철수'),
                                                      ('lee', '1234', '이영희'),
                                                      ('park', '1234', '박민수'),
                                                      ('choi', '1234', '최지현');

-- user 테이블 생성
CREATE TABLE user (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(20),
                      email VARCHAR(50),
                      user_id VARCHAR(50),
                      password VARCHAR(100),
                      role ENUM('ROLE_USER', 'ROLE_ADMIN') DEFAULT 'ROLE_USER',
                      PRIMARY KEY (id)
);