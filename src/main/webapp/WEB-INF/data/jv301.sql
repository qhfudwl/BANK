DROP TABLE AccRecord;
DROP TABLE Account;
DROP TABLE Customer;

CREATE TABLE Customer (
   id			BIGINT         PRIMARY KEY GENERATED ALWAYS AS IDENTITY,		-- 은행 내 고객 아이디
   userId		VARCHAR(30)		NOT NULL,										-- 고객 아이디
   passwd		VARCHAR(30)		NOT NULL,										-- 고객 비번
   userName		VARCHAR(10)		NOT NULL,										-- 고객 이름
   ssn			VARCHAR(14)		NOT NULL,										-- 주민번호
   phone		VARCHAR(13)		NOT	NULL	DEFAULT '',							-- 폰번호
   email		VARCHAR(40)		NOT NULL   DEFAULT '',							-- 이메일 (없어도되지만 안적으면 그냥 빈문자열)
   addr			VARCHAR(100),													-- 주소
   regDate		TIMESTAMP		NOT NULL   DEFAULT CURRENT_TIMESTAMP,			-- 등록날짜(읽기 전용)
   
   CONSTRAINT   Customer_userId_UK   UNIQUE(userId),							-- 고객 아이디는 중복 없이
   CONSTRAINT   Customer_ssn_UK      UNIQUE(ssn)								-- 주민번호도 중복 없이
);

CREATE TABLE Account (
   id				BIGINT      PRIMARY KEY GENERATED ALWAYS AS IDENTITY,   	-- 계좌 아이디
   customerId		BIGINT      NOT NULL,                           			-- 고객 번호
   accType			CHAR(1)     NOT NULL   DEFAULT 'S',               			-- 계좌 타입
   accNumber		VARCHAR(9) 	NOT NULL,                           				-- 계좌 번호
   balance			DOUBLE      NOT NULL   DEFAULT 0.0,               			-- 잔액
   interestRate		DOUBLE      NOT NULL   DEFAULT 0.0,               			-- 이자율
   overDraft		DOUBLE      NOT NULL   DEFAULT 0.0,               			-- 한도액
   regDate			TIMESTAMP   NOT NULL   DEFAULT CURRENT_TIMESTAMP,      		-- 등록날짜(읽기전용)
   
   CONSTRAINT		Account_customerId_FK 	FOREIGN KEY(customerId) REFERENCES Customer(id) ON DELETE CASCADE,	-- 고객 번호는 FK
   CONSTRAINT		Account_accNumber_UK	UNIQUE(accNumber) 
);

CREATE TABLE AccRecord (
	id			BIGINT			PRIMARY KEY GENERATED ALWAYS AS IDENTITY,	-- 거래 아이디
	accId		BIGINT			NOT NULL,									-- 계좌 아이디 가져오기		
	transType	CHAR(1)			NOT NULL,									-- 입금(D) / 출금(W)
	amount		DOUBLE			NOT NULL,									-- 거래액
	balance		DOUBLE 			NOT NULL,									-- 거래 후 잔액
	content		VARCHAR(20)		NOT NULL,									-- 거래 내용
	regDate		TIMESTAMP		NOT NULL	DEFAULT CURRENT_TIMESTAMP,		-- 거래 날짜(읽기전용)
	
	CONSTRAINT	AccRecord_accId_FK				FOREIGN KEY(accId) REFERENCES Account(id) ON DELETE CASCADE
);

-- TEST ON DELETE CASCADE
DELETE FROM Account WHERE accNumber='1234-5678';


SELECT * FROM Customer;
SELECT * FROM Account;
SELECT * FROM AccRecord;

DELETE FROM Customer WHERE id=8;

INSERT INTO Customer(userId, passwd, userName, ssn) 
VALUES('aru', 'aru123', '김아루', '111111-1111111');


-- -- @author 윤효심
INSERT INTO Customer(userId, passwd, userName, ssn) 
VALUES('thisid', 'thispw', 'thisname', '111111-1111112');

INSERT INTO Account(customerId,accType,accNumber,balance,interestRate,overDraft)
VALUES(5,'S','1234-5678',5000.0,0.1,0.0);

INSERT INTO Account(customerId,accType,accNumber,balance,interestRate,overDraft)
VALUES(5,'C','1234-5679',4000.0,0.0,2000);

-- 1고객이 가진 모든 계좌 가져오기
SELECT * FROM Account INNER JOIN Customer ON Customer.userId='thisid' WHERE Account.customerId = Customer.id;

-- -- end 

-- --@author 김보령

SELECT c.id, a.accType, a.accNumber, a.balance
FROM Customer c, Account a
WHERE c.ssn='111111-1111111' AND a.customerId=c.id

INSERT INTO AccRecord(accId, transType, amount, balance, content)
VALUES(1, 'D', 1000, 10000, '훈련고용비');

INSERT INTO AccRecord(accId, transType, amount, balance, content)
VALUES(1, 'W', 2000, 20000, '카드출금');

INSERT INTO AccRecord(accId, transType, amount, balance, content)
VALUES(1, 'W', 3000, 30000, 'CU편의점');

INSERT INTO AccRecord(accId, transType, amount, balance, content)
VALUES(1, 'D', 3000, 30000, '용돈');

SELECT c.id, a.accType, a.accNumber, a.balance
FROM Customer c, Account a
WHERE c.ssn='111111-1111111' AND a.customerId=c.id


-- --  end
