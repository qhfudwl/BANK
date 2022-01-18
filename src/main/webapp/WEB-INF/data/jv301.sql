DROP TABLE AccRecord;
DROP TABLE Account;
DROP TABLE Customer;

CREATE TABLE Customer (
   id			BIGINT         PRIMARY KEY GENERATED ALWAYS AS IDENTITY,		-- ���� �� �� ���̵�
   userId		VARCHAR(30)		NOT NULL,										-- �� ���̵�
   passwd		VARCHAR(30)		NOT NULL,										-- �� ���
   userName		VARCHAR(10)		NOT NULL,										-- �� �̸�
   ssn			VARCHAR(14)		NOT NULL,										-- �ֹι�ȣ
   phone		VARCHAR(13)		NOT	NULL	DEFAULT '',							-- ����ȣ
   email		VARCHAR(40)		NOT NULL   DEFAULT '',							-- �̸��� (��������� �������� �׳� ���ڿ�)
   addr			VARCHAR(100),													-- �ּ�
   regDate		TIMESTAMP		NOT NULL   DEFAULT CURRENT_TIMESTAMP,			-- ��ϳ�¥(�б� ����)
   
   CONSTRAINT   Customer_userId_UK   UNIQUE(userId),							-- �� ���̵�� �ߺ� ����
   CONSTRAINT   Customer_ssn_UK      UNIQUE(ssn)								-- �ֹι�ȣ�� �ߺ� ����
);

CREATE TABLE Account (
   id				BIGINT      PRIMARY KEY GENERATED ALWAYS AS IDENTITY,   	-- ���� ���̵�
   customerId		BIGINT      NOT NULL,                           			-- �� ��ȣ
   accType			CHAR(1)     NOT NULL   DEFAULT 'S',               			-- ���� Ÿ��
   accNumber		VARCHAR(9) 	NOT NULL,                           				-- ���� ��ȣ
   balance			DOUBLE      NOT NULL   DEFAULT 0.0,               			-- �ܾ�
   interestRate		DOUBLE      NOT NULL   DEFAULT 0.0,               			-- ������
   overDraft		DOUBLE      NOT NULL   DEFAULT 0.0,               			-- �ѵ���
   regDate			TIMESTAMP   NOT NULL   DEFAULT CURRENT_TIMESTAMP,      		-- ��ϳ�¥(�б�����)
   
   CONSTRAINT		Account_customerId_FK 	FOREIGN KEY(customerId) REFERENCES Customer(id) ON DELETE CASCADE,	-- �� ��ȣ�� FK
   CONSTRAINT		Account_accNumber_UK	UNIQUE(accNumber) 
);

CREATE TABLE AccRecord (
	id			BIGINT			PRIMARY KEY GENERATED ALWAYS AS IDENTITY,	-- �ŷ� ���̵�
	accId		BIGINT			NOT NULL,									-- ���� ���̵� ��������		
	transType	CHAR(1)			NOT NULL,									-- �Ա�(D) / ���(W)
	amount		DOUBLE			NOT NULL,									-- �ŷ���
	balance		DOUBLE 			NOT NULL,									-- �ŷ� �� �ܾ�
	content		VARCHAR(20)		NOT NULL,									-- �ŷ� ����
	regDate		TIMESTAMP		NOT NULL	DEFAULT CURRENT_TIMESTAMP,		-- �ŷ� ��¥(�б�����)
	
	CONSTRAINT	AccRecord_accId_FK				FOREIGN KEY(accId) REFERENCES Account(id) ON DELETE CASCADE
);

-- TEST ON DELETE CASCADE
DELETE FROM Account WHERE accNumber='1234-5678';


SELECT * FROM Customer;
SELECT * FROM Account;
SELECT * FROM AccRecord;

DELETE FROM Customer WHERE id=8;

INSERT INTO Customer(userId, passwd, userName, ssn) 
VALUES('aru', 'aru123', '��Ʒ�', '111111-1111111');


-- -- @author ��ȿ��
INSERT INTO Customer(userId, passwd, userName, ssn) 
VALUES('thisid', 'thispw', 'thisname', '111111-1111112');

INSERT INTO Account(customerId,accType,accNumber,balance,interestRate,overDraft)
VALUES(5,'S','1234-5678',5000.0,0.1,0.0);

INSERT INTO Account(customerId,accType,accNumber,balance,interestRate,overDraft)
VALUES(5,'C','1234-5679',4000.0,0.0,2000);

-- 1���� ���� ��� ���� ��������
SELECT * FROM Account INNER JOIN Customer ON Customer.userId='thisid' WHERE Account.customerId = Customer.id;

-- -- end 

-- --@author �躸��

SELECT c.id, a.accType, a.accNumber, a.balance
FROM Customer c, Account a
WHERE c.ssn='111111-1111111' AND a.customerId=c.id

INSERT INTO AccRecord(accId, transType, amount, balance, content)
VALUES(1, 'D', 1000, 10000, '�Ʒð���');

INSERT INTO AccRecord(accId, transType, amount, balance, content)
VALUES(1, 'W', 2000, 20000, 'ī�����');

INSERT INTO AccRecord(accId, transType, amount, balance, content)
VALUES(1, 'W', 3000, 30000, 'CU������');

INSERT INTO AccRecord(accId, transType, amount, balance, content)
VALUES(1, 'D', 3000, 30000, '�뵷');

SELECT c.id, a.accType, a.accNumber, a.balance
FROM Customer c, Account a
WHERE c.ssn='111111-1111111' AND a.customerId=c.id


-- --  end
