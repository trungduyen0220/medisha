drop database if EXISTS MEDISHA;
create database MEDISHA;

ALTER DATABASE MEDISHA CHARACTER SET utf8 COLLATE utf8_general_ci;

use MEDISHA;
CREATE TABLE user(
	user_id INT(11) NOT NULL PRIMARY KEY,
	user_name VARCHAR(64) NOT NULL, 
	encryted_password VARCHAR(128) NOT NULL,
	first_name NVARCHAR(200),
	last_name NVARCHAR(200),
	gender BIT,
	email NVARCHAR(200),
	address NVARCHAR(200),
	enabled BIT
);

CREATE TABLE role(
	role_id INT(11) NOT NULL PRIMARY KEY,
    role_name NVARCHAR(100) NOT NULL UNIQUE,
	enabled BIT
);

CREATE TABLE user_role (
	user_id INT(11) NOT NULL,
	role_id INT(11) NOT NULL,
    PRIMARY KEY (user_id, role_id),
	FOREIGN KEY (user_id) REFERENCES user(user_id),
	FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- Used by Spring Remember Me API.  
CREATE TABLE persistent_logins (
    user_name VARCHAR(64) NOT NULL, 
    series VARCHAR(64) NOT NULL PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used timestamp NOT NULL
);

CREATE TABLE drug_store (
	store_id INT(11) NOT NULL PRIMARY KEY,
	store_image TEXT,
	store_url TEXT,
	store_name VARCHAR(200) NOT NULL,
	store_tel VARCHAR(20),
	store_address VARCHAR(200) NOT NULL,
	parent_drug_store_id INT(11), -- Chi nhánh con của một chuỗi cửa hàng
	enabled BIT,
	description TEXT,
    FOREIGN KEY (parent_drug_store_id) REFERENCES drug_store(store_id)
);
CREATE TABLE medicine (
	medicine_id INT(11) NOT NULL PRIMARY KEY,
    medicine_name VARCHAR(100) NOT NULL,
    medicine_unit VARCHAR(100) NOT NULL,
    medicine_amount VARCHAR(100) NOT NULL,
    medicine_physical VARCHAR(100), -- Dạng lỏng hay rắn hay siro...
    prescript_medication BIT, -- Thuốc kê đơn hay không
	image TEXT,
	expiry_date VARCHAR(20),
	date_created date,
	user_created_id INT(11),
	description TEXT,
	enabled BIT,
	max_quantity INT(11), -- Số lượng max chỉ được mua theo quy định
    FOREIGN KEY (user_created_id) REFERENCES user(user_id)
);


-- Mô tả thuốc ở trong nhà thuốc nào đang ở trong kho nào
CREATE TABLE medicine_in_store (
	store_id INT(11) NOT NULL,
	medicine_id INT(11) NOT NULL,
	quantity_inventory INT(11) NOT NULL,
	sale_percent SMALLINT,
	note TEXT,
    price int(11),
	PRIMARY KEY (store_id, medicine_id),
	FOREIGN KEY (store_id) REFERENCES drug_store(store_id),
	FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id)
);

CREATE TABLE category(
	category_id INT(11) NOT NULL PRIMARY KEY,
	category_name TEXT,
    category_parent_id INT(11),
	date_created date,
	enabled BIT,
    FOREIGN KEY (category_parent_id) REFERENCES category(category_id)
);


CREATE TABLE medicine_category(
	medicine_id INT(11) NOT NULL,
	category_id INT(11) NOT NULL,
    PRIMARY KEY (medicine_id, category_id),
	FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id),
	FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE order_status (
	status_id INT(11) NOT NULL PRIMARY KEY,
    status_name VARCHAR(200) NOT NULL,
    date_created date
);

CREATE TABLE `order` (
	order_id INT(11) NOT NULL PRIMARY KEY,
	user_id INT(11),
	date_created date,
	shipping_adress TEXT,
	status_id INT(11),
	note TEXT,
	FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (status_id) REFERENCES order_status(status_id)
);

CREATE TABLE order_detail(
	order_id INT(11),
	medicine_id INT(11),
	quantity SMALLINT,
	note TEXT,
	enabled BIT,
	PRIMARY KEY (order_id, medicine_id),
    FOREIGN KEY (order_id) REFERENCES `order`(order_id),
	FOREIGN KEY (medicine_id) REFERENCES medicine(medicine_id)
);

CREATE TABLE search_form(
	medicine_name TEXT,
    medicine_amount TEXT,
    medicine_unit TEXT
);

INSERT INTO user VALUES (1, 'ntmduyen', '123456','Duyen','Nguyen',0,'duyenntm@gmail.com','66 Nguyen Phuc Nguyen',1);
INSERT INTO user VALUES (2, 'ltyen', '123456','Yen','Luyen',0,'yenlt@gmail.com','Ha Tinh',1);
INSERT INTO user VALUES (3, 'lttrinh', '123456','Trinh','Lai',0,'trinhlt@gmail.com','Ha Tinh',1);
INSERT INTO user VALUES (4, 'shipper', '123456', NULL, NULL, 1, NULL, NULL,1);

INSERT INTO role VALUES (1, 'User', 1);
INSERT INTO role VALUES (3, 'Store owner', 1);
INSERT INTO role VALUES (2, 'Administrator', 1);
INSERT INTO role VALUES (4, 'Shipper', 1);

INSERT INTO user_role VALUES (1, 1);
INSERT INTO user_role VALUES (2, 2);
INSERT INTO user_role VALUES (3, 3);
INSERT INTO user_role VALUES (4, 4);

INSERT INTO drug_store VALUES (1, NULL, 'https://www.pharmacity.vn', 'DrugVietNam.vn', '0123456789', 'Binh Duong', NULL, 1, NULL);
INSERT INTO drug_store VALUES (2, NULL, 'https://www.pharmacity.vn', 'Pharmacity.vn', '0123456789', 'Ha Noi', 1, 1, NULL);
INSERT INTO drug_store VALUES (3, NULL, 'https://www.pharmacity.vn', 'Pharmacountry.vn', '0123456789', 'HCM', 1, 1, NULL);
INSERT INTO drug_store VALUES (4, NULL, 'https://www.pharmacity.vn', 'MedicineVn.vn', '0123456789', 'Hue', 1, 1, NULL);
INSERT INTO drug_store VALUES (5, NULL, 'https://www.pharmacity.vn', 'Ggenemedicine.vn', '0123456789', 'Vung Tau', 1, 1, NULL);
INSERT INTO drug_store VALUES (6, NULL, 'https://www.pharmacity.vn', 'Meddicc SGPMC114', '0123456789', N'Bình Dương', NULL, 1, NULL);
INSERT INTO drug_store VALUES (7, NULL, 'https://www.pharmacity.vn', 'MedicineVn HNPMC415', '0123456789', N'Hà Nội', 1, 1, NULL);
INSERT INTO drug_store VALUES (8, NULL, 'https://www.pharmacity.vn', 'Pharmacity SGPMC137', '0123456789', 'HCM', 1, 1, NULL);
INSERT INTO drug_store VALUES (9, NULL, 'https://www.pharmacity.vn', 'Pharmacity DNPMC298', '0123456789', N'Huế', 1, 1, NULL);
INSERT INTO drug_store VALUES (10, NULL, 'https://www.pharmacity.vn', 'Pharmacity SGPMC160', '0123456789', N'Vũng Tàu', 1, 1, NULL);
INSERT INTO drug_store VALUES (11, NULL, 'https://www.pharmacity.vn', 'Pharmacity DNPMC299', '0123456789', N'Đà Nẵng ', 1, 1, NULL);
INSERT INTO drug_store VALUES (12, NULL, 'https://www.pharmacity.vn', 'Pharmacity HNPMC415', '0123456789', 'HCM', 1, 1, NULL);
INSERT INTO drug_store VALUES (13, NULL, 'https://www.pharmacity.vn', 'Pharmacity SGPMC220', '0123456789', N'Cần Thơ', 1, 1, NULL);
INSERT INTO drug_store VALUES (14, NULL,'https://www.pharmacity.vn', 'Pharmacity SGPMC220', '0123456789', N'Vũng Tàu', 1, 1, NULL);
INSERT INTO drug_store VALUES (15, NULL, 'https://pharmacy.jiohealth.com', 'JioHealth', '0902987123', N'Hà Nội', NULL, 1, NULL);
INSERT INTO drug_store VALUES (16, NULL, 'https://trungtamthuoc.com', 'Nhà Thuốc An Huy', '0902986546', N'Hà Nội', NULL, 1, NULL);
INSERT INTO drug_store VALUES (17, NULL, 'https://trungtamthuoc.com', 'Nhà Thuốc An Huy', '0902987098', N'Hà Nội', 11, 1, NULL);
INSERT INTO drug_store VALUES (18, NULL, 'https://pharmacy.jiohealth.com', 'JioHealth', '0902987123', N'Hồ Chí Minh', NULL, 1, NULL);

INSERT INTO medicine VALUES (21, 'Zyrtec', 'pill', '10', 'solid', 0, NULL, '2021-12-31', NULL, 1, NULL, 1,10);
INSERT INTO medicine VALUES (22, 'Aerius', 'bottle', '100ml', 'syrup', 0, NULL, '2021-12-31', NULL, 1, NULL, 1,10);
INSERT INTO medicine VALUES (23, 'Aerius',  'pill', '10', 'solid', 0, NULL, '2021-12-31', NULL, 1, NULL, 1,10);
INSERT INTO medicine VALUES (24, 'Telfast HD', 'pill', '10', 'syrup', 0, NULL, '2021-12-31', NULL, 1, NULL, 1, 10);
INSERT INTO medicine VALUES (1, 'Zyrtec(10mg)', N'viên', '10', 'solid', 0, 1, '2020-09-02', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (2, 'Efferalgan(500mg)', N'viên', '10', 'solid', 1, 2, '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (3, 'Eugica', N'viên', '10', 'solid', 0, 3, '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (4, N'Kẹo ngậm ho không đường vị cam Zuckerfrei Pulmoll (50g/hộp)', N'viên', '10', 'solid', 0, 4 , '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (5, N'Viên bổ não Blackmores Brain Active (Lọ 30 viên)', N'viên', '10', 'solid', 0, 5, '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (6, N'Bột khử mùi Trapha', 'pill', '10', 'solid', 0, 6, '2021-12-31', NULL, 1, NULL, 1,10);
INSERT INTO medicine VALUES (7, N'Khăn ướt cho bé Pharmacity', N'gói', '10', 'solid', 0, 7, '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (8, N' Kem tẩy lông cho da nhạy cảm Cléo  ', N'lọ', '18', N'lỏng', 0, 8, '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (9, N'Viên dưỡng tâm an thần Trasleepy ', N'viên', '5', 'solid', 1, 9, '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (10, N'Viên uống hỗ trợ xương khớp JEX', N'viên', '10', 'solid', 1, 10, '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (11, N'Trà thảo mộc giảm cân Lanui', N'túi' , '10', 'solid', 0, 11, '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (12, N'Dung dịch bù nước và điện giải Oresol Cam Phúc Vinh', N'chai', '12', N'lỏng', 0, 12, '2020-12-20', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (13, N'Dung dịch vệ sinh phụ nữ Lactacyd', N'lọ', '10', 'solid', 0, 13, '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (14, N'Dầu gội Thái Dương 3', N'chai', '9', N'lỏng', 0, 14, '2020-08-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (15, N'Kem bôi mờ sẹo thâm Acnes', N'lọ', '4', N'lỏng', 0, 15, '2021-12-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (16, N'Diphenhydramine 10mg/1ml', N'ống', '10', N'lỏng', 1, 16, '2020-06-31', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (17, N'Pediakid - Vitamin D3', N'viên', '10', 'solid', 1, 17, '2020-09-11', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (18, N'Bonioxy', N'viên', '10', 'solid', 0, 18, '2020-12-11', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (19, N'HAPPY KIDS', N'viên', '8', 'solid', 0, 19, '2020-12-30', NULL, 1, NULL, 1,10);				
INSERT INTO medicine VALUES (20, N'Rabeolone', N'viên', '15', 'solid', 0, 20, '2020-09-02', NULL, 1, NULL, 1,10);
INSERT INTO medicine_in_store VALUES (1, 1, 100, 20, NULL,12000);
INSERT INTO medicine_in_store VALUES (1, 2, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (1, 3, 100, 2, NULL,20000);
INSERT INTO medicine_in_store VALUES (1, 4, 100, 5, NULL,10000);
INSERT INTO medicine_in_store VALUES (2, 1, 100, 6, NULL,15000);
INSERT INTO medicine_in_store VALUES (2, 2, 100, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (2, 3, 100, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (2, 4, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (3, 1, 100, 9, NULL,20000);
INSERT INTO medicine_in_store VALUES (3, 2, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (3, 3, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (3, 4, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (4, 1, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (4, 2, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (4, 3, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (4, 4, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (5, 1, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (5, 2, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (5, 3, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (6, 4, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (6, 1, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (7, 2, 100, 10, NULL,10000);
INSERT INTO medicine_in_store VALUES (7, 3, 80, 8, NULL,10000);
INSERT INTO medicine_in_store VALUES (7, 4, 80, 8, NULL,10000);
INSERT INTO medicine_in_store VALUES (8, 1, 80, 8, NULL,10000);
INSERT INTO medicine_in_store VALUES (8, 2, 80, 8, NULL,10000);
INSERT INTO medicine_in_store VALUES (8, 3, 80, 8, NULL,10000);
INSERT INTO medicine_in_store VALUES (9, 4, 50, 5, NULL,10000);
INSERT INTO medicine_in_store VALUES (9, 1, 50, 5, NULL,10000);
INSERT INTO medicine_in_store VALUES (9, 2, 50, 5, NULL,10000);
INSERT INTO medicine_in_store VALUES (9, 3, 50, 5, NULL,10000);
INSERT INTO medicine_in_store VALUES (10, 11, 50, 5, NULL,10000);
INSERT INTO medicine_in_store VALUES (11, 5, 85, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (12, 3, 85, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (13, 4, 85, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (14, 1, 85, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (15, 6, 85, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (16, 5, 85, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (17, 3, 85, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (18, 4, 85, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (16, 1, 85, 7, NULL,10000);
INSERT INTO medicine_in_store VALUES (15, 4, 85, 7, NULL,10000);

INSERT INTO category VALUES(1, N'Thuốc không kê đơn', NULL, current_date(), 1);
INSERT INTO category VALUES(2, N'Thuốc kê đơn', NULL, current_date(), 1);
INSERT INTO category VALUES(3, N'Thực phẩm chức năng', NULL, current_date(), 1);
INSERT INTO category VALUES(4, N'Chăm sóc sức khỏe', NULL, current_date(), 1);
INSERT INTO category VALUES(5, N'Chăm sóc cá nhân', NULL, current_date(), 1);
INSERT INTO category VALUES(6, N'Chăm sóc sắc đẹp', NULL, current_date(), 1);
INSERT INTO category VALUES(7, N'Sản phẩm tiện lợi', NULL, current_date(), 1);
INSERT INTO category VALUES(8, N'Thuốc kháng dị ứng', 1, current_date(), 1);
INSERT INTO category VALUES(9, N'Giảm đau hạ sốt', 1, current_date(), 1);
INSERT INTO category VALUES(10, N'Thuốc cảm lạnh, ho', 1, current_date(), 1);
INSERT INTO category VALUES(11, N'Thuốc cơ xương khớp', 1, current_date(), 1);
INSERT INTO category VALUES(12, N'Thuốc da liễu', 1, current_date(), 1);
INSERT INTO category VALUES(13, N'Thuốc giảm cân', 1, current_date(), 1);
INSERT INTO category VALUES(14, N'Thuốc dành cho phụ nữ', 1, current_date(), 1);
INSERT INTO category VALUES(15, N'Thuốc dành cho nam giới', 1, current_date(), 1);
INSERT INTO category VALUES(16, N'Thuốc kháng viêm', 1, current_date(), 1);
INSERT INTO category VALUES(17, N'Thuốc ngừa thai', 1, current_date(), 1);
INSERT INTO category VALUES(18, N'Nhóm hô hấp', 2, current_date(), 1);
INSERT INTO category VALUES(19, N'Nhóm tiêu hóa', 2, current_date(), 1);
INSERT INTO category VALUES(20, N'Chăm sóc sức khỏe cho nam và nữ', 2, current_date(), 1);
INSERT INTO category VALUES(21, N'Dành cho trẻ em', 2, current_date(), 1);
INSERT INTO category VALUES(22, N'Đẹp da', 2, current_date(), 1);
INSERT INTO category VALUES(23, N'Nhóm cơ xương khớp', 2, current_date(), 1);
INSERT INTO category VALUES(24, N'Vitamin tổng hợp', 2, current_date(), 1);
INSERT INTO category VALUES(25, N'Nhóm khác', 2, current_date(), 1);

INSERT INTO medicine_category VALUES(21,1);
INSERT INTO medicine_category VALUES(22,1);
INSERT INTO medicine_category VALUES(23,2);
INSERT INTO medicine_category VALUES(24,2);
INSERT INTO medicine_category VALUES(1,3);
INSERT INTO medicine_category VALUES(2,3);
INSERT INTO medicine_category VALUES(3,3);
INSERT INTO medicine_category VALUES(4,4);
INSERT INTO medicine_category VALUES(5,5);
INSERT INTO medicine_category VALUES(5,6);
INSERT INTO medicine_category VALUES(7,7);
INSERT INTO medicine_category VALUES(8,7);
INSERT INTO medicine_category VALUES(9,8);
INSERT INTO medicine_category VALUES(10,8);
INSERT INTO medicine_category VALUES(11,8);
INSERT INTO medicine_category VALUES(12,9);
INSERT INTO medicine_category VALUES(13,10);
INSERT INTO medicine_category VALUES(14,11);
INSERT INTO medicine_category VALUES(15,12);
INSERT INTO medicine_category VALUES(16,12);
INSERT INTO medicine_category VALUES(17,14);
INSERT INTO medicine_category VALUES(18,13);
INSERT INTO medicine_category VALUES(19,13);
INSERT INTO medicine_category VALUES(20,13);
INSERT INTO order_status VALUES (1, N'Đã xác nhận', NULL);
INSERT INTO order_status VALUES (2, N'Đã bàn giao', NULL);
INSERT INTO order_status VALUES (3, N'Đang vận chuyển', NULL);
INSERT INTO order_status VALUES (4, N'Đã đến', NULL);

INSERT INTO `order` VALUES (1, 1, current_date(), 'Hue', 1,NULL);
INSERT INTO `order` VALUES (2, 1, current_date(), 'HCM', 2,NULL);
INSERT INTO `order` VALUES (3, 2, current_date(), 'HCM', 3,NULL);
INSERT INTO `order` VALUES (4, 2, current_date(), 'HCM', 3,NULL);
INSERT INTO `order` VALUES (5, 1, current_date(), N'Huế', 1,NULL);
INSERT INTO `order` VALUES (6, 1, current_date(), 'HCM', 2,NULL);
INSERT INTO `order` VALUES (7, 2, current_date(), 'HCM', 3,NULL);
INSERT INTO `order` VALUES (8, 2, current_date(), 'HCM', 3,NULL);
INSERT INTO `order` VALUES (9, 2, current_date(), N'Hà Nội', 4,NULL);
INSERT INTO `order` VALUES (10, 2, current_date(), N'Cần Thơ', 1,NULL);
INSERT INTO `order` VALUES (11, 3, current_date(), N'Đà Nẵng', 2,NULL);
INSERT INTO `order` VALUES (12, 1, current_date(), N'Bình Dương', 4,NULL);
INSERT INTO `order` VALUES (13, 3, current_date(), N'Hà Nội', 2,NULL);
INSERT INTO `order` VALUES (14, 2, current_date(), N'Hà Nội', 3,NULL);
INSERT INTO `order` VALUES (15, 2, current_date(), N'Hà Nội', 4,NULL);
INSERT INTO `order` VALUES (16, 3, current_date(), N'Đà Nẵng', 1,NULL);

INSERT INTO order_detail VALUE (2, 1, 2, NULL,1);
INSERT INTO order_detail VALUE (1, 2, 2, NULL,1);
INSERT INTO order_detail VALUE (1, 3, 2, NULL,1);
INSERT INTO order_detail VALUE (2, 2, 2, NULL,1);
INSERT INTO order_detail VALUE (2, 3, 2, NULL,1);
INSERT INTO order_detail VALUE (2, 4, 2, NULL,1);
INSERT INTO order_detail VALUE (3, 3, 2, NULL,1);
INSERT INTO order_detail VALUE (4, 4, 2, NULL,1);
INSERT INTO order_detail VALUE (1, 1, 2, NULL,1);
INSERT INTO order_detail VALUE (3, 2, 2, NULL,1);
INSERT INTO order_detail VALUE (4, 3, 2, NULL,1);
INSERT INTO order_detail VALUE (4, 2, 2, NULL,1);
INSERT INTO order_detail VALUE (5, 3, 4, NULL,1);
INSERT INTO order_detail VALUE (6, 4, 6, NULL,1);
INSERT INTO order_detail VALUE (7, 3, 2, NULL,1);
INSERT INTO order_detail VALUE (8, 4, 2, NULL,1);
INSERT INTO order_detail VALUE (9, 3, 1, NULL,1);
INSERT INTO order_detail VALUE (10, 11, 2, NULL,1);
INSERT INTO order_detail VALUE (11, 7, 3, NULL,1);
INSERT INTO order_detail VALUE (12, 4, 2, NULL,1);
INSERT INTO order_detail VALUE (13, 5, 11, NULL,1);
INSERT INTO order_detail VALUE (14, 8, 4, NULL,1);
INSERT INTO order_detail VALUE (15, 20, 10, NULL,1);
INSERT INTO order_detail VALUE (16, 4, 2, NULL,1);
INSERT INTO order_detail VALUE (5, 4, 2, NULL,1);
INSERT INTO order_detail VALUE (5, 17, 20, NULL,1);
INSERT INTO order_detail VALUE (5, 14, 9, NULL,1);








