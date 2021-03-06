--  上大学时就是这三张表，多年以后，还是在捣鼓这三张表
-- Student
DROP TABLE EI.STUDENT;
CREATE TABLE EI.STUDENT
    (
        SNO VARCHAR (50) NOT NULL ,
        SNAME VARCHAR (100) ,
        SSEX VARCHAR (3) ,--每个汉字占 3 个字节（数据库是 UTF-8 编码）
        SAGE SMALLINT ,
        SDEPT VARCHAR (200) ,
        CONSTRAINT PK_STUDENT PRIMARY KEY ( SNO)
    ) ;
INSERT INTO EI.STUDENT VALUES('95003', '张三', '男', 23, 'CS');
INSERT INTO EI.STUDENT VALUES('95004', '李四', '女', 24, 'IS');
INSERT INTO EI.STUDENT VALUES('95005', '王五', '男', 25, 'MA');
INSERT INTO EI.STUDENT VALUES('95006', '赵六', '女', 26, 'FL');
INSERT INTO EI.STUDENT VALUES('95007', '欧阳七', '男', 27, 'CS');
INSERT INTO EI.STUDENT VALUES('95008', '令狐八', '女', 28, 'CS');
INSERT INTO EI.STUDENT VALUES('95009', '岳不九', '男', 29, 'CS');
INSERT INTO EI.STUDENT VALUES('95010', '宋老十', '女', 30, 'CS');

-- COURSE
DROP TABLE EI.COURSE;
CREATE TABLE EI.COURSE
    (
        CNO INTEGER NOT NULL ,
        CNAME VARCHAR (150) ,
        CPNO INTEGER ,
        CCREDIT SMALLINT ,
        CONSTRAINT PK_COURSE PRIMARY KEY ( CNO)
    ) ;
INSERT INTO EI.COURSE VALUES(1, '数据库', 5, 4);
INSERT INTO EI.COURSE VALUES(2, '数学', NULL, 2);
INSERT INTO EI.COURSE VALUES(3, '信息系统', 1, 4);
INSERT INTO EI.COURSE VALUES(4, '操作系统', 6, 3);
INSERT INTO EI.COURSE VALUES(5, '数据结构', 7, 4);
INSERT INTO EI.COURSE VALUES(6, '数据处理', NULL, 2);
INSERT INTO EI.COURSE VALUES(7, 'PASCAL语言', 6, 4);

-- SC
DROP TABLE EI.SC;
CREATE TABLE EI.SC
    (
        SNO VARCHAR (50) NOT NULL ,
        CNO INTEGER NOT NULL ,
        GRADE SMALLINT ,
        CONSTRAINT PK_SC PRIMARY KEY ( SNO, CNO)
    ) ;
INSERT INTO EI.SC VALUES('95003', 1, 92);
INSERT INTO EI.SC VALUES('95003', 2, 85);
INSERT INTO EI.SC VALUES('95003', 3, 88);
INSERT INTO EI.SC VALUES('95004', 2, 90);
INSERT INTO EI.SC VALUES('95004', 3, 80);
INSERT INTO EI.SC VALUES('95007', 6, 100);

