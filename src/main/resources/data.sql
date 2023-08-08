CREATE DATABASE TEST
USE TEST
GO
CREATE TABLE Electrical_Bill ( MaSoCongTo varchar(20) PRIMARY KEY , HoTenChuHo nvarchar(30) NOT NULL, DiaChi nvarchar(30) NOT NULL, ChiSoCu int ,ChiSoMoi int  , ThanhTien int )
CREATE TABLE LOGINDATA ( Username varchar(20) NOT NULL, Pass varchar(20) NOT NULL)
INSERT INTO LOGINDATA VALUES ('giang','giang')
INSERT INTO LOGINDATA VALUES ('admin','1')
SELECT*FROM LOGINDATA
SELECT*FROM Electrical_Bill