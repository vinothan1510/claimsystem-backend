-- ✅ 1. USERS TABLE
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
);

-- ✅ 2. POLICY LIST TABLE
CREATE TABLE policy_list (
    policy_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    policy_name VARCHAR(255),
    policy_type VARCHAR(100),
    coverage_amount DECIMAL(15,2),
    premium_amount DECIMAL(15,2),
    description VARCHAR(500),
    is_active BOOLEAN,
    created_at DATETIME,
    updated_at DATETIME
);

-- ✅ 3. CLAIMS TABLE
CREATE TABLE claims (
    claim_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    claim_amount DECIMAL(15,2),
    claim_reason VARCHAR(500),
    claim_status VARCHAR(50),
    officer_remark VARCHAR(255),

    policy_id BIGINT,
    user_id BIGINT,

    created_at DATETIME,
    updated_at DATETIME,

    FOREIGN KEY (policy_id) REFERENCES policy_list(policy_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- ✅ 4. CLAIM DOCUMENT TABLE
CREATE TABLE claim_document (
    document_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    document_name VARCHAR(255),
    document_path VARCHAR(500),

    claims_id BIGINT,

    FOREIGN KEY (claims_id) REFERENCES claims(claim_id)
);

-- ✅ 5. USER POLICY TABLE
CREATE TABLE user_policy (
    user_policy_id BIGINT AUTO_INCREMENT PRIMARY KEY,

    user_id BIGINT NOT NULL,
    policy_id BIGINT NOT NULL,

    purchase_date DATETIME,
    expiry_date DATE,
    is_active BOOLEAN,
    premium_paid DECIMAL(15,2),

    created_at DATETIME,
    updated_at DATETIME,

    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (policy_id) REFERENCES policy_list(policy_id)
);

-- ✅ 6. TOKEN BLACKLIST TABLE
CREATE TABLE token_blacklist (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(600) NOT NULL,
    expiry DATETIME
);